package net.deemu.slayers.Bosses.RevenantHorror;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.entity.Zombie;

public class ZOMBIE_SLAYER_TIER_1 extends EntityZombie {
    @SuppressWarnings("deprecation")
    public ZOMBIE_SLAYER_TIER_1(World world) {
        super(world);

        Zombie craftZombie = (Zombie) this.getBukkitEntity();
        this.setBaby(false);
        craftZombie.setMaxHealth(1000);
        this.setHealth(1000);
        this.setCustomName("§c☠ §bRevenant Horror §a" + this.getHealth() + "§7/§a" + this.getMaxHealth());
        this.setCustomNameVisible(true);

        this.targetSelector.a(0, new PathfinderGoalNearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        this.getWorld().addEntity(this);

    }
}

