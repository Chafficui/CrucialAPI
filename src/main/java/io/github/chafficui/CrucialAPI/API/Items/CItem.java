package io.github.chafficui.CrucialAPI.API.Items;

import io.github.chafficui.CrucialAPI.Main;
import org.bukkit.Bukkit;

import java.util.ArrayList;

/**
 * @deprecated {will be removed in next version} included in {@link CrucialItem} now.
 */
@Deprecated
public class CItem {
    private static final Main plugin = Main.getPlugin(Main.class);

    /**
     * @deprecated {will be removed in next version} use {@link CrucialItem#getRegisteredByKey(String)} instead.
     * @param name name of the searched CrucialItem
     * @return the searched CrucialItem
     */
    @Deprecated
    public static CrucialItem getCrucialItemByName(String name){
        for (CrucialItem crucialItem:plugin.getCrucialItems()) {
            if (!name.trim().equals(crucialItem.getName().trim()) || !crucialItem.isRegistered()) {
                continue;
            }
            return crucialItem;
        }
        return null;
    }

    /**
     * @deprecated {will be removed in next version} use {@link CrucialItem#getByKey(String)} instead.
     * @param name name of the searched CrucialItem
     * @return the searched CrucialItem
     */
    @Deprecated
    public static CrucialItem getCrucialItemByNameIgnoreRegistration(String name){
        for (CrucialItem crucialItem:plugin.getCrucialItems()) {
            if (name.trim().equals(crucialItem.getName().trim())) {
                return crucialItem;
            }
        }
        return null;
    }

    /**
     * @deprecated {will be removed in next version} use {@link CrucialItem#getRegisteredCrucialItems()} instead.
     * @return all registered CrucialItems
     */
    @Deprecated
    public static ArrayList<CrucialItem> getRegisteredCrucialItems(){
        ArrayList<CrucialItem> registered = new ArrayList<>();
        for (CrucialItem cItem: plugin.getCrucialItems()) {
            if(cItem.isRegistered()){
                registered.add(cItem);
            }
        }
        return registered;
    }

    /**
     * @deprecated {will be removed in next version} use {@link CrucialItem#delete()} instead.
     * @param cItem a CrucialItem Object that shall be deleted.
     */
    @Deprecated
    public static void deleteItem(CrucialItem cItem){
        Bukkit.removeRecipe(cItem.getNamespacedKey());
        getCrucialItems().remove(cItem);
    }

    /**
     * @deprecated {will be removed in next version} use {@link CrucialItem#getCrucialItems()} instead.
     * @return all CrucialItems
     */
    @Deprecated
    public static ArrayList<CrucialItem> getCrucialItems(){
        return plugin.getCrucialItems();
    }

    /**
     * @deprecated {will be removed in next version} it is now included in {@link CrucialItem#CrucialItem(String, String, String)} instead.
     * @param crucialItem a CrucialItem Object that shall be added.
     */
    @Deprecated
    public static void addCrucialItem(CrucialItem crucialItem){
        plugin.getCrucialItems().add(crucialItem);
    }

    /**
     * @deprecated {will be removed in next version}
     * @param crucialItems CrucialItem Objects that shall be added
     */
    @Deprecated
    public static void addCrucialItems(ArrayList<CrucialItem> crucialItems){
        plugin.getCrucialItems().addAll(crucialItems);
    }
}
