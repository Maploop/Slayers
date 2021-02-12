package net.deemu.slayers.Listeners;

import net.deemu.slayers.Bosses.RevenantHorror.ZOMBIE_SLAYER_TIER_1;
import net.deemu.slayers.Bosses.SlayerBoss;
import net.deemu.slayers.Quests.Quest;
import net.deemu.slayers.Quests.QuestType;
import net.deemu.slayers.Slayers;
import net.deemu.slayers.Utilities;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

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
                    Utilities.sendActionbar(player, "&3+5 Combat (" + combat_exp.get(player.getUniqueId()) + "/100");
                } else if (combat_exp.get(player.getUniqueId()) >= 100) {
                    player.sendMessage(ChatColor.GREEN + "The slayer boss has spawned! SLAY THE BOSS!");
                    player.getWorld().playEffect(event.getEntity().getLocation(), Effect.EXPLOSION_HUGE, 0);
                    player.playSound(player.getLocation(), Sound.EXPLODE, 10F, 1);
                    SlayerBoss.spawnSlayerBoss(SlayerBoss.ZOMBIE_SLAYER_TIER_1, event.getEntity().getLocation(), player);
                    Utilities.sendActionbar(player, "&c&lSLAY THE BOSS!");

                    Quest.cancelQuest(player);
                    return;
                }
                return;
            }
            combat_exp.put(player.getUniqueId(), 5);
            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10F, 0);
            Utilities.sendActionbar(player, "&3+5 Combat (" + combat_exp.get(player.getUniqueId()) + "/100)");
        }
    }
}
