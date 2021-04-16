package com.isnakebuzz.worldmanager.Cmd.SubCommands;

import com.isnakebuzz.worldmanager.Cmd.SubCommand;
import com.isnakebuzz.worldmanager.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class unload extends SubCommand {

    public unload(WorldManager plugin) {
        super(plugin, "Unload World", "unload", "Unload world", "/wm unload (worldName)");
    }

    @Override
    public boolean onSubCommand(String command, CommandSender sender, String[] args) {
        if (args.length < 2) {
            sendHelp(sender);
            return false;
        }

        String worldName = args[1];

        if (Bukkit.getWorld(worldName) == null) {
            sender.sendMessage(c(String.format("&cWorld &f%s&c not exist.", worldName)));
            return false;
        }

        Bukkit.unloadWorld(worldName, true);
        sender.sendMessage(c(String.format("&aSuccessfully unloaded world &f%s", worldName)));
        return false;
    }
}
