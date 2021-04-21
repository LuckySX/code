package com.flow.traffic.dao;


import com.flow.traffic.entity.TrafficTrendEntity;
import com.flow.traffic.util.Page;

import java.util.List;

public interface IKeySiteTrafficTrendDao {
//    List<TrafficTrendEntity> getTrafficTrend(List<TrafficTrendEntity> trafficTrendEntityList);
    Page<TrafficTrendEntity> getTrafficBasics(TrafficTrendEntity trafficTrendEntity);
    List<TrafficTrendEntity> getTrafficTrend(TrafficTrendEntity trafficTrendEntity);


}