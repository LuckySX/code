package com.flow.traffic.service;


import com.flow.traffic.entity.OperatorProtocolEntity;

public interface IKeySiteTrafficOverviewService {
    OperatorProtocolEntity getOperatorProtocol(Integer area);

}