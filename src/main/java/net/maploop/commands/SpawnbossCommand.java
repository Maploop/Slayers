package net.maploop.commands;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.maploop.Slayers;
import net.maploop.util.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SpawnbossCommand implements CommandExecutor {
    private Map<NPC, ArmorStand> nametag = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;


        if (s.equalsIgnoreCase("spawnboss")) {
            if (player.hasPermission("spawnboss.use")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Please specify a boss!");
                    return true;
                }
                if (args[0].equalsIgnoreCase("CRAFT_UNDEAD_MAPLOOP")) {
                    NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§8[§7Lv" + args[1] + "§8] §cMaploop");
                    npc.setName("§f");
                    npc.data().set(NPC.PLAYER_SKIN_UUID_METADATA, "Maploop");

                    ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
                    stand.setSmall(true);
                    stand.setVisible(false);
                    stand.setGravity(false);
                    stand.setCustomNameVisible(true);
                    nametag.put(npc, stand);
                    npc.spawn(player.getLocation());
                    Entity[] target = (npc.getStoredLocation().getWorld().getNearbyEntities(npc.getStoredLocation(), 10, 10, 10)).toArray(new Entity[0]);
                    int r = Utilities.getRandomInteger(npc.getStoredLocation().getWorld().getNearbyEntities(npc.getStoredLocation(), 10, 10 ,10).size());

                    npc.getNavigator().getLocalParameters().attackRange(5.0).attackDelayTicks(15);
                    npc.getNavigator().setTarget(target[r], true);

                    Bukkit.getScheduler().scheduleSyncRepeatingTask(Slayers.getPlugin(), new Runnable() {
                        @Override
                        public void run() {
                            if (npc.isSpawned()) {
                                if (nametag.containsKey(npc)) {
                                    stand.setCustomName("§8[§7Lv" + args[1] + "§8] §cMaploop");
                                    nametag.get(npc).teleport(npc.getEntity().getLocation().add(0, 1, 0));
                                    if (!(npc.getNavigator().isNavigating())) {
                                        npc.getNavigator().setTarget(player, true);
                                    }
                                }
                            } else {
                                stand.remove();
                                nametag.remove(npc);
                            }
                        }
                    }, 0L, (long) 0.1);

                    player.sendMessage(ChatColor.GREEN + "Successfully spawned §e" + args[0]);
                }

            } else {
                player.sendMessage(ChatColor.RED + "You must be admin or higher to use that command!");
            }
        }

        return false;
    }
}
