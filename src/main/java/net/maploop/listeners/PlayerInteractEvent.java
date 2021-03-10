package net.maploop.listeners;

import net.maploop.Slayers;
import net.maploop.util.Utilities;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerInteractEvent implements Listener {
    public static String uniqueId;
    public static Map<UUID, String> id = new HashMap<>();
    public static Map<UUID, String> cooldownmn = new HashMap<>();

    @EventHandler
    public void onInteract(org.bukkit.event.player.PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getItemInHand() == null) return;
        if (!(player.getItemInHand().hasItemMeta())) return;
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (player.getItemInHand().getItemMeta().getDisplayName().equals("§aMaddox Batphone")) {
                uniqueId = UUID.randomUUID().toString();
                id.put(player.getUniqueId(), uniqueId);

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        uniqueId = "none";
                        id.remove(player.getUniqueId());

                    }
                }.runTaskLater(Slayers.getPlugin(), 200L);

                event.setCancelled(true);
                sendMessageWithDelay("&e✆ Ring...", player, 0);
                sendMessageWithDelay("&e✆ Ring... Ring...", player, 20);
                sendMessageWithDelay("&e✆ Ring... Ring... Ring...", player, 40);

                TextComponent text = new TextComponent("§a✆ Someone answers! ");
                TextComponent text1 = new TextComponent("§2§l[OPEN MENU]");
                text1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click!").color(net.md_5.bungee.api.ChatColor.YELLOW).create()));
                text1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/cb " + uniqueId));
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if (!(cooldownmn.containsKey(player.getUniqueId()))) {
                            player.spigot().sendMessage(text, text1);
                            cooldownmn.put(player.getUniqueId(), uniqueId);

                            new BukkitRunnable() {

                                @Override
                                public void run() {
                                    cooldownmn.remove(player.getUniqueId());
                                }
                            }.runTaskLater(Slayers.getPlugin(), 600L);

                        } else {
                            player.sendMessage(ChatColor.RED + "✆ No answer.");
                        }
                        player.playSound(player.getLocation(), Sound.WOOD_CLICK, 1f, 1.5f);
                    }
                }.runTaskLater(Slayers.getPlugin(), 60);
            }
        }
    }

    private void sendMessageWithDelay(String msg, Player player, long delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage(Utilities.color(msg));
                for (int i = 0; i < 15; i++) {
                    playSoundWithDelay(player, i);
                }
            }
        }.runTaskLater(Slayers.getPlugin(), delay);
    }

    private void playSoundWithDelay(Player player, long delay) {
        new BukkitRunnable() {

            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1f, 2f);
            }
        }.runTaskLater(Slayers.getPlugin(), delay);
    }
}
