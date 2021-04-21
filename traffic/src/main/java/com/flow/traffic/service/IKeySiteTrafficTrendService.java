package com.flow.traffic.service;


import com.flow.traffic.entity.TrafficTrendEntity;
import com.flow.traffic.util.Page;

public interface IKeySiteTrafficTrendService {
    Page<TrafficTrendEntity> getTrafficTrend(TrafficTrendEntity trafficTrendEntity);

}