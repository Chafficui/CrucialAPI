package io.github.chafficui.CrucialAPI.Utils.player.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;

public class Page {
    public final static ArrayList<Page> pages = new ArrayList<>();

    public static Page get(Inventory inventory) {
        for (Page page : pages) {
            if (page.inventory.equals(inventory)) {
                return page;
            }
        }
        return null;
    }

    public static Page get(int index) {
        return pages.get(index);
    }

    public static boolean exists(Inventory inventory) {
        return get(inventory) != null;
    }

    private final int size;
    private final String title;
    private final ArrayList<InventoryItem> inventoryItems = new ArrayList<>();
    private Inventory inventory;
    public final HashMap<String, Object> extraData = new HashMap<>();

    /**
     * Every child needs to do super() in its constructor.
     *
     * @param size  of the inventory. Can be between 1 and 6.
     * @param title of the inventory.
     */
    public Page(int size, String title) {
        if (size > 6 || size < 1)
            throw new IllegalArgumentException("Size of an inventory can only be between 1 and 6!");
        this.size = size * 9;
        this.title = title;
        pages.add(this);
    }

    public void open(Player player) {
        inventory = Bukkit.createInventory(null, size, title);
        reloadInventory();
        player.openInventory(inventory);
    }

    protected void addItem(InventoryItem item) {
        inventoryItems.add(item);
        reloadInventory();
    }

    public void reloadInventory() {
        if (inventory != null) {
            inventory.clear();
            for (InventoryItem item : inventoryItems) {
                inventory.setItem(item.getSlot(), item.getItem());
            }
        }
    }

    public void click(int slot, Player player) {
        for (InventoryItem item : inventoryItems) {
            if (item.getSlot() == slot) {
                item.execute(this, player);
            }
        }
    }
}
