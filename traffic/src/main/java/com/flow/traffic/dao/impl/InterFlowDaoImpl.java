package com.flow.traffic.dao.impl;


import com.flow.traffic.dao.InterFlowDao;
import com.flow.traffic.util.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class InterFlowDaoImpl extends BaseDao implements InterFlowDao {
    public List map(Map map) {
        return super.selectList("com.chanct.interFlowMapper.map",map);
    }

    public List worldMap(Map map) {
        return super.selectList("com.chanct.interFlowMapper.worldMap",map);
    }

    @Override
    public Map totalInfo(Map map) {
        return super.selectObject("com.chanct.interFlowMapper.totalInfo",map);
    }

    @Override
    public List protocolTop(Map map) {
        return super.selectList("com.chanct.interFlowMapper.protocolTop",map);
    }

    @Override
    public List userTop(Map map) {
        return super.selectList("com.chanct.interFlowMapper.userTop",map);
    }
}