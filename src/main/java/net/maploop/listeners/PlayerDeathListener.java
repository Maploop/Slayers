package net.maploop.listeners;

import net.maploop.bosses.SlayerBoss;
import net.maploop.quests.Quest;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (!(SlayerBoss.bossMap.containsKey(player.getUniqueId()))) return;

        player.sendMessage(ChatColor.RED + "Slayer Quest failed! Maddox will be angry!");
        player.playSound(player.getLocation(), Sound.NOTE_PLING, 10F, 0.5F);

        Quest.questComplete.put(player.getUniqueId(), false);
        Quest.fightingBoss.remove(player.getUniqueId());
        SlayerBoss.bossMap.get(player.getUniqueId()).remove();
        SlayerBoss.bossMap.remove(player.getUniqueId());
    }
}