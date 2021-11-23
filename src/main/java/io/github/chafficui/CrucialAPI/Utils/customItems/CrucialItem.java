package io.github.chafficui.CrucialAPI.Utils.customItems;

import io.github.chafficui.CrucialAPI.exceptions.CrucialException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CrucialItem {
    //Custom Items
    public final static Set<CrucialItem> CRUCIAL_ITEMS = new HashSet<>();

    /**
     * @deprecated Use {@link CrucialItem#getId(ItemStack)} ()} instead.
     */
    @Deprecated
    public static String getKey(ItemStack stack){
        if(stack != null && stack.getItemMeta() != null && stack.getItemMeta().getLore() != null){
            return ChatColor.stripColor(stack.getItemMeta().getLore().get(stack.getItemMeta().getLore().size()-1)).substring(0,36);
        }
        return null;
    }

    public static UUID getId(ItemStack stack){
        if(stack != null && stack.getItemMeta() != null && stack.getItemMeta().getLore() != null){
            return UUID.fromString(ChatColor.stripColor(stack.getItemMeta().getLore().get(stack.getItemMeta().getLore().size()-1)));
        }
        return null;
    }

    public static CrucialItem getByStack(ItemStack stack){
        UUID id = getId(stack);
        if(id != null){
            return getById(id);
        }
        return null;
    }

    public static CrucialItem getById(UUID id){
        for(CrucialItem crucialItem: CRUCIAL_ITEMS){
            if(crucialItem.getId().equals(id)){
                return crucialItem;
            }
        }
        return null;
    }

    /**
     * @deprecated Use {@link CrucialItem#getById(UUID)} ()} instead.
     */
    @Deprecated
    public static CrucialItem getByKey(String key){
        for (CrucialItem crucialItem :
                CRUCIAL_ITEMS) {
            if(crucialItem.getId().equals(UUID.fromString(key.substring(0,36)))){
                return crucialItem;
            }
        }
        return null;
    }

    //Custom Item
    protected NamespacedKey namespacedKey;
    protected String name = "";
    public boolean isHead = false;
    protected String material = "";
    protected List<String> lore = new ArrayList<>();
    protected String[] recipe = new String[9];
    protected final UUID id;
    protected final String type;
    protected boolean isRegistered = false;

    public boolean isCraftable = true;
    public boolean isUsable = true;
    public boolean isAllowedForCrafting = false;

    public CrucialItem(String type){
        this.id = UUID.randomUUID();
        this.type = type;
    }

    public CrucialItem(String name, String head, List<String> lore, String[] recipe, String type, boolean isCraftable, boolean isUsable, boolean isAllowedForCrafting) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.lore = lore;
        this.recipe = recipe;
        this.type = type;
        this.isCraftable = isCraftable;
        this.isUsable = isUsable;
        this.isAllowedForCrafting = isAllowedForCrafting;
        this.isHead = true;
        this.material = head;
    }

    public CrucialItem(String name, Material material, List<String> lore, String[] recipe, String type, boolean isCraftable, boolean isUsable, boolean isAllowedForCrafting) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.lore = lore;
        this.recipe = recipe;
        this.type = type;
        this.isCraftable = isCraftable;
        this.isUsable = isUsable;
        this.isAllowedForCrafting = isAllowedForCrafting;
        this.isHead = false;
        this.material = material.name();
    }

    public void unregister() {
        if(isRegistered) {
            if(Bukkit.getRecipe(namespacedKey) != null){
                Bukkit.removeRecipe(namespacedKey);
            }
            CRUCIAL_ITEMS.remove(this);
            isRegistered = false;
        }
    }

    public void register() throws CrucialException {
        if(!isRegistered){
            if(!CRUCIAL_ITEMS.contains(this) ) {
                if (isHead) {
                    namespacedKey = Item.createHead(id+type, name, getUniqueLore(), material, recipe);
                } else {
                    namespacedKey = Item.createItem(id+type, name, getUniqueLore(), Material.getMaterial(material), recipe);
                }
                isRegistered = true;
                CRUCIAL_ITEMS.add(this);
            } else {
                throw new CrucialException(7);
            }
        }
    }

    public void reload() throws CrucialException {
        if(isRegistered){
            Bukkit.removeRecipe(namespacedKey);
            if(isHead){
                namespacedKey = Item.createHead(id+type, name, getUniqueLore(), material, recipe);
            } else {
                namespacedKey = Item.createItem(id+type, name, getUniqueLore(), Material.getMaterial(material), recipe);
            }
        }
    }

    public void delete(){
        if(isRegistered){
            isRegistered = false;
            Bukkit.removeRecipe(namespacedKey);
            CRUCIAL_ITEMS.remove(this);
        }
    }

    public ItemStack getItemStack(){
        if(isRegistered){
            if(isHead){
                return Stack.getStack(material, name, getUniqueLore());
            } else {
                return Stack.getStack(Material.getMaterial(material), name, getUniqueLore());
            }
        }
        return null;
    }

    public void setLore(List<String> lore){
        this.lore = lore;
    }

    public String getLore(){
        return String.valueOf(lore);
    }

    public List<String> getUniqueLore(){
        List<String> uniqueLore = new ArrayList<>(lore);
        uniqueLore.add("");
        uniqueLore.add(ChatColor.DARK_GRAY + "_______________");
        uniqueLore.add(ChatColor.DARK_GRAY + id.toString() + type);
        return uniqueLore;
    }

    public String getName() {
        return name;
    }

    public CrucialItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getMaterial() {
        return material;
    }

    public CrucialItem setMaterial(String material) {
        this.material = material;
        return this;
    }

    public String[] getRecipe() {
        return recipe;
    }

    public CrucialItem setRecipe(String[] recipe) {
        this.recipe = recipe;
        return this;
    }

    public NamespacedKey getNamespacedKey() {
        return namespacedKey;
    }

    public UUID getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    /**
     * @deprecated Use {@link CrucialItem#getId()} ()} instead.
     */
    @Deprecated
    public String getKey(){
        return id+type;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CrucialItem){
            CrucialItem crucialItem = (CrucialItem) obj;
            return crucialItem.id == this.id && crucialItem.type.equalsIgnoreCase(this.type);
        }
        return false;
    }
}
