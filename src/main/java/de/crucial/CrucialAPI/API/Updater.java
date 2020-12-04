package de.crucial.CrucialAPI.API;

import de.crucial.CrucialAPI.Utils.UpdateUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Updater {

    public static void update(JavaPlugin plugin, int id, File file){
        new UpdateUtils(plugin, id, file, UpdateUtils.UpdateType.CHECK_DOWNLOAD, true);
    }

    public static void forceUpdate(JavaPlugin plugin, int id, File file){
        new UpdateUtils(plugin, id, file, UpdateUtils.UpdateType.DOWNLOAD, true);
    }

    public static void checkUpdate(JavaPlugin plugin, int id, File file){
        new UpdateUtils(plugin, id, file, UpdateUtils.UpdateType.VERSION_CHECK, true);
    }
}
