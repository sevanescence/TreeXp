package com.makotomiyamoto.treexp.thread;

import com.makotomiyamoto.treexp.TreeXp;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UnbanThread implements Runnable {

    private TreeXp plugin;
    private Player player;
    private final Object obj = new Object();

    public UnbanThread(TreeXp plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    @Override
    public void run() {

        synchronized (obj) {

            try {
                obj.wait(10000);
            } catch (InterruptedException e) {
                System.out.println("This isn't properly synchronized!");
            }

            Bukkit.getBanList(BanList.Type.NAME).pardon(player.getName());

            System.out.println("Player " + player.getName() + " has been unbanned.");

        }

    }

}
