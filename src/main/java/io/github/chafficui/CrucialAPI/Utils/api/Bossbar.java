package io.github.chafficui.CrucialAPI.Utils.api;

import io.github.chafficui.CrucialAPI.Main;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Bossbar {
    private static final Main PLUGIN = Main.getPlugin(Main.class);
    private static final HashMap<Player, Bar> bossbars = new HashMap<>();

    private static class Bar {
        BossBar bar;
        Player player;
        BukkitRunnable runnable;

        public Bar(Player player, BossBar bar, long ticks) {
            this.player = player;
            this.bar = bar;
            runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    bar.removePlayer(player);
                }
            };
            runnable.runTaskLater(PLUGIN, ticks);
        }

        public void newTime(long ticks) {
            runnable.cancel();
            runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    bar.removePlayer(player);
                }
            };
            runnable.runTaskLater(PLUGIN, ticks);
        }
    }

    /**
     * Sends a bossbar for the specified time to a player.
     * @param player The player to send the bossbar to.
     * @param text The text of the bossbar.
     * @param color The color of the bossbar.
     * @param progress The progress of the bossbar. Specified as float in percent.
     * @param ticks The ticks to display the bossbar for.
     */
    public static void sendBossbar(Player player, String text, BarColor color, float progress, long ticks) {
        Bar bar;
        if(bossbars.containsKey(player)) {
            bar = bossbars.get(player);
            bar.bar.setTitle(text);
            bar.bar.setColor(color);
            bar.bar.setStyle(BarStyle.SOLID);
            bar.newTime(ticks);
        } else {
            bar = new Bar(player, Bukkit.createBossBar(text, color, BarStyle.SOLID), ticks);
            bossbars.put(player, bar);
        }
        bar.bar.setProgress(progress/100);
        bar.bar.addPlayer(player);
        bar.bar.setVisible(true);
    }
}
