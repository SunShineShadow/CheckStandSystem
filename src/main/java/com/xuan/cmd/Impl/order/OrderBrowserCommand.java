package com.xuan.cmd.Impl.order;

import com.xuan.cmd.Impl.AbstractCommand;
import com.xuan.cmd.Subject;
import com.xuan.cmd.annotation.CommandMeta;
import com.xuan.cmd.annotation.CustomerCommand;
import com.xuan.entity.Order;

import java.util.List;

/**
 * Created by hx on 2019/8/4
 */

@CommandMeta(
        name = "LLDD",
        desc = "浏览订单",
        group = "订单信息"
)
@CustomerCommand
public class OrderBrowserCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("我的订单列表：");
        List<Order> orderList = this.orderService.queryOrderByAccount(subject.getAccount().getId());
        if (orderList.isEmpty()) {
            System.out.println("暂无订单");
        } else {
            for (Order order : orderList) {
                System.out.println("-----------------开始分割线-----------------");
                System.out.println(order);
                System.out.println("-----------------结束分割线-----------------");
            }
        }
    }
}
