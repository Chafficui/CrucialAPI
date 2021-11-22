package io.github.chafficui.CrucialAPI.Utils;

import io.github.chafficui.CrucialAPI.Main;
import org.bukkit.Bukkit;

public class Server {
    private static final Main plugin = Main.getPlugin(Main.class);

    public static String getVersion(){
        return Bukkit.getVersion();
    }

    public static boolean checkCompatibility(String... validVersions){
        try {
            for (String validVersion :
                    validVersions) {
                if (Bukkit.getVersion().contains(validVersion)) {
                    return true;
                }
            }
            return false;
        } catch (Exception | Error e){
            error("Error checking compatibility. There might be issues with plugins that use CrucialAPI!");
            return true;
        }
    }

    public static void log(String message){
        plugin.log(message);
    }

    public static void error(String message){
        plugin.error(message);
    }
}
