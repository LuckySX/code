package com.flow.traffic.controller;


import com.flow.traffic.entity.CompanyInfo;
import com.flow.traffic.entity.KeyAssetStatisticEntity;
import com.flow.traffic.entity.KeyAssetTestingEntity;
import com.flow.traffic.service.KeyAssetTestingService;
import com.flow.traffic.util.ExportUtil;
import com.flow.traffic.util.Page;
import com.flow.traffic.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 曹大庆
 * @date 2020/4/3 1:26
 * 重点资产情况
 */
@Controller
@RequestMapping("/KeyAsset")
public class KeyAssetTestingController {

    @Autowired
    private KeyAssetTestingService keyAssetTestingService;

    /**
     * 昨日深圳市流量监测情况
     * @return
     */
    @RequestMapping("/keyAssetsStatistic")
    @ResponseBody
    public Object keyAssetsStatistic(KeyAssetStatisticEntity keyAssetStatisticEntity){
        KeyAssetStatisticEntity result = keyAssetTestingService.getKeyAssetStatistic(keyAssetStatisticEntity);
        return ResultUtil.success(result);
    }

    /**
     * 资产列表
     * 用于页面左侧单位目录查询
     * 默认展示全部，可输入地区，行业，单位名称，ip，域名查询单位目录。点击单位目录可查询右侧基本信息
     * 单位名称，ip，域名可模糊查询
     * county 地区
     * categories 行业
     * companyName 单位名称
     * ip
     * domain 域名
     * 分页
     * @param companyInfo
     * @return
     */
    @RequestMapping("/companyInfoList")
    @ResponseBody
    public Object companyInfoList(CompanyInfo companyInfo){
        int code = 200;
        String msg = null;
        Page<CompanyInfo> result = null;
        boolean ispar = true;
        /*
        if(companyInfo.getCompanyName() !=null && !"".equals(companyInfo.getCompanyName())){
            ispar = !this.isPar(companyInfo.getCompanyName());
        }
         */
        if(companyInfo.getCompanyName() !=null && !"".equals(companyInfo.getCompanyName())&&companyInfo.getCompanyName().contains("%")){
            companyInfo.setCompanyName(companyInfo.getCompanyName().replace("%","/%"));
        }
        if(companyInfo.getDomain() !=null && !"".equals(companyInfo.getDomain()) && companyInfo.getDomain().contains("%")){
            companyInfo.setDomain(companyInfo.getDomain().replace("%","/%"));
        }
        if(companyInfo.getIp() !=null && !"".equals(companyInfo.getIp()) && companyInfo.getIp().contains("%")){
            companyInfo.setIp(companyInfo.getIp().replace("%","/%"));
        }
        if(companyInfo.getPage()>0){//处理分页
            companyInfo.setStart((companyInfo.getPage()-1)*companyInfo.getRows());
        }
        //处理排序
        if(companyInfo.getSort() !=null && !"".equals(companyInfo.getSort())){
            String sort = companyInfo.getSort();
            if("companyName".equals(sort)){
                companyInfo.setSort("ip_name");
            }else if("categories".equals(sort)){
                companyInfo.setSort("ip_categories");
            }else if("county".equals(sort)){
                companyInfo.setSort("ip_county");
            }
        }else {
            companyInfo.setSort("ip_name");
        }
        if(companyInfo.getOrder() == null || "".equals(companyInfo.getOrder())){
            companyInfo.setSort("asc");
        }
        result = keyAssetTestingService.getCompanyInfoPage(companyInfo);
        msg = "请求成功";



        return ResultUtil.initResult(result,code,msg);
    }

    /**
     * 下拉框
     * @return
     */
    @RequestMapping("/dropList")
    @ResponseBody
    public Object getDropList(){
        Map<String,Object> result = keyAssetTestingService.getDropList();

        return ResultUtil.success(result);
    }

    /**
     * 资产信息
     *根据公司id查询该公司所有的资产
     * @param keyAssetTestingEntity
     * @return
     */
    @RequestMapping("/keyAssetTesting")
    @ResponseBody
    public Object getgetKeyAssetTesting(KeyAssetTestingEntity keyAssetTestingEntity){

        return ResultUtil.success(keyAssetTestingService.getCompanyInfoById(keyAssetTestingEntity));
    }

    /**
     * 分页查询重点资产信息
     * @param keyAssetTestingEntity
     * @return
     */
    @RequestMapping("/KeyAssetTestingPage")
    @ResponseBody
    public Object getKeyAssetTestingPage(KeyAssetTestingEntity keyAssetTestingEntity){
        if(keyAssetTestingEntity.getPage()>0){//处理分页
            keyAssetTestingEntity.setStart((keyAssetTestingEntity.getPage()-1)*keyAssetTestingEntity.getRows());
        }
        //处理排序
        if(keyAssetTestingEntity.getSort() ==null || "".equals(keyAssetTestingEntity.getSort())){
            keyAssetTestingEntity.setSort("ip");
        }
        if(keyAssetTestingEntity.getOrder() == null || "".equals(keyAssetTestingEntity.getOrder())){
            keyAssetTestingEntity.setSort("asc");
        }
        Page<KeyAssetTestingEntity> result = this.keyAssetTestingService.getKeyAssetTestingEntityPage(keyAssetTestingEntity);
        return ResultUtil.success(result);
    }

