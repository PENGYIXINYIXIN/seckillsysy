package com.qfedu.seckillsysy.pojo;


import java.util.Date;


public class SeckillGoods {

    private Integer sid;
    private Goods goods;
    private Double seckillPrice;
    private Integer seckillStock;
    private Date beginTime;
    private Date endTime;
    private String staticUrl;





    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Integer getSeckillStock() {
        return seckillStock;
    }

    public void setSeckillStock(Integer seckillStock) {
        this.seckillStock = seckillStock;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStaticUrl() {
        return staticUrl;
    }

    public void setStaticUrl(String staticUrl) {
        this.staticUrl = staticUrl;
    }

    @Override
    public String toString() {
        return "SeckillGoods{" +
                "sid=" + sid +
                ", goods=" + goods +
                ", seckillPrice=" + seckillPrice +
                ", seckillStock=" + seckillStock +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", staticUrl='" + staticUrl + '\'' +
                '}';
    }
}
