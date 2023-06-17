package io.github.chafficui.CrucialAPI.Utils.player.inventory.prefabs;

import io.github.chafficui.CrucialAPI.Utils.player.inventory.InventoryItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TogglePrefab implements InventoryItemPrefab {
    private final InventoryItem[] items;
    private final boolean isOn;

    public TogglePrefab(int slot, ItemStack onItem, ItemStack offItem, InventoryItem.Action toggleOn, InventoryItem.Action toggleOff, boolean isOn) {
        this.items = new InventoryItem[2];
        this.items[0] = new InventoryItem(slot, onItem, (click) -> {
            toggleOn.run(click);
            click.getPage().removeItem(items[0]);
            click.getPage().addItem(items[1]);
            click.getPage().reloadInventory();
        });
        this.items[1] = new InventoryItem(slot, offItem, (click) -> {
            toggleOff.run(click);
            click.getPage().removeItem(items[1]);
            click.getPage().addItem(items[0]);
            click.getPage().reloadInventory();
        });
        this.isOn = isOn;
    }

    public TogglePrefab(int slot, InventoryItem.Action toggleOn, InventoryItem.Action toggleOff, boolean isOn) {
        this(slot, new ItemStack(Material.GREEN_STAINED_GLASS_PANE), new ItemStack(Material.RED_STAINED_GLASS_PANE), toggleOn, toggleOff, isOn);
    }

    public TogglePrefab(int slot, InventoryItem.Action toggleOn, InventoryItem.Action toggleOff) {
        this(slot, toggleOn, toggleOff, false);
    }


    @Override
    public InventoryItem[] getItems() {
        return new InventoryItem[isOn ? 0 : 1];
    }
}
