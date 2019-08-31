package com.xuan.cmd;

import com.xuan.cmd.Impl.AbstractCommand;
import com.xuan.cmd.annotation.CustomerCommand;
import com.xuan.entity.Account;

/**
 * Created by hx on 2019/8/4
 */
public class CheckStand extends AbstractCommand {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new CheckStand().execute(subject);
    }

    @Override
    public void execute(Subject subject){
        try{
            Commands.getCachedHelpCommands().execute(subject);
            while (true) {
                System.out.println(">>>");
                String line = scanner.nextLine();
                String commandCode = line.trim().toUpperCase();
                Account account = subject.getAccount();
                if (account == null) {
                    Commands.getEntranceCommand(commandCode).execute(subject);
                } else {
                    switch (account.getAccountType()) {
                        case ADMIN:
                            Commands.getAdminCommand(commandCode).execute(subject);
                            break;
                        case CUSTOMER:
                            Commands.getCustomerCommand(commandCode).execute(subject);
                            break;
                        default:
                    }
                }
            }
        }catch (IllegalStateException e){
        }
    }
}
