package de.crucial.CrucialAPI.API;

import de.crucial.CrucialAPI.Main;
import de.crucial.CrucialAPI.Utils.BorderUtils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class Effects {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final BorderUtils functions = new BorderUtils(plugin);

    public static void setBlood(Player player, int percentage){
        functions.setBorder(player, 100 - percentage);
    }

    public static void removeBlood(Player player){
        functions.setBorder(player, 0);
        functions.removeBorder(player);
    }

    public static void setBlood(Player player, int percentage, float seconds){
        if(seconds > 0){
            setBlood(player, percentage);

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                setBlood(player, 0);
                removeBlood(player);
            }, (long) (seconds*20));
        }
    }

    public static void showParticles(Player player, Particle type){
        player.spawnParticle(type, player.getLocation(), 50);
    }

    public static void showParticles(Player player, Particle type, Color color){
        player.spawnParticle(type, player.getLocation(), 50, color);
    }

    public static void showParticles(Player player, Particle type, int amount){
        player.spawnParticle(type, player.getLocation(), amount);
    }

    public static void showParticles(Player player, Particle type, int amount, Color color){
        player.spawnParticle(type, player.getLocation(), amount, color);
    }

    public static void showParticles(Player player, Particle type, int amount, int seconds){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            for (int i = 0; i < seconds*5; i++){
                player.spawnParticle(type, player.getLocation(), amount);
                try{
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void showParticles(Player player, Particle type, int amount, int seconds, Color color){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            for (int i = 0; i < seconds*5; i++){
                player.spawnParticle(type, player.getLocation(), amount, color);
                try{
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
