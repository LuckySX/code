package com.flow.traffic.dao;

import java.util.List;
import java.util.Map;

public interface FlowNewOperatorsDao {
    public List<Map<String,Object>> selectChart(Map<String, Object> paramMap);//运营商协议流量统计图
    public List<Map<String,Object>> selectLine(Map<String, Object> paramMap);//运营商流量变化
    public List<String> selectOperator(Map<String, Object> paramMap);//查询运营商的种类
}
