package net.maploop.slayers.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RightClickNPC implements Listener {
    @EventHandler
    public void onClickNPC(net.maploop.slayers.Events.RightClickNPC event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.RED + "Success!");
    }
}
