package io.github.chafficui.crucialAPI.utils.api;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Title {
    private static Class<?> componentClass = null;
    private static Class<?> serializerClass = null;
    private static Constructor<?> packetConstructor = null;
    private static Constructor<?> packetTabConstructor = null;
    private static Class<Enum> enumTitleAction = null;

    public static void sendTitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        tries(p, fadeIn, stay, fadeOut, subtitle);
        tries(p, fadeIn, stay, fadeOut, subtitle);
        if (title != null) {
            Object titleSer;
            Object titlePacket;
            try {
                titleSer = serializerClass.getMethod("a", String.class).invoke(null, "{\"text\": \"" + title + "\"}");
                titlePacket = packetConstructor.newInstance(enumTitleAction.getEnumConstants()[0], titleSer, fadeIn, stay, fadeOut);
                Package.sendPacket(p, titlePacket);
            } catch (IllegalAccessException | InstantiationException | SecurityException | NoSuchMethodException | InvocationTargetException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    private static void tries(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String subtitle) {
        Class<?> packetClass = Package.getNmsClass("PacketPlayOutTitle");
        componentClass = Package.getNmsClass("IChatBaseComponent");
        serializerClass = Package.getNmsClass("IChatBaseComponent$ChatSerializer");
        enumTitleAction = (Class<Enum>) Package.getNmsClass("PacketPlayOutTitle$EnumTitleAction");
        try {
            packetConstructor = packetClass.getConstructor(enumTitleAction, componentClass, int.class, int.class, int.class);
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        if (subtitle != null) {
            Object subTitleSer;
            Object subTitlePacket;
            try {
                subTitleSer = serializerClass.getMethod("a", String.class).invoke(null, "{\"text\": \"" + subtitle + "\"}");
                subTitlePacket = packetConstructor.newInstance(enumTitleAction.getEnumConstants()[1], subTitleSer, fadeIn, stay, fadeOut);
                Package.sendPacket(p, subTitlePacket);
            } catch (IllegalAccessException | SecurityException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                System.out.println(Arrays.toString(enumTitleAction.getEnumConstants()));
                e.printStackTrace();
                System.out.println(Arrays.toString(enumTitleAction.getEnumConstants()));
            } catch (InstantiationException e) {
                e.printStackTrace();
                System.out.println(Arrays.toString(enumTitleAction.getEnumConstants()));
            }
        }
    }

    public static void sendTabTitle(Player p, String header, String footer) {
        Class<?> packetTabClass = Package.getNmsClass("PacketPlayOutPlayerListHeaderFooter");
        componentClass = Package.getNmsClass("IChatBaseComponent");
        serializerClass = Package.getNmsClass("IChatBaseComponent$ChatSerializer");
        try {
            packetTabConstructor = packetTabClass.getConstructor(componentClass);
        } catch (NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        }
        if (header == null) header = "";
        if (footer == null) footer = "";
        Object tabTitle;
        Object tabFoot;
        Object headerPacket;
        try {
            tabTitle = serializerClass.getMethod("a", String.class).invoke(null, "{\"text\": \"" + header + "\"}");
            tabFoot = serializerClass.getMethod("a", String.class).invoke(null, "{\"text\": \"" + footer + "\"}");
            headerPacket = packetTabConstructor.newInstance(tabTitle);
            Field field = headerPacket.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(headerPacket, tabFoot);
            Package.sendPacket(p, headerPacket);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}