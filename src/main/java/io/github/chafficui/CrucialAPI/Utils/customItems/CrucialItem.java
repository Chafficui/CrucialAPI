package io.github.chafficui.CrucialAPI.Utils.customItems;

import com.google.common.collect.Multimap;
import io.github.chafficui.CrucialAPI.exceptions.CrucialException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CrucialItem {
    //Custom Items
    public final static Set<CrucialItem> CRUCIAL_ITEMS = new HashSet<>();

    public static UUID getId(ItemStack stack) {

        if (stack != null && stack.getItemMeta() != null) {
            Multimap<Attribute, AttributeModifier> attributeModifiers = stack.getItemMeta().getAttributeModifiers();
            if (attributeModifiers != null) {
                Collection<AttributeModifier> healthModifiers = attributeModifiers.get(Attribute.GENERIC_MAX_HEALTH);
                if (healthModifiers != null) {
                    for (AttributeModifier modifier : healthModifiers) {
                        if (modifier.getName().equals("CRUCIALITEM_ID")) {
                            return modifier.getUniqueId();
                        }
                    }
                }
            }
        }
        return null;
    }

    public static CrucialItem getByStack(ItemStack stack) {
        UUID id = getId(stack);
        if (id != null) {
            return getById(id);
        }
        return null;
    }

    public static CrucialItem getById(UUID id) {
        for (CrucialItem crucialItem : CRUCIAL_ITEMS) {
            if (crucialItem.id.equals(id)) {
                return crucialItem;
            }
        }
        return null;
    }

    //Custom Item
    protected NamespacedKey namespacedKey;
    protected String name = "";
    protected String material = "";
    protected List<String> lore = new ArrayList<>();
    protected String[] recipe = new String[9];
    protected final UUID id;
    protected final String type;
    protected boolean isRegistered = false;

    public boolean isCraftable = true;
    public boolean isUsable = true;
    public boolean isAllowedForCrafting = false;

    public CrucialItem(String type) {
        this.id = UUID.randomUUID();
        this.type = type;
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
        this.material = material.name();
    }

    public void unregister() {
        if (isRegistered) {
            if (Bukkit.getRecipe(namespacedKey) != null) {
                Bukkit.removeRecipe(namespacedKey);
            }
            CRUCIAL_ITEMS.remove(this);
            isRegistered = false;
        }
    }

    public void register() throws CrucialException {
        if (!isRegistered) {
            if (!CRUCIAL_ITEMS.contains(this)) {
                registerRecipe();
                isRegistered = true;
                CRUCIAL_ITEMS.add(this);
            } else {
                throw new CrucialException(7);
            }
        }
    }

    public void reload() throws CrucialException {
        if (isRegistered) {
            Bukkit.removeRecipe(namespacedKey);
            registerRecipe();
        }
    }

    protected void registerRecipe() throws CrucialException {
        AttributeModifier modifier = new AttributeModifier(this.id, "CRUCIALITEM_ID", 0, AttributeModifier.Operation.ADD_NUMBER);
        namespacedKey = Item.createItem(id + type, name, Stack.addAttributeModifier(Stack.getStack(Material.getMaterial(material), name, lore), Attribute.GENERIC_MAX_HEALTH, modifier), recipe);
    }

    public void delete() {
        if (isRegistered) {
            isRegistered = false;
            Bukkit.removeRecipe(namespacedKey);
            CRUCIAL_ITEMS.remove(this);
        }
    }

    public ItemStack getItemStack() {
        if (isRegistered) {
            AttributeModifier modifier = new AttributeModifier(this.id, "CRUCIALITEM_ID", 0, AttributeModifier.Operation.ADD_NUMBER);
            return Stack.addAttributeModifier(Stack.getStack(Material.getMaterial(material), name, lore), Attribute.GENERIC_MAX_HEALTH, modifier);
        }
        return null;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public String getLore() {
        return String.valueOf(lore);
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

    public boolean isRegistered() {
        return isRegistered;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CrucialItem) {
            CrucialItem crucialItem = (CrucialItem) obj;
            return crucialItem.id == this.id && crucialItem.type.equalsIgnoreCase(this.type);
        }
        return false;
    }
}
