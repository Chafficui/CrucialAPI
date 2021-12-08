package io.github.chafficui.CrucialAPI;

import io.github.chafficui.CrucialAPI.Events.CrucialItemEvents;
import io.github.chafficui.CrucialAPI.Utils.Plugin;
import io.github.chafficui.CrucialAPI.Utils.Server;
import io.github.chafficui.CrucialAPI.Utils.Stats;
import io.github.chafficui.CrucialAPI.Utils.player.inventory.InventoryListener;
import io.github.chafficui.CrucialAPI.Utils.player.inventory.Page;
import io.github.chafficui.CrucialAPI.exceptions.CrucialException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public final class Main extends JavaPlugin {
    private final String version = getDescription().getVersion();

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!Server.checkCompatibility("1.15", "1.16", "1.17")) {
            Server.error("Your server is not compatible with this plugin. Please update to 1.15 or 1.16 or 1.17.");
        }
        registerEvents(new CrucialItemEvents(), new InventoryListener());
        new Stats(this, 9549);
        try {
            setupConfig();
        } catch (IOException e) {
            try {
                throw new CrucialException(10);
            } catch (CrucialException ex) {
                ex.printStackTrace();
            }
        }
        Server.log(ChatColor.DARK_GREEN + getDescription().getName() + " is now enabled (Version: " + version + ") made by "
                + ChatColor.AQUA + getDescription().getAuthors() + ".");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getScheduler().cancelTasks(this);
        Server.log(ChatColor.DARK_GREEN + getDescription().getName() + " is now disabled.");
    }

    public File getFile() {
        return super.getFile();
    }

    private void registerEvents(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    public String getVersion() {
        return version;
    }

    /**
     * @deprecated moved to {@link Plugin#getVersion()}
     */
    @Deprecated
    public void downgradeToLegacyVersion(String version) throws CrucialException {
        if(!version.equalsIgnoreCase((this.version))){
            try {
                URL website = new URL("https://github.com/Chafficui/CrucialAPI/releases/download/v" + version + "/CrucialAPI-v" + version + ".jar");
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream("plugins/CrucialAPI.jar");
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
