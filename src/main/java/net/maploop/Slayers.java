package net.maploop;

import net.maploop.bosses.SlayerBoss;
import net.maploop.commands.*;
import net.maploop.enums.AbilityType;
import net.maploop.enums.ItemType;
import net.maploop.enums.Rarity;
import net.maploop.files.DataFile;
import net.maploop.item.ItemAbility;
import net.maploop.item.SBItems;
import net.maploop.item.items.MaddoxBatphone;
import net.maploop.listeners.*;
import net.maploop.bosses.MagmabossCommand;
import net.maploop.data.VaultData;
import net.maploop.menus.PlayerMenuUtility;
import net.maploop.packet.PacketReader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        init();

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Slayers enabled. §dMade by Maploop §ev1.2");
    }

    @Override
    public void onDisable() {
        VaultData.save();

        unInject();
        if (!(Bukkit.getOnlinePlayers().isEmpty())) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                PacketReader reader = new PacketReader();
                reader.uninject(players);
            }
        }

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Slayers disabled.");
    }

    private void init() {
        plugin = this;
        registerCommands();
        registerItems();
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
        DataFile.setDefaults();
        VaultData.restore();
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
        Bukkit.getPluginManager().registerEvents(new PlayerUseCustomItem(this), this);
    }

    private void registerItems() {
        SBItems.putItem("maddox_batphone", new MaddoxBatphone(1, Rarity.UNCOMMON, "Maddox Batphone", Material.SKULL_ITEM , 3, true, false, false, Collections.singletonList(new ItemAbility("Whassup?", AbilityType.RIGHT_CLICK, "§7Lets you call §5Maddox§7, when\nhe is not busy.\n")), 0, false, ItemType.ITEM, "http://textures.minecraft.net/texture/9336d7cc95cbf6689f5e8c954294ec8d1efc494a4031325bb427bc81d56a484d"));
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

    private void unInject() {
        for(Entity e : SlayerBoss.bossMap.values()) {
            e.remove();
        }
        for(ArmorStand a : SlayerBoss.tag.values()) {
            a.remove();
        }
    }
}
