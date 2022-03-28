package io.github.chafficui.CrucialAPI.Utils.localization;

import java.util.HashMap;

public class Localizer {
    public static HashMap<String, Localized> localizedElements = new HashMap<>();

    public static String getLocalizedString(String key, String... values) {
        String[] splitKey = key.split("_");
        Localized localized = localizedElements.get(splitKey[0]);
        if (localized == null) {
            return "";
        }
        //set all splitKey together to a string except the first one
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < splitKey.length; i++) {
            sb.append(splitKey[i]);
        }
        String localizedString = localized.getLocalizedString(sb.toString());
        for (int i = 0; i < values.length; i++) {
            localizedString = localizedString.replace("{" + i + "}", values[i]);
        }
        return localizedString == null ? "" : localizedString;
    }
}
