package io.github.chafficui.CrucialAPI.Utils.customItems;

import io.github.chafficui.CrucialAPI.exceptions.CrucialException;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class CrucialHead extends CrucialItem {
    protected UUID headOwner;

    public CrucialHead(String type) {
        super(type);
        material = String.valueOf(Material.PLAYER_HEAD);
        headOwner = null;
    }

    public CrucialHead(String name, UUID headOwner, List<String> lore, String[] recipe, String type, boolean isCraftable, boolean isUsable, boolean isAllowedForCrafting) {
        super(name, Material.PLAYER_HEAD, lore, recipe, type, isCraftable, isUsable, isAllowedForCrafting);
        this.headOwner = headOwner;
    }

    public UUID getHeadOwner() {
        return headOwner;
    }

    @Override
    protected void registerRecipe() throws CrucialException {
        AttributeModifier modifier = new AttributeModifier(this.id, "CRUCIALITEM_ID", 0, AttributeModifier.Operation.ADD_NUMBER);
        if(headOwner != null)
            this.namespacedKey = Item.createItem(id + type, name, Stack.addAttributeModifier(Stack.getStack(headOwner, name, lore), Attribute.GENERIC_MAX_HEALTH, modifier), recipe);
        else
            throw new CrucialException(8);
    }

    @Override
    public ItemStack getItemStack() {
        if(isRegistered) {
            AttributeModifier modifier = new AttributeModifier(this.id, "CRUCIALITEM_ID", 0, AttributeModifier.Operation.ADD_NUMBER);
            if(headOwner != null)
                return Stack.addAttributeModifier(Stack.getStack(headOwner, name, lore), Attribute.GENERIC_MAX_HEALTH, modifier);
        }
        return null;
    }
}
