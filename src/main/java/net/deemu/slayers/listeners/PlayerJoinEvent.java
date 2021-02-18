package net.deemu.slayers.listeners;

import net.deemu.slayers.npcs.MaddoxTheSlayer;
import net.deemu.slayers.packets.PacketReader;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener {
    @EventHandler
    public void onJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        MaddoxTheSlayer.addJoinPacket(player);

        PacketReader reader = new PacketReader();
        reader.inject(player);
    }
}