    /**
     * 资产信息
     * @param keyAssetTestingEntity
     * @return
     */
//    @RequestMapping("/keyAssetTesting")
    @ResponseBody
    public Object getKeyAssetTesting2(KeyAssetTestingEntity keyAssetTestingEntity){
        if(keyAssetTestingEntity.getPage()>0){//处理分页
            keyAssetTestingEntity.setStart((keyAssetTestingEntity.getPage()-1)*keyAssetTestingEntity.getRows());
        }
        //处理排序
        if(keyAssetTestingEntity.getSort() !=null && !"".equals(keyAssetTestingEntity.getSort())){
            String sort = keyAssetTestingEntity.getSort();
            if("globalIp".equals(sort)){
                keyAssetTestingEntity.setSort("global_ip");
            }else if("domainOs".equals(sort)){
                keyAssetTestingEntity.setSort("domain_os");
            }else if("deviceOs".equals(sort)){
                keyAssetTestingEntity.setSort("device_os");
            }else if("webLinks".equals(sort)){
                keyAssetTestingEntity.setSort("web_links");
            }else if("webDownbytes".equals(sort)){
                keyAssetTestingEntity.setSort("web_downbytes");
            }else if("webRate".equals(sort)){
                keyAssetTestingEntity.setSort("web_rate");
            }
        }else {
            keyAssetTestingEntity.setSort("ip");
        }
        if(keyAssetTestingEntity.getOrder() == null || "".equals(keyAssetTestingEntity.getOrder())){
            keyAssetTestingEntity.setSort("asc");
        }
        Map<String,Object> result = keyAssetTestingService.getKeyAssetTesting(keyAssetTestingEntity);
        return ResultUtil.success(result);
    }

    //导出条数
    @ResponseBody
    @RequestMapping("/getExportCount")
    public Object getExportCount(HttpServletResponse response ,KeyAssetTestingEntity keyAssetTestingEntity){
        long result = keyAssetTestingService.getExportCount(keyAssetTestingEntity);
        return ResultUtil.success(result);
    }

    @ResponseBody
    @RequestMapping("/excelExport")
    public void excelExport(HttpServletResponse response ,KeyAssetTestingEntity keyAssetTestingEntity)throws Exception{
        //处理排序
        if(keyAssetTestingEntity.getSort() !=null && !"".equals(keyAssetTestingEntity.getSort())){
            String sort = keyAssetTestingEntity.getSort();
            if("globalIp".equals(sort)){
                keyAssetTestingEntity.setSort("global_ip");
            }else if("deviceType".equals(sort)){
                keyAssetTestingEntity.setSort("device_type");
            }else if("deviceOs".equals(sort)){
                keyAssetTestingEntity.setSort("device_os");
            }else if("webLinks".equals(sort)){
                keyAssetTestingEntity.setSort("web_links");
            }else if("webDownbytes".equals(sort)){
                keyAssetTestingEntity.setSort("web_downbytes");
            }else if("webRate".equals(sort)){
                keyAssetTestingEntity.setSort("web_rate");
            }
        }else {
            keyAssetTestingEntity.setSort("ip");
        }
        if(keyAssetTestingEntity.getOrder() == null || "".equals(keyAssetTestingEntity.getOrder())){
            keyAssetTestingEntity.setSort("[[asc]]");
        }
        //导出数据
        CompanyInfo companyInfo = keyAssetTestingService.getCompanyInfoById(keyAssetTestingEntity);
        List<KeyAssetTestingEntity> keyAssetTestingEntityList = keyAssetTestingService.getgetKeyAssetTestingEntityList(keyAssetTestingEntity);
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();//整理后数据
        String title ="流量流向监测-重点资产检测";
        Map<String,Object> head1 = new LinkedHashMap<String, Object>();
        head1.put("companyName","单位名称");
        head1.put("county","所属区域");
        head1.put("categories","所属行业");
        head1.put("info","所属IP情况");
        Map<String,Object> row1 = new LinkedHashMap<String, Object>();
        row1.put("companyName",companyInfo.getCompanyName());
        row1.put("county",companyInfo.getCounty());
        row1.put("categories",companyInfo.getCategories());
        row1.put("ips","共有IP"+companyInfo.getIps()+"个 共有网站"+companyInfo.getDomains()+"个");
        list.add(head1);
        list.add(row1);
        Map<String,Object> head2 = new LinkedHashMap<String, Object>();
        head2.put("ip","单位IP");

        head2.put("domain","网站域名");
//        head2.put("globalIp","对公IP");
        head2.put("deviceType","设备类型");
        head2.put("deviceOs","操作系统");
        head2.put("operator","所属运营商");
        head2.put("webLinks","活跃度");
        head2.put("webDownbytes","网站请求量");
        head2.put("webRate","网站响应速度");
        head2.put("sipCnt","访问用户IP数");
        list.add(head2);
        for(KeyAssetTestingEntity keyAssetTesting:keyAssetTestingEntityList){
            Map<String,Object> map = new LinkedHashMap<String, Object>();
            map.put("ip",keyAssetTesting.getIp());
            map.put("domain",keyAssetTesting.getDomain());
//            map.put("globalIp",keyAssetTesting.getGlobalIp());
            map.put("deviceType",keyAssetTesting.getDeviceType());
            map.put("deviceOs",keyAssetTesting.getDeviceOs());
            map.put("operator",keyAssetTesting.getOperator());
            map.put("webLinks",keyAssetTesting.getWebLinks());
            map.put("webDownbytes",keyAssetTesting.getWebDownbytes());
            map.put("webRate",keyAssetTesting.getWebRate());
            map.put("sipCnt",keyAssetTesting.getSipCnt());
            list.add(map);
        }

        ExportUtil.export(response,title,list);
    }

    /**
     * 判断参数是否含有特殊字符
     * @param str
     * @return
     */
    private boolean isPar(String str){
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
       return m.find();
    }

}
