package com.isnakebuzz.worldmanager.Cmd.SubCommands;

import com.isnakebuzz.worldmanager.Cmd.SubCommand;
import com.isnakebuzz.worldmanager.WorldManager;
import org.bukkit.command.CommandSender;

public class Template extends SubCommand {
    public Template(WorldManager plugin) {
        super(plugin, "", "", "", "");
    }

    @Override
    public boolean onSubCommand(String command, CommandSender sender, String[] args) {
        return false;
    }
}
