package net.maploop.npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NPC {

    private final int entityID;
    private final Location location;
    private final GameProfile gameprofile;
    private final String texture;
    private final String signature;
    private PacketPlayOutNamedEntitySpawn spawnPacket;

    private static final ArrayList<NPC> npcs = new ArrayList<>();

    public NPC(String name, Location location, String texture, String signature) {
        entityID = (int) Math.ceil(Math.random() * 1000) + 2000;
        gameprofile = new GameProfile(UUID.randomUUID(), name);
        this.texture = texture;
        this.signature = signature;
        this.location = location;
        changeSkin();
    }

    public void changeSkin() {
        gameprofile.getProperties().put("textures", new Property("textures", texture, signature));
    }

    public void createNPC() {
        System.out.println("create NPC");
        PacketPlayOutNamedEntitySpawn packet = new PacketPlayOutNamedEntitySpawn();

        Reflections.setValue(packet, "a", entityID);
        Reflections.setValue(packet, "b", gameprofile.getId());
        Reflections.setValue(packet, "c", getFixLocation(location.getX()));
        Reflections.setValue(packet, "d", getFixLocation(location.getY()));
        Reflections.setValue(packet, "e", getFixLocation(location.getZ()));
        Reflections.setValue(packet, "f", getFixRotation(location.getYaw()));
        Reflections.setValue(packet, "g", getFixRotation(location.getPitch()));
        Reflections.setValue(packet, "h", 0);
        DataWatcher w = new DataWatcher(null);
        w.a(6, (float) 20);
        w.a(10, (byte) 127);
        Reflections.setValue(packet, "i", w);
        spawnPacket = packet;
        npcs.add(this);
    }

    public void removeNPC(Player player) {
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(entityID);
        rmvFromTablist(player);
        npcs.remove(this);
    }

    public void spawn(Player player) {
        addToTablist(player);
        Reflections.sendPacket(spawnPacket, player);
        rmvFromTablist(player);
    }

    public void despawn(Player player) {
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(entityID);
        rmvFromTablist(player);
        Reflections.sendPacket(packet, player);

    }

    public void addToTablist(Player player) {
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(gameprofile, 1, WorldSettings.EnumGamemode.NOT_SET, CraftChatMessage.fromString("")[0]);
        @SuppressWarnings("unchecked")
        List<PacketPlayOutPlayerInfo.PlayerInfoData> players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) Reflections.getValue(packet, "b");
        players.add(data);

        Reflections.setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER);
        Reflections.setValue(packet, "b", players);

        Reflections.sendPacket(packet, player);
    }

    public void rmvFromTablist(Player player) {
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(gameprofile, 1, WorldSettings.EnumGamemode.NOT_SET, CraftChatMessage.fromString("")[0]);
        @SuppressWarnings("unchecked")
        List<PacketPlayOutPlayerInfo.PlayerInfoData> players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) Reflections.getValue(packet, "b");
        players.add(data);

        Reflections.setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER);
        Reflections.setValue(packet, "b", players);

        Reflections.sendPacket(packet, player);
    }

    public void rotateHeadtoPlayer(Player player) {
        Location npcloc = location.setDirection(player.getLocation().subtract(location).toVector());
        float yaw1 = npcloc.getYaw();
        float pitch1 = npcloc.getPitch();

        PacketPlayOutEntity.PacketPlayOutEntityLook packet = new PacketPlayOutEntity.PacketPlayOutEntityLook(entityID, getFixRotation(yaw1), getFixRotation(pitch1), true);
        PacketPlayOutEntityHeadRotation packetHead = new PacketPlayOutEntityHeadRotation();

        Reflections.setValue(packetHead, "a", entityID);
        Reflections.setValue(packetHead, "b", getFixRotation(yaw1));

        Reflections.sendPacket(packet, player);
        Reflections.sendPacket(packetHead, player);
    }

    public int getFixLocation(double pos) {
        return MathHelper.floor(pos * 32.0D);
    }

    public byte getFixRotation(float rotation) {
        return (byte) ((int) (rotation * 256.0F / 360.0F));
    }

    public static ArrayList<NPC> getNpcs() {
        return npcs;
    }

    public Location getLocation() {
        return location;
    }

    public int getEntityID() {
        return entityID;
    }
}
