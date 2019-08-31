package com.xuan.cmd.Impl.account;

import com.xuan.cmd.Impl.AbstractCommand;
import com.xuan.cmd.Subject;
import com.xuan.cmd.annotation.AdminCommand;
import com.xuan.cmd.annotation.CommandMeta;
import com.xuan.cmd.common.AccountStatus;
import com.xuan.entity.Account;

/**
 * Created by hx on 2019/8/4
 */
@CommandMeta(
        name = "QTZH",
        desc = "启停账号",
        group = "帐号信息"
)
@AdminCommand
public class AccountStatusSetCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        AccountBrowserCommand accountBrowserCommand = new AccountBrowserCommand();
        System.out.println("启停帐号");
        accountBrowserCommand.execute(subject);
        System.out.println("请输入需要启停的账户编号：");
        try{
            Integer reset = scanner.nextInt();
            Account account = this.accountService.queryAccountsById(reset);
            System.out.println("确认是否启停帐号：Y/N");
            String confirm = scanner.next();
            if(confirm.equalsIgnoreCase("Y")){
                account.setAccountStatus(AccountStatus.LOCK);
                boolean effect = this.accountService.stopAccount(account);
                if(effect){
                    System.out.println("启停帐号成功！");
                }else{
                    System.out.println("启停帐号失败！");
                }
            }else{
                System.out.println("取消启停帐号！");
            }
        }catch (NumberFormatException e){
            System.out.println("输入的格式不符合要求！");
        }
    }
}
