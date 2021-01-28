package io.github.chafficui.CrucialAPI;

import io.github.chafficui.CrucialAPI.API.CItem;
import io.github.chafficui.CrucialAPI.API.Server;
import io.github.chafficui.CrucialAPI.API.Stats;
import io.github.chafficui.CrucialAPI.Interfaces.CrucialItem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;

public class Main extends JavaPlugin {
    private ArrayList<CrucialItem> crucialItems = new ArrayList<>();

    @Override
    public void onEnable() {
        getLogger().info("Enabled");
        Stats.setMetrics(this, 9549);
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                for (CrucialItem cItem: CItem.getRegisteredCrucialItems()) {
                    cItem.register();
                }
            }
        };
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        getLogger().info("Disabled");
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
