package io.github.chafficui.CrucialAPI.Utils;

import io.github.chafficui.CrucialAPI.Main;

public class Plugin {
    private static final Main PLUGIN = Main.getPlugin(Main.class);

    public static String getVersion(){
        return PLUGIN.getDescription().getVersion();
    }
}
