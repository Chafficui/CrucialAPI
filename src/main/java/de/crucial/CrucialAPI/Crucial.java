package de.crucial.CrucialAPI;

import de.crucial.CrucialAPI.API.Server;
import de.crucial.CrucialAPI.API.Updater;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;

public class Crucial {

    private static final Main PLUGIN = Main.getPlugin(Main.class);
    private static final Logger LOGGER = Server.getLogger("CrucialAPI");

    public static void getUpdate(){
        Updater.update(PLUGIN, 86380, PLUGIN.getFileH());
    }

    public static void getVersion(String version, JavaPlugin plugin){
        if(version != PLUGIN.getDescription().getVersion()) {
            try {
                URL website = new URL("https://github.com/Chafficui/CrucialAPI/releases/download/v" + version + "/CrucialAPI-v" + version + ".jar");
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream("plugins/CrucialAPI.jar");
                fos.getChannel().transferFrom(rbc, 0L, Long.MAX_VALUE);
            } catch (IOException e) {
                LOGGER.severe("Error 28: Failed to update CrucialAPI");
            }
            Bukkit.getPluginManager().disablePlugin(PLUGIN);
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }
}
