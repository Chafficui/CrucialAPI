package io.github.chafficui.CrucialAPI.Interfaces;

import io.github.chafficui.CrucialAPI.API.Item;
import io.github.chafficui.CrucialAPI.API.Stack;
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
    private static final Main plugin = Main.getPlugin(Main.class);

    public CrucialItem(String name, String head, String type){
        try {
            this.name = name;
            this.type = type;
            this.isHead = true;
            this.material = head;
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public CrucialItem(String name, Material material, String type){
        try {
            this.name = name;
            this.type = type;
            this.material = material.name();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    /**
     * @deprecated Since v1.2
     */
    @Deprecated
    public CrucialItem(){

    }

    /**
     * @deprecated Since v1.2
     */
    @Deprecated
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

    private String name;
    private String material;
    private List<String> lore = new ArrayList<>();
    private String[] crafting;
    private String type;
    private NamespacedKey namespacedKey;
    private boolean isHead;
    private boolean isRegistered;
    private boolean isUsable;
    private boolean isAllowedForCrafting;

    public boolean isAllowedForCrafting() {
        return isAllowedForCrafting;
    }

    public void setAllowedForCrafting(boolean allowedForCrafting) {
        isAllowedForCrafting = allowedForCrafting;
    }

    public boolean isUsable() {
        return isUsable;
    }

    public void setUsable(boolean usable) {
        isUsable = usable;
    }

    /**
     * @deprecated Since v1.2 | renamed to getKey(ItemStack stack)
     */
    @Deprecated
    public String getId() {
        return type.toLowerCase() + ":" + material.toLowerCase() + "." + name.toLowerCase();
    }

    public String getKey() {
        return type.toLowerCase() + ":" + material.toLowerCase() + "." + name.toLowerCase();
    }

    public void register(){
        if(!isRegistered){
            if(isHead){
                namespacedKey = Item.addCustomHeadNSK(getId(), name, getFixedLore(), material, crafting);
            } else {
                namespacedKey = Item.addCustomItemNSK(getId(), name, getFixedLore(), material, crafting);
            }
            if(namespacedKey != null){
                isRegistered = true;
            } else {
                this.delete();
            }
        }
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

    public void setMaterial(String head) {
        isHead = true;
        material = head;
    }

    public void setMaterial(Material material){
        isHead = false;
        this.material = material.name();
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

    public static ArrayList<CrucialItem> getCrucialItems(){
        return plugin.getCrucialItems();
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