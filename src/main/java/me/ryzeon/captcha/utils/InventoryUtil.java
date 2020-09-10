package me.ryzeon.captcha.utils;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

/**
 * Created by Ryzeon
 * Project: SimpleGUICatpcha
 * Date: 10/09/2020 @ 08:15
 * Template by Elb1to
 */

public final class InventoryUtil {

    public static void fillInventory(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null || inventory.getItem(i).getType().equals(Material.AIR)) {
                inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability(7).setName(" ").get());
            }
        }
    }

    public static boolean clickedTopInventory(InventoryDragEvent event) {
        InventoryView view = event.getView();
        Inventory topInventory = view.getTopInventory();
        if (topInventory == null) {
            return false;
        }
        boolean result = false;
        int size = topInventory.getSize();
        for (Integer entry : event.getNewItems().keySet()) {
            if (entry >= size) continue;
            result = true;
            break;
        }
        return result;
    }
}
