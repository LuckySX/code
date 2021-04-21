package com.flow.traffic.service;



import com.flow.traffic.entity.CompanyInfo;
import com.flow.traffic.entity.KeyAssetStatisticEntity;
import com.flow.traffic.entity.KeyAssetTestingEntity;
import com.flow.traffic.util.Page;

import java.util.List;
import java.util.Map;

/**
 * @author 曹大庆
 * @date 2020/4/3 3:44
 */
public interface KeyAssetTestingService {
    KeyAssetStatisticEntity getKeyAssetStatistic(KeyAssetStatisticEntity keyAssetStatisticEntity);//查询资产统计
    List<CompanyInfo> getCompanyInfoList(CompanyInfo companyInfo);
    Page<CompanyInfo> getCompanyInfoPage(CompanyInfo companyInfo);
    Map<String,Object> getDropList();
    CompanyInfo getCompanyInfoById(KeyAssetTestingEntity keyAssetTestingEntity);
    Map<String,Object> getKeyAssetTesting(KeyAssetTestingEntity keyAssetTestingEntity);//分页查询加资产详情
    Page<KeyAssetTestingEntity> getKeyAssetTestingEntityPage(KeyAssetTestingEntity keyAssetTestingEntity);
    long getExportCount(KeyAssetTestingEntity keyAssetTestingEntity);
    List<KeyAssetTestingEntity> getgetKeyAssetTestingEntityList(KeyAssetTestingEntity keyAssetTestingEntity);

}
