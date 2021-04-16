package com.isnakebuzz.worldmanager.Cmd.SubCommands;

import com.isnakebuzz.worldmanager.Cmd.SubCommand;
import com.isnakebuzz.worldmanager.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class delete extends SubCommand {

    public delete(WorldManager plugin) {
        super(plugin, "Delete World", "delete", "Delete world", "/wm &cdelete &f(worldName)");
    }

    @Override
    public boolean onSubCommand(String command, CommandSender sender, String[] args) {
        if (args.length < 2) {
            sendHelp(sender);
            return false;
        }

        String worldName = args[1];

        if (Bukkit.getWorld(worldName) == null) {
            sender.sendMessage(c(String.format("&c%s&f do not exist.", worldName)));
            return false;
        }

        boolean success = plugin.getWorldUtils().deleteWorld(worldName);

        sender.sendMessage(c(
                success ?
                        String.format("Successfully deleted %s.", worldName)
                        :
                        String.format("Error deleting %s.", worldName)
        ));

        return false;
    }
}
