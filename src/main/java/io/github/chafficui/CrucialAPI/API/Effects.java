package io.github.chafficui.CrucialAPI.API;

import io.github.chafficui.CrucialAPI.Main;
import io.github.chafficui.CrucialAPI.Utils.BorderUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Effects {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final BorderUtils functions = new BorderUtils(plugin);

    /**
     * @param player a player to receive the blood effect.
     * @param percentage strength of the blood effect.
     */
    public static void setBlood(Player player, int percentage){
        functions.setBorder(player, 100 - percentage);
    }

    /**
     * @bug does not work now and then.
     * @param player a player whose screen is to be cleaned of blood.
     */
    public static void removeBlood(Player player){
        functions.removeBorder(player);
    }

    /**
     * @param player a player to receive the blood effect.
     * @param percentage strength of the blood effect.
     * @param seconds how long the player should receive the blood effect..
     */
    public static void setBlood(Player player, int percentage, float seconds){
        if(seconds > 0){
            setBlood(player, percentage);

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                setBlood(player, 0);
                removeBlood(player);
            }, (long)seconds*20L);
        }
    }

    /**
     * @param player a player to receive particle effects.
     * @param type the type of particles.
     */
    public static void showParticles(Player player, Particle type){
        player.getWorld().spawnParticle(type, player.getEyeLocation(), 50);
    }

    /**
     * @param player a player to receive particle effects.
     * @param type the type of particles.
     * @param amount the amount of particles.
     */
    public static void showParticles(Player player, Particle type, int amount){
        player.getWorld().spawnParticle(type, player.getEyeLocation(), amount);
    }

    /**
     * @param player a player to receive particle effects.
     * @param type the type of particles.
     * @param amount the amount of particles.
     * @param seconds the time particles should be spawned.
     */
    public static void showParticles(Player player, Particle type, int amount, int seconds){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            for (int i = 0; i < seconds*5; i++){
                player.getWorld().spawnParticle(type, player.getEyeLocation(), amount);
                try{
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * @param location a location to spawn particle effects.
     * @param type the type of particles.
     */
    public static void showParticles(Location location, Particle type){
        Objects.requireNonNull(location.getWorld()).spawnParticle(type, location, 50);
    }

    /**
     * @param location a location to spawn particle effects.
     * @param type the type of particles.
     * @param amount the amount of particles.
     */
    public static void showParticles(Location location, Particle type, int amount){
        Objects.requireNonNull(location.getWorld()).spawnParticle(type, location, amount);
    }

    /**
     * @param location a location to spawn particle effects.
     * @param type the type of particles.
     * @param amount the amount of particles.
     * @param seconds the time particles should be spawned.
     */
    public static void showParticles(Location location, Particle type, int amount, int seconds){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            for (int i = 0; i < seconds*5; i++){
                location.getWorld().spawnParticle(type, location, amount);
                try{
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
