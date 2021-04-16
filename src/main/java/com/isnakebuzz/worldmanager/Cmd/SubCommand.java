package com.isnakebuzz.worldmanager.Cmd;

import com.isnakebuzz.worldmanager.WorldManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@Getter
public abstract class SubCommand {

    protected WorldManager plugin;

    private final String name;
    private final String command;
    private final String description;
    private final String help;

    @Setter
    private String examples;

    protected SubCommand(WorldManager plugin, String name, String command, String description, String help) {
        this.plugin = plugin;

        this.name = name;
        this.command = command;
        this.description = description;
        this.help = help;
        this.examples = "none";

        if (!Commands.subCommandMap.containsKey(command.toLowerCase()))
            Commands.subCommandMap.put(command.toLowerCase(), this);
    }

    public abstract boolean onSubCommand(String command, CommandSender sender, String[] args);

    protected void sendHelp(CommandSender sender) {
        String format = "&6● &f%s &8|&f %s";
        sender.sendMessage(c(String.format(format, this.getHelp(), this.getDescription())));
    }

    protected void sendExamples(CommandSender sender) {
        sender.sendMessage(c(examples));
    }


    protected String c(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
