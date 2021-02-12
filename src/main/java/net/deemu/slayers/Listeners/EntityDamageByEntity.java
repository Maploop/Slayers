package net.deemu.slayers.Listeners;

import net.deemu.slayers.Slayers;
import net.deemu.slayers.Utilities;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EntityDamageByEntity implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Slayers plugin = Slayers.getPlugin();
        if (plugin.getConfig().getBoolean("damage-indicator")) {
            if (!(event.getDamager() instanceof Player)) return;
            Entity entity = event.getEntity();

            int random = Utilities.getRandomInteger(2);

            if (random == 0) {
                int randomX = Utilities.getRandomInteger(2);
                ArmorStand indicator = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation().add(randomX, 0, 0), EntityType.ARMOR_STAND);
                indicator.setCustomNameVisible(true);
                indicator.setGravity(false);
                indicator.setVisible(false);
                indicator.setSmall(true);
                if(event.getDamage() < 10) {
                    indicator.setCustomName("§c❤ §a" + String.valueOf(event.getDamage() + "§c❤"));
                } else if (event.getDamage() > 10) {
                    indicator.setCustomName("§c❤ §e" + String.valueOf(event.getDamage() + "§c❤"));
                }
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        indicator.remove();
                    }
                }.runTaskLater(plugin, 10);
                return;
            }

            if (random == 1) {
                int randomX = Utilities.getRandomInteger(2);
                ArmorStand indicator = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation().add(randomX, 0, 0), EntityType.ARMOR_STAND);
                indicator.setCustomNameVisible(true);
                indicator.setGravity(false);
                indicator.setVisible(false);
                indicator.setSmall(true);
                if(event.getDamage() < 10) {
                    indicator.setCustomName("§c❤ §a" + String.valueOf(event.getDamage() + "§c❤"));
                } else if (event.getDamage() > 10) {
                    indicator.setCustomName("§c❤ §e" + String.valueOf(event.getDamage() + "§c❤"));
                }
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        indicator.remove();
                    }
                }.runTaskLater(plugin, 10);
                return;
            }
            if (random == 2) {
                int randomZ = Utilities.getRandomInteger(2);
                ArmorStand indicator = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation().add(0, 0, randomZ), EntityType.ARMOR_STAND);
                indicator.setCustomNameVisible(true);
                indicator.setGravity(false);
                indicator.setVisible(false);
                indicator.setSmall(true);
                if(event.getDamage() < 10) {
                    indicator.setCustomName("§c❤ §a" + String.valueOf(event.getDamage() + "§c❤"));
                } else if (event.getDamage() > 10) {
                    indicator.setCustomName("§c❤ §e" + String.valueOf(event.getDamage() + "§c❤"));
                }

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        indicator.remove();
                    }
                }.runTaskLater(plugin, 10);
            }
        }
    }
}
