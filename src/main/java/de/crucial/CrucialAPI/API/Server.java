package de.crucial.CrucialAPI.API;

import org.bukkit.Bukkit;

import java.util.logging.Logger;

public class Server {

    /**
     * Replaced
     * Use checkVersion(String[] valid Version)
     */
    @Deprecated
    public static boolean getVersion(String[] validVersions) {
        for (String validVersion:validVersions) {
            if(Bukkit.getVersion().contains(validVersion)){
                return true;
            }
        }
        return false;
    }

    public static boolean checkVersion(String[] validVersions) {
        for (String validVersion:validVersions) {
            if(Bukkit.getVersion().contains(validVersion)){
                return true;
            }
        }
        return false;
    }

    public static Logger getLogger(String name){
        return Logger.getLogger(name);
    }
}
