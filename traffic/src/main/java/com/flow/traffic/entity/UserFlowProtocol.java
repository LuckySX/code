package com.flow.traffic.entity;

public class UserFlowProtocol {
    private String protocol;
    private String date;
    private String upbytes;
    private String downbytes;
    private String links;
    private String sumbytes;
    private String orderNum;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getProtocol() {
        return protocol;
    }
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getUpbytes() {
        return upbytes;
    }
    public void setUpbytes(String upbytes) {
        this.upbytes = upbytes;
    }
    public String getDownbytes() {
        return downbytes;
    }
    public void setDownbytes(String downbytes) {
        this.downbytes = downbytes;
    }
    public String getLinks() {
        return links;
    }
    public void setLinks(String links) {
        this.links = links;
    }
    public String getSumbytes() {
        return sumbytes;
    }
    public void setSumbytes(String sumbytes) {
        this.sumbytes = sumbytes;
    }
    @Override
    public String toString() {
        return "UserFlowProtocol [protocol=" + protocol + ", date=" + date
                + ", upbytes=" + upbytes + ", downbytes=" + downbytes
                + ", links=" + links + ", sumbytes=" + sumbytes + "]";
    }

}
