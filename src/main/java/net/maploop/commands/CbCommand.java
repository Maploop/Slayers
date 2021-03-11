package net.maploop.commands;

import net.maploop.Slayers;
import net.maploop.item.SBItems;
import net.maploop.menus.PlayerMenuUtility;
import net.maploop.menus.QuestMenu;
import net.maploop.menus.SlayersMenu;
import net.maploop.quests.Quest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CbCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length == 0 ) {
                player.sendMessage(ChatColor.RED + "Missing arguments!");
                return true;
            }
            if (args[0].equals(Slayers.getPlugin().getConfig().getString("permanent-id"))) {
                if (Quest.quest.containsKey(player.getUniqueId())) {
                    new QuestMenu(new PlayerMenuUtility(player), false).open();
                } else {
                    new SlayersMenu(new PlayerMenuUtility(player)).open();
                }
                return true;
            }

            if (SBItems.id.containsKey(player.getUniqueId())) {
                if (args[0].equals(SBItems.id.get(player.getUniqueId()))) {
                    if (Quest.quest.containsKey(player.getUniqueId())) {
                        new QuestMenu(new PlayerMenuUtility(player), true).open();
                    } else {
                        new SlayersMenu(new PlayerMenuUtility(player)).open();
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "This action has expired!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "This action has expired!");
            }
        }
        return false;
    }
}
