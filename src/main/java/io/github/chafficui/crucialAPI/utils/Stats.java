package io.github.chafficui.crucialAPI.utils;

import io.github.chafficui.crucialAPI.exceptions.CrucialException;
import io.github.chafficui.crucialAPI.utils.api.BStats;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * An implementation of bstats.org pie charts
 */
public class Stats {
    private static BStats metrics;

    /**
     * @param plugin is the main class of your project
     * @param pluginID The unique ID you get at bstats website
     */
    public  static void setMetrics(JavaPlugin plugin, int pluginID){
        metrics = new BStats(plugin, pluginID);
    }


    /**
     * @param name of the pie chart
     * @param data that will be processed in the pie chart
     * @throws io.github.chafficui.crucialAPI.exceptions.CrucialException if there was no metrics created before.
     */
    public static void addPieChart(String name, String data) throws CrucialException {
        if(metrics != null){
            metrics.addCustomChart(new BStats.SimplePie(name, () -> data));
        } else {
            throw new CrucialException(4);
        }
    }
}
