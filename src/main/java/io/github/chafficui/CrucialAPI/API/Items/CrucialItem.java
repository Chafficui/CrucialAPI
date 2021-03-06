package io.github.chafficui.CrucialAPI.API.Items;

import io.github.chafficui.CrucialAPI.Main;
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
    private static Main plugin = Main.getPlugin(Main.class);

    public final static class Builder {
        private static String name = "undefined";
        private static String material = "DIRT";
        private static List<String> lore = new ArrayList<>();
        private static String[] crafting = {"AIR", "AIR", "AIR",
                "AIR", "DIAMOND", "AIR",
                "AIR", "AIR", "AIR"};
        private static String type = "ITEM";

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
            String[] m = crucialItem.material.split(":");
            if(m[0].equals("HEAD")){
                crucialItem.isHead = true;
                crucialItem.material = m[1];
            }
            return crucialItem;
        }
    }

    protected String name;
    protected String material;
    protected List<String> lore = new ArrayList<>();
    protected String[] crafting;
    protected String type;
    protected NamespacedKey namespacedKey;
    protected boolean isHead;
    protected boolean isRegistered;

    public void register(){
        if(isHead){
            namespacedKey = Item.addCustomHeadNSK(getId(), name, getFixedLore(), material, crafting);
        } else {
            namespacedKey = Item.addCustomItemNSK(getId(), name, getFixedLore(), material, crafting);
        }
        if(namespacedKey != null){
            isRegistered = true;
        } else {
            CItem.deleteItem(this);
        }
    }

    public String getId() {
        return type.toLowerCase() + ":" + material.toLowerCase() + "." + name.toLowerCase();
    }

    public void reload(){
        Bukkit.removeRecipe(namespacedKey);
        if(isHead){
            namespacedKey = Item.addCustomHeadNSK(getId(), name, getFixedLore(), material, crafting);
        } else {
            namespacedKey = Item.addCustomItemNSK(getId(), name, getFixedLore(), material, crafting);
        }
    }

    public void delete(){
        if(isRegistered){
            isRegistered = false;
            Bukkit.removeRecipe(namespacedKey);
        }
    }

    public ItemStack get(){
        if(isHead){
            return Stack.setStack(material, ChatColor.WHITE + name, getFixedLore());
        }
        return Stack.setStack(Objects.requireNonNull(Material.getMaterial(material)), ChatColor.WHITE + name, getFixedLore());
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

    public List<String> getFixedLore() {
        ArrayList<String> fixedLore = new ArrayList<>(lore);
        fixedLore.add("");
        fixedLore.add(ChatColor.DARK_GRAY + "___________________");
        fixedLore.add(ChatColor.DARK_GRAY + getId());
        return fixedLore;
    }

    public static String getKey(ItemStack stack){
        if(stack != null && stack.getItemMeta() != null && stack.getItemMeta().getLore() != null){
            return ChatColor.stripColor(stack.getItemMeta().getLore().get(stack.getItemMeta().getLore().size()-1));
        }
        return null;
    }

    public static CrucialItem getByKey(String key){
        for (CrucialItem crucialItem: plugin.getCrucialItems()) {
            if(key.equals(crucialItem.getId())){
                return crucialItem;
            }
        }
        return null;
    }

    public static CrucialItem getByKey(ItemStack stack){
        for (CrucialItem crucialItem: plugin.getCrucialItems()) {
            if(getKey(stack) != null && getKey(stack).equals(crucialItem.getId())){
                return crucialItem;
            }
        }
        return null;
    }

    public static CrucialItem getRegisteredByKey(String key){
        for (CrucialItem crucialItem: plugin.getCrucialItems()) {
            if(key.equals(crucialItem.getId())){
                if(crucialItem.isRegistered()){
                    return crucialItem;
                }
                return null;
            }
        }
        return null;
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

    @Deprecated
    public static Builder builder(){
        return new Builder();
    }
}
