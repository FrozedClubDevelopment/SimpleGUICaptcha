package me.ryzeon.captcha.menu;

import me.ryzeon.captcha.Captcha;
import me.ryzeon.captcha.listeners.PlayerListener;
import me.ryzeon.captcha.utils.InventoryUtil;
import me.ryzeon.captcha.utils.ItemBuilder;
import me.ryzeon.captcha.utils.Utils;
import me.ryzeon.captcha.utils.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.Random;

/**
 * Created by Ryzeon
 * Project: SimpleGUICatpcha
 * Date: 10/09/2020 @ 08:50
 * Template by Elb1to
 */

public class CaptchaMenu implements Menu {
    private Inventory inventory;

    public CaptchaMenu() {
        this.inventory = Bukkit.createInventory((InventoryHolder) this, 9 * 3, Captcha.getInstance().getMainConfig().getString("MENU-TITLE"));
    }

    @Override
    public void open(Player player) {
        this.update();
        player.openInventory(this.inventory);
    }

    public void update() {
        this.inventory.clear();
        this.inventory.setItem(Utils.randomInteger(0,26),new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability(13).setName("&aClick here!").get());
        InventoryUtil.fillInventory(this.inventory);
    }

    @Override
    public void onInventoryClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        Inventory closeInventory = e.getInventory();
        Inventory topInventory = e.getView().getTopInventory();
        if (!topInventory.equals(this.inventory)) {
            return;
        }
        if (topInventory.equals(closeInventory)){
            if (!PlayerListener.passedCaptcha.contains(player.getUniqueId())){
                player.kickPlayer(Captcha.getInstance().getMainConfig().getString("KICK-MESSAGE.BAD-ITEM"));
            }
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        final Inventory clickedInventory = e.getClickedInventory();
        final Inventory topInventory = e.getView().getTopInventory();
        if (!topInventory.equals(this.inventory)) {
            return;
        }
        if (topInventory.equals(clickedInventory)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR))
                return;
            if (!e.getCurrentItem().hasItemMeta()) return;

            if (e.getCurrentItem().equals(new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability(13).setName("&aClick here!").get())){
                PlayerListener.passedCaptcha.add(p.getUniqueId());
                p.closeInventory();
            } else {
                p.kickPlayer(Captcha.getInstance().getMainConfig().getString("KICK-MESSAGE.BAD-ITEM"));
            }

        } else if ((!topInventory.equals(clickedInventory) && e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) || e.getAction() == InventoryAction.COLLECT_TO_CURSOR) {
            e.setCancelled(true);
        }
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}