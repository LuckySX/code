package com.flow.traffic.dao.impl;


import com.flow.traffic.dao.IKeySiteTrafficOverviewDao;
import com.flow.traffic.entity.OPEntity;
import com.flow.traffic.util.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KeySiteTrafficOverviewDaoImpl extends BaseDao implements IKeySiteTrafficOverviewDao {

    public List<OPEntity> getOperator(Integer area) {
        return selectList("com.chanct.trafficOverviewMapper.selectOperator",area);
    }

    public List<OPEntity> getProtocol(Integer area) {
        return selectList("com.chanct.trafficOverviewMapper.selectProtocol",area);
    }
}