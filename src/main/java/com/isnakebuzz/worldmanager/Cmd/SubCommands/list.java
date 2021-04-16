package com.isnakebuzz.worldmanager.Cmd.SubCommands;

import com.isnakebuzz.worldmanager.Cmd.SubCommand;
import com.isnakebuzz.worldmanager.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

public class list extends SubCommand {
    public list(WorldManager plugin) {
        super(plugin, "List Worlds", "list", "List worlds", "/wm list");
    }

    @Override
    public boolean onSubCommand(String command, CommandSender sender, String[] args) {

        StringBuilder builder = new StringBuilder();

        for (World world : Bukkit.getWorlds()) {
            builder.append("World: ").append(world.getName()).append("\n");
        }

        sender.sendMessage(builder.toString());

        return false;
    }
}
