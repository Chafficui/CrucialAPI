package de.crucial.CrucialAPI.Interfaces;

import de.crucial.CrucialAPI.API.Item;
import de.crucial.CrucialAPI.API.Stack;
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
            crucialItem.name = Builder.name.replaceAll("#space#", " ");
            crucialItem.material = Builder.material;
            crucialItem.lore = Builder.lore;
            crucialItem.crafting = Builder.crafting;
            crucialItem.type = Builder.type;
            crucialItem.id = crucialItem.name + "." + crucialItem.material + "." + crucialItem.type;
            String[] m = crucialItem.material.split(":");
            if(m[0].equals("HEAD")){
                crucialItem.isHead = true;
                crucialItem.material = m[1];
                crucialItem.namespacedKey = Item.addCustomHeadNSK(crucialItem.id, crucialItem.name, crucialItem.lore, crucialItem.material, crucialItem.crafting);
            } else {
                crucialItem.namespacedKey = Item.addCustomItemNSK(crucialItem.id, crucialItem.name, crucialItem.lore, crucialItem.material, crucialItem.crafting);
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

    public void reload(){
        if(!isRegistered) {
            Bukkit.removeRecipe(namespacedKey);
            if(isHead){
                namespacedKey = Item.addCustomHeadNSK(id, name, lore, material, crafting);
            } else {
                namespacedKey = Item.addCustomItemNSK(id, name, lore, material, crafting);
            }
        }
    }

    public void delete(){
        if(isRegistered){
            Bukkit.removeRecipe(namespacedKey);
            isRegistered = false;
        }
    }

    public String toCScript(){
        String cScript = "new Item " + getName().replaceAll(" ", "#space#") + "\n";
        cScript += "material(" + getName() + ", " + getMaterial() + ")\n";
        cScript += "addLore(" + getName() + ", " + getLoreString() + ")\n";
        cScript += "crafting(" + getName() + ", " + getCraftingString() + ")\n";
        cScript += "type(" + getName() + ", " + getType() + ")\n";
        return cScript;
    }

    public ItemStack getItem(){
        if(isHead){
            return Stack.setStack(material, name, lore);
        }
        return Stack.setStack(Objects.requireNonNull(Material.getMaterial(material)), name, lore);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.replaceAll("#space#", " ");
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

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static Builder builder(){
        return new Builder();
    }
}
