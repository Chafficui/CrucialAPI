package io.github.chafficui.CrucialAPI.Utils;

import io.github.chafficui.CrucialAPI.Utils.api.BStats;
import io.github.chafficui.CrucialAPI.exceptions.CrucialException;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * An implementation of bstats.org pie charts
 */
public class Stats {
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
}
