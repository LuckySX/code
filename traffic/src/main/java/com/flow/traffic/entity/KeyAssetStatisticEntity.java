package com.flow.traffic.entity;

/**
 * @author 曹大庆
 * @date 2020/4/3 1:50
 * 资产统计情况
 */
public class KeyAssetStatisticEntity  {
    private int assetips;//单位ip数
    private int usedips;//启用ip数
    private String sumBytes;//总流量
    private String domains;//网站数
    private String webDownbytes;//请求流量
    private String webUpbytes;//响应流量
    private int sips;//访问用户ip数

    public int getAssetips() {
        return assetips;
    }

    public void setAssetips(int assetips) {
        this.assetips = assetips;
    }

    public int getUsedips() {
        return usedips;
    }

    public void setUsedips(int usedips) {
        this.usedips = usedips;
    }

    public String getSumBytes() {
        return sumBytes;
    }

    public void setSumBytes(String sumBytes) {
        this.sumBytes = sumBytes;
    }

    public String getDomains() {
        return domains;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public String getWebDownbytes() {
        return webDownbytes;
    }

    public void setWebDownbytes(String webDownbytes) {
        this.webDownbytes = webDownbytes;
    }

    public String getWebUpbytes() {
        return webUpbytes;
    }

    public void setWebUpbytes(String webUpbytes) {
        this.webUpbytes = webUpbytes;
    }

    public int getSips() {
        return sips;
    }

    public void setSips(int sips) {
        this.sips = sips;
    }
}
