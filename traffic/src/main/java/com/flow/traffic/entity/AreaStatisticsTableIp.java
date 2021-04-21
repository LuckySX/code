package com.flow.traffic.entity;

public class AreaStatisticsTableIp {
    private String area;
    private String usernum;
    private String hostup;
    private String hostupBps;
    private String hostdown;
    private String hostdownBps;
    private String hostlink;
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getUsernum() {
        return usernum;
    }
    public void setUsernum(String usernum) {
        this.usernum = usernum;
    }
    public String getHostup() {
        return hostup;
    }
    public void setHostup(String hostup) {
        this.hostup = hostup;
    }
    public String getHostdown() {
        return hostdown;
    }
    public void setHostdown(String hostdown) {
        this.hostdown = hostdown;
    }
    public String getHostlink() {
        return hostlink;
    }
    public void setHostlink(String hostlink) {
        this.hostlink = hostlink;
    }
    public String getHostupBps() {
        return hostupBps;
    }
    public void setHostupBps(String hostupBps) {
        this.hostupBps = hostupBps;
    }
    public String getHostdownBps() {
        return hostdownBps;
    }
    public void setHostdownBps(String hostdownBps) {
        this.hostdownBps = hostdownBps;
    }
    @Override
    public String toString() {
        return "AreaStatisticsTableIp [area=" + area + ", usernum=" + usernum
                + ", hostup=" + hostup + ", hostupBps=" + hostupBps
                + ", hostdown=" + hostdown + ", hostdownBps=" + hostdownBps
                + ", hostlink=" + hostlink + "]";
    }


}
