package net.maploop.traits;

import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;
import org.bukkit.entity.Entity;

public class craft_undead_maploop extends Trait {
    protected craft_undead_maploop(String name) {
        super("CRAFT_UNDEAD_MAPLOOP_TRAIT");
    }

    @Override
    public void onSpawn() {
        NPC npc = getNPC();

        for (Entity e : npc.getEntity().getNearbyEntities(5, 5, 5)) {

        }
    }
}
