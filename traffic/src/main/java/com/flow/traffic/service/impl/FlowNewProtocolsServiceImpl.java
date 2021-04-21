package com.flow.traffic.service.impl;


import com.flow.traffic.dao.FlowNewProtocolsDao;
import com.flow.traffic.service.FlowNewProtocolsService;
import com.flow.traffic.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FlowNewProtocolsServiceImpl implements FlowNewProtocolsService {

    @Autowired
    FlowNewProtocolsDao protocolsDao;
    @Override
    public List<Map<String, Object>> selectChart1(Map<String, Object> paramMap) {
        List<Map<String,Object>> chart1 = protocolsDao.selectChart1(paramMap);
        return chart1;
    }
    @Override
    public List<Map<String, Object>> selectChart2(Map<String, Object> paramMap) {
        List<Map<String,Object>> chart2 = protocolsDao.selectChart2(paramMap);
        List<Map<String,Object>> newchart2 = new ArrayList<Map<String,Object>>();
        if(chart2.size()>0){
            for(Map<String,Object> map: chart2){
                Map<String,Object> ll = new HashMap<String,Object>();
                Map<String,Object> ljs = new HashMap<String,Object>();
                ll.put("name", map.get("name"));
                ll.put("category", "流量");
                if(map.get("ll").equals("0.00")){
                    ll.put("value", 0.01);
                }else{
                    ll.put("value", map.get("ll"));
                }
                ll.put("type", "bar");
                ljs.put("name", map.get("name"));
                ljs.put("category", "连接数");
                ljs.put("value", map.get("ljs"));
                ljs.put("type", "line");
                newchart2.add(ll);
                newchart2.add(ljs);
            }
        }
        return newchart2;
    }
    @Override
    public List<Map<String, Object>> selectChart3(Map<String, Object> paramMap) {
        List<Map<String,Object>> chart3 = protocolsDao.selectChart3(paramMap);
        List<Map<String,Object>> newchart3 = new ArrayList<Map<String,Object>>();

        if(chart3.size()>0){
            for(Map<String,Object> map: chart3){
                Map<String,Object> ljs = new HashMap<String,Object>();
                Map<String,Object> ll = new HashMap<String,Object>();
                ljs.put("name", map.get("name"));
                ljs.put("category", "连接数");
                ljs.put("value", map.get("ljs"));
                ljs.put("type", "bar");
                ll.put("name", map.get("name"));
                ll.put("category", "流量");
                if(map.get("ll").equals("0.00")){
                    ll.put("value", 0.01);
                }else{
                    ll.put("value", map.get("ll"));
                }
                ll.put("type", "line");
                newchart3.add(ljs);
                newchart3.add(ll);
            }
        }
        return newchart3;
    }
    @Override
    public List<Map<String, Object>> selectChart4(Map<String, Object> paramMap) {
        List<Map<String,Object>> chart4s = null;//连接数趋势图
        List<Map<String,Object>> chart4 = protocolsDao.selectChart4(paramMap);
        chart4s = this.zeroize(chart4, paramMap);

        for (Map<String, Object> map : chart4s) {
            String date = Tool.TimeStamp2DateNoYear(Tool.getTime(String.valueOf(map.get("name"))));
            map.put("name", date);

        }
        return chart4s;
    }
    @Override
    public List<Map<String, Object>> selectChart5(Map<String, Object> paramMap) {
        List<Map<String,Object>> chart5s = null;//上行流量趋势图
        List<Map<String,Object>> chart5 = protocolsDao.selectChart5(paramMap);
        chart5s = this.zeroize(chart5, paramMap);
        if(chart5.size()>0){
            for (Map<String, Object> map : chart5s) {
                String date = Tool.TimeStamp2DateNoYear(Tool.getTime(String.valueOf(map.get("name"))));
                map.put("name", date);
                String category =  map.get("category").toString();
                String value = map.get("value").toString();
                if(value.equals("0.00")){
                    map.put("category", category);
                    map.put("name", date);
                    map.put("value", 0.01);
                }

            }
        }
        return chart5s;
    }
    @Override
    public List<Map<String, Object>> selectChart6(Map<String, Object> paramMap) {
        List<Map<String,Object>> chart6s = null;//下行流量趋势图
        List<Map<String,Object>> chart6 = protocolsDao.selectChart6(paramMap);
        chart6s = this.zeroize(chart6, paramMap);
        if(chart6s.size()>0){

            for (Map<String, Object> map : chart6s) {
                String date = Tool.TimeStamp2DateNoYear(Tool.getTime(String.valueOf(map.get("name"))));
                map.put("name", date);
                String category =  map.get("category").toString();
                String value = map.get("value").toString();
                if(value.equals("0.00")){
                    map.put("category", category);
                    map.put("name", date);
                    map.put("value", 0.01);
                }
            }
        }
        return chart6s;
    }
    @Override
    public List<Map<String,Object>> selectMaxGroupName(Map<String, Object> paramMap) {
        List<Map<String,Object>> maxGroupName = protocolsDao.selectMaxGroupName(paramMap);
        return maxGroupName;
    }
    //折线补零
    private  List<Map<String,Object>> zeroize(List<Map<String,Object>> mapList,Map<String,Object> paramMap){
        List<Map<String,Object>> resultMap = new ArrayList<Map<String,Object>>();
        List<String> protocals = new ArrayList<String>();
        //循环mapList获取所有category
        for(Map<String,Object> map:mapList ){
            if(! protocals.contains(map.get("category"))){
                protocals.add((String)map.get("category"));
            }

        }

        Long begintime = Long.parseLong(Tool.getTime((String)paramMap.get("startTime")));
        Long endTime = Long.parseLong(Tool.getTime((String)paramMap.get("endTime")));
        for(String protocal:protocals){
            for(long i = begintime; i <= endTime; i += 3600){
                String time = Tool.TimeStamp2DateAll(Long.toString(i));
                Map<String,Object> map1 = new HashMap<String,Object>();
                map1.put("category", protocal);
                map1.put("name",time);
                map1.put("value", 0);
                for(Map<String,Object> map:mapList){
                    String category = (String)map.get("category");
//						String name1 = (String)map.get("name");
                    String name = String.valueOf(map.get("name"));
                    if(category.equals(protocal)&&name.equals(time)){
                        map1.put("value", map.get("value"));
                    }
                }
                resultMap.add(map1);

            }
        }
        return resultMap;
    }




}


