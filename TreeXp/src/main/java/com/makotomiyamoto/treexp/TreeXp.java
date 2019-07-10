package com.makotomiyamoto.treexp;

import com.makotomiyamoto.treexp.listener.LogBreakListener;
import org.bukkit.plugin.java.JavaPlugin;

public class TreeXp extends JavaPlugin {

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        this.getServer().getPluginManager().registerEvents(new LogBreakListener(this), this);

        System.out.println("[TreeXp] TreeXp 1.0.0 enabled!");

    }

}
