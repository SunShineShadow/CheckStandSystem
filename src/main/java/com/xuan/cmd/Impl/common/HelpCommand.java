package com.xuan.cmd.Impl.common;

import com.xuan.cmd.Command;
import com.xuan.cmd.Commands;
import com.xuan.cmd.Impl.AbstractCommand;
import com.xuan.cmd.Subject;
import com.xuan.cmd.annotation.*;
import com.xuan.entity.Account;

import java.util.*;

/**
 * Created by hx on 2019/8/4
 */
@CommandMeta(
        name = "BZXX",
        desc = "帮助信息",
        group = "公共命令"
)
@AdminCommand
@CustomerCommand
@EntranceCommand
public class HelpCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        Account account = subject.getAccount();
        if (account == null) {
            entranceHelp();
        } else {
            switch (account.getAccountType()) {
                case ADMIN:
                    adminHelp();
                    break;
                case CUSTOMER:
                    customerHelp();
                    break;
                default:
            }
        }
    }

    //Map.values方法会返回所有Value的集合
    public void entranceHelp(){
        print("欢迎", Commands.ENTRANCE_COMMAND.values());
    }
    public void customerHelp(){
        print("客户端", Commands.CUSTOMER_COMMAND.values());
    }
    public void adminHelp(){
        print("管理端", Commands.ADMIN_COMMAND.values());
    }



    public void print(String title, Collection<Command> collections) {
        System.out.println("****************" + title + "******************");

        Map<String, List<String>> helpInfo = new HashMap<>();

        for (Command command : collections) {
            CommandMeta commandMeta =
                    command.getClass().getDeclaredAnnotation(CommandMeta.class);
            String group = commandMeta.group();//Key值
            List<String> func = helpInfo.computeIfAbsent(group, k -> new ArrayList<>());
            func.add(commandMeta.desc() + "(" + commandMeta.name() + ")");
        }
        //entrySet取出键值对集合
        int i = 0;
        for (Map.Entry<String, List<String>> entry : helpInfo.entrySet()) {
            i++;
            System.out.println(i + "." + entry.getKey());
            int j = 0;
            for (String items : entry.getValue()) {
                j++;
                System.out.println("\t" + (i) + "." + (j) + items);
            }
        }
        System.out.println("输入对应指令后面的编号（忽略大小写）进行操作");
        System.out.println("****************************************");
    }

}
