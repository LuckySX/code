package com.flow.traffic.entity;

public class ProtocolsDay {
    private String area;//地区
    private String protocol;//协议名
    private long upbytes;//上行流量
    private long downbytes;//下行流量
    private String date;//时间
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getProtocol() {
        return protocol;
    }
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    public long getUpbytes() {
        return upbytes;
    }
    public void setUpbytes(long upbytes) {
        this.upbytes = upbytes;
    }
    public long getDownbytes() {
        return downbytes;
    }
    public void setDownbytes(long downbytes) {
        this.downbytes = downbytes;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
