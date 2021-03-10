package net.maploop;

import net.maploop.commands.*;
import net.maploop.files.DataFile;
import net.maploop.listeners.*;
import net.maploop.bosses.MagmabossCommand;
import net.maploop.data.VaultData;
import net.maploop.menus.PlayerMenuUtility;
import net.maploop.packet.PacketReader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * All rights to the Hypixel Network for the plugin idea.
 * This is only a re-creation.
 * @author Maploop
 */

public class Slayers extends JavaPlugin {
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    public static Slayers plugin;

    @Override
    public void onEnable() {
        plugin = this;
        registerCommands();
        registerListeners();
        saveDefaultConfig();
        DataFile.setup();
        DataFile.get().options().copyDefaults(true);
        DataFile.save();

        if (!(Bukkit.getOnlinePlayers().isEmpty())) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                PacketReader reader = new PacketReader();
                reader.inject(players);
            }
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Slayers enabled. §dMade by Maploop §ev1.2");
        DataFile.setDefaults();
        VaultData.restore();
    }

    @Override
    public void onDisable() {
        VaultData.save();

        if (!(Bukkit.getOnlinePlayers().isEmpty())) {
            for (Player players : Bukkit.getOnlinePlayers()) {
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
        this.getCommand("privatevault").setExecutor(new PrivatevaultCommand());
        this.getCommand("pv").setExecutor(new PrivatevaultCommand());
        this.getCommand("wipevault").setExecutor(new WipevaultCommand());
        this.getCommand("cb").setExecutor(new CbCommand());
        this.getCommand("spawnboss").setExecutor(new SpawnbossCommand());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new InventoryClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new RightClickNPC(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntity(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractEvent(), this);
    }

    public static Slayers getPlugin() {
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
