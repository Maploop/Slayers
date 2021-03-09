package net.maploop.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RightClickNPC implements Listener {
    @EventHandler
    public void onClickNPC(net.maploop.events.RightClickNPC event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.RED + "Success!");
    }
}
