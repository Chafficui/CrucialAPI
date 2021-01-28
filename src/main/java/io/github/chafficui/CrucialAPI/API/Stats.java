package io.github.chafficui.CrucialAPI.API;

import io.github.chafficui.CrucialAPI.Utils.StatUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class Stats {
    private static StatUtils metrics;

    public static void setMetrics(JavaPlugin plugin, int pluginID){
        metrics = new StatUtils(plugin, pluginID);
    }

    /**
     * Outdated!
     * Use setMetrics(JavaPlugin, int) to connect to Bstats
     * Use addChart(String, String) to add Chart
     */
    @Deprecated
    public static void addChart(int pluginID, JavaPlugin plugin, String name, String data){
        try {
            metrics = new StatUtils(plugin, pluginID);
            metrics.addCustomChart(new StatUtils.SimplePie(name, () -> data));
        } catch(Exception e) {
            Server.getLogger("CrucialAPI").severe("Error 004: Failed to submit stats.");
        }
    }

    public static void addChart(String name, String data){
        try {
            metrics.addCustomChart(new StatUtils.SimplePie(name, () -> data));
        } catch(Exception e) {
            Server.getLogger("CrucialAPI").severe("Error 004: Failed to submit stats.");
        }
    }
}
