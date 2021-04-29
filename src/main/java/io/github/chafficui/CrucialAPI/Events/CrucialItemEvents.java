package io.github.chafficui.CrucialAPI.Events;

import io.github.chafficui.CrucialAPI.Interfaces.CrucialItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class CrucialItemEvents implements Listener {

    @EventHandler
    private void onInteract(PlayerInteractEntityEvent event){
        ItemStack item = event.getPlayer().getItemOnCursor();
        if(item.getItemMeta() != null){
            CrucialItem crucialItem = CrucialItem.getByKey(item);
            if(crucialItem != null && !crucialItem.isUsable()){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onInteract(PlayerInteractEvent event){
        ItemStack item = event.getPlayer().getItemOnCursor();
        if(item.getItemMeta() != null){
            CrucialItem crucialItem = CrucialItem.getByKey(item);
            if(crucialItem != null && !crucialItem.isUsable()){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onCrafting(PrepareItemCraftEvent event){
        CraftingInventory inventory = event.getInventory();
        for (ItemStack stack:inventory.getStorageContents()){
            CrucialItem crucialItem = CrucialItem.getByKey(stack);
            if(crucialItem != null && !crucialItem.isAllowedForCrafting() && CrucialItem.getByKey(inventory.getResult()) == null){
                inventory.setResult(null);
            }
        }
    }
}
