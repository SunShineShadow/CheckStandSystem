package com.xuan.cmd.Impl.order;

import com.xuan.cmd.Impl.AbstractCommand;
import com.xuan.cmd.Subject;
import com.xuan.cmd.annotation.CommandMeta;
import com.xuan.cmd.annotation.CustomerCommand;
import com.xuan.cmd.common.OrderStatus;
import com.xuan.entity.Goods;
import com.xuan.entity.Order;
import com.xuan.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hx on 2019/8/4
 */
@CommandMeta(
        name = "ZFDD",
        desc = "支付订单",
        group = "订单信息"
)
@CustomerCommand
public class OrderPayCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("请输入你要购买的货物id以及数量"+
        "多个货物之间使用,隔开：格式：1-7,3-5");
        String string = scanner.nextLine();
        String[] strings = string.split(",");
        List<Goods> goodsList = new ArrayList<>();
        for (String goodsString : strings) {
            String[] str = goodsString.split("-");
            Goods goods = this.goodsService.getGoods
                    (Integer.parseInt(str[0]));
            if(Integer.parseInt(str[1])>goods.getStock()){
                System.out.println("商品库存不足！请确认库存后再次下单");
                System.out.println("当前商品库存：" + goods.getStock());
                return;
            }
            goods.setBuyGoodsNum(Integer.parseInt(str[1]));
            goodsList.add(goods);
        }
        Order order = new Order();
        order.setId(String.valueOf(System.currentTimeMillis()));
        order.setAccount_id(subject.getAccount().getId());
        order.setAccount_name(subject.getAccount().getName());
        order.setCreate_time(LocalDateTime.now());

        int totalMoney = 0;
        int actualMoney = 0;

        for (Goods goods : goodsList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderID(order.getId());
            orderItem.setGoodsId(goods.getId());
            orderItem.setGoodsName(goods.getName());
            orderItem.setGoodsIntroduce(goods.getIntroduce());
            orderItem.setGoodsNum(goods.getBuyGoodsNum());
            orderItem.setGoodsUnit(goods.getUnit());
            orderItem.setGoodsPrice(goods.getPrice());
            orderItem.setGoodsDiscount(goods.getDiscount());
            order.orderItemList.add(orderItem);
            int currentMoney = goods.getBuyGoodsNum() * goods.getPrice();
            totalMoney += currentMoney;
            actualMoney += currentMoney * goods.getDiscount() / 100;
        }
        order.setTotal_money(totalMoney);
        order.setActual_amount(actualMoney);
        order.setOrderStatus(OrderStatus.PLAYING);
        //展示当前订单信息
        System.out.println(order);
        System.out.println("请确认是否支付以上订单，确认支付输入：zf");
        String confirm = scanner.next();
        if ("zf".equalsIgnoreCase(confirm)) {
            order.setFinish_time(LocalDateTime.now());
            order.setOrderStatus(OrderStatus.OK);

            boolean effect = this.orderService.commitOrder(order);

            if (effect) {//如果插入订单成功
                for (Goods goods : goodsList) {
                    boolean isUpdate = this.goodsService.
                            updateAfterPay(goods, goods.getBuyGoodsNum());
                    if (isUpdate) {
                        System.out.println("库存更新成功！");
                    }else System.out.println("库存更新失败！");
                }
            }

        }
    }
}
