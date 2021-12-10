package io.github.chafficui.CrucialAPI.Utils.player.inventory;

import io.github.chafficui.CrucialAPI.Utils.customItems.Stack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class InventoryItem {
    private final ItemStack stack;
    private final int slot;
    private final Action action;
    public final HashMap<String, Object> extraData = new HashMap<>();

    public InventoryItem(int slot, Material material, String name, List<String> lore, Action action) {
        this.stack = Stack.getStack(material, name, lore);
        this.action = action;
        this.slot = slot;
    }

    public InventoryItem(int slot, ItemStack stack, Action action) {
        this.slot = slot;
        this.stack = stack;
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
