package me.ryzeon.captcha;

import lombok.Getter;
import lombok.Setter;
import me.ryzeon.captcha.listeners.PlayerListener;
import me.ryzeon.captcha.utils.CC;
import me.ryzeon.captcha.utils.config.ConfigFile;
import me.ryzeon.captcha.utils.menu.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Captcha extends JavaPlugin {

    @Getter public static Captcha instance;

    private ConfigFile mainConfig;

    @Setter private int kickTime;

    @Override
    public void onEnable() {
        instance = this;
        this.mainConfig = new ConfigFile(this,"config");
        this.kickTime = this.mainConfig.getInt("TIME-TO-KICK");

        Bukkit.getConsoleSender().sendMessage(CC.translate("&e&m-----------------------"));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&6SimpleGUICaptcha"));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&7|-"));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&6Version &f: &abv"+getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&6Made by&f: &aRyzeon"));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&e&m-----------------------"));
        registerListener();
    }

    private void registerListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerListener(),this);
        pluginManager.registerEvents(new MenuListener(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
