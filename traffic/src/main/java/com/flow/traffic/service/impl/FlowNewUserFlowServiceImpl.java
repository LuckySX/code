package com.flow.traffic.service.impl;


import com.flow.traffic.dao.FlowNewUserFlowDao;
import com.flow.traffic.entity.*;
import com.flow.traffic.service.FlowNewUserFlowService;
import com.flow.traffic.util.Page;
import com.flow.traffic.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlowNewUserFlowServiceImpl implements FlowNewUserFlowService {
    @Autowired
    private FlowNewUserFlowDao userFlowDao;
    DecimalFormat df=new DecimalFormat("#.##");
    @Override
    public List<NewUserFlowT> selectUserFlowT(QueryCondition con, String index) {
        List<NewUserFlowT> newList = new ArrayList<NewUserFlowT>();
        if("zhu".equals(index)){
            List<UserFlowT> list = userFlowDao.selectUserFlowT(con);
            for(int i=0;i<list.size();i++){
                NewUserFlowT userflow = new NewUserFlowT();
                userflow.setCategory("连接数");
                userflow.setName(list.get(i).getName());
                userflow.setValue(list.get(i).getValue_2());
                userflow.setType("line");
                NewUserFlowT userflow2 = new NewUserFlowT();
                userflow2.setCategory("流量");
                userflow2.setName(list.get(i).getName());
                userflow2.setValue(getS(list.get(i).getValue_1()));
                userflow2.setType("bar");
                newList.add(userflow);
                newList.add(userflow2);
            }
        }
        if("lian".equals(index)){
            List<UserFlowT> list = userFlowDao.selectUserFlowTZ(con);
            for(int i=0;i<list.size();i++){
                NewUserFlowT userflow = new NewUserFlowT();
                userflow.setCategory("连接数");
                userflow.setName(list.get(i).getName());
                userflow.setValue(list.get(i).getValue_2());
                userflow.setType("bar");
                NewUserFlowT userflow2 = new NewUserFlowT();
                userflow2.setCategory("流量");
                userflow2.setName(list.get(i).getName());
                userflow2.setValue(getS(list.get(i).getValue_1()));
                userflow2.setType("line");
                newList.add(userflow);
                newList.add(userflow2);
            }
        }
        return newList;
    }
    @Override
    public Page<UserFlowB> selectUserFlowB(Params params) {
        int byt = 1024*1024*1024;
        Page<UserFlowB> list = new Page<UserFlowB>(params);
        if("zhu".equals(params.getIndex())){
            list = userFlowDao.selectUserFlowB(params);
            for(UserFlowB flowb:list.getRows()){
                flowb.setDownBytes(Tool.UnitConversion(Double.toString(Double.parseDouble(flowb.getDownBytes())/byt)));
                flowb.setUpBytes(Tool.UnitConversion(Double.toString(Double.parseDouble(flowb.getUpBytes())/byt)));
                flowb.setSumBytes(Tool.UnitConversion(Double.toString(Double.parseDouble(flowb.getSumBytes())/byt)));
            }

        }
        if("lian".equals(params.getIndex())){
            list = userFlowDao.selectUserFlowBZ(params);
            for(UserFlowB flowb:list.getRows()){
                flowb.setDownBytes(Tool.UnitConversion(Double.toString(Double.parseDouble(flowb.getDownBytes())/byt)));
                flowb.setUpBytes(Tool.UnitConversion(Double.toString(Double.parseDouble(flowb.getUpBytes())/byt)));
                flowb.setSumBytes(Tool.UnitConversion(Double.toString(Double.parseDouble(flowb.getSumBytes())/byt)));
            }

        }
        return list;
    }
    @Override
    public List<UserFlowPopup> selectUserFlowPopupUp(QueryCondition con) {
        List<UserFlowPopup> list = userFlowDao.selectUserFlowPopupUp(con);
        List<UserFlowPopup> returnlist   = new ArrayList<UserFlowPopup>();
        Long begintime = Long.parseLong(Tool.getTime(con.getStartDate()));
        Long endTime = Long.parseLong(Tool.getTime(con.getEndDate()));
        for (long i = begintime; i < endTime; i += 3600) {
            String time = Tool.TimeStamp2DateHour(Long.toString(i));
            String insertTime = Tool.TimeStamp2DateNoYear(Long.toString(i));
            UserFlowPopup popup = new UserFlowPopup();
            popup.setCategory("上行流量");
            popup.setName(insertTime);
            popup.setType("bar");
            if(list.size()>0){
                for(int j = 0; j < list.size(); j++){
                    if(list.get(j).getName().equals(time)){
                        popup.setValue(df.format(Double.parseDouble(list.get(j).getValue())));
                        list.remove(j);
                        break;
                    }
                    popup.setValue("0");
                }
            }else{
                popup.setValue("0");
            }

            returnlist.add(popup);
        }
        return returnlist;
    }
    @Override
    public List<UserFlowPopup> selectUserFlowPopupDown(QueryCondition con) {
        List<UserFlowPopup> list = userFlowDao.selectUserFlowPopupDown(con);
        //System.out.println("11111111111111111111111111111111111"+list);
        List<UserFlowPopup> returnlist   = new ArrayList<UserFlowPopup>();
        Long begintime = Long.parseLong(Tool.getTime(con.getStartDate()));
        Long endTime = Long.parseLong(Tool.getTime(con.getEndDate()));
        for (long i = begintime; i < endTime; i += 3600) {
            String time = Tool.TimeStamp2DateHour(Long.toString(i));
            String insertTime = Tool.TimeStamp2DateNoYear(Long.toString(i));
            UserFlowPopup popup = new UserFlowPopup();
            popup.setCategory("下行流量");
            popup.setName(insertTime);
            popup.setType("bar");
            if(list.size()>0){
                for(int j = 0; j < list.size(); j++){
                    if(list.get(j).getName().equals(time)){
                        popup.setValue(df.format(Double.parseDouble(list.get(j).getValue())));
                        list.remove(j);
                        break;
                    }
                    popup.setValue("0");
                }
            }else{
                popup.setValue("0");
            }

            returnlist.add(popup);
        }
        return returnlist;
    }
    @Override
    public List<UserFlowPopup> selectUserFlowPopupLinks(QueryCondition con) {

        List<UserFlowPopup> list = userFlowDao.selectUserFlowPopupLinks(con);
        //System.out.println("11111111111111111111111111111111111"+list);
        List<UserFlowPopup> returnlist   = new ArrayList<UserFlowPopup>();
        Long begintime = Long.parseLong(Tool.getTime(con.getStartDate()));
        Long endTime = Long.parseLong(Tool.getTime(con.getEndDate()));
        for (long i = begintime; i < endTime; i += 3600) {
            String time = Tool.TimeStamp2DateHour(Long.toString(i));
            String insertTime = Tool.TimeStamp2DateNoYear(Long.toString(i));
            UserFlowPopup popup = new UserFlowPopup();
            popup.setCategory("连接数");
            popup.setName(insertTime);
            popup.setType("line");
            if(list.size()>0){
                for(int j = 0; j < list.size(); j++){
                    if(list.get(j).getName().equals(time)){
                        popup.setValue(list.get(j).getValue());
                        list.remove(j);
                        break;
                    }
                    popup.setValue("0");
                }
            }else{
                popup.setValue("0");
            }

            returnlist.add(popup);
        }
        return returnlist;

    }
    @Override
    public Page<UserFlowProtocol> selectUserFlowPopupProtocol(Params params) {
        Page<UserFlowProtocol> list = userFlowDao.selectUserFlowPopupProtocol(params);
        int byt = 1024*1024*1024;
        for(UserFlowProtocol protocol:list.getRows()){
            protocol.setDownbytes(Tool.UnitConversion(Double.toString(Double.parseDouble(protocol.getDownbytes())/byt)));
            protocol.setSumbytes(Tool.UnitConversion(Double.toString(Double.parseDouble(protocol.getSumbytes())/byt)));
            protocol.setUpbytes(Tool.UnitConversion(Double.toString(Double.parseDouble(protocol.getUpbytes())/byt)));
        }
        return list;
    }
    @Override
    public long selectUserFlowBnum(QueryCondition con) {
        long i = userFlowDao.selectUserFlowBnum(con);
        return i;
    }
    @Override
    public long selectUserFlowPopupProtocolnum(QueryCondition con) {
        long i = userFlowDao.selectUserFlowPopupProtocolnum(con);
        return i;
    }
    @Override
    public List<DataNum> selectUserDataNum(QueryCondition con) {
        List<DataNum> list = userFlowDao.selectUserDataNum(con);
        return list;
    }
    private String getS(String value){
        if(Double.parseDouble(value)>0.001){
            return df.format(Double.parseDouble(value));
        }else{
            return "0.001";
        }
    }
}

