package io.github.chafficui.CrucialAPI.Utils;

import io.github.chafficui.CrucialAPI.Main;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Plugin {
    private static final Main PLUGIN = Main.getPlugin(Main.class);

    public static String getVersion(){
        return PLUGIN.getDescription().getVersion();
    }
}
