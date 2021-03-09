package net.maploop.commands;

import net.maploop.data.VaultData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Class won't contain comments.
 * @author Mapllop
 */

public class PrivatevaultCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (label.equalsIgnoreCase("privatevault") || label.equalsIgnoreCase("pv")) {
                Inventory inv = Bukkit.createInventory(player, 36, player.getName() + "'s Private Vault");

                if (VaultData.getData().containsKey(player.getUniqueId().toString())) {
                    inv.setContents(VaultData.getData().get(player.getUniqueId().toString()));
                    player.openInventory(inv);
                    return true;
                }
                player.openInventory(inv);
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "No!");
        }
        return false;
    }
}
