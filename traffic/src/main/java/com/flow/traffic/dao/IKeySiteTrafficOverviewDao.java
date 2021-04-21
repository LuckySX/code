package com.flow.traffic.dao;


import com.flow.traffic.entity.OPEntity;

import java.util.List;

public interface IKeySiteTrafficOverviewDao {
    List<OPEntity> getOperator(Integer area);
    List<OPEntity> getProtocol(Integer area);
}