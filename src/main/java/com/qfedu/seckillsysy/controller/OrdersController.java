package com.qfedu.seckillsysy.controller;

import com.qfedu.seckillsysy.pojo.Orders;
import com.qfedu.seckillsysy.service.OrderService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrdersController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/orders")
    public String orderPage(Integer uid, Model model){
        List<Orders> ordersList = orderService.findById(uid);
        model.addAttribute("ordersList",ordersList);
        return  "order_detail";
    }
}
