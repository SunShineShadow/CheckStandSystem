package com.xuan.cmd.Impl.common;

import com.xuan.cmd.Impl.AbstractCommand;
import com.xuan.cmd.Subject;
import com.xuan.cmd.annotation.*;

/**
 * Created by hx on 2019/8/4
 */
@CommandMeta(
        name = "GYXT",
        desc = "关于系统",
        group = "公共命令"
)
@AdminCommand
@CustomerCommand
@EntranceCommand
public class AboutCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("**********基于字符界面的收银台系统************");
        System.out.println("*****************2019/8/4********************");
        System.out.println("*****************author:hx*******************");
    }
}
