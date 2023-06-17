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

    /**
     * @deprecated Auto updates won't be supported in v2.2 and higher
     */
    @Deprecated
    public static void getVersion(String version, JavaPlugin plugin) throws IOException {
        if(!version.equals(PLUGIN.getDescription().getVersion())) {
            URL website = new URL("https://github.com/Chafficui/CrucialAPI/releases/download/v" + version + "/CrucialAPI-v" + version + ".jar");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("plugins/CrucialAPI.jar");
            fos.getChannel().transferFrom(rbc, 0L, Long.MAX_VALUE);
            Bukkit.reload();
        }
    }
}
