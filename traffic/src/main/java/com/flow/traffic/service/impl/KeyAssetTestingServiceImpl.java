package com.flow.traffic.service.impl;


import com.flow.traffic.dao.KeyIpDayDao;
import com.flow.traffic.entity.CompanyInfo;
import com.flow.traffic.entity.KeyAssetStatisticEntity;
import com.flow.traffic.entity.KeyAssetTestingEntity;
import com.flow.traffic.service.KeyAssetTestingService;
import com.flow.traffic.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class KeyAssetTestingServiceImpl implements KeyAssetTestingService {
    @Autowired
    private KeyIpDayDao keyIpDayDao;


    public List<CompanyInfo> getCompanyInfoList(CompanyInfo companyInfo) {
        return keyIpDayDao.getCompanyInfoList(companyInfo);
    }

    public KeyAssetStatisticEntity getKeyAssetStatistic(KeyAssetStatisticEntity keyAssetStatisticEntity) {
        return keyIpDayDao.getKeyAssetStatistic(keyAssetStatisticEntity);

    }

    public Map<String, Object> getDropList() {
        return keyIpDayDao.getDropList();
    }


    public Page<CompanyInfo> getCompanyInfoPage(CompanyInfo companyInfo) {
        return keyIpDayDao.getCompanyInfoPage(companyInfo);
    }


    public CompanyInfo getCompanyInfoById(KeyAssetTestingEntity keyAssetTestingEntity) {
        return keyIpDayDao.getCompanyInfoById(keyAssetTestingEntity);
    }

    /**
     * 分页查询加资产详情
     * @param keyAssetTestingEntity
     * @return
     */
    public Map<String, Object> getKeyAssetTesting(KeyAssetTestingEntity keyAssetTestingEntity) {
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            CompanyInfo companyInfo = keyIpDayDao.getCompanyInfoById(keyAssetTestingEntity);//查询资产详细信息
            Page<KeyAssetTestingEntity> KeyAssetTestingPage =  keyIpDayDao.getKeyAssetTestingEntityPage(keyAssetTestingEntity);//分页查询
            result.put("companyInfo",companyInfo);
            result.put("KeyAssetTestingPage",KeyAssetTestingPage);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    public Page<KeyAssetTestingEntity> getKeyAssetTestingEntityPage(KeyAssetTestingEntity keyAssetTestingEntity) {
        return keyIpDayDao.getKeyAssetTestingEntityPage(keyAssetTestingEntity);//分页查询
    }

    public long getExportCount(KeyAssetTestingEntity keyAssetTestingEntity) {
        long resutl =0;
        try {
            CompanyInfo companyInfo = keyIpDayDao.getCompanyInfoById(keyAssetTestingEntity);//查询资产详细信息
            if( companyInfo!=null){
                resutl += 1;

            }
            resutl += keyIpDayDao.getgetKeyAssetTestingEntityCount(keyAssetTestingEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resutl;
    }

    public List<KeyAssetTestingEntity> getgetKeyAssetTestingEntityList(KeyAssetTestingEntity keyAssetTestingEntity) {
        return keyIpDayDao.getgetKeyAssetTestingEntityList(keyAssetTestingEntity);
    }
}
