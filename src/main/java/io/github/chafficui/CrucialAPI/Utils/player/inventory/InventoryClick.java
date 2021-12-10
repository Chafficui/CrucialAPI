package io.github.chafficui.CrucialAPI.Utils.player.inventory;


import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick {
    private final InventoryClickEvent event;
    private final Page page;

    public InventoryClick(InventoryClickEvent event, Page page) {
        this.event = event;
        this.page = page;
    }

    public Page getPage() {
        return page;
    }

    public InventoryClickEvent getEvent() {
        return event;
    }

    public int getSlot() {
        return event.getSlot();
    }

    public Player getPlayer() {
        return (Player) event.getWhoClicked();
    }
}