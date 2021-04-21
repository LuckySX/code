package com.flow.traffic.util;

/**
 * Author: lfh
 * Version: 1.0
 * Date: 2016/12/30
 * Description: 分页实体
 * Function List:
 */
public class BaseEntity {

    //当前页数
    protected int page = 1;
    //记录数
    protected int rows = 100000000;
    //起始位置
    protected int start = 0;
    //偏移位置
    protected Integer offset;
    //排序字段
    protected String sort = "";
    //排序方式
    protected String order = "desc";
    //开始时间
    protected String startTime = "";
    //结束时间
    protected String endTime = "";
    //批量删除
    protected String ids = "";

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getOffset() {
        return (page - 1) * rows;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
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

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    //在get方法中使用
    public String changePercent(String str){
        if(str!=null)
            str = str.replace("%","\\%");
        return str;
    }

}
