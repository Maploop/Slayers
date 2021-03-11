package net.maploop.commands;

import net.maploop.Slayers;
import net.maploop.files.DataFile;
import net.maploop.item.CustomItem;
import net.maploop.item.ItemUtilities;
import net.maploop.menus.*;
import net.maploop.bosses.SlayerBoss;
import net.maploop.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import javax.xml.crypto.Data;
import java.util.Collections;

public class SlayerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;
        if (s.equalsIgnoreCase("slayer")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.GREEN + "Success!");
                return true;
            }
            if (args[0].equalsIgnoreCase("menu")) {
                player.sendMessage(ChatColor.RED + "This command is not supported anymore. Use /cb ID to open the menu (with a valid id)");
            }
            if (args[0].equalsIgnoreCase("npc")) {
                if (player.hasPermission("slayers.admin")) {
                    /*
                    DataFile.get().set("npc-location.world", player.getWorld().getName());
                    DataFile.get().set("npc-location.x", player.getLocation().getX());
                    DataFile.get().set("npc-location.y", player.getLocation().getY());
                    DataFile.get().set("npc-location.z", player.getLocation().getZ());
                    DataFile.get().set("npc-location.yaw", player.getLocation().getYaw());
                    DataFile.get().set("npc-location.pitch", player.getLocation().getPitch());
                    DataFile.save();

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            double x = DataFile.get().getDouble("npc-location.x");
                            double y = DataFile.get().getDouble("npc-location.y");
                            double z = DataFile.get().getDouble("npc-location.z");
                            String rawWorld = DataFile.get().getString("npc-location.world");
                            World world = Bukkit.getServer().getWorld(rawWorld);
                            float yaw = DataFile.get().getInt("npc-location.yaw");
                            float pitch = DataFile.get().getInt("npc-location.pitch");

                            net.maploop.npc.NPC npc = new NPC("§cMaddox", new Location(world, x, y, z, yaw, pitch), "ewogICJ0aW1lc3RhbXAiIDogMTYxNTM3OTU3NDUyMiwKICAicHJvZmlsZUlkIiA6ICI4MzdjZjgzZGJiZGU0OTUxOWI1ZGEzZTZhY2E1ZDBkMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJGYXJkaW5fTmV0aGVyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzkzMzZkN2NjOTVjYmY2Njg5ZjVlOGM5NTQyOTRlYzhkMWVmYzQ5NGE0MDMxMzI1YmI0MjdiYzgxZDU2YTQ4NGQiCiAgICB9CiAgfQp9",
                                    "Ehy6Fvkqqmw0dj9K82Lk8Gucgo29oKw4vfRp60XBnW62yn72TcBsVNDNxvn8L81S+WyI4dRq+Pwwf9apCTTxGv1SmK+qJKIviwIB3So63F7Wn/yNV3hl4Jp4GNaHGpW3yZxO/fn/SPKNDrd3nYOPsnyZ5Dk/gVNGJ2Z8VhvAH10SuIdFVWUSvVKUknaS5Ebxp831RvM1vHM+wIzZvcsBGJqj1CyUaL2nVu2VcMFArQDKAkU9wePiLrjUyb/UXygxC4INeeANFr3C8/vzS720M65UZ7UZP3JPCzBOBHtODzi++IpFGzvoAfXkMKxvsJdEoB65UFmbrATUBIzD5Cy/YYypNE3+4FjuM6ijRYMmNAASetH1PhnsVHbUpnEzxF3tcRf3tAs0u+mBAlDZ76QbS2kJgYIpaUq8tw8BYTe7BDRG9a5YZxQRTihhnoEBO4OLaqq9KNvfqjx8CjRtdKU5l+yBU/bFcLj1IXp3z10GUPVkyHRp4vEVQQ6LwHVFsCQ2GTYyVA/E7Sd7XfYD5sMBVS7TtY9NXo/mMsRZLEJ/k3wPtGZvSjVfX6uhzO6dVDCQjDTuzRFZLf3RhYmkRQhZaGDqSb4lYfDZY4vaw0+a0DWaC4KUd6/+R2VezIbu8/Xb5VbGeOGNRP6RTEuOPgefI7oMKZaaozHPjexZauHVbRE=");
                            npc.createNPC();
                        }
                    }.runTaskLater(Slayers.getPlugin(), 3L);

                    player.sendMessage(ChatColor.GREEN + "NPC was made and spawned!");
                     */
                } else {
                    player.sendMessage(ChatColor.RED + "You cannot do this!");
                }
            }
            if (args[0].equalsIgnoreCase("myquest")) {
                player.sendMessage(ChatColor.RED + "This command is not supported anymore.");
            }
            if (args[0].equalsIgnoreCase("spawnboss")) {
                if (player.hasPermission("slayers.admin")) {
                    player.sendMessage("§aSuccess!");
                    SlayerBoss.spawnSlayerBoss(SlayerBoss.valueOf(args[1]), player.getLocation(), player);
                } else {
                    player.sendMessage("§cYou cannot do this!");
                }
            }
            if (args[0].equalsIgnoreCase("shop")) {
                new SlayerShopMenu(new PlayerMenuUtility(player)).open();
            }
            if (args[0].equalsIgnoreCase("item")) {
                if (player.hasPermission("slayers.admin")) {
                    give(commandSender, args);
                } else {
                    player.sendMessage(ChatColor.RED + "You do not have the permission to use this!");
                }
            }
            if (args[0].equalsIgnoreCase("olditemsmenu")) {
                if (player.hasPermission("slayers.admin")) {
                    new ItemsMenu(new PlayerMenuUtility(player)).open();
                } else {
                    player.sendMessage(ChatColor.RED + "You do not have the permission to use this!");
                }
            }
        }
        return false;
    }

    private void give(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            ItemUtilities.warnPlayer(sender, Collections.singletonList("Only Players"));
            return;
        }
        Player player = (Player) sender;
        int stack = 1;
        if (args.length > 1)
            stack = Integer.parseInt(args[1]);
        ItemStack itemStack = CustomItem.fromString(Slayers.getPlugin(), args[1], stack);
        if (itemStack == null) {
            ItemUtilities.warnPlayer(sender, Collections.singletonList("No SB Item"));
            return;
        }
        player.getInventory().addItem(itemStack);
        player.sendMessage(ChatColor.GREEN + "Given " + player.getName() + " item " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + ".");
    }
}
