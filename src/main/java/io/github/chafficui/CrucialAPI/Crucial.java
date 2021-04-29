package io.github.chafficui.CrucialAPI;

import io.github.chafficui.CrucialAPI.API.Updater;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Crucial {

    private static final Main PLUGIN = Main.getPlugin(Main.class);

    public static void getUpdate(){
        Updater.update(PLUGIN, 86380, PLUGIN.getFileH());
    }

    public static void getVersion(String version, JavaPlugin plugin){
        if(!version.equals(PLUGIN.getDescription().getVersion())) {
            System.out.println(PLUGIN.getDescription().getVersion() + " oder " + version);
            try {
                URL website = new URL("https://github.com/Chafficui/CrucialAPI/releases/download/v" + version + "/CrucialAPI-v" + version + ".jar");
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream("plugins/CrucialAPI.jar");
                fos.getChannel().transferFrom(rbc, 0L, Long.MAX_VALUE);
            } catch (IOException e) {
                PLUGIN.getLogger().severe("Error 28: Failed to update CrucialAPI");
            }
            Bukkit.getPluginManager().disablePlugin(plugin);
            Bukkit.getPluginManager().disablePlugin(PLUGIN);
        }
    }
}
