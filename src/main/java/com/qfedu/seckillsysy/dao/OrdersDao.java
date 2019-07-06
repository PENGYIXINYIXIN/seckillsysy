package com.qfedu.seckillsysy.dao;

import com.qfedu.seckillsysy.pojo.Orders;

import java.util.List;

public interface OrdersDao {
    public void insert(Orders orders);
    public List<Orders > selectAll(Integer uid);
}
