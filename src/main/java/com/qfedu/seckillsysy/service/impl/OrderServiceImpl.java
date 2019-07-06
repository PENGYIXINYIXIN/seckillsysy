package com.qfedu.seckillsysy.service.impl;

import com.qfedu.seckillsysy.dao.OrdersDao;
import com.qfedu.seckillsysy.pojo.Orders;
import com.qfedu.seckillsysy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired(required = false)
    private OrdersDao ordersDao;

    @Override
    public void addOrder(Orders orders) {
        ordersDao.insert(orders);
    }

    @Override
    public List<Orders> findById(Integer uid) {
        return ordersDao.selectAll(uid);
    }
}
