package com.flow.traffic.entity;

public class ProtocolsHour {
    private String protocolgroup;//协议分组名
    private int upbytes;//上流量
    private int downbytes;//下流量
    private int links;//连接数
    private String date;//数据所属时间
    public String getProtocolgroup() {
        return protocolgroup;
    }
    public void setProtocolgroup(String protocolgroup) {
        this.protocolgroup = protocolgroup;
    }
    public int getUpbytes() {
        return upbytes;
    }
    public void setUpbytes(int upbytes) {
        this.upbytes = upbytes;
    }
    public int getDownbytes() {
        return downbytes;
    }
    public void setDownbytes(int downbytes) {
        this.downbytes = downbytes;
    }
    public int getLinks() {
        return links;
    }
    public void setLinks(int links) {
        this.links = links;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
