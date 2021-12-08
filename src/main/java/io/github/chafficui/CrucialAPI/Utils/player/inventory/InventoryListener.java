package io.github.chafficui.CrucialAPI.Utils.player.inventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class InventoryListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Page page = Page.get(event.getInventory());
        HumanEntity entity = event.getWhoClicked();
        if(page != null && event.getClick().equals(ClickType.LEFT) && entity instanceof Player) {
            page.click(event.getSlot(), (Player) entity);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryPickupItemEvent event) {
        Page page = Page.get(event.getInventory());
        if(page != null) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Page.get(0).open(event.getPlayer());
    }
}
