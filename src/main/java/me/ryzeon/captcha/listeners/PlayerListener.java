package me.ryzeon.captcha.listeners;

import me.ryzeon.captcha.Captcha;
import me.ryzeon.captcha.menu.CaptchaMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ryzeon
 * Project: SimpleGUICatpcha
 * Date: 10/09/2020 @ 08:31
 * Template by Elb1to
 */
public class PlayerListener implements Listener {

    public static List<UUID> passedCaptcha = new ArrayList<>();

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        new BukkitRunnable() {
            @Override
            public void run() {
                new CaptchaMenu().open(p);
            }
        }.runTaskLater(Captcha.getInstance(), 5L);

        new BukkitRunnable() {
            @Override
            public void run() {
                int time = Captcha.getInstance().getKickTime();

                time = time - 1;

                if (passedCaptcha.contains(p.getUniqueId())) {
                    p.sendMessage(Captcha.getInstance().getMainConfig().getString("CAPTCHA-PASSED"));
                    cancel();
                    passedCaptcha.remove(p.getUniqueId());
                }

                if (!p.isOnline()) {
                    cancel();
                }

                if (time <= 0) {
                    p.kickPlayer(Captcha.getInstance().getMainConfig().getString("KICK-MESSAGE.TIME"));
                    cancel();
                }

            }
        }.runTaskTimer(Captcha.getInstance(), 20L, 20L);
    }
}
