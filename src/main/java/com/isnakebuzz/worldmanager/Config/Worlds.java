package com.isnakebuzz.worldmanager.Config;

import com.google.common.collect.Sets;
import lombok.Data;
import org.bukkit.World;

import java.util.Set;

@Data
public class Worlds {

    private Set<String> worlds;

    public Worlds() {
        this.worlds = Sets.newConcurrentHashSet();
    }

    public void addWorld(World world) {
        this.worlds.add(world.getName().toLowerCase());
    }

    public void removeWorld(String worldName) {
        this.worlds.remove(worldName.toLowerCase());
    }

}
