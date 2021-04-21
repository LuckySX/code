package com.flow.traffic.dao;

import java.util.List;
import java.util.Map;

public interface FlowNewProtocolsDao {
    public List<Map<String,Object>> selectChart1(Map<String, Object> paramMap);//流量分布图
    public List<Map<String,Object>> selectChart2(Map<String, Object> paramMap);//协议流量TOP图
    public List<Map<String,Object>> selectChart3(Map<String, Object> paramMap);//连接数分布图
    public List<Map<String,Object>> selectChart4(Map<String, Object> paramMap);//连接数流量趋势图
    public List<Map<String,Object>> selectChart5(Map<String, Object> paramMap);//上行流量趋势图
    public List<Map<String,Object>> selectChart6(Map<String, Object> paramMap);//下行流量趋势图
    public List<Map<String,Object>> selectMaxGroupName(Map<String, Object> paramMap);//查询最大占比协议组名


}
