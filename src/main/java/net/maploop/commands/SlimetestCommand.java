package net.maploop.commands;

import net.maploop.enums.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;

public class SlimetestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;
        if (s.equalsIgnoreCase("slimetest")) {
            if (player.hasPermission("slimetest.use")) {
                if (args.length == 0) {
                    Slime mob = (Slime) player.getWorld().spawnEntity(player.getLocation(), EntityType.SLIME);
                    mob.setSize(1);
                } else {
                    try {
                        Integer.parseInt(args[0]);
                    } catch (NumberFormatException e) {
                        player.sendMessage("§cYou must specify an integer for the size!");
                    }
                    int size = Integer.parseInt(args[0]);
                    Slime mob = (Slime) player.getWorld().spawnEntity(player.getLocation(), EntityType.SLIME);
                    mob.setSize(size);
                }
            } else {
                player.sendMessage(String.valueOf(Message.NO_PERMISSION));
            }
        }
        return false;
    }
}
