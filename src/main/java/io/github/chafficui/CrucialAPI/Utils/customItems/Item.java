package io.github.chafficui.CrucialAPI.Utils.customItems;

import io.github.chafficui.CrucialAPI.Utils.Server;
import io.github.chafficui.CrucialAPI.Main;
import io.github.chafficui.CrucialAPI.exceptions.CrucialException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;
import java.util.Objects;

public class Item {
    private static final Main PLUGIN = Main.getPlugin(Main.class);

    public static NamespacedKey createItem(String key, String name, List<String> lore,
                                           Material material, String[] recipe) throws CrucialException {
        try{
            ItemStack stack = Stack.getStack(material, name, lore);
            return addRecipe(key, name, recipe, stack);
        } catch(IllegalArgumentException e){
            throw new CrucialException(2);
        }
    }

    public static NamespacedKey createHead(String key, String name, List<String> lore,
                                           String head, String[] recipe) throws CrucialException {
        try{
            ItemStack stack = Stack.getStack(head, name, lore);
            return addRecipe(key, name, recipe, stack);
        } catch(IllegalArgumentException e){
            throw new CrucialException(2);
        }
    }

    private static NamespacedKey addRecipe(String key, String name, String[] recipe, ItemStack stack){
        name = name.replaceAll(" ", "_");
        key = key.replaceAll(" ", "_");
        key = key.replaceAll(":", ".");
        NamespacedKey namespacedKey = new NamespacedKey(PLUGIN, name + key);
        ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, stack);
        int num = 48;

        shapedRecipe.shape("123","456","789");

        for (String item:recipe) {
            num++;
            char c = (char)num;
            shapedRecipe.setIngredient(c, Objects.requireNonNull(Material.getMaterial(item)));
        }

        Bukkit.addRecipe(shapedRecipe);
        Server.log("Successfully created " + name + " (key: " + name + key + ")");
        return namespacedKey;
    }
}
