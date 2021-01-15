package de.crucial.CrucialAPI.Interfaces;

import de.crucial.CrucialAPI.API.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;

import java.util.ArrayList;
import java.util.List;

public class CrucialItem {

    public static final class Builder {
        private static String name = "undefined";
        private static String material = "DIRT";
        private static List<String> lore = new ArrayList<>();
        private static String[] crafting = {"AIR", "AIR", "AIR",
                "AIR", "DIAMOND", "AIR",
                "AIR", "AIR", "AIR"};
        private static String type = "STANDARD";
        private static String id = name + "." + material + ".type";

        private Builder(){

        }

        public Builder name(String name){
            Builder.name = name;
            return this;
        }

        public Builder material(String material){
            Builder.material = material;
            return this;
        }

        public Builder lore(List<String> lore){
            Builder.lore = lore;
            return this;
        }

        public Builder crafting(String[] crafting){
            Builder.crafting = crafting;
            return this;
        }

        public Builder type(String type){
            Builder.type = type;
            return this;
        }

        public CrucialItem build(){
            CrucialItem crucialItem = new CrucialItem();
            crucialItem.name = Builder.name;
            crucialItem.material = Builder.material;
            crucialItem.lore = Builder.lore;
            crucialItem.crafting = Builder.crafting;
            crucialItem.type = Builder.type;
            crucialItem.id = crucialItem.name + "." + crucialItem.material + "." + crucialItem.type;
            crucialItem.namespacedKey = Item.addCustomItemNSK(crucialItem.id, crucialItem.name, crucialItem.lore, crucialItem.material, crucialItem.crafting);
            crucialItem.isRegistered = true;
            return crucialItem;
        }
    }

    protected String name;
    protected String material;
    protected List<String> lore;
    protected String[] crafting;
    protected String id;
    protected String type;
    protected NamespacedKey namespacedKey;
    protected boolean isRegistered;

    private void reload(){
        if(!isRegistered) {
            Bukkit.removeRecipe(namespacedKey);
            namespacedKey = Item.addCustomItemNSK(id, name, lore, material, crafting);
        }
    }

    public void delete(){
        if(isRegistered){
            Bukkit.removeRecipe(namespacedKey);
            isRegistered = false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        reload();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
        reload();
    }

    public List<String> getLore() {
        return lore;
    }

    public void addLore(String text) {
        lore.add(ChatColor.WHITE + text);
        reload();
    }

    public String[] getCrafting() {
        return crafting;
    }

    public void setCrafting(String[] crafting) {
        this.crafting = crafting;
        reload();
    }

    public String getId() {
        return id;
    }

    public NamespacedKey getNamespacedKey() {
        return namespacedKey;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
