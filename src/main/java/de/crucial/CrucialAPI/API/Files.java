package de.crucial.CrucialAPI.API;

import de.crucial.CrucialAPI.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Files {

    private static Main plugin = Main.getPlugin(Main.class);
    private static Logger logger = Server.getLogger("CrucialAPI");

    public static YamlConfiguration setupYaml(File dataFolder, String path){
        if(!dataFolder.exists()){
            dataFolder.mkdir();
        }

        File file = new File(dataFolder, path);

        if(!file.exists()){
            try{
                file.createNewFile();
                logger.info("Successfully created " + path);
            } catch (IOException e) {
                logger.severe("Error 11: Could not create " + path);
            }
        }

        return YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Will be replaced very soon
     * Don't use
     */
    @Deprecated
    public static void saveYaml(FileConfiguration file, File dataFolder, String path){
        try {
            file.save(new File(dataFolder, path));
        }catch(IOException e) {
            plugin.getLogger().severe("Error 015: Could not save " + path);
        }
    }
}
