package io.github.chafficui.CrucialAPI.Utils.localization;

public abstract class Localized {
    public abstract String getLocalizedString(String key);

    public Localized(String identifier) {
        Localizer.localizedElements.put(identifier, this);
    }
}
