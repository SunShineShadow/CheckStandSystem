package com.xuan.cmd.Impl;

import com.xuan.cmd.*;
import com.xuan.service.*;

/**
 * Created by hx on 2019/8/4
 */
public abstract class AbstractCommand implements Command {
    //启动所有的服务
    public AccountService accountService;
    public GoodsService goodsService;
    public OrderService orderService;
    public AbstractCommand() {
        this.accountService = new AccountService();
        this.goodsService = new GoodsService();
        this.orderService = new OrderService();
    }
}
