package net.deemu.slayers.Bosses;

import net.deemu.slayers.Enums.Message;
import net.deemu.slayers.Slayers;
import net.deemu.slayers.Utilities.Utilities;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.util.HashMap;
import java.util.Map;

public class MagmabossCommand implements CommandExecutor {
    private Map<MagmaCube, ArmorStand> tag = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;
        if (s.equalsIgnoreCase("magmaboss")) {
            if (player.hasPermission("magmaboss.use")) {
                player.sendMessage(ChatColor.GREEN + "Spawned!");
                World world = player.getWorld();
                MagmaCube boss = (MagmaCube) world.spawnEntity(player.getLocation(), EntityType.MAGMA_CUBE);
                boss.setSize(50);

                ArmorStand name = (ArmorStand) boss.getWorld().spawnEntity(boss.getLocation().add(0, 30, 0), EntityType.ARMOR_STAND);
                name.setCustomNameVisible(true);
                name.setCustomName("§8[§7100§8] §cMagma Boss §a" + Utilities.formatValue(Math.round(boss.getHealth())) + "§7/§a" + Utilities.formatValue(Math.round(boss.getMaxHealth())));
                name.setVisible(false);
                name.setOp(true);
                tag.put(boss, name);
                int i = 1;
                i = Bukkit.getScheduler().scheduleSyncRepeatingTask(Slayers.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        if (tag.containsKey(boss)) {
                            if (!(boss.isDead())) {
                                name.setCustomName("§8[§7100§8] §cMagma Boss §a" + Utilities.formatValue(Math.round(boss.getHealth())) + "§7/§a" + Utilities.formatValue(Math.round(boss.getMaxHealth())));
                                name.teleport(boss.getLocation().add(0, 30, 0));
                            } else {
                                tag.remove(boss);
                                name.remove();
                            }
                        }
                    }
                }, 0L, 1L);
            } else {
                player.sendMessage(String.valueOf(Message.NO_PERMISSION));
            }
        }
        return false;
    }
}
