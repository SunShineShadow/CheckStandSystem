package com.xuan.cmd.common;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by hx on 2019/8/4
 */
@Getter
@ToString
public enum AccountType {

    ADMIN(1, "管理员"), CUSTOMER(2, "客户");
    private int flag;
    private String desc;

    AccountType(int flag, String desc) {
        this.desc = desc;
        this.flag = flag;
    }

    public static AccountType valueOf(int flag) {
        for (AccountType accountType : values()) {
            if (accountType.flag == flag
                    ) {
                return accountType;
            }
        }
        throw new RuntimeException("accountType flag" + flag + "not found!");
    }
}
