package net.maploop;

import net.maploop.bosses.SlayerBoss;
import net.maploop.commands.*;
import net.maploop.enums.AbilityType;
import net.maploop.enums.ItemType;
import net.maploop.enums.Rarity;
import net.maploop.files.DataFile;
import net.maploop.item.ItemAbility;
import net.maploop.item.SBItems;
import net.maploop.item.items.*;
import net.maploop.listeners.*;
import net.maploop.bosses.MagmabossCommand;
import net.maploop.data.VaultData;
import net.maploop.menus.PlayerMenuUtility;
import net.maploop.npc.NPC;
import net.maploop.npc.PacketReader;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Collections;
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
        registerItems();
        registerListeners();
        saveDefaultConfig();
        DataFile.setup();
        DataFile.get().options().copyDefaults(true);
        DataFile.save();

        DataFile.setDefaults();
        VaultData.restore();

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Slayers enabled. §dMade by Maploop §ev1.2");
    }

    @Override
    public void onDisable() {
        VaultData.save();
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Slayers disabled.");
    }

    private void init() {
        /*
        if (DataFile.get().getString("npc-location") != null) {
            double x = DataFile.get().getDouble("npc-location.x");
            double y = DataFile.get().getDouble("npc-location.y");
            double z = DataFile.get().getDouble("npc-location.z");
            String rawWorld = DataFile.get().getString("npc-location.world");
            World world = Bukkit.getServer().getWorld(rawWorld);
            float yaw = DataFile.get().getInt("npc-location.yaw");
            float pitch = DataFile.get().getInt("npc-location.pitch");

            NPC npc = new NPC("§cMaddox", new Location(world, x, y, z, yaw, pitch), "ewogICJ0aW1lc3RhbXAiIDogMTYxNTM3OTU3NDUyMiwKICAicHJvZmlsZUlkIiA6ICI4MzdjZjgzZGJiZGU0OTUxOWI1ZGEzZTZhY2E1ZDBkMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJGYXJkaW5fTmV0aGVyIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzkzMzZkN2NjOTVjYmY2Njg5ZjVlOGM5NTQyOTRlYzhkMWVmYzQ5NGE0MDMxMzI1YmI0MjdiYzgxZDU2YTQ4NGQiCiAgICB9CiAgfQp9",
                    "Ehy6Fvkqqmw0dj9K82Lk8Gucgo29oKw4vfRp60XBnW62yn72TcBsVNDNxvn8L81S+WyI4dRq+Pwwf9apCTTxGv1SmK+qJKIviwIB3So63F7Wn/yNV3hl4Jp4GNaHGpW3yZxO/fn/SPKNDrd3nYOPsnyZ5Dk/gVNGJ2Z8VhvAH10SuIdFVWUSvVKUknaS5Ebxp831RvM1vHM+wIzZvcsBGJqj1CyUaL2nVu2VcMFArQDKAkU9wePiLrjUyb/UXygxC4INeeANFr3C8/vzS720M65UZ7UZP3JPCzBOBHtODzi++IpFGzvoAfXkMKxvsJdEoB65UFmbrATUBIzD5Cy/YYypNE3+4FjuM6ijRYMmNAASetH1PhnsVHbUpnEzxF3tcRf3tAs0u+mBAlDZ76QbS2kJgYIpaUq8tw8BYTe7BDRG9a5YZxQRTihhnoEBO4OLaqq9KNvfqjx8CjRtdKU5l+yBU/bFcLj1IXp3z10GUPVkyHRp4vEVQQ6LwHVFsCQ2GTYyVA/E7Sd7XfYD5sMBVS7TtY9NXo/mMsRZLEJ/k3wPtGZvSjVfX6uhzO6dVDCQjDTuzRFZLf3RhYmkRQhZaGDqSb4lYfDZY4vaw0+a0DWaC4KUd6/+R2VezIbu8/Xb5VbGeOGNRP6RTEuOPgefI7oMKZaaozHPjexZauHVbRE=");
            npc.createNPC();
        }
         */
    }

    private void registerCommands() {
        this.getCommand("slayer").setExecutor(new SlayerCommand());
        this.getCommand("slimetest").setExecutor(new SlimetestCommand());
        this.getCommand("magmaboss").setExecutor(new MagmabossCommand());
        this.getCommand("privatevault").setExecutor(new PrivatevaultCommand());
        this.getCommand("pv").setExecutor(new PrivatevaultCommand());
        this.getCommand("wipevault").setExecutor(new WipevaultCommand());
        this.getCommand("cb").setExecutor(new CbCommand());
        this.getCommand("unbreakable").setExecutor(new UnbreakableCommand());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new InventoryClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new RightClickNPC(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerUseCustomItem(this), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntity(), this);
    }

    private void registerItems() {
        SBItems.putItem("maddox_batphone", new MaddoxBatphone(1, Rarity.UNCOMMON, "Maddox Batphone", Material.SKULL_ITEM , 3, true, false, false, Collections.singletonList(new ItemAbility("Whassup?", AbilityType.RIGHT_CLICK, "§7Lets you call §5Maddox§7, when\nhe is not busy.\n")), 0, false, ItemType.ITEM, "http://textures.minecraft.net/texture/9336d7cc95cbf6689f5e8c954294ec8d1efc494a4031325bb427bc81d56a484d", false));
        SBItems.putItem("wand_of_healing", new WandOfHealing(2, Rarity.UNCOMMON, "Wand of Healing", Material.STICK, 0, false, false, false, Collections.singletonList(new ItemAbility("Small Heal", AbilityType.RIGHT_CLICK, "§7Heal §c20❤§7/s for 7s.\n§8Wand heals don't stack.", 1)), 60, false, ItemType.WAND, true));
        SBItems.putItem("revenant_falchion", new RevenantFalchion(3, Rarity.RARE, "Revenant Falchion", Material.DIAMOND_SWORD, 0, false, false, false, null, 0, true, ItemType.SWORD, false));
        SBItems.putItem("wand_of_mending", new WandOfMending(4, Rarity.RARE, "Wand of Mending", Material.STICK, 0, false, false, false, Collections.singletonList(new ItemAbility("Medium Heal", AbilityType.RIGHT_CLICK, "§7Heal §c90❤§7/s for 3s.\n§8Wand heals don't stack.", 1)), 80, false, ItemType.WAND, true));
        SBItems.putItem("wand_of_restoration", new WandOfRestoration(5, Rarity.EPIC, "Wand of Restoration", Material.STICK, 0, false, false, false, Collections.singletonList(new ItemAbility("Big Heal", AbilityType.RIGHT_CLICK, "§7Heal §c120❤§7/s for 3s.\n§8Wand heals don't stack.", 1)), 100, false, ItemType.WAND, true));
        SBItems.putItem("revenant_flesh", new RevenantFlesh(6, Rarity.UNCOMMON, "Revenant Flesh", Material.ROTTEN_FLESH, 0, true, false, false, null, 0, false, ItemType.ITEM, true));
        SBItems.putItem("zombie_ring", new ZombieRing(7, Rarity.UNCOMMON, "Zombie Ring", Material.SKULL_ITEM, 3, false, false, true, null, 0, true, ItemType.ACCESSORY, "http://textures.minecraft.net/texture/177c9c638bf3dcda348edea44e9a3db4abc1e239558661611f80c110472ad", false));
        SBItems.putItem("reaper_falchion", new ReaperFalchion(8, Rarity.EPIC, "Reaper Falchion", Material.DIAMOND_SWORD, 0, false, false, false, null, 0, true, ItemType.SWORD, false));
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

    public void startRotating(Player player) {
        this.getServer().getScheduler().runTaskTimer(this, new BukkitRunnable() {
            @Override
            public void run() {
                for (NPC npc : NPC.getNpcs()) {
                    if (npc.getLocation().distance(player.getLocation()) < 10)
                        npc.rotateHeadtoPlayer(player);
                }
            }
        }, 0L, 1L);
    }
}
