package com.flow.traffic.dao.impl;


import com.flow.traffic.dao.KeyIpDayDao;
import com.flow.traffic.entity.CompanyInfo;
import com.flow.traffic.entity.KeyAssetStatisticEntity;
import com.flow.traffic.entity.KeyAssetTestingEntity;
import com.flow.traffic.util.BaseDao;
import com.flow.traffic.util.Page;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class KeyIpDayDaoImpl   extends BaseDao implements KeyIpDayDao {

    /**
     * 获取重点资产统计
     * @param keyAssetStatisticEntity
     * @return
     */
    public KeyAssetStatisticEntity getKeyAssetStatistic(KeyAssetStatisticEntity keyAssetStatisticEntity) {
        KeyAssetStatisticEntity result = null;//返回结果
        try {
            //查询
            result = selectObject("com.chanct.keyIpDayMapper.getKeyAssetStatistic",keyAssetStatisticEntity);
            //计算流量换算单位
            long down = 0;
            long up =0;
            if(result.getWebDownbytes() !=null && !"".equals(result.getWebDownbytes())){
                down =Long.parseLong(result.getWebDownbytes());
            }
            if(result.getWebUpbytes() !=null && !"".equals(result.getWebUpbytes())){
                up =Long.parseLong(result.getWebUpbytes());
            }
            result.setSumBytes(this.unitConversion(down+up));
            result.setWebUpbytes(this.unitConversion(up));
            result.setWebDownbytes(this.unitConversion(down));
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return result;
    }

    /**
     * 查询重点单位名称
     * @param companyInfo
     * @return
     */
    public List<CompanyInfo> getCompanyInfoList(CompanyInfo companyInfo) {
        List<CompanyInfo> result = null;
        try{
            result = this.selectList("com.chanct.keyIpDayMapper.getCompanyInfoList",companyInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 分页查询资产名称
     * @param companyInfo
     * @return
     */
    public Page<CompanyInfo> getCompanyInfoPage(CompanyInfo companyInfo) {
        Page<CompanyInfo> result = new Page<CompanyInfo>(companyInfo);
        try {
            long total = this.selectTotal("com.chanct.keyIpDayMapper.getCompanyInfoTotal",companyInfo);
            List<CompanyInfo> rows = this.selectList("com.chanct.keyIpDayMapper.getCompanyInfoList",companyInfo);
            result.setTotal(total);
            result.setRows(rows);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return result;
    }

    /**
     * 获取单位详细信息
     * @param keyAssetTestingEntity
     * @return
     */
    public CompanyInfo getCompanyInfoById(KeyAssetTestingEntity keyAssetTestingEntity) {
        CompanyInfo result = null;
        try{
            result = this.selectObject("com.chanct.keyIpDayMapper.getCompanyInfoById",keyAssetTestingEntity);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return result;
    }

    /**
     * 分页查询
     * @param keyAssetTestingEntity
     * @return
     */
    public Page<KeyAssetTestingEntity> getKeyAssetTestingEntityPage(KeyAssetTestingEntity keyAssetTestingEntity) {
        Page<KeyAssetTestingEntity> result = new Page<KeyAssetTestingEntity>(keyAssetTestingEntity);//列表
        List<KeyAssetTestingEntity> rows = null;//总条数
        long total = 0;
        try{
            total = this.selectTotal("com.chanct.keyIpDayMapper.selectTotal",keyAssetTestingEntity);
            rows = this.selectList("com.chanct.keyIpDayMapper.selectList",keyAssetTestingEntity);
            if(rows !=null && rows.size()>0){
                int orderNum = keyAssetTestingEntity.getStart();
                for(KeyAssetTestingEntity temp:rows){
                    orderNum +=1;
                    temp.setOrderNum(orderNum);
                    long downbytes= Long.parseLong( temp.getWebDownbytes());//请求流量
                    double webRate = Double.parseDouble(temp.getWebRate());//相应速率
                    temp.setWebDownbytes(this.unitConversion(downbytes));//转化请求流量单位
                    temp.setWebRate(this.unitConversion2(webRate));//转化相应速率单位
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        result.setRows(rows);
        result.setTotal(total);
        return result;
    }



    /**
     * 获取下拉菜单
     * @return
     */
    public Map<String,Object> getDropList() {
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            List<String> categories = this.selectList("com.chanct.keyIpDayMapper.getCategories");
            List<String> countis = this.selectList("com.chanct.keyIpDayMapper.getCounties");
            result.put("categories",categories);
            result.put("countis",countis);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return result;
    }


    /**
     * 根据流量大小换算单位
     * @param l
     * @return
     */
    private String unitConversion(long l){
        String result = null;
        DecimalFormat df=new DecimalFormat("#.##");
//        long t = 1024*1024*1024*1024;
        long g = 1024*1024*1024;
        long m = 1024*1024;
        if(l>=g ){
            result= df.format(l/(1024.0*1024*1024))+"GB";
        }else if(l>=m & l<g){
            result= df.format(l/(1024.0*1024))+"MB";
        }else if(l>=1024 & l<m){
            result= df.format(l/(1024.0))+"KB";
        }else {
            result= l+"B";
        }

        return result;
    }

    private String unitConversion2(double l){
        String result = null;
        DecimalFormat df=new DecimalFormat("#.##");
        if(l>=1024*1024*1024){
            result= df.format(l/(1024.0*1024*1024*1024))+"TB/S";
        }else if(l>=1024*1024*1024 & l<1024*1024*1024*1024){
            result= df.format(l/(1024.0*1024*1024))+"GB/S";
        }else if(l>=1024*1024 & l<1024*1024*1024){
            result= df.format(l/(1024.0*1024))+"MB/S";
        }else if(l>=1024 & l<1024*1024){
            result= df.format(l/(1024.0))+"KB/S";
        }else {
            result= l+"B/S";
        }

        return result;
    }

    public long getgetKeyAssetTestingEntityCount(KeyAssetTestingEntity keyAssetTestingEntity) {
        long result =0;
        try {
            result = this.selectTotal("com.chanct.keyIpDayMapper.selectTotal",keyAssetTestingEntity);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return result;
    }

    public List<KeyAssetTestingEntity> getgetKeyAssetTestingEntityList(KeyAssetTestingEntity keyAssetTestingEntity) {
        List<KeyAssetTestingEntity> result = null;//总条数
        try{
            result = this.selectList("com.chanct.keyIpDayMapper.selectList",keyAssetTestingEntity);
            for(KeyAssetTestingEntity temp:result){
                long downbytes= Long.parseLong( temp.getWebDownbytes());//请求流量
                double webRate = Double.parseDouble(temp.getWebRate());//相应速率
                temp.setWebDownbytes(this.unitConversion(downbytes));//转化请求流量单位
                temp.setWebRate(this.unitConversion2(webRate));//转化相应速率单位
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return result;
    }
}
