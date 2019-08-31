package com.xuan.cmd.Impl.entrence;


import com.xuan.cmd.Impl.AbstractCommand;
import com.xuan.cmd.Subject;
import com.xuan.cmd.annotation.*;
import com.xuan.cmd.common.AccountStatus;
import com.xuan.entity.Account;

/**
 * Created by hx on 2019/8/4
 */
@CommandMeta(
        name = "DL",
        desc = "登录",
        group = "入口命令"
)
@EntranceCommand
public class LoginCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        Account account = subject.getAccount();
        if (account != null) {
            System.out.println("您已经登录过");
        }
        System.out.println("输入用户名：");
        String username = scanner.nextLine();
        System.out.println("输入密码：");
        String password = scanner.nextLine();

        account = this.accountService.login(username,password);
        subject.setAccount(account);
        if (account != null && account.getAccountStatus() == AccountStatus.UNLOCK) {
            System.out.println(account.getAccountType() + "登录成功！");
            subject.setAccount(account);
        } else {
            System.out.println("用户名或密码错误！");
        }
    }
}
