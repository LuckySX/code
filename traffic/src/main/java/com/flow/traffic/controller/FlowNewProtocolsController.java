package com.flow.traffic.controller;

import com.flow.traffic.service.FlowNewProtocolsService;
import com.flow.traffic.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/flow")
public class FlowNewProtocolsController {


    @Autowired
    FlowNewProtocolsService protocolsService;

    /**
     * 流量流向监测-应用统计-饼图
     * @param response
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/introduce")
    public Object selectchart1(HttpServletResponse response, String startDate, String endDate) throws Exception{
//		Map<String,Object> row1 = new HashMap<String,Object>();
//		List<Map<String,Object>> maxProtocolgroup = null;//最大协议组名
        List<Map<String,Object>> chart1 = null;
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("startTime", startDate+" 00:00:00");
        paramMap.put("endTime", endDate+" 23:59:59");
        try {
            chart1 = protocolsService.selectChart1(paramMap);
            if(chart1.size()>0){
                for(Map<String,Object> map:chart1){
                    String category =  map.get("category").toString();
                    String name = map.get("name").toString();
                    String value = map.get("value").toString();
                    if(value.equals("0.00")){
                        map.put("category", category);
                        map.put("name", name);
                        map.put("value", 0.01);
                    }
                }
            }


//			Map<String,Object> map = new HashMap<String,Object>();
//			maxProtocolgroup = protocolsService.selectMaxGroupName(paramMap);
//			if(maxProtocolgroup.equals("") & maxProtocolgroup!=null){
//				map.put("protocolgroup", maxProtocolgroup.get(0).get("protocolgroup"));
//			}
//
//			List<Map> rr1List = new ArrayList();
//			rr1List.add(map);

//			row1.put("rr1", rr1List);//最大协议组名
//			row1.put("rr2", chart1);//流量分布图(父图)数据
        } catch (Exception e) {

           e.printStackTrace();
        }
        return ResultUtil.success(chart1) ;
    }

    /**
     * 流量流向监测-应用统计-折柱和折线图
     * @param response
     * @param startDate
     * @param endDate
     * @param protocolgroup
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/chart")
    public Object select2(HttpServletResponse response,String startDate,String endDate,String protocolgroup) throws Exception{
        Map<String,Object> row2 = new HashMap<String,Object>();
//        List<Map<String,Object>> chart2 = null;//协议流量TOP图
//        List<Map<String,Object>> chart3 = null;//连接数分布图
//        List<Map<String,Object>> chart4 = null;//连接数趋势图
//        List<Map<String,Object>> chart5 = null;//上行流量趋势图
//        List<Map<String,Object>> chart6 = null;//下行流量趋势图
        List<Map<String,Object>> chart5s = null;//上行流量趋势图
        List<Map<String,Object>> chart4s = null;//连接数趋势图
        List<Map<String,Object>> chart6s = null;//下行流量趋势图
        List<Map<String,Object>> protocolgroupList = new ArrayList<Map<String,Object>>();//最大协议组
        List<Map<String,Object>> newchart2 = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> newchart3 = new ArrayList<Map<String,Object>>();

        Map<String,Object> paramMap = new HashMap<String,Object>();
        Map<String,Object> paramMap1 = new HashMap<String,Object>();//查询最大协议组名的参数
        if(protocolgroup!="" && protocolgroup  !=null){
            paramMap.put("protocolgroup", protocolgroup);
        }else{
            paramMap1.put("startTime", startDate+" 00:00:00");
            paramMap1.put("endTime", endDate+" 23:59:59");
            //查询最大协议组名称
            List<Map<String,Object>> maxGroup = protocolsService.selectMaxGroupName(paramMap1);
            if(maxGroup.size()>0){
                paramMap.put("protocolgroup", maxGroup.get(0).get("protocolgroup"));
            }else{
                paramMap.put("protocolgroup", "-1");
            }

        }
        paramMap.put("startTime", startDate+" 00:00:00");
        paramMap.put("endTime", endDate+" 23:59:59");

        try {
            //流量协议top图
            CompletableFuture<List<Map<String,Object>>> future2 =
                    CompletableFuture.supplyAsync(()->{
                        List<Map<String,Object>> ff1 = new ArrayList<Map<String,Object>>();
                        ff1= protocolsService.selectChart2(paramMap);
                        return ff1;
            }).exceptionally(e ->null);
            //连接数分布图
            CompletableFuture<List<Map<String,Object>>> future3 =
                    CompletableFuture.supplyAsync(()->{
                        List<Map<String,Object>> ff1 = new ArrayList<Map<String,Object>>();
                        ff1=protocolsService.selectChart3(paramMap);
                        return  ff1;
                    }).exceptionally(e ->null);
            //连接数趋势图
            CompletableFuture<List<Map<String,Object>>> future4 =
                    CompletableFuture.supplyAsync(()->{
                        List<Map<String,Object>> ff1 = new ArrayList<Map<String,Object>>();
                        ff1= protocolsService.selectChart4(paramMap);
                        return ff1;
                    }).exceptionally(e ->null);
            //上行流量趋势图
            CompletableFuture<List<Map<String,Object>>> future5 =
                    CompletableFuture.supplyAsync(()->{
                        List<Map<String,Object>> ff1 = new ArrayList<Map<String,Object>>();
                        ff1=protocolsService.selectChart5(paramMap);
                        return ff1;
                    }).exceptionally(e ->null);
            //下行流量趋势图
            CompletableFuture<List<Map<String,Object>>> future6 =
                    CompletableFuture.supplyAsync(()->{
                        List<Map<String,Object>> ff1 = new ArrayList<Map<String,Object>>();
                        ff1=protocolsService.selectChart6(paramMap);
                        return ff1;
                    }).exceptionally(e ->null);


/*
            newchart2 = protocolsService.selectChart2(paramMap);//流量协议top图

            newchart3 = protocolsService.selectChart3(paramMap);//连接数分布图

            chart4s = protocolsService.selectChart4(paramMap);//连接数趋势图

            chart5s = protocolsService.selectChart5(paramMap);//上行流量趋势图

            chart6s = protocolsService.selectChart6(paramMap);//下行流量趋势图
*/

