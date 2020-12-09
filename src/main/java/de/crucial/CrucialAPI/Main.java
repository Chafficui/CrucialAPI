package de.crucial.CrucialAPI;

import de.crucial.CrucialAPI.API.Server;
import de.crucial.CrucialAPI.API.Stats;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Server.getLogger("CrucialAPI").info("Enabled");
        Stats.setMetrics(this, 9549);
    }

    @Override
    public void onDisable() {
        Server.getLogger("CrucialAPI").info("Disabled");
    }

    public File getFileH(){
        return getFile();
    }
}
