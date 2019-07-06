package com.qfedu.seckillsysy.service;

import com.qfedu.seckillsysy.pojo.Orders;

import java.util.List;

public interface OrderService {
    public  void addOrder(Orders orders);

    public List<Orders> findById(Integer uid);
}
