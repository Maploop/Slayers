package net.maploop.commands;

import net.maploop.Slayers;
import net.maploop.item.CustomItem;
import net.maploop.item.ItemUtilities;
import net.maploop.menus.*;
import net.maploop.quests.Quest;
import net.maploop.bosses.SlayerBoss;
import net.maploop.npc.MaddoxTheSlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
                    if (args[1].length() != 0) {
                        MaddoxTheSlayer.createNPC(player, args[1]);
                        player.sendMessage(ChatColor.GREEN + "Success!");
                        return true;
                    } else {
                        MaddoxTheSlayer.createNPC(player, player.getName());
                        player.sendMessage(ChatColor.GREEN + "Success!");
                    }
                    player.sendMessage(ChatColor.GREEN + "Success!");
                } else {
                    player.sendMessage(ChatColor.RED + "You cannot do this!");
                }
            }
            if(args[0].equalsIgnoreCase("myquest")) {
                player.sendMessage(ChatColor.RED + "This command is not supported anymore.");
            }
            if(args[0].equalsIgnoreCase("spawnboss")) {
                if(player.hasPermission("slayers.admin")) {
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
                give(commandSender, args);
            }
            if (args[0].equalsIgnoreCase("olditemsmenu")) {
                new ItemsMenu(new PlayerMenuUtility(player)).open();
            }
        }
        return false;
    }

    private void give(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            ItemUtilities.warnPlayer(sender, Collections.singletonList("Only Players"));
            return;
        }
        Player player = (Player)sender;
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
