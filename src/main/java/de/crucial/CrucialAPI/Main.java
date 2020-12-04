package de.crucial.CrucialAPI;

import de.crucial.CrucialAPI.API.Files;
import de.crucial.CrucialAPI.API.Server;
import de.crucial.CrucialAPI.API.Stats;
import de.crucial.CrucialAPI.API.Updater;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Server.getLogger("CrucialAPI").info("Enabled");
        Stats.addChart(9549, this, "Name", (String) getServer().getName());
    }

    @Override
    public void onDisable() {
        Updater.update(this, 86380, getFile());
        Server.getLogger("CrucialAPI").info("Disabled");

        FileConfiguration file = Files.setupYaml(getDataFolder(), "lol.yml");
        file.addDefault("hello", "1");
        file.addDefault("bye", "2");
        Files.saveYaml(file, getDataFolder(), "lol.yml");
    }
}
