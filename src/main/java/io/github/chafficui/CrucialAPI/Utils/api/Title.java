package io.github.chafficui.CrucialAPI.Utils.api;

import org.bukkit.entity.Player;

public class Title {

    public static void sendTitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        p.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }
}
