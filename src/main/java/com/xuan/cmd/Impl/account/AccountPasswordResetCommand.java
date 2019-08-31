package com.xuan.cmd.Impl.account;

import com.xuan.cmd.Impl.AbstractCommand;
import com.xuan.cmd.Subject;
import com.xuan.cmd.annotation.AdminCommand;
import com.xuan.cmd.annotation.CommandMeta;
import com.xuan.entity.Account;

/**
 * Created by hx on 2019/8/4
 */
@CommandMeta(
        name = "CZMM",
        desc = "重置密码",
        group = "帐号信息"
)
@AdminCommand
public class AccountPasswordResetCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("重置密码");
        AccountBrowserCommand accountBrowserCommand = new AccountBrowserCommand();
        accountBrowserCommand.execute(subject);
        System.out.println("请输入需要重置密码的账户编号：");
        try{
            Integer reset = scanner.nextInt();
            Account account = this.accountService.queryAccountsById(reset);
            System.out.println("请输入新的密码：");
            String newPassword = scanner.next();
            System.out.println("确认是否重置密码：Y/N");
            String confirm = scanner.next();
            if(confirm.equalsIgnoreCase("Y")){
                account.setPassword(newPassword);
                boolean effect = this.accountService.reSetPassword(account);
                if(effect){
                    System.out.println("重置密码成功！新的密码为" + newPassword);
                }else{
                    System.out.println("重置密码失败！");
                }
            }else{
                System.out.println("取消重置密码！");
            }
        }catch (NumberFormatException e){
            System.out.println("输入的格式不符合要求！");
        }
    }
}
