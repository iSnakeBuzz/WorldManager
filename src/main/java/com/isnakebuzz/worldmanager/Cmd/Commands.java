package com.isnakebuzz.worldmanager.Cmd;

import com.google.common.collect.Maps;
import com.isnakebuzz.worldmanager.Cmd.SubCommands.*;
import com.isnakebuzz.worldmanager.WorldManager;
import lombok.SneakyThrows;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class Commands implements CommandExecutor {

    public static Map<String, SubCommand> subCommandMap = Maps.newHashMap();
    private final WorldManager plugin;

    public Commands(WorldManager plugin) {
        this.plugin = plugin;

        new create(plugin);
        new delete(plugin);
        new teleport(plugin);
        new load(plugin);
        new save(plugin);
        new unload(plugin);
        new list(plugin);
    }

    @SneakyThrows
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("wm.admin")) {
            sender.sendMessage(c("&cYou need &ewm.admin&c to use this command."));
            return false;
        }

        if (cmd.getName().equalsIgnoreCase("worldmanager")) {
            if (args.length < 1) {
                sendHelpMSG(sender);
                return true;
            } else {
                String subcmd = args[0].toLowerCase();
                this.execute(sender, subcmd, args);
            }
        }

        return false;
    }

    public void execute(CommandSender sender, String subCmd, String[] args) {
        if (!subCommandMap.containsKey(subCmd)) {
            sendHelpMSG(sender);
            return;
        }
        subCommandMap.get(subCmd).onSubCommand(subCmd, sender, args);
    }

    private void sendHelpMSG(CommandSender p) {
        p.sendMessage(c("WorldManager v" + plugin.getDescription().getVersion()));

        subCommandMap.values().forEach(subCommand -> {
            String format = "&2➠ &f%s &8|&f %s";
            p.sendMessage(c(String.format(format, subCommand.getHelp(), subCommand.getDescription())));
        });

    }


    protected String c(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }


}
