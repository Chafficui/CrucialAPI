package io.github.chafficui.CrucialAPI.API;

import io.github.chafficui.CrucialAPI.Interfaces.CrucialItem;
import io.github.chafficui.CrucialAPI.Main;

import java.util.ArrayList;

public class CItem {
    private static Main plugin = Main.getPlugin(Main.class);

    public static CrucialItem getCrucialItemByName(String name){
        name = name.replaceAll(" ", "_");
        for (CrucialItem crucialItem:plugin.getCrucialItems()) {
            if (!name.trim().equals(crucialItem.getName().trim()) || !crucialItem.isRegistered()) {
                continue;
            }
            return crucialItem;
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
}
