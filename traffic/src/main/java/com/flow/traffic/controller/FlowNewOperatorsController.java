package com.flow.traffic.controller;

import com.flow.traffic.service.FlowNewOperatorsService;
import com.flow.traffic.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/operators")
public class FlowNewOperatorsController {

    @Autowired
    FlowNewOperatorsService operatorsService;

    /**
     * 跳转到jsp页面
     */
    @RequestMapping("/index")
    public String index(Model model)throws Exception{
        return "traffic/Operators";
    }

    /**
     * 运营商协议流量统计图
     * @param
     * @param startTime
     * @param endTime
     * @param operator
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/chart")
    public Map selectChart(String startTime, String endTime, String operator) throws Exception{
        List<Map<String,Object>> chart = null;
        Map<String,Object> paramMap = new HashMap<String,Object>();

        paramMap.put("startTime", startTime+" 00:00:00");
        paramMap.put("endTime", endTime+" 23:59:59");
        paramMap.put("operator", operator);
        //List<Map<String, Object>> newchart = new ArrayList<Map<String, Object>>();

        try {
            chart = operatorsService.selectChart(paramMap);
            for(Map<String,Object> map:chart){
                String category =  map.get("category").toString();
                String name = map.get("name").toString();
                String value = map.get("value").toString();
                if(value.equals("0.00")){
                    map.put("category", category);
                    map.put("name", name);
                    map.put("value", 0.01);
                }
            }


        } catch (Exception e) {

            return ResultUtil.initResult(chart,ResultUtil.State.UNUSUAL);
        }
        return ResultUtil.success(chart);

    }

    /**
     * 运营商流量变化(xj同步)
     *
     * @param
     * @param startTime
     * @param endTime
     * @param operator
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/line")
    public Map selectLine(String startTime, String endTime, String operator) throws Exception {
        List<Map<String, Object>> line = null;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startTime", startTime + " 00:00:00");
        paramMap.put("endTime", endTime + " 23:59:59");
        paramMap.put("operator", operator);
        try {
            line = operatorsService.selectLine(paramMap);
//            for (Map<String, Object> map : line) {
//                String date = Tool.TimeStamp2DateNoYear(Tool.getTime(String.valueOf(map.get("name"))));
//                String value = map.get("value").toString();
//                map.put("name", date);
//                if(value.equals("0.00")){
//                    map.put("value", 0.01);
//                }
//
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.success(line);

    }
}
