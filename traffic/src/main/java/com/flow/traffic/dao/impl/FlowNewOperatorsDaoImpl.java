package com.flow.traffic.dao.impl;


import com.flow.traffic.dao.FlowNewOperatorsDao;
import com.flow.traffic.util.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class FlowNewOperatorsDaoImpl extends BaseDao implements FlowNewOperatorsDao {

    /**
     * 运营商协议流量统计图
     */
    @Override
    public List<Map<String, Object>> selectChart(Map<String, Object> paramMap) {
        List<Map<String,Object>> chart = this.selectList("com.chanct.flowDirection.newOperatorsMapper.selectChart",paramMap);
        return chart;
    }

    /**
     * 运营商流量变化
     */
    @Override
    public List<Map<String, Object>> selectLine(Map<String, Object> paramMap) {
        List<Map<String,Object>> line = this.selectList("com.chanct.flowDirection.newOperatorsMapper.selectLine",paramMap);
        return line;
    }

    /*
     * 查询运营商分类
     */
    @Override
    public List<String> selectOperator(Map<String,Object> paramMap) {
        List<String> ope = this.selectList("com.chanct.flowDirection.newOperatorsMapper.selectOperator",paramMap);
        return ope;
    }

}

