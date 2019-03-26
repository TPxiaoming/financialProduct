package com.xiaoming.seller.controller;

import com.xiaoming.entity.Order;
import com.xiaoming.seller.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;


/**
 * 订单相关
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 下单
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    public Order apply(@RequestBody Order order){
        LOG.info("申购请求:{}",order);
        order = orderService.apply(order);
        LOG.info("申购结果:{}",order);
        return order;
    }

}
