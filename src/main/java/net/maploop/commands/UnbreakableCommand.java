package net.maploop.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class UnbreakableCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;
        if (player.hasPermission("unbreakable.use")) {
            if(strings.length == 0) {
                player.sendMessage(ChatColor.RED + "Usage: /unbreakable -h <true/false>");
                return true;
            }
            if(strings[0].equalsIgnoreCase("-h")) {
                if(strings[1].length() < 1) {
                    player.sendMessage(ChatColor.RED + "Please say true or false!");
                    return true;
                }
                ItemStack item = player.getItemInHand();
                if(item == null) {
                    player.sendMessage(ChatColor.RED + "You must hold an item to make it unbreakable!");
                    return true;
                }
                ItemMeta meta = item.getItemMeta();
                meta.spigot().setUnbreakable(true);
                if(strings[1].equalsIgnoreCase("true")) {
                    meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                }
                item.setItemMeta(meta);
                player.setItemInHand(item);
            }
        }
        return false;
    }
}
