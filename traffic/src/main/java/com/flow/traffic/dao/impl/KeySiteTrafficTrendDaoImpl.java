package com.flow.traffic.dao.impl;


import com.flow.traffic.dao.IKeySiteTrafficTrendDao;
import com.flow.traffic.entity.TrafficTrendEntity;
import com.flow.traffic.util.BaseDao;
import com.flow.traffic.util.Page;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class KeySiteTrafficTrendDaoImpl extends BaseDao implements IKeySiteTrafficTrendDao {


    public List<TrafficTrendEntity> getTrafficTrend( TrafficTrendEntity trafficTrendEntity) {
        List<TrafficTrendEntity> list = new ArrayList<TrafficTrendEntity>();
        list = selectList("com.chanct.trafficTrendMapper.selectTrafficTrend",trafficTrendEntity);
        return list;
    }

    public Page<TrafficTrendEntity> getTrafficBasics(TrafficTrendEntity trafficTrendEntity) {
        Page<TrafficTrendEntity> page = new Page<TrafficTrendEntity>(trafficTrendEntity);
        page.setRows(selectList("com.chanct.trafficTrendMapper.selectTrafficBasics",trafficTrendEntity,trafficTrendEntity.getPage(),trafficTrendEntity.getRows()));
        page.setTotal(selectTotal("com.chanct.trafficTrendMapper.selectTrafficBasicsTotal",trafficTrendEntity));
        return page;
    }
}