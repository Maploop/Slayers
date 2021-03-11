package net.maploop.listeners;

import net.maploop.Slayers;
import net.maploop.bosses.Dps;
import net.maploop.quests.Quest;
import net.maploop.bosses.SlayerBoss;
import net.maploop.quests.QuestType;
import net.maploop.util.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class EntityDeathEvent implements Listener {
    public static HashMap<UUID, Integer> combat_exp = new HashMap<>();

    @EventHandler
    public void onEntityDeath(org.bukkit.event.entity.EntityDeathEvent event) {
        // if (!(event.getEntity() instanceof EntityZombie || event.getEntity() instanceof EntitySpider || event.getEntity() instanceof EntityWolf)) return;
        if (!(event.getEntity().getKiller() instanceof Player)) return;
        Player player = event.getEntity().getKiller();

        HashMap<UUID, QuestType> playerQuest = Quest.getQuest(player);
        Quest quest = new Quest();
        if (!(playerQuest.containsKey(player.getUniqueId()))) return;
        if (quest.getType(player) == null) return;
        if (event.getEntity().getType() == EntityType.ZOMBIE) {

            // Just checking if they fighting da boss!
            if (Quest.fightingBoss.containsKey(player.getUniqueId())) {
                if (SlayerBoss.bossMap.get(player.getUniqueId()) == event.getEntity()) {
                    if (event.getEntity().getEquipment().getHelmet().getItemMeta().getDisplayName().equals("ZOMBIE_SLAYER_TIER_1")) {
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "  NICE! SLAYER BOSS SLAIN!");
                        player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "  » " + ChatColor.GRAY + "Talk to Maddox again to collect your reward!");
                        this.playSoundWithDelay(player, 3L, 1);
                        this.playSoundWithDelay(player, 7, 1.5f);
                        this.playSoundWithDelay(player, 11, 2f);
                        event.getDrops().clear();

                        // End DPS, (damage per second)
                        // This is after the defeat the boss.


                        Quest.questComplete.put(player.getUniqueId(), true);
                        Quest.fightingBoss.remove(player.getUniqueId());
                        SlayerBoss.bossMap.remove(player.getUniqueId());
                        return;
                    }

                    if (event.getEntity().getEquipment().getHelmet().getItemMeta().getDisplayName().equals("ZOMBIE_SLAYER_TIER_2")) {
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "  NICE! SLAYER BOSS SLAIN!");
                        player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "  » " + ChatColor.GRAY + "Talk to Maddox again to collect your reward!");
                        this.playSoundWithDelay(player, 3L, 1);
                        this.playSoundWithDelay(player, 7, 1.5f);
                        this.playSoundWithDelay(player, 11, 2f);
                        event.getDrops().clear();

                        Quest.questComplete.put(player.getUniqueId(), true);
                        Quest.fightingBoss.remove(player.getUniqueId());
                        SlayerBoss.bossMap.remove(player.getUniqueId());
                        return;
                    }

                    if (event.getEntity().getEquipment().getHelmet().getItemMeta().getDisplayName().equals("ZOMBIE_SLAYER_TIER_3")) {
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "  NICE! SLAYER BOSS SLAIN!");
                        player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "  » " + ChatColor.GRAY + "Talk to Maddox again to collect your reward!");
                        this.playSoundWithDelay(player, 3L, 1);
                        this.playSoundWithDelay(player, 7, 1.5f);
                        this.playSoundWithDelay(player, 11, 2f);
                        event.getDrops().clear();

                        Quest.questComplete.put(player.getUniqueId(), true);
                        Quest.fightingBoss.remove(player.getUniqueId());
                        SlayerBoss.bossMap.remove(player.getUniqueId());
                        return;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "That was not your boss! You won't get your quest completed.");
                }
                return;
            }

            if (quest.getType(player).equals(QuestType.ZOMBIE_SLAYER_TIER_1)) {

                if (combat_exp.containsKey(player.getUniqueId())) {
                    if (combat_exp.get(player.getUniqueId()) < 100) {
                        combat_exp.put(player.getUniqueId(), combat_exp.get(player.getUniqueId()) + 5);
                        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10F, 2);
                        Utilities.sendActionbar(player, "&3+5 Combat (" + combat_exp.get(player.getUniqueId()) + "/100)");
                    } else if (combat_exp.get(player.getUniqueId()) >= 100) {

                        if (!(Quest.fightingBoss.containsKey(player.getUniqueId()))) {
                            if (!(Quest.questComplete.containsKey(player.getUniqueId()))) {
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 1);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 4);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 7);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 10);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 13);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 16);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 19);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 22);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 25);

                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        player.sendMessage(ChatColor.GREEN + "The slayer boss has spawned! SLAY THE BOSS!");
                                        player.getWorld().playEffect(event.getEntity().getLocation(), Effect.EXPLOSION_HUGE, 0);
                                        player.playSound(player.getLocation(), Sound.EXPLODE, 10F, 1);
                                        SlayerBoss.spawnSlayerBoss(SlayerBoss.ZOMBIE_SLAYER_TIER_1, event.getEntity().getLocation(), player);
                                        Quest.fightingBoss.put(player.getUniqueId(), true);
                                        player.getWorld().playSound(event.getEntity().getLocation(), Sound.WITHER_SPAWN, 1f, 1f);

                                        // Strating DPS, (damage per second)
                                        // Pretty self explanitory.
                                        Dps dps = new Dps(player, 0.5d);
                                        dps.start();
                                    }
                                }.runTaskLater(Slayers.getPlugin(), 27L);

                                return;
                            }
                        }
                        return;
                    }
                    return;
                }
                combat_exp.put(player.getUniqueId(), 5);
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10F, 0);
                Utilities.sendActionbar(player, "&3+5 Combat (" + combat_exp.get(player.getUniqueId()) + "/100)");

            } else if (quest.getType(player).equals(QuestType.ZOMBIE_SLAYER_TIER_2)) {
                if (combat_exp.containsKey(player.getUniqueId())) {
                    if (combat_exp.get(player.getUniqueId())  < 440) {
                        combat_exp.put(player.getUniqueId(), combat_exp.get(player.getUniqueId()) + 5);
                        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10F, 2);
                        Utilities.sendActionbar(player, "&3+5 Combat (" + combat_exp.get(player.getUniqueId()) + "/440)");
                    } else if (combat_exp.get(player.getUniqueId()) >= 440) {
                        if (!(Quest.fightingBoss.containsKey(player.getUniqueId()))) {
                            if (!(Quest.questComplete.containsKey(player.getUniqueId()))) {
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 1);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 4);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 7);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 10);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 13);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 16);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 19);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 22);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 25);

                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        player.sendMessage(ChatColor.GREEN + "The slayer boss has spawned! SLAY THE BOSS!");
                                        player.getWorld().playEffect(event.getEntity().getLocation(), Effect.EXPLOSION_HUGE, 0);
                                        player.playSound(player.getLocation(), Sound.EXPLODE, 10F, 1);
                                        SlayerBoss.spawnSlayerBoss(SlayerBoss.ZOMBIE_SLAYER_TIER_2, event.getEntity().getLocation(), player);
                                        Quest.fightingBoss.put(player.getUniqueId(), true);
                                        player.getWorld().playSound(event.getEntity().getLocation(), Sound.WITHER_SPAWN, 1f, 1f);

                                        // Strating DPS, (damage per second)
                                        // Pretty self explanitory.
                                        Dps dps = new Dps(player, 0.5d);
                                        dps.start();
                                    }
                                }.runTaskLater(Slayers.getPlugin(), 27L);

                            }
                        }
                    }
                    return;
                }
                combat_exp.put(player.getUniqueId(), 5);
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10F, 0);
                Utilities.sendActionbar(player, "&3+5 Combat (" + combat_exp.get(player.getUniqueId()) + "/670)");

            } else if (quest.getType(player).equals(QuestType.ZOMBIE_SLAYER_TIER_3)) {
                if (combat_exp.containsKey(player.getUniqueId())) {
                    if (combat_exp.get(player.getUniqueId())  < 670) {
                        combat_exp.put(player.getUniqueId(), combat_exp.get(player.getUniqueId()) + 5);
                        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10F, 2);
                        Utilities.sendActionbar(player, "&3+5 Combat (" + combat_exp.get(player.getUniqueId()) + "/670)");
                    } else if (combat_exp.get(player.getUniqueId()) >= 670) {
                        if (!(Quest.fightingBoss.containsKey(player.getUniqueId()))) {
                            if (!(Quest.questComplete.containsKey(player.getUniqueId()))) {
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 1);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 4);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 7);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 10);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 13);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 16);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 19);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 22);
                                playSoundWithDelay(player, Sound.WITHER_SHOOT, 1f, 1f, 25);

                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        player.sendMessage(ChatColor.GREEN + "The slayer boss has spawned! SLAY THE BOSS!");
                                        player.getWorld().playEffect(event.getEntity().getLocation(), Effect.EXPLOSION_HUGE, 0);
                                        player.playSound(player.getLocation(), Sound.EXPLODE, 10F, 1);
                                        SlayerBoss.spawnSlayerBoss(SlayerBoss.ZOMBIE_SLAYER_TIER_3, event.getEntity().getLocation(), player);
                                        Quest.fightingBoss.put(player.getUniqueId(), true);
                                        player.getWorld().playSound(event.getEntity().getLocation(), Sound.WITHER_SPAWN, 1f, 1f);

                                        // Strating DPS, (damage per second)
                                        // Pretty self explanitory.
                                        Dps dps = new Dps(player, 0.5d);
                                        dps.start();
                                    }
                                }.runTaskLater(Slayers.getPlugin(), 27L);

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

    private void playSoundWithDelay(Player player, long delay, float pitch) {
        new BukkitRunnable() {

            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1f, pitch);
            }
        }.runTaskLater(Slayers.getPlugin(), delay);
    }

    private void playSoundWithDelay(Player player, Sound sound, float vol, float pitch, long delay) {
        new BukkitRunnable() {

            @Override
            public void run() {
                player.playSound(player.getLocation(), sound, vol, pitch);
            }
        }.runTaskLater(Slayers.getPlugin(), delay);
    }
}
