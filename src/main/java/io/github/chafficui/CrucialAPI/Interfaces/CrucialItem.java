package io.github.chafficui.CrucialAPI.Interfaces;

import io.github.chafficui.CrucialAPI.API.CItem;
import io.github.chafficui.CrucialAPI.API.Item;
import io.github.chafficui.CrucialAPI.API.Stack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CrucialItem {

    /**
     * Use register()
    */
    @Deprecated
    public final static class Builder {
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
            crucialItem.name = name;
            crucialItem.material = Builder.material;
            crucialItem.lore = Builder.lore;
            crucialItem.crafting = Builder.crafting;
            crucialItem.type = Builder.type;
            crucialItem.id = crucialItem.name + "." + crucialItem.material + "." + crucialItem.type;
            String[] m = crucialItem.material.split(":");
            if(m[0].equals("HEAD")){
                crucialItem.isHead = true;
                crucialItem.material = m[1];
            }
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
    protected boolean isHead;
    protected boolean isRegistered;

    public void register(){
        try {
            if(!isRegistered) {
                if(isHead){
                    namespacedKey = Item.addCustomHeadNSK(id, name, lore, material, crafting);
                } else {
                    namespacedKey = Item.addCustomItemNSK(id, name, lore, material, crafting);
                }
                CItem.addCrucialItem(this);
            } else {
                throw new IllegalArgumentException("Item already registered");
            }
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public void reload(){
        Bukkit.removeRecipe(namespacedKey);
        if(isHead){
            namespacedKey = Item.addCustomHeadNSK(id, name, lore, material, crafting);
        } else {
            namespacedKey = Item.addCustomItemNSK(id, name, lore, material, crafting);
        }
    }

    public void delete(){
        if(isRegistered){
            Bukkit.removeRecipe(namespacedKey);
            CItem.deleteItem(this);
            isRegistered = false;
        }
    }

    public String toCScript(){
        String cScript = "new Item " + getName().replaceAll(" ", "_") + "\n";
        cScript += "material(" + getName() + ", " + getMaterial() + ")\n";
        cScript += "addLore(" + getName() + ", " + getLoreString() + ")\n";
        cScript += "crafting(" + getName() + ", " + getCraftingString() + ")\n";
        cScript += "type(" + getName() + ", " + getType() + ")\n";
        return cScript;
    }

    public ItemStack get(){
        if(isHead){
            return Stack.setStack(material, name, lore);
        }
        return Stack.setStack(Objects.requireNonNull(Material.getMaterial(material)), name, lore);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        String[] m = material.split(":");
        if(m[0].equals("HEAD")) {
            isHead = true;
            this.material = m[1];
        } else {
            isHead = false;
            this.material = material;
        }
    }

    public List<String> getLore() {
        return lore;
    }

    public String getLoreString(){
        return (String.valueOf(getLore()).substring(1).replace("]", ""));
    }

    public void addLore(String text) {
        lore.add(ChatColor.WHITE + text);
    }

    public String[] getCrafting() {
        return crafting;
    }

    public String getCraftingString(){
        return (Arrays.toString(getCrafting()).substring(1).replace("]", ""));
    }

    public void setCrafting(String[] crafting) {
        this.crafting = crafting;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Use register()
     */
    @Deprecated
    public static Builder builder(){
        return new Builder();
    }
}
