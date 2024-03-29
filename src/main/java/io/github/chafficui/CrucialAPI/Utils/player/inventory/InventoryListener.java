package io.github.chafficui.CrucialAPI.Utils.player.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;

public class InventoryListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Page page = Page.get(event.getInventory());
        if(page != null) {
            page.click(event);
        }
    }

    @EventHandler
    public void onInventoryPickup(InventoryPickupItemEvent event) {
        Page page = Page.get(event.getInventory());
        if(page != null) {
            InventoryItem item = page.getInventoryItem(event.getItem().getItemStack());
            if((item != null && !item.isMovable) || (item == null && !page.isMovable)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryMove(InventoryMoveItemEvent event) {
        Page page = Page.get(event.getSource());
        if(page != null) {
            InventoryItem item = page.getInventoryItem(event.getItem());
            if((item != null && !item.isMovable) || (item == null && !page.isMovable)) {
                event.setCancelled(true);
            }
        }
    }
}
