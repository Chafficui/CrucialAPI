package io.github.chafficui.CrucialAPI.Utils.localization;

import io.github.chafficui.CrucialAPI.io.Yaml;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocalizedFromYaml extends Localized {
    YamlConfiguration yaml;
    public LocalizedFromYaml(String identifier, File dataFolder, String filePath) throws IOException {
        super(identifier);
        yaml = Yaml.loadFile(dataFolder, filePath);
    }

    @Override
    public String getLocalizedString(String key) {
        String localizedString = yaml.getString(key);
        if (localizedString == null) {
            return "";
        }
        return localizedString;
    }
}
