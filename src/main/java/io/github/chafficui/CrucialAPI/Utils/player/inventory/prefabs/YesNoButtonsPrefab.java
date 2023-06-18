package io.github.chafficui.CrucialAPI.Utils.player.inventory.prefabs;

import io.github.chafficui.CrucialAPI.Utils.customItems.Stack;
import io.github.chafficui.CrucialAPI.Utils.player.inventory.InventoryItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class YesNoButtonsPrefab implements InventoryItemPrefab {
    private final InventoryItem[] items;

    public YesNoButtonsPrefab(int slotYes, int slotNo, ItemStack yesItem, ItemStack noItem, InventoryItem.Action yesAction, InventoryItem.Action noAction) {
        this.items = new InventoryItem[2];
        this.items[0] = new InventoryItem(slotYes, yesItem, yesAction);
        this.items[1] = new InventoryItem(slotNo, noItem, noAction);
    }

    public YesNoButtonsPrefab(int slotYes, int slotNo, InventoryItem.Action yesAction, InventoryItem.Action noAction) {
        this(slotYes, slotNo, Stack.getStack(Material.GREEN_WOOL, "§aYES"), Stack.getStack(Material.RED_WOOL, "§cNO"), yesAction, noAction);
    }


    @Override
    public InventoryItem[] getItems() {
        return items;
    }
}
