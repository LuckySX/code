package com.flow.traffic.entity;


import com.flow.traffic.util.BaseEntity;

/**
 * @author 曹大庆
 * @date 2020/4/4 21:59
 */
public class KeyAssetTestingEntity extends BaseEntity {
    private String ip;//单位IP
    private String segment;//网段
    private String domain;//网站域名
    private String globalIp;//对公IP
    private String deviceType;//操作类型
    private String deviceOs;//设备操作系统
    private String operator;//所属运营商
    private String webLinks;//活跃度
    private String webDownbytes;//网站请求量
    private String webRate;//网站响应速率b/s
    private String sipCnt;//访问用户IP数
    private String companyId;//单位ID
    private String companyName;//单位名称
    private Integer orderNum;//

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getGlobalIp() {
        return globalIp;
    }

    public void setGlobalIp(String globalIp) {
        this.globalIp = globalIp;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getWebLinks() {
        return webLinks;
    }

    public void setWebLinks(String webLinks) {
        this.webLinks = webLinks;
    }

    public String getWebDownbytes() {
        return webDownbytes;
    }

    public void setWebDownbytes(String webDownbytes) {
        this.webDownbytes = webDownbytes;
    }

    public String getWebRate() {
        return webRate;
    }

    public void setWebRate(String webRate) {
        this.webRate = webRate;
    }

    public String getSipCnt() {
        return sipCnt;
    }

    public void setSipCnt(String sipCnt) {
        this.sipCnt = sipCnt;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
