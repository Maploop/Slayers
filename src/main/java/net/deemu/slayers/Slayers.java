package net.deemu.slayers;

import net.deemu.slayers.bosses.MagmabossCommand;
import net.deemu.slayers.commands.SlayerCommand;
import net.deemu.slayers.commands.SlimetestCommand;
import net.deemu.slayers.files.DataFile;
import net.deemu.slayers.listeners.*;
import net.deemu.slayers.menus.PlayerMenuUtility;
import net.deemu.slayers.packets.PacketReader;
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
        saveDefaultConfig();
        DataFile.setup();
        DataFile.get().options().copyDefaults(true);
        DataFile.save();

        if(!(Bukkit.getOnlinePlayers().isEmpty())){
            for(Player players : Bukkit.getOnlinePlayers()) {
                PacketReader reader = new PacketReader();
                reader.inject(players);
            }
        }

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Slayers enabled. §dMade by Maploop §ev1.2");
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

    private void registerCommands() {
        this.getCommand("slayer").setExecutor(new SlayerCommand());
        this.getCommand("slimetest").setExecutor(new SlimetestCommand());
        this.getCommand("magmaboss").setExecutor(new MagmabossCommand());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new InventoryClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new RightClickNPC(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntity(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
    }

    public static Slayers getPlugin(){
        return plugin;
    }

    private static PlayerMenuUtility getPlayerMenuUtility(Player player) {
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
