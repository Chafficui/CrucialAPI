package de.crucial.CrucialAPI.API;

import de.crucial.CrucialAPI.Utils.StatUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Callable;

public class Stats {

    public static void addChart(int pluginID, JavaPlugin plugin, String name, String data){
        try {
            StatUtils metrics = new StatUtils(plugin, pluginID);
            metrics.addCustomChart(new StatUtils.SimplePie(name, () -> data));
        } catch(Exception e) {
            Server.getLogger("CrucialAPI").severe("Error 004: Failed to submit stats.");
        }
    }
}
