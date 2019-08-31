package com.xuan.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by hx on 2019/8/4
 */
@Data
public class OrderItem {
    private Integer id;
    private String orderID;
    private Integer goodsId;
    private String goodsName;
    private String goodsIntroduce;
    private Integer goodsNum;
    private String goodsUnit;
    private Integer goodsPrice;
    private Integer goodsDiscount;
}
