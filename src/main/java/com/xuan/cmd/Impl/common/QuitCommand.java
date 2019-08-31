package com.xuan.cmd.Impl.common;

import com.xuan.cmd.Impl.AbstractCommand;
import com.xuan.cmd.Subject;
import com.xuan.cmd.annotation.AdminCommand;
import com.xuan.cmd.annotation.CommandMeta;
import com.xuan.cmd.annotation.CustomerCommand;
import com.xuan.cmd.annotation.EntranceCommand;

/**
 * Created by hx on 2019/8/4
 */
@CommandMeta(
        name = "TC",
        desc = "退出",
        group = "公共命令"
)
@AdminCommand
@CustomerCommand
@EntranceCommand
public class QuitCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        try {
            System.out.println("*************退出系统，欢迎下次使用************");
            scanner.close();
        } catch (IllegalStateException e) {

        }
    }
}
