package net.maploop.listeners;

import net.maploop.npc.MaddoxTheSlayer;
import net.maploop.packet.PacketReader;
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
