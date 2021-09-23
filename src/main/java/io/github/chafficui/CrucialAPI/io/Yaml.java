package io.github.chafficui.CrucialAPI.io;

import io.github.chafficui.CrucialAPI.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Yaml {
    private static final Main plugin = Main.getPlugin(Main.class);

    public static void saveFile(FileConfiguration file, File datafolder, String filename){
        try {
            file.save(new File(datafolder, filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static YamlConfiguration loadFile(File datafolder, String filename){
        File file = new File(datafolder, filename);

        if(!file.exists()){
            file.mkdir();
            try{
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return YamlConfiguration.loadConfiguration(file);
    }
}
