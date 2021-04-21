package com.flow.traffic.service.impl;


import com.flow.traffic.dao.IKeySiteTrafficOverviewDao;
import com.flow.traffic.entity.OperatorProtocolEntity;
import com.flow.traffic.service.IKeySiteTrafficOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeySiteTrafficOverviewServiceImpl implements IKeySiteTrafficOverviewService {
    @Autowired
    IKeySiteTrafficOverviewDao dao;
    public OperatorProtocolEntity getOperatorProtocol(Integer area) {
        OperatorProtocolEntity entity = new OperatorProtocolEntity();
        entity.setOperators(dao.getOperator(area));
        entity.setProtocols(dao.getProtocol(area));
        return entity;
    }
}