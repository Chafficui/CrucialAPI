package io.github.chafficui.CrucialAPI.Utils.api;

import org.bukkit.entity.Player;

public class Title {

    public static void sendTitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        p.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

    /**
     * @deprecated Not functional anymore. Replaced by 
     * {@link io.github.chafficui.CrucialAPI.Utils.player.effects.Interface#setTablistTitle(Player, String[], String[])}
     */
    @Deprecated
    public static void sendTabTitle(Player p, String header, String footer) {}
}
