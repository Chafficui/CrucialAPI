package io.github.chafficui.CrucialAPI.Utils.customItems;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.UUID;

public class Stack {

    public static ItemStack getStack(Material material){
        if(material!=null){
            return new ItemStack(material);
        }
        return new ItemStack(Material.AIR);
    }

    public static ItemStack getStack(Material material, String name){
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        return getCleanMeta(stack, meta);
    }

    public static ItemStack getStack(Material material, String name, List<String> lore){
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        meta.setLore(lore);
        return getCleanMeta(stack, meta);
    }

    public static ItemStack getStack(UUID uuid, String name, List<String> lore){
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();
        assert meta != null;
        getCleanMeta(stack, meta);
        meta = (SkullMeta) stack.getItemMeta();
        meta.setDisplayName(name);
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
        meta.setLore(lore);
        stack.setItemMeta(meta);

        return stack;
    }

    public static ItemStack getStack(Material material, String name, List<String> lore, boolean shiny){
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        meta.setLore(lore);
        if(shiny)
            meta.addEnchant(Enchantment.DURABILITY, 0, true);
        return getCleanMeta(stack, meta);
    }

    public static ItemStack addAttributeModifier(ItemStack stack, Attribute attribute, AttributeModifier modifier) {
        if(stack == null || stack.getType().equals(Material.AIR) || attribute == null || modifier == null) {
            return stack;
        }
        ItemMeta meta = stack.getItemMeta();
        meta.addAttributeModifier(attribute, modifier);
        return getCleanMeta(stack, meta);
    }

    private static ItemStack getCleanMeta(ItemStack stack, ItemMeta meta) {
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        stack.setItemMeta(meta);
        return stack;
    }
}
