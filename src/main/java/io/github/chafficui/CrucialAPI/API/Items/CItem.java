package io.github.chafficui.CrucialAPI.API.Items;

import io.github.chafficui.CrucialAPI.Main;

import java.util.ArrayList;

public class CItem {
    private static Main plugin = Main.getPlugin(Main.class);

    @Deprecated
    /**
     * Use CrucialItem.getRegisteredByKey(ItemStack)
     */
    public static CrucialItem getCrucialItemByName(String name){
        for (CrucialItem crucialItem:plugin.getCrucialItems()) {
            if (!name.trim().equals(crucialItem.getName().trim()) || !crucialItem.isRegistered()) {
                continue;
            }
            return crucialItem;
        }
        return null;
    }

    @Deprecated
    /**
     * Use CrucialItem.getByKey(ItemStack)
     */
    public static CrucialItem getCrucialItemByNameIgnoreRegistration(String name){
        for (CrucialItem crucialItem:plugin.getCrucialItems()) {
            if (name.trim().equals(crucialItem.getName().trim())) {
                return crucialItem;
            }
        }
        return null;
    }

    public static ArrayList<CrucialItem> getRegisteredCrucialItems(){
        ArrayList<CrucialItem> registered = new ArrayList<>();
        for (CrucialItem cItem: plugin.getCrucialItems()) {
            if(cItem.isRegistered()){
                registered.add(cItem);
            }
        }
        return registered;
    }

    public static void deleteItem(CrucialItem cItem){
        cItem.delete();
        getCrucialItems().remove(cItem);
    }

    public static ArrayList<CrucialItem> getCrucialItems(){
        return plugin.getCrucialItems();
    }

    public static void addCrucialItem(CrucialItem crucialItem){
        plugin.getCrucialItems().add(crucialItem);
    }

    public static void addCrucialItems(ArrayList<CrucialItem> crucialItems){
        plugin.getCrucialItems().addAll(crucialItems);
    }
}
