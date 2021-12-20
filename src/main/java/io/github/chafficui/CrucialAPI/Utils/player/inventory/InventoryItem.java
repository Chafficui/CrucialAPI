package io.github.chafficui.CrucialAPI.Utils.player.inventory;

import com.google.common.collect.Multimap;
import io.github.chafficui.CrucialAPI.Utils.customItems.Stack;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class InventoryItem {
    public static boolean isInventoryItem(ItemStack stack) {
        if (stack != null && stack.getItemMeta() != null) {
            Multimap<Attribute, AttributeModifier> attributeModifiers = stack.getItemMeta().getAttributeModifiers();
            if (attributeModifiers != null) {
                Collection<AttributeModifier> healthModifiers = attributeModifiers.get(Attribute.GENERIC_MAX_HEALTH);
                if (healthModifiers != null) {
                    for (AttributeModifier modifier : healthModifiers) {
                        if (modifier.getName().equals("CRUCIALAPI_INVENTORYITEM")) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private final ItemStack stack;
    private final int slot;
    private final Action action;
    public final HashMap<String, Object> extraData = new HashMap<>();

    public InventoryItem(int slot, Material material, String name, List<String> lore, Action action) {
        AttributeModifier modifier = new AttributeModifier("CRUCIALAPI_INVENTORYITEM", 0d, AttributeModifier.Operation.ADD_NUMBER);
        this.stack = Stack.addAttributeModifier(Stack.getStack(material, name, lore), Attribute.GENERIC_MAX_HEALTH, modifier);
        this.action = action;
        this.slot = slot;
    }

    public InventoryItem(int slot, ItemStack stack, Action action) {
        this.slot = slot;
        AttributeModifier modifier = new AttributeModifier("CRUCIALAPI_INVENTORYITEM", 0d, AttributeModifier.Operation.ADD_NUMBER);
        this.stack = Stack.addAttributeModifier(stack, Attribute.GENERIC_MAX_HEALTH, modifier);
        this.action = action;
    }

    public int getSlot() {
        return slot;
    }

    public Material getMaterial() {
        return stack.getType();
    }

    public ItemStack getItem() {
        return stack;
    }

    public String getName() {
        ItemMeta meta = stack.getItemMeta();
        if(meta != null) {
            return meta.getDisplayName();
        }
        return "";
    }

    public List<String> getLore() {
        ItemMeta meta = stack.getItemMeta();
        if(meta != null) {
            return meta.getLore();
        }
        return null;
    }

    public void execute(InventoryClick click) {
        action.run(click);
    }

    public interface Action {
        void run(InventoryClick click);
    }
}
