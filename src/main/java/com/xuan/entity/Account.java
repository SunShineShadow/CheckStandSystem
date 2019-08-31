package com.xuan.entity;

import com.xuan.cmd.common.*;
import lombok.Data;

/**
 * Created by hx on 2019/8/4
 */

@Data
public class Account {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private AccountType accountType;
    private AccountStatus accountStatus;
}
