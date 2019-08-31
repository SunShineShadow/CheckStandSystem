package com.xuan.cmd;

import com.xuan.cmd.Impl.account.*;
import com.xuan.cmd.Impl.common.*;
import com.xuan.cmd.Impl.entrence.*;
import com.xuan.cmd.Impl.goods.*;
import com.xuan.cmd.Impl.order.*;
import com.xuan.cmd.annotation.*;
import java.util.*;

/**
 * Created by hx on 2019/8/4
 * 对命令分类
 */
@AdminCommand
public class Commands {
    public static final Map<String, Command> ADMIN_COMMAND = new HashMap<>();
    public static final Map<String, Command> CUSTOMER_COMMAND = new HashMap<>();
    public static final Map<String, Command> ENTRANCE_COMMAND = new HashMap<>();

    //用来存放所有命令的集合
    private static final Set<Command> COMMANDS = new HashSet<>();

    private static final Command CACHED_HELP_COMMANDS;
    static {
        Collections.addAll(COMMANDS,
                new AccountBrowserCommand(),
                new AccountPasswordResetCommand(),
                new AccountStatusSetCommand(),
                new AboutCommand(),
                //将HelpCommand进行缓存
                CACHED_HELP_COMMANDS = new HelpCommand(),
                new QuitCommand(),
                new LoginCommand(),
                new RegisterCommand(),
                new GoodsBrowserCommand(),
                new GoodsPutAwayCommand(),
                new GoodsSoldOutCommand(),
                new GoodsUpdateCommand(),
                new OrderBrowserCommand(),
                new OrderPayCommand()
        );
        for (Command command : COMMANDS) {
            //利用反射将命令进行分类 到不同的map
            Class<?> cls = command.getClass();
            AdminCommand adminCommand =
                    cls.getDeclaredAnnotation(AdminCommand.class);
            CustomerCommand customerCommand =
                    cls.getDeclaredAnnotation(CustomerCommand.class);
            EntranceCommand entranceCommand =
                    cls.getDeclaredAnnotation(EntranceCommand.class);
            CommandMeta commandMeta =
                    cls.getDeclaredAnnotation(CommandMeta.class);

            if (commandMeta == null) {
                continue;
            }
            String commandKey = commandMeta.name();
            if (adminCommand != null) {
                ADMIN_COMMAND.put(commandKey, command);
            }
            if (customerCommand != null) {
                CUSTOMER_COMMAND.put(commandKey, command);
            }
            if (entranceCommand != null) {
                ENTRANCE_COMMAND.put(commandKey, command);
            }
        }
    }
    //得到缓存的命令
    public static Command getCachedHelpCommands(){
        return CACHED_HELP_COMMANDS;
    }

    public static Command getAdminCommand(String commandKey) {
        return getCommand(commandKey,ADMIN_COMMAND);
    }

    public static Command getCustomerCommand(String commandKey) {
        return getCommand(commandKey, CUSTOMER_COMMAND);
    }

    public static Command getEntranceCommand(String commandKey) {
        return getCommand(commandKey, ENTRANCE_COMMAND);
    }

    public static Command getCommand(String commandKey, Map<String, Command> commandMap) {
        //遍历相应的map，根据CommandKey得到对应的Value值
        return commandMap.getOrDefault(commandKey, CACHED_HELP_COMMANDS);
    }
}
