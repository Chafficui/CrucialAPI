package io.github.chafficui.CrucialAPI.API.Items;

import io.github.chafficui.CrucialAPI.API.Server;
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

    public static boolean addCustomItem(String id, String name, List<String> lore, String material, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(Material.getMaterial(material), name, lore);
            addRecipe(id, name, ingredients, stack);
            return true;
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            PLUGIN.getLogger().info("Failed to create " + name);
            return false;
        }
    }

    public static boolean addCustomItem(String id, ItemStack stack, String[] ingredients){
        String name = Objects.requireNonNull(stack.getItemMeta()).getDisplayName();
        try{
            addRecipe(id, name, ingredients, stack);
            return true;
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            PLUGIN.getLogger().info("Failed to create " + name);
            return false;
        }
    }

    public static NamespacedKey addCustomItemNSK(String id, String name, List<String> lore, String material, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(Material.getMaterial(material), name, lore);
            return addRecipe(id, name, ingredients, stack);
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            PLUGIN.getLogger().info("Failed to create " + name);
            return null;
        }
    }

    public static NamespacedKey addCustomHeadNSK(String id, String name, List<String> lore, String head, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(head, name, lore);
            return addRecipe(id, name, ingredients, stack);
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            PLUGIN.getLogger().info("Failed to create " + name);
            return null;
        }
    }

    public static boolean addCustomItem(String id, String name, String material, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(Material.getMaterial(material), name);
            addRecipe(id, name, ingredients, stack);
            return true;
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            PLUGIN.getLogger().info("Failed to create " + name);
            return false;
        }
    }

    public static boolean addCustomHead(String id, String name, List<String> lore, String head, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(head, name, lore);
            addRecipe(id, name, ingredients, stack);
            return true;
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            PLUGIN.getLogger().info("Failed to create " + name);
            return false;
        }
    }

    private static NamespacedKey addRecipe(String id, String name, String[] ingredients, ItemStack stack) {
        name = name.replaceAll(" ", "_");
        id = id.replaceAll(" ", "_");
        id = id.replaceAll(":", ".");
        NamespacedKey key = new NamespacedKey(PLUGIN, name + id);
        ShapedRecipe recipe = new ShapedRecipe(key, stack);
        int num = 48;

        recipe.shape("123","456","789");

        for (String item:ingredients) {
            num++;
            char c = (char)num;
            recipe.setIngredient(c, Objects.requireNonNull(Material.getMaterial(item)));
        }

        Bukkit.addRecipe(recipe);
        PLUGIN.getLogger().info("Successfully created " + name + " (key: " + name+id + ")");
        return key;
    }
}
