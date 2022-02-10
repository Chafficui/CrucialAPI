package io.github.chafficui.CrucialAPI.Utils.player.inventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;

public class InventoryListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Page page = Page.get(event.getInventory());
        HumanEntity entity = event.getWhoClicked();
        if(page != null && page.isMovable() && event.getClick().equals(ClickType.LEFT) && entity instanceof Player) {
            page.click(event);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryPickupItemEvent event) {
        Page page = Page.get(event.getInventory());
        if(page != null) {
            if(!page.isMovable() || InventoryItem.isInventoryItem(event.getItem().getItemStack())) {
                System.out.println("Seems to be an inventory item");
                event.setCancelled(true);
            }
        }
    }
}
