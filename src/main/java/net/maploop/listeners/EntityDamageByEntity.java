package net.maploop.listeners;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import net.maploop.Slayers;
import net.maploop.bosses.SlayerBoss;
import net.maploop.item.items.RevenantFalchion;
import net.maploop.util.Utilities;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.Arrays;

public class EntityDamageByEntity implements Listener {
    public boolean yes = true;
    public float damage = 0;
    public boolean crit = RevenantFalchion.getCrit();

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Slayers plugin = Slayers.getPlugin();
        if (event.getEntity() instanceof NPC) return;
        new BukkitRunnable() {

            @Override
            public void run() {
                if (!yes) return;
                if (plugin.getConfig().getBoolean("damage-indicator")) {
                    if (event.getEntity().getType() == EntityType.ARMOR_STAND) return;
                    if (damage > 0) {
                        addIndicator(damage, Utilities.getRandomLocation(event.getEntity().getLocation(), 2), event.getCause());
                    } else {
                        addIndicator(event.getDamage(), Utilities.getRandomLocation(event.getEntity().getLocation(), 2), event.getCause());
                    }
                }

                Entity entity = event.getEntity();
                if (event.getEntity() instanceof Player) return;
                if (event.getEntity().isDead()) return;
                LivingEntity e = (LivingEntity) event.getEntity();
                if (!(e.getEquipment().getHelmet().hasItemMeta())) return;
                if (e.getEquipment().getHelmet().getItemMeta().getDisplayName().contains("SLAYER")) {
                    if (!(SlayerBoss.bossHealth.get(entity) <= 0)) {
                        e.setHealth(e.getMaxHealth());
                        double rawDamage;
                        if (damage > 0) {
                            rawDamage = SlayerBoss.bossHealth.get(entity) - damage;
                            SlayerBoss.bossHealth.put(entity, rawDamage);
                            return;
                        }
                        rawDamage = SlayerBoss.bossHealth.get(entity) - event.getDamage();
                        SlayerBoss.bossHealth.put(entity, rawDamage);
                    } else {
                        e.setHealth(0);
                        entity.remove();
                        SlayerBoss.bossHealth.remove(entity);
                        Bukkit.getPluginManager().callEvent(new EntityDeathEvent(e, Arrays.asList(null, null), 5));
                    }
                }
            }
        }.runTaskLater(Slayers.getPlugin(), 1);
    }

    public String addCritTexture(String str) {
        String new_string = null;
        if (str.length() == 1)
            new_string = "§f✧§f" + str + "§f✧";
        if (str.length() == 2)
            new_string = "§f✧" + String.valueOf(str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§c✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        if (str.length() == 3)
            new_string = "§f✧" + String.valueOf(str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        if (str.length() == 4)
            new_string = "§f✧" + String.valueOf(str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + "§c✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        if (str.length() == 5)
            new_string = "§f✧" + String.valueOf(str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + str.charAt(4) + "§f✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        if (str.length() == 6)
            new_string = "§f✧" + String.valueOf(str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + str.charAt(4) + str.charAt(5) + "§f✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        if (str.length() == 7)
            new_string = "§f✧" + String.valueOf(str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + str.charAt(4) + str.charAt(5) + str.charAt(6) + "§f✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        return new_string;
    }

    public void addIndicator(double damage, Location loc, EntityDamageEvent.DamageCause cause) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formatted = formatter.format(damage);

        EntityArmorStand Indicator = new EntityArmorStand(((CraftWorld) loc.getWorld()).getHandle(), loc.getX(), loc.getY(), loc.getZ());
        Indicator.setCustomNameVisible(true);
        Indicator.setGravity(false);
        Indicator.setInvisible(true);
        Indicator.setSmall(true);
        Indicator.setSize(1, 1);
        Indicator.setAirTicks(20);
        NBTEntity nbtas = new NBTEntity(Indicator.getBukkitEntity());
        nbtas.setBoolean("Invulnerable", true);

        if (cause == EntityDamageEvent.DamageCause.FIRE_TICK || cause == EntityDamageEvent.DamageCause.FIRE) {
            Indicator.setCustomName("§6" + damage);
        } else {
            if (damage < 600) {
                Indicator.setCustomName("§7" + damage);
            } else if (damage > 600) {
                Indicator.setCustomName(addCritTexture(formatted));
            }
        }

        PacketPlayOutSpawnEntityLiving pakcet = new PacketPlayOutSpawnEntityLiving(Indicator);
        PacketPlayOutEntityDestroy removePacket = new PacketPlayOutEntityDestroy(Indicator.getId());
        for (Player players : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) players).getHandle().playerConnection.sendPacket(pakcet);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    ((CraftPlayer) players).getHandle().playerConnection.sendPacket(removePacket);
                }
            }
        }.runTaskLater(Slayers.getPlugin(), 10);
    }

    /*
    ArmorStand indicator = (ArmorStand) entity.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
            indicator.setCustomNameVisible(true);
            indicator.setGravity(false);
            indicator.setVisible(false);
            indicator.setSmall(true);
            indicator.setOp(true);
            if (event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK || event.getCause() == EntityDamageEvent.DamageCause.FIRE) {
                indicator.setCustomName("§6" + event.getDamage());
            } else {
                if (event.getDamage() < 10) {
                    indicator.setCustomName("§7" + event.getDamage());
                } else if (event.getDamage() > 10) {
                    indicator.setCustomName(Utilities.color("&f✧ &3" + formatted + " &f✧"));
                }
            }
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        indicator.remove();
                    }
                }.runTaskLater(plugin, 10);
                return;
     */
}
