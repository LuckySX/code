package com.flow.traffic.entity;


import com.flow.traffic.util.BaseEntity;

public class QueryCondition extends BaseEntity {
    private String startDate;
    private String endDate;
    private String area;
    private String ip;
    private String[] citysArr;
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }


    public String[] getCitysArr() {
        return citysArr;
    }
    public void setCitysArr(String[] citysArr) {
        this.citysArr = citysArr;
    }
    @Override
    public String toString() {
        return "QueryCondition [startDate=" + startDate + ", endDate="
                + endDate + ", area=" + area + ", ip=" + ip + "]";
    }


}
