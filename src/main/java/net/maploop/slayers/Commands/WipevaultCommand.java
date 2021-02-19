package net.maploop.slayers.Commands;

import net.maploop.slayers.DataManagers.VaultData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WipevaultCommand implements CommandExecutor {
    private boolean success;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        if (s.equalsIgnoreCase("wipevault")) {
            if (player.hasPermission("wipevault.use")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Correct usage: /wipevault <offline player>");
                } else {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                    player.sendMessage(ChatColor.GRAY + "Attempting to wipe " + target.getName() + "'s vault data...");
                    if (target.isOnline()) {
                        wipeVault(target);
                        if (success) {
                            player.sendMessage(ChatColor.GREEN + "Wiping " + target.getName() + "'s vault:");
                            player.sendMessage(ChatColor.YELLOW + "Success: §atrue");
                            player.sendMessage("§eFail: §cfalse");
                            player.sendMessage("§eWarnings Count: §a0");
                            player.sendMessage("§eWarnings: {}");
                            player.playSound(player.getLocation(), Sound.LEVEL_UP, 10F, 1.5F);
                        } else {
                            player.sendMessage(ChatColor.GREEN + "Wiping " + target.getName() + "'s vault:");
                            player.sendMessage(ChatColor.YELLOW + "Success: §cfalse");
                            player.sendMessage("§eFail: §atrue");
                            player.sendMessage("§eWarnings Count: §c3");
                            player.sendMessage("§eWarnings: ");
                            player.sendMessage("§8 - §cThis player does not exist!");
                            player.sendMessage("§8 - §cThis player is offline!");
                            player.sendMessage("§8 - §cThis player does not have vault data!");
                            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10F, 1.5F);
                        }
                        return true;
                    }
                    wipeVault(target);
                    if (success) {
                        player.sendMessage(ChatColor.GREEN + "Wiping " + target.getName() + "'s vault:");
                        player.sendMessage(ChatColor.YELLOW + "Success: §atrue");
                        player.sendMessage("§eFail: §cfalse");
                        player.sendMessage("§eWarnings Count: §c1");
                        player.sendMessage("§eWarnings:");
                        player.sendMessage(" §8- §cThe player is offline!");
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10F, 1.5F);
                    } else {
                        player.sendMessage(ChatColor.GREEN + "Wiping " + target.getName() + "'s vault:");
                        player.sendMessage(ChatColor.YELLOW + "Success: §cfalse");
                        player.sendMessage("§eFail: §atrue");
                        player.sendMessage("§eWarnings Count: §c3");
                        player.sendMessage("§eWarnings: ");
                        player.sendMessage("§8 - §cThis player does not exist!");
                        player.sendMessage("§8 - §cThis player is offline!");
                        player.sendMessage("§8 - §cThis player does not have vault data!");
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10F, 1.5F);
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "You cannot do this!");
            }
        }
        return false;
    }
    private void wipeVault(OfflinePlayer target) {
        if (VaultData.getData().containsKey(target.getUniqueId().toString())) {
            VaultData.getData().remove(target.getUniqueId().toString());
            success = true;
        } else {
            success = false;
        }
    }
}
