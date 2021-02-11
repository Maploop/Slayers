package net.deemu.slayers.Listeners;

import net.deemu.slayers.Bosses.RevenantHorror.ZOMBIE_SLAYER_TIER_1;
import net.deemu.slayers.Quests.Quest;
import net.deemu.slayers.Quests.QuestType;
import net.minecraft.server.v1_8_R3.EntitySpider;
import net.minecraft.server.v1_8_R3.EntityWolf;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.UUID;

public class EntityDeathEvent implements Listener {
    public static HashMap<UUID, Integer> combat_exp = new HashMap<>();

    @EventHandler
    public void onEntityDeath(org.bukkit.event.entity.EntityDeathEvent event) {
        // if (!(event.getEntity() instanceof EntityZombie || event.getEntity() instanceof EntitySpider || event.getEntity() instanceof EntityWolf)) return;
        Player player = event.getEntity().getKiller();

        HashMap<UUID, QuestType> playerQuest = Quest.getQuest(player);
        Quest quest = new Quest();
        if (!(playerQuest.containsKey(player.getUniqueId()))) return;
        if (quest.getType(player) == null) return;
        if (quest.getType(player).equals(QuestType.ZOMBIE_SLAYER_TIER_1)) {
            if (combat_exp.containsKey(player.getUniqueId())) {
                if (combat_exp.get(player.getUniqueId()) < 100) {
                    combat_exp.put(player.getUniqueId(), combat_exp.get(player.getUniqueId()) + 5);
                    player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10F, 2);
                } else if (combat_exp.get(player.getUniqueId()) >= 100) {
                    Location loc = event.getEntity().getLocation();

                    World world = ((CraftWorld) loc.getWorld()).getHandle();
                    ZOMBIE_SLAYER_TIER_1 boss = new ZOMBIE_SLAYER_TIER_1(world);
                    boss.setPosition(loc.getX(), loc.getY(), loc.getZ());
                    world.addEntity(boss);

                    playerQuest.remove(player.getUniqueId());
                    combat_exp.remove(player.getUniqueId());
                    return;
                }
                return;
            }
            combat_exp.put(player.getUniqueId(), 5);
            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10F, 0);
        }
    }
}
