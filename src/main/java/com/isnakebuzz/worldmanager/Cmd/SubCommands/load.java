package com.isnakebuzz.worldmanager.Cmd.SubCommands;

import com.isnakebuzz.worldmanager.Cmd.SubCommand;
import com.isnakebuzz.worldmanager.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

public class load extends SubCommand {

    public load(WorldManager plugin) {
        super(plugin, "Load World", "load", "Load world", "/wm load (worldName)");
    }

    @Override
    public boolean onSubCommand(String command, CommandSender sender, String[] args) {
        if (args.length < 2) {
            sendHelp(sender);
            return false;
        }

        String worldName = args[1];

        if (Bukkit.getWorld(worldName) != null) {
            sender.sendMessage(c(String.format("&cWorld &f%s&c is already loaded.", worldName)));
            return false;
        }

        World world = plugin.getWorldUtils().createWorld(worldName);

        if (world == null) {
            sender.sendMessage(c(String.format("&cError loading &f%s", worldName)));
            return false;
        }

        sender.sendMessage(c(String.format("&aSuccessfully loaded &f%s", worldName)));

        return false;
    }
}
