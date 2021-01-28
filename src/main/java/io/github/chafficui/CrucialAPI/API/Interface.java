package io.github.chafficui.CrucialAPI.API;

import io.github.chafficui.CrucialAPI.Utils.TitleUtils;
import org.bukkit.entity.Player;

public class Interface {

    public static void showText(Player player, String title, String subTitle){
        TitleUtils.sendTitle(player, 10, 40, 10, title, subTitle);
    }

    public static void showText(Player player, String title, String subTitle, int seconds){
        TitleUtils.sendTitle(player, 0, seconds, 0, title, subTitle);
    }

    public static void showText(Player player, String title, String subTitle, int seconds, int fade){
        TitleUtils.sendTitle(player, fade, seconds, fade, title, subTitle);
    }

    public static void showText(Player player, String title, String subTitle, int seconds, int fadeIn, int fadeOut){
        TitleUtils.sendTitle(player, fadeIn, seconds, fadeOut, title, subTitle);
    }

    public static void clearText(Player player){
        TitleUtils.sendTitle(player, 0, 0, 0, "", "");
    }

    public static void setTablistTitle(Player player, String[] header, String[] footer){
        String head = "";
        String foot = "";
        for (String s:header) {
            head = s + "\n";
        }
        for (String s:footer) {
            foot = s + "\n";
        }
        player.setPlayerListHeaderFooter(head, foot);
    }

    public static void removeTablist(Player player){
        player.setPlayerListHeaderFooter(null, null);
    }

    public static void setName(Player player, String name){
        setChatName(player, name);
        setTablistName(player, name);
        setDisplayName(player, name);
    }

    public static void setTablistName(Player player, String name){
        player.setPlayerListName(name);
    }

    public static void setChatName(Player player, String name){
        player.setDisplayName(name);
    }

    public static void setDisplayName(Player player, String name){
        player.setCustomName(name);
        player.setCustomNameVisible(true);
    }
}
