package com.flow.traffic.entity;

public class UserFlowB {
    private String ip;
    private String filing;
    private String upBytes;
    private String downBytes;
    private String sumBytes;
    private String links;
    private String orderNum;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getFiling() {
        return filing;
    }
    public void setFiling(String filing) {
        this.filing = filing;
    }
    public String getUpBytes() {
        return upBytes;
    }
    public void setUpBytes(String upBytes) {
        this.upBytes = upBytes;
    }
    public String getDownBytes() {
        return downBytes;
    }
    public void setDownBytes(String downBytes) {
        this.downBytes = downBytes;
    }
    public String getSumBytes() {
        return sumBytes;
    }
    public void setSumBytes(String sumBytes) {
        this.sumBytes = sumBytes;
    }
    public String getLinks() {
        return links;
    }
    public void setLinks(String links) {
        this.links = links;
    }
    @Override
    public String toString() {
        return "UserFlowB [ipAddress=" + ip + ", filing=" + filing
                + ", upBytes=" + upBytes + ", downBytes=" + downBytes
                + ", sumBytes=" + sumBytes + ", links=" + links + "]";
    }
}
