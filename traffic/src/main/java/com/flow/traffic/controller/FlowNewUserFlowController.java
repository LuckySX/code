package com.flow.traffic.controller;


import com.flow.traffic.entity.*;
import com.flow.traffic.service.FlowNewUserFlowService;
import com.flow.traffic.util.BaseEntity;
import com.flow.traffic.util.Page;
import com.flow.traffic.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userflow")
@Scope("prototype")
public class FlowNewUserFlowController extends BaseEntity {



    @Autowired
    private FlowNewUserFlowService userFlowService;

    @RequestMapping("/getUserPage")
    public String getlistPage(){
        return "traffic/ByUser";
    }
    @RequestMapping("/userFlowT")
    public @ResponseBody Map selecBareaForIp(QueryCondition con, String index){
        comTime(con);
        List<NewUserFlowT> list = userFlowService.selectUserFlowT(con,index);
        return ResultUtil.success(list);
    }
    @RequestMapping("/userFlowB")
    public @ResponseBody Map selecTableForIp(Params params){
        params.setEndDate(params.getEndDate()+" 23:59:59");
        Page<UserFlowB> list = userFlowService.selectUserFlowB(params);
        return ResultUtil.success(list);
    }


    //弹出框：流量链接数趋势
    @RequestMapping("/UserFlowPopup")
    public @ResponseBody Map selecUserFlowPopupUp(QueryCondition con){
        List<UserFlowPopup> listup = userFlowService.selectUserFlowPopupUp(con);
        List<UserFlowPopup> listdowm = userFlowService.selectUserFlowPopupDown(con);
        List<UserFlowPopup> listlinks = userFlowService.selectUserFlowPopupLinks(con);
        listup.addAll(listdowm);
        listup.addAll(listlinks);
        //System.out.println("mapmapmapmapmapmapmap"+map);
        return ResultUtil.success(listup);
    }


    @RequestMapping("/UserFlowPopupProtocol")
    public @ResponseBody Map selecUserFlowPopupProtocol(Params params) {
        params.setEndDate(params.getEndDate()+" 23:59:59");
        Page<UserFlowProtocol> list = userFlowService.selectUserFlowPopupProtocol(params);
        return ResultUtil.success(list);
    }

    @RequestMapping("/UserFlowDataNum")
    public @ResponseBody Map selectDataNum(QueryCondition con){
        comTime(con);
        List<DataNum> list = userFlowService.selectUserDataNum(con);
        return ResultUtil.success(list);
    }

    private void comTime(QueryCondition con){
        if(con.getEndDate()!=null && con.getEndDate().length()<12){
            con.setEndDate(con.getEndDate()+" 23:59:59");
        }
    }
}

