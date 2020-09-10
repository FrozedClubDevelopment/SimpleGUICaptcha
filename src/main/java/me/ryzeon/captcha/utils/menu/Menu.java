package me.ryzeon.captcha.utils.menu;

import me.ryzeon.captcha.utils.InventoryUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.InventoryHolder;

/**
 * Created by Ryzeon
 * Project: SimpleGUICatpcha
 * Date: 10/09/2020 @ 08:14
 * Template by Elb1to
 */
public interface Menu extends InventoryHolder {

    default void open(Player player) {
        player.openInventory(this.getInventory());
    }

    void onInventoryClick(InventoryClickEvent event);

    default void onInventoryDrag(InventoryDragEvent event) {
        if (InventoryUtil.clickedTopInventory(event)) event.setCancelled(true);
    }

    default void onInventoryClose(InventoryCloseEvent event) {
    }
}

