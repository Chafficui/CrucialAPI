package io.github.chafficui.CrucialAPI.Utils.player.inventory;

import io.github.chafficui.CrucialAPI.Utils.customItems.Stack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class InventoryItem {
    private final int slot;
    private final Material material;
    private final String name;
    private final Action action;
    private final List<String> lore;
    public final HashMap<String, Object> extraData = new HashMap<>();

    public InventoryItem(int slot, Material material, String name, List<String> lore, Action action) {
        this.slot = slot;
        this.material = material;
        this.name = name;
        this.action = action;
        this.lore = lore;
    }

    public int getSlot() {
        return slot;
    }

    public Material getMaterial() {
        return material;
    }

    public ItemStack getItem() {
        return Stack.getStack(material, name);
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public void execute(InventoryClick click) {
        action.run(click);
    }

    public interface Action {
        void run(InventoryClick click);
    }
}
