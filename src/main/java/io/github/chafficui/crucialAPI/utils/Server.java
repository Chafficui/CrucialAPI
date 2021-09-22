package io.github.chafficui.crucialAPI.utils;

import io.github.chafficui.crucialAPI.Main;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

public class Server {
    private static final Main plugin = Main.getPlugin(Main.class);

    public static String getVersion(){
        return Bukkit.getVersion();
    }

    public static boolean checkCompatibility(String[] validVersions){
        for (String validVersion :
                validVersions) {
            if (Bukkit.getVersion().contains(validVersion)) {
                return true;
            }
        }
        return false;
    }

    public static void log(String message){
        plugin.log(message);
    }

    public static void error(String message){
        plugin.error(message);
    }
}
