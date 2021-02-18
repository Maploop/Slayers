package net.deemu.slayers.commands;

import net.deemu.slayers.bosses.SlayerBoss;
import net.deemu.slayers.menus.PlayerMenuUtility;
import net.deemu.slayers.menus.QuestMenu;
import net.deemu.slayers.menus.SlayerShopMenu;
import net.deemu.slayers.menus.SlayersMenu;
import net.deemu.slayers.npcs.MaddoxTheSlayer;
import net.deemu.slayers.quests.Quest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                if (Quest.quest.containsKey(player.getUniqueId())) {
                    new QuestMenu(new PlayerMenuUtility(player)).open();
                } else {
                    new SlayersMenu(new PlayerMenuUtility(player)).open();
                }
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
                new QuestMenu(new PlayerMenuUtility(player)).open();
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
        }
        return false;
    }
}
