package com.flow.traffic.controller;


import com.flow.traffic.entity.OperatorProtocolEntity;
import com.flow.traffic.service.IKeySiteTrafficOverviewService;
import com.flow.traffic.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/interFlow")
public class KeySiteTrafficOverviewController {

    @Autowired
    IKeySiteTrafficOverviewService service;

    /**
     * 流量概览-运营商及其协议流量占比双环形图
     * @return
     */

    @ResponseBody
    @RequestMapping("/operatorProtocol")
    private Object getOperatorProtocol(@RequestParam (value="area",required = false,defaultValue = "0") Integer area){
        OperatorProtocolEntity entity = null;
        try{
            entity=service.getOperatorProtocol(area);

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultUtil.success(entity);

    }

}