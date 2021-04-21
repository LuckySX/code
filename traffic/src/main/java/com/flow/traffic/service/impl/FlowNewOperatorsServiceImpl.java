package com.flow.traffic.service.impl;


import com.flow.traffic.dao.FlowNewOperatorsDao;
import com.flow.traffic.service.FlowNewOperatorsService;
import com.flow.traffic.util.StringUtil;
import com.flow.traffic.util.TimeCUtil;
import com.flow.traffic.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlowNewOperatorsServiceImpl implements FlowNewOperatorsService {
    @Autowired
    FlowNewOperatorsDao operatorsDao;
    /**
     * 运营商协议流量统计图
     */
    @Override
    public List<Map<String, Object>> selectChart(Map<String, Object> paramMap) {
        List<Map<String,Object>> chart = operatorsDao.selectChart(paramMap);
        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
//		List<String> categorys = operatorsDao.selectOperator(paramMap);
        List<String> categorys = new ArrayList<String>();
        if(!StringUtil.isEmpty(paramMap.get("operator").toString())){
            categorys.add(paramMap.get("operator").toString());
        }else {
            categorys.add("移动");
            categorys.add("联通");
            categorys.add("电信");
            categorys.add("铁通");
            categorys.add("其它");
        }
        List<String> names = new ArrayList<String>();
        //循环得到所有的name
        for(Map<String, Object> map:chart ){
            if(map.get("name") !=null && !"".equals(map.get("name")) && !names.contains(map.get("name")) ){
                names.add((String) map.get("name"));
            }
        }
        //将缺少运营商的值赋0
        for(String name:names ){
            for(String category:categorys){
                Map<String, Object> cha = new HashMap<String,Object>();
                cha.put("category", category);
                cha.put("name", name);
                cha.put("value", 0);
                for(Map<String, Object> m:chart){
                    String n = (String) m.get("name");
                    String c = (String) m.get("category");
                    if(name.equals(n) && category.equals(c)){
                        cha.put("value",m.get("value"));
                        break;
                    }
                }
                resultList.add(cha);
            }
        }
        return resultList;
    }

    /**
     * 运营商流量变化
     */
    @Override
    public List<Map<String, Object>> selectLine(Map<String, Object> paramMap) {
        long time1 = System.currentTimeMillis();
        List<Map<String,Object>> line = operatorsDao.selectLine(paramMap);
        //System.out.println("1====="+(System.currentTimeMillis()-time1));
        List<Map<String,Object>> newLine = new ArrayList<Map<String,Object>>();
        Tool.getTime((String)paramMap.get("startTime"));
        Long beginTime = TimeCUtil.stringToLong((String)paramMap.get("startTime"),"yyyy-MM-dd HH:mm:ss") ;
        Long endTime =TimeCUtil.stringToLong((String)paramMap.get("endTime"),"yyyy-MM-dd HH:mm:ss") ;
        //数据补零
//		List<String> operators = operatorsDao.selectOperator(paramMap);
        List<String> categorys = new ArrayList<String>();
        if(!StringUtil.isEmpty(paramMap.get("operator").toString())){
            categorys.add(paramMap.get("operator").toString());
        }else {
            categorys.add("移动");
            categorys.add("联通");
            categorys.add("电信");
            categorys.add("铁通");
            categorys.add("其它");
        }
    //    System.out.println("2====="+(System.currentTimeMillis()-time1));
        String name =null;
        String category =null;
        String value =null;
        int cSize = categorys.size();
        for(String operator:categorys){
            for(long i=beginTime; i<=endTime; i=i+3600000 ){
                Map<String,Object> operatorMap = new HashMap<String,Object>();
                String time = TimeCUtil.longToString(i,"yyyy-MM-dd HH");
                operatorMap.put("category", operator);
                operatorMap.put("name", time);
                if(name==null && line.size()>0) {
                    Map<String, Object> operatorMap2 = line.get(0);
                    if (operatorMap2 != null) {
                        name = operatorMap2.get("name").toString();
                        if(cSize!=1)
                            category = categorys.get(Integer.parseInt(operatorMap2.get("category").toString()));
                        value = String.valueOf(operatorMap2.get("value"));
                    }
                }
                if(name!=null){
                    if(name.equals(time) && (cSize==1 || category.equals(operator))){
                        name=null;
                        if(value.equals("0.00"))
                            operatorMap.put("value", "0.01");
                        else
                            operatorMap.put("value", value);
                        line.remove(0);
                    }
                }

                if(name!=null) operatorMap.put("value", "0");

                newLine.add(operatorMap);
            }
        }
    //    System.out.println("3====="+(System.currentTimeMillis()-time1));


        return newLine;
    }

}

