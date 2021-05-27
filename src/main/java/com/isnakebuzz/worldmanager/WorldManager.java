package com.isnakebuzz.worldmanager;

import com.isnakebuzz.worldmanager.Cmd.Commands;
import com.isnakebuzz.worldmanager.Utils.Console;
import com.isnakebuzz.worldmanager.Utils.WorldUtils;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

@Getter
public final class WorldManager extends JavaPlugin {

    private final WorldUtils worldUtils;

    public WorldManager() {
        this.worldUtils = new WorldUtils(this);
    }

    @Override
    public void onEnable() {
        this.worldUtils.loadWorlds();
        this.getCommand("worldmanager").setExecutor(new Commands(this));
    }

    @Override
    public void onDisable() {
        try {
            this.worldUtils.saveWorlds();
        } catch (IOException e) {
            Console.error(String.format("Error saving worlds. %s", e.getMessage()));
        }
    }
}
