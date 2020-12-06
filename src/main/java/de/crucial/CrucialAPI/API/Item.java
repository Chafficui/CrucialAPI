package de.crucial.CrucialAPI.API;

import de.crucial.CrucialAPI.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;
import java.util.logging.Logger;

public class Item {

    private static final Main PLUGIN = Main.getPlugin(Main.class);
    private static final Logger LOGGER = Server.getLogger("CrucialAPI");

    public static boolean addCustomItem(String id, String name, List<String> lore, String material, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(Material.getMaterial(material), name, lore);
            return addRecipe(id, name, ingredients, stack);
        } catch(IllegalArgumentException e) {
            LOGGER.info("Failed to create " + name);
            return false;
        }
    }

    public static boolean addCustomItem(String id, ItemStack stack, String[] ingredients){
        String name = stack.getItemMeta().getDisplayName();
        try{
            return addRecipe(id, name, ingredients, stack);
        } catch(IllegalArgumentException e) {
            LOGGER.info("Failed to create " + name);
            return false;
        }
    }

    public boolean addCustomItem(String id, String name, String material, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(Material.getMaterial(material), name);
            return addRecipe(id, name, ingredients, stack);
        } catch(IllegalArgumentException e) {
            LOGGER.info("Failed to create " + name);
            return false;
        }
    }

    public static boolean addCustomHead(String id, String name, List<String> lore, String head, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(head, name, lore);
            return addRecipe(id, name, ingredients, stack);
        } catch(IllegalArgumentException e) {
            LOGGER.info("Failed to create " + name);
            return false;
        }
    }

    private static boolean addRecipe(String id, String name, String[] ingredients, ItemStack stack) {
        NamespacedKey key = new NamespacedKey(PLUGIN, name + id);
        ShapedRecipe recipe = new ShapedRecipe(key, stack);
        int num = 47;

        recipe.shape("123","456","789");

        for (String item:ingredients) {
            num++;
            recipe.setIngredient((char) num, Material.getMaterial(item));
        }

        Bukkit.addRecipe(recipe);
        LOGGER.info("Successfully created " + name + " (key: " + name+id + ")");
        return true;
    }
}
