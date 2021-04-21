package com.flow.traffic.entity;

public class UserFlowT {
    private String name;
    private  String value_1;
    private String value_2;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue_1() {
        return value_1;
    }
    public void setValue_1(String value_1) {
        this.value_1 = value_1;
    }
    public String getValue_2() {
        return value_2;
    }
    public void setValue_2(String value_2) {
        this.value_2 = value_2;
    }
    @Override
    public String toString() {
        return "UserFlowT [name=" + name + ", value_1=" + value_1
                + ", value_2=" + value_2 + "]";
    }

}
