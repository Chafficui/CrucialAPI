package io.github.chafficui.CrucialAPI.Utils.localization;

import io.github.chafficui.CrucialAPI.io.Yaml;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocalizedFromYaml extends Localized {
    protected YamlConfiguration yaml;
    private final File dataFolder;
    private final String filePath;
    public LocalizedFromYaml(String identifier, File dataFolder, String filePath) throws IOException {
        super(identifier);
        this.dataFolder = dataFolder;
        this.filePath = filePath;
        yaml = Yaml.loadFile(dataFolder, filePath);
    }

    public void reloadYaml() throws IOException {
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
