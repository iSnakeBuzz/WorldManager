package com.isnakebuzz.worldmanager.Cmd.SubCommands;

import com.isnakebuzz.worldmanager.Cmd.SubCommand;
import com.isnakebuzz.worldmanager.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class save extends SubCommand {

    public save(WorldManager plugin) {
        super(plugin, "Save World", "save", "Save world", "/wm save (worldName)");
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

        Bukkit.getWorld(worldName).save();
        sender.sendMessage(c(String.format("&aThe world &f%s &ahas been saved.", worldName)));
        return false;
    }
}
