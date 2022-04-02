package io.github.chafficui.CrucialAPI.Utils.api;

import io.github.chafficui.CrucialAPI.Main;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class Bossbar {
    private static final Main PLUGIN = Main.getPlugin(Main.class);

    /**
     * Sends a bossbar for the specified time to a player.
     * @param player The player to send the bossbar to.
     * @param text The text of the bossbar.
     * @param color The color of the bossbar.
     * @param progress The progress of the bossbar. Specified as float in percent.
     * @param ticks The ticks to display the bossbar for.
     */
    public static void sendBossbar(Player player, String text, BarColor color, float progress, long ticks) {
        BossBar bar = Bukkit.createBossBar(text, color, BarStyle.SOLID);
        bar.setProgress(progress/100);
        bar.addPlayer(player);
        Bukkit.getScheduler().runTaskLater(PLUGIN, () -> bar.removePlayer(player), ticks);
    }
}
