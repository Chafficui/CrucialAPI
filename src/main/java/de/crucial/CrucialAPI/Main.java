package de.crucial.CrucialAPI;

import de.crucial.CrucialAPI.API.CItem;
import de.crucial.CrucialAPI.API.Server;
import de.crucial.CrucialAPI.API.Stats;
import de.crucial.CrucialAPI.Interfaces.CrucialItem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;

public class Main extends JavaPlugin {
    private ArrayList<CrucialItem> crucialItems = new ArrayList<>();

    @Override
    public void onEnable() {
        Server.getLogger("CrucialAPI").info("Enabled");
        Stats.setMetrics(this, 9549);
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                for (CrucialItem cItem:CItem.getRegisteredCrucialItems()) {
                    cItem.reload();
                }
            }
        };
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        Server.getLogger("CrucialAPI").info("Disabled");
    }

    public File getFileH(){
        return getFile();
    }

    public ArrayList<CrucialItem> getCrucialItems() {
        return crucialItems;
    }

    public void setCrucialItems(ArrayList<CrucialItem> crucialItems) {
        this.crucialItems = crucialItems;
    }
}
