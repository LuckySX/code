package com.flow.traffic.service;

import java.util.List;
import java.util.Map;

public interface FlowNewOperatorsService {
    public List<Map<String,Object>> selectChart(Map<String, Object> paramMap);//运营商协议流量统计图
    public List<Map<String,Object>> selectLine(Map<String, Object> paramMap);//运营商流量变化
}
