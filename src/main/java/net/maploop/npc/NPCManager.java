package net.maploop.npc;

import org.bukkit.entity.Player;

public class NPCManager {
    public static void spawnAll(Player player) {
        for (NPC npc : NPC.getNpcs()) {
            npc.spawn(player);
        }
    }

    public static void despawnAll(Player player) {
        for (NPC npc : NPC.getNpcs()) {
            npc.despawn(player);
        }
    }
}
