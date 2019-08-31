package com.xuan.cmd.Impl.entrence;

import com.xuan.cmd.Impl.AbstractCommand;
import com.xuan.cmd.Subject;
import com.xuan.cmd.annotation.*;
import com.xuan.cmd.common.*;
import com.xuan.entity.Account;

/**
 * Created by hx on 2019/8/4
 */
@CommandMeta(
        name = "ZC",
        desc = "注册",
        group = "入口命令"
)
@EntranceCommand
public class RegisterCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("**********注册**********");
        System.out.println("请输入用户名");
        String username = scanner.nextLine();
        //对比数据库现有用户 检查是否重复
        System.out.println("请输入密码");
        String password1 = scanner.nextLine();
        System.out.println("请确认密码");
        String password2 = scanner.nextLine();
        if (!password1.equals(password2)) {
            System.out.println("两次输入的密码不一致！");
            return;
        }
        System.out.println("请输入姓名：");
        String name = scanner.nextLine();
        System.out.println("请输入账户类型：1.管理员  2.客户");
        int accountTypeFlag = scanner.nextInt();
        AccountType accountType = AccountType.valueOf(accountTypeFlag);
        final Account account = new Account();
        account.setUsername(username);
        account.setPassword(password1);
        account.setAccountType(accountType);
        account.setName(name);
        account.setAccountStatus(AccountStatus.UNLOCK);
        boolean effect = this.accountService.register(account);
        if (effect) {
            System.out.println("恭喜注册成功！");
        }else System.out.println("注册失败，请检查后再次尝试");
    }
}
