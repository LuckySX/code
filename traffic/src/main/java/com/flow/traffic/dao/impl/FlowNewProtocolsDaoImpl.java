package com.flow.traffic.dao.impl;


import com.flow.traffic.dao.FlowNewProtocolsDao;
import com.flow.traffic.util.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class FlowNewProtocolsDaoImpl extends BaseDao implements FlowNewProtocolsDao {

    /**
     * 流量分布图
     */
    @Override
    public List<Map<String,Object>> selectChart1(Map<String, Object> paramMap) {
        List<Map<String,Object>> chart1 = this.selectMap("com.chanct.flowNewProtocolsMapper.selectFlow",paramMap);
        return chart1;
    }

    /**
     * 协议流量TOP图
     */
    @Override
    public List<Map<String, Object>> selectChart2(Map<String, Object> paramMap) {
        List<Map<String,Object>> chart2 = this.selectMap("com.chanct.flowNewProtocolsMapper.selectChart2",paramMap);
        return chart2;
    }

    /**
     * 连接数分布图
     */
    @Override
    public List<Map<String, Object>> selectChart3(Map<String, Object> paramMap) {
        List<Map<String,Object>> chart3 = this.selectMap("com.chanct.flowNewProtocolsMapper.selectChart3",paramMap);
        return chart3;
    }

    /**
     * 连接数流量趋势图
     */
    @Override
    public List<Map<String, Object>> selectChart4(Map<String, Object> paramMap) {
        List<Map<String,Object>> chart4 = this.selectMap("com.chanct.flowNewProtocolsMapper.selectChart4",paramMap);
        return chart4;
    }

    /**
     * 上行流量趋势图
     */
    @Override
    public List<Map<String, Object>> selectChart5(Map<String, Object> paramMap) {
        List<Map<String,Object>> chart5 = this.selectMap("com.chanct.flowNewProtocolsMapper.selectChart5",paramMap);
        return chart5;
    }

    /**
     * 下行流量趋势图
     */
    @Override
    public List<Map<String, Object>> selectChart6(Map<String, Object> paramMap) {
        List<Map<String,Object>> chart6 = this.selectMap("com.chanct.flowNewProtocolsMapper.selectChart6",paramMap);
        return chart6;
    }

    @Override
    public List<Map<String,Object>> selectMaxGroupName(Map<String, Object> paramMap) {
        List<Map<String,Object>> name = this.selectList("com.chanct.flowNewProtocolsMapper.selectMaxGroupName",paramMap);
        return name;
    }







}


