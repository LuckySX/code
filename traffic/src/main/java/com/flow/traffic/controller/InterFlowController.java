package com.flow.traffic.controller;



import com.flow.traffic.service.InterFlowService;
import com.flow.traffic.util.ResultUtil;
import com.flow.traffic.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("interFlow")

public class InterFlowController {
    @Autowired
    InterFlowService service;

    @ResponseBody
    @RequestMapping("/map")
    public Object map(String value,String filed) {
        Map map = new HashMap<String, String>();
            if (StringUtil.isNotNull(value))
                map.put("value", value.replace("%", "\\%"));
            map.put("filed", filed);
            return ResultUtil.success(service.map(map));
    }

    @ResponseBody
    @RequestMapping("/worldMap")
    public Object worldMap(Integer area) {
        Map map = new HashMap<String, String>();
        map.put("area",area);
        return ResultUtil.success(service.worldMap(map));
    }

    @ResponseBody
    @RequestMapping("/totalInfo")
    public Object totalInfo() {
        Map map = new HashMap<String, String>();
        return ResultUtil.success(service.totalInfo(map));
    }

    @ResponseBody
    @RequestMapping("/userTop")
    public Object userTop(Integer area) {
        if(area==null) area =2;
        Map map = new HashMap<String, String>();
        map.put("area",area);

        List list = service.userTop(map);
        return ResultUtil.success(list);
    }

    @ResponseBody
    @RequestMapping("/protocolTop")
    public Object protocolTop(Integer area) {
        if(area==null)  area =2;
        Map map = new HashMap<String, String>();
        map.put("area",area);
        List list = service.protocolTop(map);
        return ResultUtil.success(list);
    }
}
