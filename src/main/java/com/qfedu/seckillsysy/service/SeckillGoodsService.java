package com.qfedu.seckillsysy.service;

import com.qfedu.seckillsysy.pojo.SeckillGoods;
import com.qfedu.seckillsysy.pojo.User;

import java.util.List;

public interface SeckillGoodsService {
    public List<SeckillGoods> selectAll();
    public SeckillGoods selectById(Integer sid);

    //查秒杀商品的秒杀库存
    public void killStock(Integer sid, User user);


    //url地址
    public  void  updataStaticUrl(String staticUrl,Integer sid);
}
