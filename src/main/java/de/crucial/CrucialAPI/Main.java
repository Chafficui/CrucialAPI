package de.crucial.CrucialAPI;

import de.crucial.CrucialAPI.API.Server;
import de.crucial.CrucialAPI.API.Stats;
import de.crucial.CrucialAPI.API.Updater;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Server.getLogger("CrucialAPI").info("Enabled");
        Stats.addChart(9549, this, "Name", (String) getServer().getName());
        Updater.update(this, 86380, getFile());
    }

    @Override
    public void onDisable() {
        Server.getLogger("CrucialAPI").info("Disabled");
    }
}
