package com.isnakebuzz.worldmanager.Cmd.SubCommands;

import com.isnakebuzz.worldmanager.Cmd.SubCommand;
import com.isnakebuzz.worldmanager.WorldManager;
import org.bukkit.command.CommandSender;

public class teleport extends SubCommand {

    public teleport(WorldManager plugin) {
        super(plugin, "Teleport to World", "goto", "Teleport to world", "/wm goto (worldName)");
    }

    @Override
    public boolean onSubCommand(String command, CommandSender sender, String[] args) {
        if (args.length < 2) {
            sendHelp(sender);
            return false;
        }

        String worldName = args[1];

        return false;
    }
}
