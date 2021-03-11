package net.maploop.data;

import net.maploop.files.DataFile;
import net.maploop.stats.Health;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private final Player player;
    private final Health healthStat;

    public DataHandler(Player player, Health healthStat) {
        this.player = player;
        this.healthStat = healthStat;
    }

    public boolean exists() {
        return DataFile.get().getString("stats." + player.getUniqueId().toString()) != null;
    }

    public void setUpData() {
        Health healthStat = new Health();

        if (exists()) {
            healthStat.health.put(player, DataFile.get().getDouble("stats." + player.getUniqueId().toString() + ".health"));

            return;
        }
        healthStat.health.put(player, 100d);
        DataFile.get().set("stats." + player.getUniqueId().toString() + ".health", 100);
    }

    public List<String> getStats() {
        List<String> stats = new ArrayList<>();
        stats.add(ChatColor.RED + "Health: " + healthStat.health.get(player));
        // Defense and mana

        return stats;
    }
}
