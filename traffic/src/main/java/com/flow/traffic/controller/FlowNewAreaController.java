package com.flow.traffic.controller;


import com.flow.traffic.entity.AreaStatisticsGraph;
import com.flow.traffic.entity.AreaStatisticsTableIp;
import com.flow.traffic.entity.AreaStatisticsTableWeb;
import com.flow.traffic.entity.QueryCondition;
import com.flow.traffic.service.FlowNewAreaService;
import com.flow.traffic.util.ResultUtil;
import com.flow.traffic.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/areaNew")
@Scope("prototype")
public class FlowNewAreaController {

    private static String[] citysArr ="\\u77F3\\u5BB6\\u5E84,\\u5510\\u5C71,\\u90AF\\u90F8,\\u79E6\\u7687\\u5C9B,\\u4FDD\\u5B9A,\\u5F20\\u5BB6\\u53E3,\\u627F\\u5FB7,\\u5ECA\\u574A,\\u6CA7\\u5DDE,\\u8861\\u6C34,\\u90A2\\u53F0".split(",");
    @Autowired
    private FlowNewAreaService areaService;

    /**
     * 流量流向监测-地域统计-折线图
     * @param con
     * @param index
     * @return
     */

    @RequestMapping("/diagram")
    public @ResponseBody
    Object selectTarea(QueryCondition con, String index){
        List<String> citysList = new ArrayList<String>();
//		System.out.println(citys);
        con.setCitysArr(citysArr);
        con.setStartDate(con.getStartDate()+" 00:00:00");
        con.setEndDate(con.getEndDate()+" 23:59:59");

        List<AreaStatisticsGraph> list = areaService.selectTareaHour(con,index);
        return ResultUtil.success(list);
    }

    /**
     * 流量流向监测-地域统计-表格
     * @param con
     * @param index
     * @return
     */
    @RequestMapping("/tableForWebIP")
    public @ResponseBody Object selecBareaForWeb(QueryCondition con,String index){
        con.setOrder(con.getOrder());
        con.setSort(Tool.getsort(con.getSort()));
        con.setStartDate(con.getStartDate()+" 00:00:00");
        con.setEndDate(con.getEndDate()+" 23:59:59");
        //System.out.println("______________"+con.getOrder());
        List<String> citysList = new ArrayList<String>();
        con.setCitysArr(citysArr);
        if(index.equals("0")){
            Map<String ,Object> map = new HashMap<String ,Object>();
            List<AreaStatisticsTableWeb> list = areaService.selectBareaForWeb(con);
            //System.out.println("123123:"+con.getSort());
            long total = areaService.selectBareaForWebnum(con);
            map.put("total", total);
            map.put("rows", list);
            System.out.println(map);
            return ResultUtil.success(map);
        }else{
            Map<String ,Object> map = new HashMap<String ,Object>();
            List<AreaStatisticsTableIp> list = areaService.selectBareaForIp(con);
            //System.out.println("123123333333:"+con.getSort());
            long total = areaService.selectBareaForWebnum(con);
            map.put("total", total);
            map.put("rows", list);
            System.out.println(map);
            return ResultUtil.success(map);
        }
    }


}
