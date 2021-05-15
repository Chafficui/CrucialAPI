package io.github.chafficui.CrucialAPI.API;

import io.github.chafficui.CrucialAPI.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;
import java.util.Objects;

public class Item {

    private static final Main PLUGIN = Main.getPlugin(Main.class);

    public static boolean addCustomItem(String key, String name, List<String> lore, String material, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(Material.getMaterial(material), name, lore);
            addRecipe(key, name, ingredients, stack);
            return true;
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            PLUGIN.getLogger().info("Failed to create " + name);
            return false;
        }
    }

    public static boolean addCustomItem(String key, ItemStack stack, String[] ingredients){
        String name = Objects.requireNonNull(stack.getItemMeta()).getDisplayName();
        try{
            addRecipe(key, name, ingredients, stack);
            return true;
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            PLUGIN.getLogger().info("Failed to create " + name);
            return false;
        }
    }

    public static NamespacedKey addCustomItemNSK(String key, String name, List<String> lore, String material, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(Material.getMaterial(material), name, lore);
            return addRecipe(key, name, ingredients, stack);
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            PLUGIN.getLogger().info("Failed to create " + name);
            return null;
        }
    }

    public static NamespacedKey addCustomHeadNSK(String key, String name, List<String> lore, String head, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(head, name, lore);
            return addRecipe(key, name, ingredients, stack);
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            PLUGIN.getLogger().info("Failed to create " + name);
            return null;
        }
    }

    public static boolean addCustomItem(String key, String name, String material, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(Material.getMaterial(material), name);
            addRecipe(key, name, ingredients, stack);
            return true;
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            PLUGIN.getLogger().info("Failed to create " + name);
            return false;
        }
    }

    public static boolean addCustomHead(String key, String name, List<String> lore, String head, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(head, name, lore);
            addRecipe(key, name, ingredients, stack);
            return true;
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            PLUGIN.getLogger().info("Failed to create " + name);
            return false;
        }
    }

    private static NamespacedKey addRecipe(String key, String name, String[] ingredients, ItemStack stack) {
        name = name.replaceAll(" ", "_");
        key = key.replaceAll(" ", "_");
        key = key.replaceAll(":", ".");
        NamespacedKey namespacedKey = new NamespacedKey(PLUGIN, name + key);
        ShapedRecipe recipe = new ShapedRecipe(namespacedKey, stack);
        int num = 48;

        recipe.shape("123","456","789");

        for (String item:ingredients) {
            num++;
            char c = (char)num;
            recipe.setIngredient(c, Objects.requireNonNull(Material.getMaterial(item)));
        }

        Bukkit.addRecipe(recipe);
        PLUGIN.getLogger().info("Successfully created " + name + " (key: " + name+key + ")");
        return namespacedKey;
    }
}