            Map<String,Object> protocolgroupMap = new HashMap<String,Object>();
            if(protocolgroup!="" && protocolgroup  !=null){
                protocolgroupMap.put("protocolgroup", protocolgroup);
                protocolgroupList.add(protocolgroupMap);
            }else{
                Map<String,Object> param = new HashMap<String,Object>();
                Map<String,Object> map1 = new HashMap<String,Object>();
                List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
                param.put("startTime", startDate+" 00:00:00");
                param.put("endTime", endDate+" 23:59:59");
                list=protocolsService.selectMaxGroupName(param);
                if(list.size()>0){
                    map1.put("protocolgroup", list.get(0).get("protocolgroup"));
                }else{
                    map1.put("protocolgroup", "");
                }

                protocolgroupList.add(map1);
            }
//			maxProtocolgroup = protocolsService.selectMaxGroupName(paramMap1);

            CompletableFuture.allOf(future2,future3,future4,future5,future6).join();
            newchart2 =future2.get();
            newchart3 =future3.get();
            chart4s =future4.get();
            chart5s =future5.get();
            chart6s =future6.get();

            row2.put("rr1", newchart2);//流量协议top图
            row2.put("rr2", newchart3);//连接数分布图
            row2.put("rr3", chart5s);//上行流量趋势图
            row2.put("rr4", chart6s); //下行流量趋势图
            row2.put("rr5", chart4s);//连接数趋势图
            row2.put("rr6", protocolgroupList);//最大协议组名
        } catch (Exception e) {
           e.printStackTrace();
        }

        return ResultUtil.success(row2);

    }
}

