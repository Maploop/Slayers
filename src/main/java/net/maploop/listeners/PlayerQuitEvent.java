package net.maploop.listeners;

import net.maploop.npc.PacketReader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerQuitEvent implements Listener {
    @EventHandler
    public void onQuit(org.bukkit.event.player.PlayerQuitEvent event) {
        /*
        PacketReader reader = new PacketReader(event.getPlayer());
        reader.uninject();
         */
    }
}
