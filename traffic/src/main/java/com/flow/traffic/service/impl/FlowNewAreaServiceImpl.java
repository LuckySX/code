package com.flow.traffic.service.impl;

import com.flow.traffic.dao.impl.FlowNewAreaDaoImpl;
import com.flow.traffic.entity.AreaStatisticsGraph;
import com.flow.traffic.entity.AreaStatisticsTableIp;
import com.flow.traffic.entity.AreaStatisticsTableWeb;
import com.flow.traffic.entity.QueryCondition;
import com.flow.traffic.service.FlowNewAreaService;
import com.flow.traffic.util.DateUtil;
import com.flow.traffic.util.StringUtil;
import com.flow.traffic.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FlowNewAreaServiceImpl implements FlowNewAreaService {

    @Autowired
    private FlowNewAreaDaoImpl areaDao;


    @Override
    public List<AreaStatisticsGraph> selectTareaHour(QueryCondition con, String index) {
        System.out.println(con.getStartDate());
        List<AreaStatisticsGraph> returnlist = new ArrayList<AreaStatisticsGraph>();
        Long begintime = Long.parseLong(Tool.getTime(con.getStartDate()));
        Long endTime = Long.parseLong(Tool.getTime(con.getEndDate()));
        List<AreaStatisticsGraph> list = areaDao.selectTareaHour(con,index);
        List<String> citysList = Arrays.asList(con.getCitysArr());

        for(String city:citysList){

            for(long i = begintime; i <= endTime; i += 3600){
                AreaStatisticsGraph area = new AreaStatisticsGraph();
                area.setCategory(city);
                String time = Tool.TimeStamp2DateHour(Long.toString(i));
                String insertTime = Tool.TimeStamp2DateNoYear(Long.toString(i));
                area.setName(insertTime);
                double value = 0;
                for (int j = 0; j < list.size(); j++){
                    if (list.get(j).getCategory().equals(city)&& list.get(j).getName().equals(time)) {
                        value=value+list.get(j).getValue();
                        area.setValue(value);
                        list.remove(j);
                        break;
                    } else {
                        area.setValue(0);
                    }
                }
                returnlist.add(area);
            }
        }
        return returnlist;

    }


    @Override
    public List<AreaStatisticsTableWeb> selectBareaForWeb(QueryCondition con) {
        List<AreaStatisticsTableWeb> list = areaDao.selectBareaForWeb(con);
        String te = con.getEndDate().substring(0,10);
        String tee = DateUtil.getCurrDate(DateUtil.LONG_DATE_FORMAT);
        if(te.equals(tee)){
            String endtime = areaDao.selectMaxTime(con);
            if(StringUtil.isNotNull(endtime)){
                con.setEndDate(endtime);
            }
        }
        long time = (Long.parseLong(Tool.getTime(con.getEndDate()))-Long.parseLong(Tool.getTime(con.getStartDate())));
        int byt = 1024*1024*1024;
        for(int i=0;i<list.size();i++){
            AreaStatisticsTableWeb tableweb = list.get(i);
//            if(provinceName.equals(tableweb.getArea())){
//                tableweb.setArea("其它");
//            }
            tableweb.setWebupBps(Tool.UnitConversionDanwei(tableweb.getWebup(),time));
            tableweb.setWebdownBps(Tool.UnitConversionDanwei(tableweb.getWebdown(),time));
//			tableweb.setWebupBps(tableweb.getWebup());
//			tableweb.setWebdownBps(tableweb.getWebdown());
        }

        for(AreaStatisticsTableWeb tableWeb:list){
            tableWeb.setWebdown(Tool.UnitConversion(Double.toString(Double.parseDouble(tableWeb.getWebdown())/byt)));
            tableWeb.setWebup(Tool.UnitConversion(Double.toString(Double.parseDouble(tableWeb.getWebup())/byt)));
//			tableWeb.setWebdown(tableWeb.getWebdown());
//			tableWeb.setWebup(tableWeb.getWebup());
        }
        return list;
    }

@Override
    public List<AreaStatisticsTableIp> selectBareaForIp(QueryCondition con) {
        List<AreaStatisticsTableIp> list = areaDao.selectBareaForIp(con);
        String te = con.getEndDate().substring(0,10);
        String tee = DateUtil.getCurrDate(DateUtil.LONG_DATE_FORMAT);
        if(te.equals(tee)){
            String endtime = areaDao.selectMaxTime(con);
            if(StringUtil.isNotNull(endtime)){
                con.setEndDate(endtime);
            }
        }
        long time = (Long.parseLong(Tool.getTime(con.getEndDate()))-Long.parseLong(Tool.getTime(con.getStartDate())));
        int byt = 1024*1024*1024;
        for(int i=0;i<list.size();i++){
            AreaStatisticsTableIp tableIp = list.get(i);
//            if(provinceName.equals(tableIp.getArea())){
//                tableIp.setArea("其它");
//            }
            tableIp.setHostupBps(Tool.UnitConversionDanwei(tableIp.getHostup(),time));
            tableIp.setHostdownBps(Tool.UnitConversionDanwei(tableIp.getHostdown(),time));
        }
        for(AreaStatisticsTableIp tableIp:list){
            //System.out.println("1111111111111111111111111"+tableIp.getHostdown());
            tableIp.setHostdown(Tool.UnitConversion(Double.toString(Double.parseDouble(tableIp.getHostdown())/byt)));
            tableIp.setHostup(Tool.UnitConversion(Double.toString(Double.parseDouble(tableIp.getHostup())/byt)));
        }
        return list;

    }
    @Override
    public long selectBareaForWebnum(QueryCondition con) {
        long i = areaDao.selectBareaForWebnum(con);
        return i;
    }
}

