package de.crucial.CrucialAPI.API;

import de.crucial.CrucialAPI.Main;
import de.crucial.CrucialAPI.Utils.BorderUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Effects {

    private static Main plugin = Main.getPlugin(Main.class);
    private static BorderUtils functions = new BorderUtils(plugin);

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

            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    setBlood(player, 0);
                    removeBlood(player);
                }
            }, (long) (seconds*20));
        }
    }

    //TODO Particles
}
