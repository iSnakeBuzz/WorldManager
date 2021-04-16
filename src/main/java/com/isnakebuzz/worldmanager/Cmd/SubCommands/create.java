package com.isnakebuzz.worldmanager.Cmd.SubCommands;

import com.isnakebuzz.worldmanager.Cmd.SubCommand;
import com.isnakebuzz.worldmanager.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

public class create extends SubCommand {

    public create(WorldManager plugin) {
        super(plugin, "Create World", "create", "Create new world", "/wm &acreate &f(worldName)");
    }

    @Override
    public boolean onSubCommand(String command, CommandSender sender, String[] args) {
        if (args.length < 2) {
            sendHelp(sender);
            return false;
        }

        String worldName = args[1];

        if (Bukkit.getWorld(worldName) != null) {
            sender.sendMessage(c(String.format("&c%s&f already exist.", worldName)));
            return false;
        }

        World world = plugin.getWorldUtils().createWorld(worldName);

        if (world == null) {
            sender.sendMessage(c(String.format("&cError creating world &f%s", worldName)));
            return false;
        }

        sender.sendMessage(c(String.format("&aSuccessfully created world &f%s", worldName)));
        return false;
    }
}
