package com.flow.traffic.controller;


import com.flow.traffic.entity.TrafficTrendEntity;
import com.flow.traffic.service.impl.KeySiteTrafficTrendServiceImpl;
import com.flow.traffic.util.Page;
import com.flow.traffic.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/interFlow")
public class KeySiteTrafficTrendController {

    @Autowired
    KeySiteTrafficTrendServiceImpl service;

    /**
     * 重点网站流量
     * @param trafficTrendEntity
     * @return
     */
    @ResponseBody
    @RequestMapping("/trafficTrend")
    private Object getTrafficTrend(TrafficTrendEntity trafficTrendEntity){
        Map<String,Object> map = new HashMap<String,Object>();
        Page<TrafficTrendEntity> list = null;
        try{
             list=service.getTrafficTrend(trafficTrendEntity);

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.success(list);

    }


}