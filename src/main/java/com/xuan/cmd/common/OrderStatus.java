package com.xuan.cmd.common;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by hx on 2019/8/4
 */
@Getter
@ToString
public enum OrderStatus {
    PLAYING(1, "待支付"), OK(2, "支付完成");
    private int flag;
    private String desc;

    OrderStatus(int flag, String desc) {
        this.desc = desc;
        this.flag = flag;
    }

    public static OrderStatus valueOf(int flag) {
        for (OrderStatus orderStatus: values()) {
            if (orderStatus.flag == flag
                    ) {
                return orderStatus;
            }
        }
        throw new RuntimeException("orderStatus flag" + flag + "not found!");
    }
}
