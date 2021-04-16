package com.isnakebuzz.worldmanager.Cmd.SubCommands;

import com.isnakebuzz.worldmanager.Cmd.SubCommand;
import com.isnakebuzz.worldmanager.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        String worldName = args[1];

        if (Bukkit.getWorld(worldName) == null) {
            sender.sendMessage(c(String.format("&cWorld &f%s&c not exist.", worldName)));
            return false;
        }

        World world = Bukkit.getWorld(worldName);
        player.teleport(world.getSpawnLocation());

        return false;
    }
}
