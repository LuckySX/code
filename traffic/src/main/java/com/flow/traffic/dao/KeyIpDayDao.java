package com.flow.traffic.dao;



import com.flow.traffic.entity.CompanyInfo;
import com.flow.traffic.entity.KeyAssetStatisticEntity;
import com.flow.traffic.entity.KeyAssetTestingEntity;
import com.flow.traffic.util.Page;

import java.util.List;
import java.util.Map;


public interface KeyIpDayDao {
    KeyAssetStatisticEntity getKeyAssetStatistic(KeyAssetStatisticEntity keyAssetStatisticEntity);//查询资产统计
    List<CompanyInfo> getCompanyInfoList(CompanyInfo companyInfo);
    Page<CompanyInfo> getCompanyInfoPage(CompanyInfo companyInfo);
    Map<String,Object> getDropList();
    CompanyInfo getCompanyInfoById(KeyAssetTestingEntity keyAssetTestingEntity);
    Page<KeyAssetTestingEntity> getKeyAssetTestingEntityPage(KeyAssetTestingEntity keyAssetTestingEntity);
    long getgetKeyAssetTestingEntityCount(KeyAssetTestingEntity keyAssetTestingEntity);
    List<KeyAssetTestingEntity> getgetKeyAssetTestingEntityList(KeyAssetTestingEntity keyAssetTestingEntity);

}
