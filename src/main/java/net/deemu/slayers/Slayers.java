package net.deemu.slayers;

import net.deemu.slayers.Commands.SlayerCommand;
import net.deemu.slayers.Listeners.InventoryClickEvent;
import net.deemu.slayers.Listeners.PlayerJoinEvent;
import net.deemu.slayers.Listeners.PlayerQuitEvent;
import net.deemu.slayers.MenuSystem.PlayerMenuUtility;
import net.deemu.slayers.Packets.PacketReader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Slayers extends JavaPlugin {
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    public static Slayers plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        registerCommands();
        registerListeners();

        if(!(Bukkit.getOnlinePlayers().isEmpty())){
            for(Player players : Bukkit.getOnlinePlayers()) {
                PacketReader reader = new PacketReader();
                reader.inject(players);
            }
        }

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Slayers enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        if(!(Bukkit.getOnlinePlayers().isEmpty())){
            for(Player players : Bukkit.getOnlinePlayers()) {
                PacketReader reader = new PacketReader();
                reader.uninject(players);
            }
        }

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Slayers disabled.");
    }

    public void registerCommands() {
        this.getCommand("slayer").setExecutor(new SlayerCommand());
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new InventoryClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitEvent(), this);
    }

    public static Slayers getPlugin(){
        return plugin;
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player player) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(player))) {

            playerMenuUtility = new PlayerMenuUtility(player);
            playerMenuUtilityMap.put(player, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(player);
        }
    }
}
