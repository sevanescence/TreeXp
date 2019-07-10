package com.makotomiyamoto.treexp.listener;

import com.makotomiyamoto.treexp.TreeXp;
import com.makotomiyamoto.treexp.thread.UnbanThread;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Date;

public class LogBreakListener implements Listener {

    private TreeXp plugin;

    public LogBreakListener(TreeXp plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (!(event.getBlock().getType().equals(Material.OAK_LOG) ||
                event.getBlock().getType().equals(Material.ACACIA_LOG) ||
                event.getBlock().getType().equals(Material.BIRCH_LOG) ||
                event.getBlock().getType().equals(Material.DARK_OAK_LOG) ||
                event.getBlock().getType().equals(Material.JUNGLE_LOG) ||
                event.getBlock().getType().equals(Material.SPRUCE_LOG))) {

            return;

        }

        if (event.getPlayer().getItemInHand().getType().equals(Material.AIR)) {

            String expRange = plugin.getConfig().getString("exp-range");
            if (expRange == null) {
                return;
            }

            float xpMin = Float.valueOf(expRange.replaceAll("[^0-9to]", "").split("to")[0]);
            float xpMax = Float.valueOf(expRange.replaceAll("[^0-9to]", "").split("to")[1]);

            float roll = (float) Math.random() * (xpMax - xpMin) + xpMin;

            event.setExpToDrop((int) Math.floor(roll));

            String message = plugin.getConfig().getString("log-break-message");
            if (message == null) {
                return;
            }

            message = ChatColor.translateAlternateColorCodes('&', message);

            event.getPlayer().sendMessage(message.replaceAll("%exp-gained%", String.valueOf((int) Math.floor(roll))));

            return;

        }

        Bukkit.getBanList(BanList.Type.NAME).addBan(event.getPlayer().getName(), "pls hire me weest", null, null);

        event.getPlayer().kickPlayer("pls hire me weest");

        Thread thread = new Thread(new UnbanThread(plugin, event.getPlayer()));

        thread.start();

    }

}
