package io.github.chafficui.crucialAPI;

import io.github.chafficui.crucialAPI.exceptions.CrucialException;
import io.github.chafficui.crucialAPI.utils.Stats;
import io.github.chafficui.crucialAPI.utils.api.Updater;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    private final String version = getDescription().getVersion();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Stats.setMetrics(this, 9549);
        try {
            setupConfig();
        } catch (IOException e) {
            try {
                throw new CrucialException(10);
            } catch (CrucialException ex) {
                ex.printStackTrace();
            }
        }
        /* //maybe later
        if(getConfig().getBoolean("settings.AUTO_UPDATE")){
            new Updater(
                    this,
                    86380,
                    super.getFile(),
                    Updater.UpdateType.CHECK_DOWNLOAD,
                    true
            );
        }
        */
        log(ChatColor.DARK_GREEN + getDescription().getName() + " is now enabled (Version: " + version + ") made by "
                + ChatColor.AQUA + getDescription().getAuthors() + ".");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getScheduler().cancelTasks(this);
        log(ChatColor.DARK_GREEN + getDescription().getName() + " is now disabled.");
    }

    public
    File getFile(){
        return super.getFile();
    }

    public void log(String message){
        Logger.getLogger("CrucialAPI").info(message);
    }

    public void error(String message){
        Logger.getLogger("CrucialAPI").severe(message);
    }

    public String getVersion() {
        return version;
    }

    public void downgradeToLegacyVersion(String version) throws CrucialException {
        if(!version.equalsIgnoreCase((this.version))){
            try {
                URL website = new URL("https://github.com/Chafficui/BSLib/releases/download/v" + version
                        + "/BSLib-v" + version + ".jar");
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream("plugins/BSLib.jar");
                fos.getChannel().transferFrom(rbc, 0L, Long.MAX_VALUE);
            } catch (IOException e) {
                throw new CrucialException(28);
            }
            Bukkit.reload();
        }
    }

    private void setupConfig() throws IOException {
        getConfig().options().header(getDescription().getName() + " (Version: " + version + ") by "
                + getDescription().getAuthors());
        getConfig().addDefault("settings.AUTO_UPDATE", true);
        getConfig().addDefault("settings.SHARE_STATS", true);
        getConfig().options().copyDefaults(true);
        getConfig().save("options.yml");
    }
}
