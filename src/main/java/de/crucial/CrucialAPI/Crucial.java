package de.crucial.CrucialAPI;

import de.crucial.CrucialAPI.API.Server;
import de.crucial.CrucialAPI.API.Updater;
import org.bukkit.Bukkit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;

public class Crucial {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final Logger logger = Server.getLogger("CrucialAPI");

    public static void getUpdate(){
        Updater.update(plugin, 86380, plugin.getFileH());
    }

    public static void getVersion(String version){
        try {
            URL website = new URL("https://github.com/Chafficui/CrucialAPI/releases/download/v" + version + "/CrucialAPI-v" + version + ".jar");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("plugins/CrucialAPI.jar");
            fos.getChannel().transferFrom(rbc, 0L, Long.MAX_VALUE);
        } catch (IOException e) {
            logger.severe("Error 28: Failed to update CrucialAPI");
        }
        Bukkit.getPluginManager().disablePlugin(plugin);
    }
}
