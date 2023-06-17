package io.github.chafficui.CrucialAPI.Utils.player.inventory;

import io.github.chafficui.CrucialAPI.Utils.player.inventory.prefabs.InventoryItemPrefab;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
    protected ArrayList<InventoryItem> inventoryItems = new ArrayList<>();
    private Inventory inventory;
    public final HashMap<String, Object> extraData = new HashMap<>();
    private final Material fillMaterial;
    protected boolean isMovable;

    /**
     * Every child needs to do super() in its constructor.
     *
     * @param size  of the inventory. Can be between 1 and 6.
     * @param title of the inventory.
     */
    public Page(int size, String title, Material fillMaterial) {
        if (size > 6 || size < 1)
            throw new IllegalArgumentException("Size of an inventory can only be between 1 and 6!");
        this.size = size * 9;
        this.title = title;
        this.fillMaterial = fillMaterial;
        pages.add(this);
    }


    public void populate() {
        addItem(new InventoryItem(0, new ItemStack(Material.RED_STAINED_GLASS_PANE), click -> click.getPlayer().closeInventory()));
    }

    public void open(Player player) {
        inventory = Bukkit.createInventory(null, size, title);
        reloadInventory();
        player.openInventory(inventory);
    }

    public void addItem(InventoryItem item) {
        inventoryItems.add(item);
    }

    public void addItems(InventoryItem... items) {
        for(InventoryItem item : items) {
            addItem(item);
        }
    }

    public void addItems(InventoryItemPrefab items) {
        for(InventoryItem item : items.getItems()) {
            addItem(item);
        }
    }

    public void removeItem(InventoryItem item) {
        inventoryItems.remove(item);
    }

    public void reloadInventory() {
        if (inventory != null) {
            inventory.clear();
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, new InventoryItem(i, new ItemStack(fillMaterial)).getItem());
            }
            inventoryItems = new ArrayList<>();
            populate();
            for (InventoryItem item : inventoryItems) {
                inventory.setItem(item.getSlot(), item.getItem());
            }
        }
    }

    public void click(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player && event.isLeftClick()) {
            for (InventoryItem item : inventoryItems) {
                if (event.getCurrentItem() != null && item.getItem().getType() == event.getCurrentItem().getType() &&
                item.getSlot() == event.getSlot()) {
                    item.execute(new InventoryClick(event, this));
                    if(!item.isMovable){
                        event.setCancelled(true);
                    }
                    return;
                }
            }
        }
        if(event.getCurrentItem() != null && event.getCurrentItem().getType() == fillMaterial) {
            event.setCancelled(true);
        }
    }

    public InventoryItem getInventoryItem(ItemStack item) {
        for (InventoryItem inventoryItem : inventoryItems) {
            if (inventoryItem.getItem().equals(item)) {
                return inventoryItem;
            }
        }
        if(item.getType() == fillMaterial) return new InventoryItem(0, item);
        return null;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
