package net.maploop.bosses;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import net.maploop.Slayers;
import net.maploop.util.Utilities;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityTypes;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.lang.reflect.Field;
import java.util.*;

public enum SlayerBoss {
    ZOMBIE_SLAYER_TIER_1(1, 1),
    ZOMBIE_SLAYER_TIER_2(2, 2),
    ZOMBIE_SLAYER_TIER_3(3, 3),
    ZOMBIE_SLAYER_TIER_4(4, 4),

    SPIDER_SLAYER_TIER_1(5, 1),
    SPIDER_SLAYER_TIER_2(6, 2),
    SPIDER_SLAYER_TIER_3(7, 3),
    SPIDER_SLAYER_TIER_4(8, 4),

    WOLF_SLAYER_TIER_1(9, 1),
    WOLF_SLAYER_TIER_2(10, 2),
    WOLF_SLAYER_TIER_3(11, 3),
    WOLF_SLAYER_TIER_4(12, 4);

    public static HashMap<UUID, Entity> bossMap = new HashMap<>();
    public static Map<Entity, Double> bossHealth = new HashMap<>();

    private final int id;
    private final int tier;

    SlayerBoss(int id, int tier) {
        this.id = id;
        this.tier = tier;
    }

    public static Map<Entity, ArmorStand> tag = new HashMap<>();

    public static void playBossSpawnEffect(Location loc) {
        for(int i = 0; i < 50; ++i) {
            playEffectWithDelay(loc.add(0, 1, 0), i);
            playSoundWithDelay(loc, i);
        }
    }

