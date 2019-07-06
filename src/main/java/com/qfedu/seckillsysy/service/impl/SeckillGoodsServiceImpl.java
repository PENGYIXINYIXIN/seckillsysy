package com.qfedu.seckillsysy.service.impl;

import com.qfedu.seckillsysy.dao.OrdersDao;
import com.qfedu.seckillsysy.dao.SeckillGoodsDao;
import com.qfedu.seckillsysy.pojo.Orders;
import com.qfedu.seckillsysy.pojo.SeckillGoods;
import com.qfedu.seckillsysy.pojo.User;
import com.qfedu.seckillsysy.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {


    @Autowired(required = false)
    private SeckillGoodsDao seckillGoodsDao;
    @Autowired(required =  false)
    private OrdersDao ordersDao;
    @Override
    public List<SeckillGoods> selectAll() {
        return seckillGoodsDao.selectAll();
    }

    @Override
    public SeckillGoods selectById(Integer sid) {
        return seckillGoodsDao.selectById(sid);
    }

    @Override
    public void killStock(Integer sid, User user) {
       // Integer stock = seckillGoodsDao.selectSeckillStock(sid);

        SeckillGoods seckillGoods = seckillGoodsDao.selectById(sid);
        Integer stock = seckillGoods.getSeckillStock();
        if(stock<=0){
             throw new RuntimeException("已经秒杀完毕");
        }
        //生成订单
        Orders orders =new Orders();
        orders.setTotalPrice(seckillGoods.getSeckillPrice());
        orders.setGoods(seckillGoods.getGoods());
        orders.setUser(user);
        ordersDao.insert(orders);
        //减少库存
        seckillGoodsDao.updaSeckillStock(sid);
    }

    @Override
    public void updataStaticUrl(String staticUrl, Integer sid) {
        seckillGoodsDao.updateStaticUrl(staticUrl,sid);
    }


}
