package com.flow.traffic.entity;


import com.flow.traffic.util.BaseEntity;

/**
 * @author 曹大庆
 * @date 2020/4/4 13:17
 */
public class CompanyInfo extends BaseEntity {
    private String companyId;//单位ID
    private String companyName;//单位名称
    private String categories;//行业
    private String county;//区县
    private int ips;//所属IP情况
    private int domains;//共有网站
    private String ip;
    private String domain;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getIps() {
        return ips;
    }

    public void setIps(int ips) {
        this.ips = ips;
    }

    public int getDomains() {
        return domains;
    }

    public void setDomains(int domains) {
        this.domains = domains;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
