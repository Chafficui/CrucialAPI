package io.github.chafficui.CrucialAPI.Utils;

import io.github.chafficui.CrucialAPI.Utils.api.BStats;
import io.github.chafficui.CrucialAPI.exceptions.CrucialException;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * An implementation of bstats.org pie charts
 */
public class Stats {
    private static BStats METRICS;
    private final BStats metrics;

    /**
     * @param plugin   is the main class of your project
     * @param bstatsID The unique ID you get at bstats website
     */
    public Stats(JavaPlugin plugin, int bstatsID) {
        this.metrics = new BStats(plugin, bstatsID);
    }

    /**
     * @param name of the pie chart
     * @param data that will be processed in the pie chart
     */
    public void sendPieChart(String name, String data) {
        metrics.addCustomChart(new BStats.SimplePie(name, () -> data));
    }

    /**
     * @param plugin   is the main class of your project
     * @param pluginID The unique ID you get at bstats website
     * @deprecated Use the Stats Object instead
     */
    @Deprecated
    public static void setMetrics(JavaPlugin plugin, int pluginID) {
        METRICS = new BStats(plugin, pluginID);
    }


    /**
     * @deprecated Use the Stats Object instead
     * @param name of the pie chart
     * @param data that will be processed in the pie chart
     * @throws io.github.chafficui.CrucialAPI.exceptions.CrucialException if there was no metrics created before.
     */
    @Deprecated
    public static void addPieChart(String name, String data) throws CrucialException {
        if (METRICS != null) {
            METRICS.addCustomChart(new BStats.SimplePie(name, () -> data));
        } else {
            throw new CrucialException(4);
        }
    }
}