    private static void playEffectWithDelay(Location loc, long delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                loc.getWorld().playEffect(loc, Effect.POTION_SWIRL, 20, 20);
                loc.getWorld().playEffect(loc, Effect.MAGIC_CRIT, 20, 20);
            }
        }.runTaskLater(Slayers.getPlugin(), delay);
    }

    private static void playSoundWithDelay(Location loc, long delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                loc.getWorld().playSound(loc, Sound.WITHER_SHOOT, 1f, 1.5f);
            }
        }.runTaskLater(Slayers.getPlugin(), delay);
    }

    public static void spawnSlayerBoss(SlayerBoss bossType, Location loc, Player player) {
        ItemStack hoe = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta hoeMeta = hoe.getItemMeta();
        hoeMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, false);
        hoe.setItemMeta(hoeMeta);
        if(bossType == null) return;
        if(bossType.equals(SlayerBoss.ZOMBIE_SLAYER_TIER_1)) {
            Zombie boss1 = (Zombie) player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
            boss1.getEquipment().setHelmet(makeSkullItem("ZOMBIE_SLAYER_TIER_1", "JoinFUB", 1));
            boss1.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            boss1.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            boss1.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
            boss1.getEquipment().setItemInHand(hoe);
            boss1.setBaby(false);
            boss1.setMaxHealth(100);
            boss1.setHealth(100);
            boss1.setTarget(player);
            boss1.setVillager(false);
            boss1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 7), false);
            boss1.setVelocity(new Vector(0, 0.5, 0));
            boss1.setFallDistance(0);
            bossMap.put(player.getUniqueId(), boss1);
            bossHealth.put(boss1, 500d);

            ArmorStand name = (ArmorStand) player.getWorld().spawnEntity(boss1.getLocation().add(0, +1, 0), EntityType.ARMOR_STAND);
            name.setCustomNameVisible(true);
            name.setCustomName("§c☠ §bRevenant Horror §a" + boss1.getHealth() + "§7/§a" + boss1.getMaxHealth());
            name.setVisible(false);
            name.setGravity(false);
            NBTEntity nbtas = new NBTEntity(name);
            nbtas.setBoolean("Invulnerable", true);
            tag.put(boss1, name);

            int i = 1;
            i = Bukkit.getScheduler().scheduleSyncRepeatingTask(Slayers.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    if(tag.containsKey(boss1)) {
                        if(!(boss1.isDead())) {
                            Location l = boss1.getLocation().add(0, 0.3, 0);
                            name.setCustomName("§c☠ §bRevenant Horror §a" + Math.round(bossHealth.get(boss1)) + "§c ❤");
                            boss1.getWorld().playEffect(boss1.getLocation(), Effect.MAGIC_CRIT, 3);
                            name.teleport(l);
                            boss1.getWorld().playEffect(boss1.getLocation(), Effect.HEART, 2);
                        } else {
                            tag.remove(boss1);
                            name.remove();
                        }
                    }
                }
            }, 0L, 1L);
        }

        if (bossType == SlayerBoss.ZOMBIE_SLAYER_TIER_2) {
            Zombie boss = (Zombie) player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);

            boss.getEquipment().setHelmet(makeSkullItem("ZOMBIE_SLAYER_TIER_2", "JoinFUB", 1));
            boss.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            boss.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            boss.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
            boss.getEquipment().setItemInHand(hoe);
            boss.setBaby(false);
            boss.setMaxHealth(2000);
            boss.setHealth(2000);
            boss.setTarget(player);
            boss.setVillager(false);
            boss.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 7), false);
            boss.setVelocity(new Vector(0, 0.5, 0));
            boss.setFallDistance(0);
            bossMap.put(player.getUniqueId(), boss);
            bossHealth.put(boss, 20000d);

            ArmorStand name = (ArmorStand) player.getWorld().spawnEntity(boss.getLocation().add(0, +1, 0), EntityType.ARMOR_STAND);
            name.setCustomNameVisible(true);
            name.setCustomName("§c☠ §bRevenant Horror §a" + boss.getHealth() + "§7/§a" + boss.getMaxHealth());
            name.setVisible(false);
            name.setGravity(false);
            NBTEntity nbtas = new NBTEntity(name);
            nbtas.setBoolean("Invulnerable", true);
            tag.put(boss, name);

            int i = 1;
            i = Bukkit.getScheduler().scheduleSyncRepeatingTask(Slayers.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    if (tag.containsKey(boss)) {
                        if (!(boss.isDead())) {
                            Location l = boss.getLocation().add(0, 0.3, 0);
                            if (Math.round(boss.getHealth()) < 1000) {
                                name.setCustomName("§c☠ §bRevenant Horror §a" + Math.round(bossHealth.get(boss)) + "§c ❤");
                            } else if (boss.getHealth() >= 1000) {
                                name.setCustomName("§c☠ §bRevenant Horror §a" + Utilities.formatValue(bossHealth.get(boss)) + "§c ❤");
                            }
                            boss.getWorld().playEffect(boss.getLocation(), Effect.MAGIC_CRIT, 3);
                            name.teleport(l);
                            boss.getWorld().playEffect(boss.getLocation(), Effect.HEART, 2);
                        } else {
                            tag.remove(boss);
                            name.remove();
                        }
                    }
                }
            }, 0L, 1L);
        }
        if (bossType == SlayerBoss.ZOMBIE_SLAYER_TIER_3) {
            Zombie boss = (Zombie) player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);

            boss.getEquipment().setHelmet(makeSkullItem("ZOMBIE_SLAYER_TIER_3", "JoinFUB", 1));
            boss.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            boss.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            boss.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
            boss.getEquipment().setItemInHand(hoe);
            boss.setBaby(false);
            boss.setMaxHealth(2000);
            boss.setHealth(2000);
            boss.setTarget(player);
            boss.setVillager(false);
            boss.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 7), false);
            boss.setVelocity(new Vector(0, 0.5, 0));
            boss.setFallDistance(0);
            bossMap.put(player.getUniqueId(), boss);
            bossHealth.put(boss, 400000d);

            ArmorStand name = (ArmorStand) player.getWorld().spawnEntity(boss.getLocation().add(0, +1, 0), EntityType.ARMOR_STAND);
            name.setCustomNameVisible(true);
            name.setCustomName("§c☠ §bRevenant Horror §a" + boss.getHealth() + "§7/§a" + boss.getMaxHealth());
            name.setVisible(false);
            name.setGravity(false);
            NBTEntity nbtas = new NBTEntity(name);
            nbtas.setBoolean("Invulnerable", true);
            tag.put(boss, name);

            int i = 1;
            i = Bukkit.getScheduler().scheduleSyncRepeatingTask(Slayers.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    if (tag.containsKey(boss)) {
                        if (!(boss.isDead())) {
                            Location l = boss.getLocation().add(0, 0.3, 0);
                            if (Math.round(boss.getHealth()) < 1000) {
                                name.setCustomName("§c☠ §bRevenant Horror §a" + Math.round(bossHealth.get(boss)) + "§c ❤");
                            } else if (boss.getHealth() >= 1000) {
                                name.setCustomName("§c☠ §bRevenant Horror §a" + Utilities.formatValue(bossHealth.get(boss)) + "§c ❤");
                            }
                            boss.getWorld().playEffect(boss.getLocation(), Effect.MAGIC_CRIT, 3);
                            name.teleport(l);
                            boss.getWorld().playEffect(boss.getLocation(), Effect.HEART, 2);
                        } else {
                            tag.remove(boss);
                            name.remove();
                        }
                    }
                }
            }, 0L, 1L);
        }
    }

    public static void registerEntity(String name, int id, Class<? extends EntityInsentient> customClass) {
        try {

            /**
             * First, we make a list of all HashMap's in the EntityTypes class
             * by looping through all fields. I am using reflection here so we
             * have no problems later when Mojang changes the field's name. By
             * creating a list of these maps we can easily modify them later on.
             */
            List<Map<?, ?>> dataMaps = new ArrayList<Map<?, ?>>();
            for (Field f : EntityTypes.class.getDeclaredFields()) {
                if (f.getType().getSimpleName().equals(Map.class.getSimpleName())) {
                    f.setAccessible(true);
                    dataMaps.add((Map<?, ?>) f.get(null));
                }
            }

            // we need maps d (1) and f (3)

            ((Map<Class<? extends EntityInsentient>, String>) dataMaps.get(1)).put(customClass, name);
            ((Map<Class<? extends EntityInsentient>, Integer>) dataMaps.get(3)).put(customClass, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ItemStack makeSkullItem(String displayname, String owner, int amount, String... lore) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(owner);
        meta.setLore(Arrays.asList(lore));
        meta.setDisplayName(displayname);
        item.setItemMeta(meta);

        return item;
    }

    // JoinFUB
}
