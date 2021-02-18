package net.deemu.slayers.listeners;

import net.deemu.slayers.packets.PacketReader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerQuitEvent implements Listener {
    @EventHandler
    public void onQuit(org.bukkit.event.player.PlayerQuitEvent event) {
        PacketReader reader = new PacketReader();
        reader.uninject(event.getPlayer());
    }
}
