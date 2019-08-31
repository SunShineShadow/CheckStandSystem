package com.xuan.cmd.Impl.account;

import com.xuan.cmd.Impl.AbstractCommand;
import com.xuan.cmd.Subject;
import com.xuan.cmd.annotation.AdminCommand;
import com.xuan.cmd.annotation.CommandMeta;
import com.xuan.entity.Account;

import java.util.List;

/**
 * Created by hx on 2019/8/4
 */
@CommandMeta(
        name = "CKZH",
        desc = "查看账号",
        group = "帐号信息"
)
@AdminCommand
public class AccountBrowserCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("查看账户");
        List<Account> accountList = this.accountService.queryAllAcounts();
        if(accountList.isEmpty()){
            System.out.println("系统暂无任何账户！");
        }else{
            System.out.println("------------------账号信息列表---------------------");
            System.out.println("|编号| 姓名 | 账号 |   密码   | 类型 | 状态 |");
            for (Account account: accountList ) {
                String str = "| " + account.getId() + "  " +
                        "| " + account.getName() + " " +
                        "| " + account.getUsername() + " " +
                        "| " + "******" + " " +
                        "| " + account.getAccountType().getDesc() + " " +
                        "| " + account.getAccountStatus().getDesc() + " " +
                        "| ";
                System.out.println(str);
            }
            System.out.println("--------------------------------------------");
        }
    }
}
