package com.flow.traffic.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flow.traffic.util.BaseEntity;

import java.io.Serializable;
import java.util.List;

public class TrafficTrendEntity extends BaseEntity implements Serializable {
    @JsonIgnore
    private String name;
    @JsonIgnore
    private String domain;
    @JsonIgnore
    private String time="7";
    @JsonIgnore
    private String type;
    @JsonIgnore
    private String search;
    @JsonIgnore
    private String startTime = "";
    @JsonIgnore
    private String endTime = "";

    private Integer addNum;//新增用户
    private String cnName;//单位名称
    private List dateArr;//时间轴
    private String enName;//域名
    private List flowArr;//访问轴
    private List personArr;//用户轴
    private Double trendNum;//流量数
    private Integer visiteNum;//访问数
    private String unit;//单位
    private String orderNum;//单位名称
    @JsonIgnore
    private String flowStr;
    @JsonIgnore
    private String personStr;
    @JsonIgnore
    private String dateStr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAddNum() {
        return addNum;
    }

    public void setAddNum(Integer addNum) {
        this.addNum = addNum;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public List getDateArr() {
        return dateArr;
    }

    public void setDateArr(List dateArr) {
        this.dateArr = dateArr;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public List getFlowArr() {
        return flowArr;
    }

    public void setFlowArr(List flowArr) {
        this.flowArr = flowArr;
    }

    public List getPersonArr() {
        return personArr;
    }

    public void setPersonArr(List personArr) {
        this.personArr = personArr;
    }

    public Double getTrendNum() {
        return trendNum;
    }

    public void setTrendNum(Double trendNum) {
        this.trendNum = trendNum;
    }

    public Integer getVisiteNum() {
        return visiteNum;
    }

    public void setVisiteNum(Integer visiteNum) {
        this.visiteNum = visiteNum;
    }

    public String getFlowStr() {
        return flowStr;
    }

    public void setFlowStr(String flowStr) {
        this.flowStr = flowStr;
    }

    public String getPersonStr() {
        return personStr;
    }

    public void setPersonStr(String personStr) {
        this.personStr = personStr;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
