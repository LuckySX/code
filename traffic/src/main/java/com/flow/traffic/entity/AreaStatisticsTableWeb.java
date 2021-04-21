package com.flow.traffic.entity;

public class AreaStatisticsTableWeb {
    private String area;
    private String webnum;
    private String webup;
    private String webupBps;
    private String webdown;
    private String webdownBps;
    private String weblink;
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getWebnum() {
        return webnum;
    }
    public void setWebnum(String webnum) {
        this.webnum = webnum;
    }
    public String getWebup() {
        return webup;
    }
    public void setWebup(String webup) {
        this.webup = webup;
    }
    public String getWebdown() {
        return webdown;
    }
    public void setWebdown(String webdown) {
        this.webdown = webdown;
    }
    public String getWeblink() {
        return weblink;
    }
    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }
    public String getWebupBps() {
        return webupBps;
    }
    public void setWebupBps(String webupBps) {
        this.webupBps = webupBps;
    }
    public String getWebdownBps() {
        return webdownBps;
    }
    public void setWebdownBps(String webdownBps) {
        this.webdownBps = webdownBps;
    }
    @Override
    public String toString() {
        return "AreaStatisticsTableWeb [area=" + area + ", webnum=" + webnum
                + ", webup=" + webup + ", webupBps=" + webupBps + ", webdown="
                + webdown + ", webdownBps=" + webdownBps + ", weblink="
                + weblink + "]";
    }


}
