package com.isnakebuzz.worldmanager.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isnakebuzz.worldmanager.Config.Worlds;
import com.isnakebuzz.worldmanager.WorldManager;
import org.bukkit.*;

import java.io.File;
import java.io.IOException;

public class WorldUtils {

    private final WorldManager worldManager;
    private final Gson gson;
    private Worlds worlds;

    public WorldUtils(WorldManager worldManager) {
        this.worldManager = worldManager;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void loadWorlds() {
        if (worldManager.getDataFolder().mkdir()) Console.log("Created WorldManager plugin data folder.");
        File worldsFile = new File(worldManager.getDataFolder(), "worlds.json");

        // If the file do not exist, create a new object.
        if (!worldsFile.exists()) {
            this.worlds = new Worlds();
            return;
        }

        String worldsString = JsonConfig.readFile(worldsFile);
        worlds = gson.fromJson(worldsString, Worlds.class);

        for (String worldName : worlds.getWorlds()) {
            World world = createEmptyWorld(worldName);

            if (world == null) {
                worlds.getWorlds().remove(worldName);
                Console.error(String.format("Error loading %s", worldName));
                continue;
            }

            Console.log(String.format("Successfully loaded %s", worldName));
        }
    }

    public void saveWorlds() throws IOException {
        File worldsFile = new File(worldManager.getDataFolder(), "worlds.json");
        if (!worldsFile.createNewFile()) Console.debug("Created worlds.json file.");

        JsonConfig.writeFile(worldsFile, gson.toJson(worlds));
    }

    public World createWorld(String name) {
        World world = createEmptyWorld(name);

        if (world != null) {
            this.worlds.addWorld(world);
            Console.debug(String.format("Successfully created %s", name));
        }

        return world;
    }

    private World createEmptyWorld(String name) {
        WorldCreator wc = new WorldCreator(name);
        wc.type(WorldType.FLAT);
        wc.generatorSettings("2;0;1;");

        World world = wc.createWorld();

        if (world == null) {
            return null;
        }

        world.setSpawnLocation(0, 30, 0);
        world.getBlockAt(world.getSpawnLocation()).setType(Material.DIAMOND_BLOCK);
        world.setGameRuleValue("doMobSpawning", "false");
        world.setGameRuleValue("doDaylightCycle", "false");
        world.setGameRuleValue("randomTickSpeed", "0");

        world.setTime(6000);

        return world;
    }

    public boolean deleteWorld(String worldName) {
        Bukkit.unloadWorld(worldName, false);
        File file = new File(Bukkit.getWorldContainer(), worldName);

        if (file.exists()) {
            return file.delete();
        }

        worlds.removeWorld(worldName);

        return false;
    }
}
