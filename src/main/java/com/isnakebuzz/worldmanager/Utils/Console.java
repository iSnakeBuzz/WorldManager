package com.isnakebuzz.worldmanager.Utils;

import com.sun.istack.internal.Nullable;
import org.bukkit.Bukkit;

import static com.isnakebuzz.worldmanager.Utils.Statics.c;

public class Console {

    private static String PREFIX = "WorldManager";

    public static void log(String... strings) {
        messageFactor(null, strings);
    }

    public static void info(String... strings) {
        messageFactor("&9INFO", strings);
    }

    public static void warning(String... strings) {
        messageFactor("&6WARNING", strings);
    }

    public static void error(String... strings) {
        messageFactor("&4ERROR", strings);
    }

    public static void debug(String... strings) {
        if (Statics.DEBUG)
            messageFactor("&eDEBUG", strings);
    }

    private static void messageFactor(@Nullable String prefix, String... strings) {
        StringBuilder s = new StringBuilder();
        for (String string : strings) {
            s.append(string).append(" ");
        }

        String message = s.substring(0, s.length() - 1);

        if (prefix == null) {
            Bukkit.getConsoleSender().sendMessage(c(String.format("&8[&a%s&8]&f %s", PREFIX, message)));
        } else {
            Bukkit.getConsoleSender().sendMessage(c(String.format("&8[&a%s&8] [%s&8]&f %s", PREFIX, prefix, message)));
        }

    }

}
