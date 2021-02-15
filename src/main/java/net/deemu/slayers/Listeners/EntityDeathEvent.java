package net.deemu.slayers.Listeners;

import net.deemu.slayers.Bosses.SlayerBoss;
import net.deemu.slayers.Quests.Quest;
import net.deemu.slayers.Quests.QuestType;
import net.deemu.slayers.Utilities.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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
        if (event.getEntity().getType() == EntityType.ZOMBIE) {
            if (quest.getType(player).equals(QuestType.ZOMBIE_SLAYER_TIER_1)) {

                if (combat_exp.containsKey(player.getUniqueId())) {
                    if (combat_exp.get(player.getUniqueId()) < 100) {
                        combat_exp.put(player.getUniqueId(), combat_exp.get(player.getUniqueId()) + 5);
                        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10F, 2);
                        Utilities.sendActionbar(player, "&3+5 Combat (" + combat_exp.get(player.getUniqueId()) + "/100)");
                    } else if (combat_exp.get(player.getUniqueId()) >= 100) {

                        if (!(Quest.fightingBoss.containsKey(player.getUniqueId()))) {
                            player.sendMessage(ChatColor.GREEN + "The slayer boss has spawned! SLAY THE BOSS!");
                            player.getWorld().playEffect(event.getEntity().getLocation(), Effect.EXPLOSION_HUGE, 0);
                            player.playSound(player.getLocation(), Sound.EXPLODE, 10F, 1);
                            SlayerBoss.spawnSlayerBoss(SlayerBoss.ZOMBIE_SLAYER_TIER_1, event.getEntity().getLocation(), player);
                            Utilities.sendActionbar(player, "&c&lSLAY THE BOSS!");
                            Quest.fightingBoss.put(player.getUniqueId(), true);
                        } else if (Quest.fightingBoss.containsKey(player.getUniqueId())) {
                            if (SlayerBoss.bossMap.get(player.getUniqueId()) == event.getEntity()) {
                                if (event.getEntity().getEquipment().getHelmet().getItemMeta().getDisplayName().equals("ZOMBIE_SLAYER_TIER_1")) {
                                    Quest.fightingBoss.remove(player.getUniqueId());
                                    player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "  NICE! SLAYER BOSS SLAIN!");
                                    player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "  » " + ChatColor.GRAY + "Talk to Maddox again to collect your reward!");
                                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10F, 0);
                                    event.getDrops().clear();

                                    Quest.questComplete.put(player.getUniqueId(), true);
                                    SlayerBoss.bossMap.remove(player.getUniqueId());
                                    return;
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "That was not your boss! You won't get your quest completed.");
                            }
                        }
                        return;
                    }
                    return;
                }
                combat_exp.put(player.getUniqueId(), 5);
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10F, 0);
                Utilities.sendActionbar(player, "&3+5 Combat (" + combat_exp.get(player.getUniqueId()) + "/100)");
            }

            if (quest.getType(player).equals(QuestType.ZOMBIE_SLAYER_TIER_2)) {
                if (combat_exp.containsKey(player.getUniqueId())) {
                    if (combat_exp.get(player.getUniqueId())  < 1440) {
                        combat_exp.put(player.getUniqueId(), combat_exp.get(player.getUniqueId()) + 5);
                        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10F, 2);
                        Utilities.sendActionbar(player, "&3+5 Combat (" + combat_exp.get(player.getUniqueId()) + "/1440)");
                    } else if (combat_exp.get(player.getUniqueId()) >= 1440) {
                        if (!(Quest.fightingBoss.containsKey(player.getUniqueId()))) {
                            player.sendMessage(ChatColor.GREEN + "The slayer boss has spawned! SLAY THE BOSS!");
                            player.getWorld().playEffect(event.getEntity().getLocation(), Effect.EXPLOSION_HUGE, 0);
                            player.playSound(player.getLocation(), Sound.EXPLODE, 10F, 1);
                            SlayerBoss.spawnSlayerBoss(SlayerBoss.ZOMBIE_SLAYER_TIER_2, event.getEntity().getLocation(), player);
                            Utilities.sendActionbar(player, "&c&lSLAY THE BOSS!");
                            Quest.fightingBoss.put(player.getUniqueId(), true);
                        } else if (Quest.fightingBoss.containsKey(player.getUniqueId())) {
                            if (SlayerBoss.bossMap.get(player.getUniqueId()) == event.getEntity()) {
                                if (event.getEntity().getEquipment().getHelmet().getItemMeta().getDisplayName().equals("ZOMBIE_SLAYER_TIER_2")) {
                                    Quest.fightingBoss.remove(player.getUniqueId());
                                    player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "  NICE! SLAYER BOSS SLAIN!");
                                    player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "  » " + ChatColor.GRAY + "Talk to Maddox again to collect your reward!");
                                    Utilities.sendActionbar(player, "&5&lBOSS SLAIN! &7(Revenant Horror II)");
                                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10F, 0);
                                    event.getDrops().clear();

                                    Quest.questComplete.put(player.getUniqueId(), true);
                                    SlayerBoss.bossMap.remove(player.getUniqueId());
                                    return;
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "That was not your boss! You won't get your quest completed.");
                            }
                        }
                    }
                    return;
                }
                combat_exp.put(player.getUniqueId(), 5);
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10F, 0);
                Utilities.sendActionbar(player, "&3+5 Combat (" + combat_exp.get(player.getUniqueId()) + "/1440)");
            }
        } // else if its spider or wolf
    }
}
