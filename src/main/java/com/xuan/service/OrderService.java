package com.xuan.service;

import com.xuan.dao.OrderDao;
import com.xuan.entity.Order;

import java.util.List;

/**
 * Created by hx on 2019/8/12
 */
public class OrderService {
    private OrderDao orderDao;

    public OrderService(){
        this.orderDao = new OrderDao();
    }

    public boolean commitOrder(Order order) {
        return this.orderDao.commitOrder(order);
    }

    public List<Order> queryOrderByAccount(Integer accountId) {
        return this.orderDao.queryOrderByAccount(accountId);
    }
}
