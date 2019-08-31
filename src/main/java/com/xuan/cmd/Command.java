package com.xuan.cmd;

import java.util.Scanner;

/**
 * Created by hx on 2019/8/4
 */
public interface Command {
    Scanner scanner = new Scanner(System.in);

    void execute(Subject subject);

}
