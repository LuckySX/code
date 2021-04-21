package com.flow.traffic.service.impl;


import com.flow.traffic.dao.InterFlowDao;
import com.flow.traffic.service.InterFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InterFlowServiceImpl implements InterFlowService {
    @Autowired
    InterFlowDao dao;

    public List map(Map map) {
        return dao.map(map);
    }

    public List worldMap(Map map) {
        return dao.worldMap(map);
    }

    @Override
    public Map totalInfo(Map map) {
        return dao.totalInfo(map);
    }

    @Override
    public List userTop(Map map) {
        return dao.userTop(map);
    }

    @Override
    public List protocolTop(Map map) {
        return dao.protocolTop(map);
    }
}