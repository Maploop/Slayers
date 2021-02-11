package net.deemu.slayers.Packets;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.deemu.slayers.Events.RightClickNPC;
import net.deemu.slayers.NPC.MaddoxTheSlayer;
import net.deemu.slayers.Slayers;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PacketReader {
    Channel channel;
    public static HashMap<UUID, Channel> channels = new HashMap<>();

    public void inject(Player player) {
        // Simple method to inject the player to packet reader.
        // Must inject when player joins the server.

        CraftPlayer craftPlayer = ((CraftPlayer) player);
        channel = craftPlayer.getHandle().playerConnection.networkManager.channel;
        channels.put(player.getUniqueId(), channel);

        if (channel.pipeline().get("PacketInjector") != null) return;
        channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<PacketPlayInUseEntity>() {

            @Override
            protected void decode(ChannelHandlerContext channelHandlerContext, PacketPlayInUseEntity packet, List<Object> list) throws Exception {
                list.add(packet);
                readPacket(player, packet);
            }
        });
    }

    public void uninject(Player player) {
        // Simple method to uninject the player from packet reader.
        // Must uninject when player leaves the server.

        channel = channels.get(player.getUniqueId());
        if (channel.pipeline().get("PacketInjector") != null) channel.pipeline().remove("PacketInjector");
    }

    public void readPacket(Player player, Packet<?> packet) {
        if (packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity")) {
            // Error checking/seeing if the action is what we are looking for

            if (getValue(packet, "action").toString().equalsIgnoreCase("ATTACK")) return;
            if (getValue(packet, "action").toString().equalsIgnoreCase("INTERACT_AT")) return;

            int id = (int) getValue(packet, "a");

            if (getValue(packet, "action").toString().equalsIgnoreCase("INTERACT")) {
                for(EntityPlayer npc : MaddoxTheSlayer.getNPCs()) {
                    if (npc.getId() == id) {
                        // If the action is what we are looking for,
                        // We call the RightClickNPC event with Player player, EntityPlayer npc

                        Bukkit.getScheduler().scheduleSyncDelayedTask(Slayers.getPlugin(), new Runnable() {
                            // We must schedule a delayed task so we don't get an error saying
                            // Multiple threads running at the same time.

                            @Override
                            public void run() {
                                Bukkit.getPluginManager().callEvent(new RightClickNPC(player, npc));
                            }
                        }, 0);
                    }
                }
            }

        }
    }

    private Object getValue(Object instance, String name) {
        // Basic method to get all the values
        // From the fields in the NMS classes.

        Object result = null;
        try {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);

            result = field.get(instance);

            field.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
