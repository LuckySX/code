package com.flow.traffic.entity;

public class DataNum {
    private int unIp;
    private int acIp;
    public int getUnIp() {
        return unIp;
    }
    public void setUnIp(int unIp) {
        this.unIp = unIp;
    }
    public int getAcIp() {
        return acIp;
    }
    public void setAcIp(int acIp) {
        this.acIp = acIp;
    }
    @Override
    public String toString() {
        return "DataNum [unIp=" + unIp + ", acIp=" + acIp + "]";
    }

}
