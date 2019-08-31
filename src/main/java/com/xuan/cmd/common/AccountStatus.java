package com.xuan.cmd.common;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by hx on 2019/8/4
 */
@Getter
@ToString
public enum AccountStatus {
    UNLOCK(1,"启用"),LOCK(2,"启停");
    private int flag;
    private String desc;
    AccountStatus(int flag, String desc) {
        this.desc = desc;
        this.flag = flag;
    }
    public static AccountStatus valueOf(int flag) {
        for (AccountStatus accountStatus : values()    ) {
            if (accountStatus.flag == flag) {
                return accountStatus;
            }
        }
        throw new RuntimeException("accountStatus flag" + flag + "not found!");
    }
}
