package dev.faceless.template;

import dev.faceless.PluginUtils;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class TemplatePlugin extends JavaPlugin {
    @Getter private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        PluginUtils.init(this);
    }

    @Override
    public void onDisable() {
        PluginUtils.getPackManager().disable();
    }
}