package net.deemu.slayers.Commands;

import net.deemu.slayers.Bosses.SlayerBoss;
import net.deemu.slayers.MenuSystem.PlayerMenuUtility;
import net.deemu.slayers.MenuSystem.QuestMenu;
import net.deemu.slayers.MenuSystem.SlayersMenu;
import net.deemu.slayers.NPC.MaddoxTheSlayer;
import net.deemu.slayers.Quests.Quest;
import net.deemu.slayers.Quests.QuestType;
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
        }
        return false;
    }
}
