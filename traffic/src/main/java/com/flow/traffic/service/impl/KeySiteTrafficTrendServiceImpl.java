package com.flow.traffic.service.impl;


import com.flow.traffic.dao.impl.KeySiteTrafficTrendDaoImpl;
import com.flow.traffic.entity.TrafficTrendEntity;
import com.flow.traffic.service.IKeySiteTrafficTrendService;
import com.flow.traffic.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class KeySiteTrafficTrendServiceImpl implements IKeySiteTrafficTrendService {
    @Autowired
    KeySiteTrafficTrendDaoImpl dao;

    public Page<TrafficTrendEntity> getTrafficTrend(TrafficTrendEntity trafficTrendEntity) {
        //先查询基础信息
        Page<TrafficTrendEntity> page = null;
        page =dao.getTrafficBasics(trafficTrendEntity);
        List<TrafficTrendEntity> trend = new ArrayList<TrafficTrendEntity>();
        //根据基础信息查询折线图
        if (page.getRows().size()>0){
            for(int i=0;i<page.getRows().size();i++){
                trend= dao.getTrafficTrend(page.getRows().get(i));
                page.getRows().get(i).setDateArr(Arrays.asList(trend.get(0).getDateStr().split(",")));
                page.getRows().get(i).setFlowArr(Arrays.asList(trend.get(0).getFlowStr().split(",")));
                page.getRows().get(i).setPersonArr(Arrays.asList(trend.get(0).getPersonStr().split(",")));
            }
        }

        return page;
    }


}