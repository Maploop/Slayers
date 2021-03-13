package net.maploop.item.items;

import net.maploop.Slayers;
import net.maploop.enums.ItemType;
import net.maploop.enums.Rarity;
import net.maploop.item.CustomItem;
import net.maploop.item.ItemAbility;
import net.maploop.item.ItemUtilities;
import net.maploop.util.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.List;

public class WandOfRestoration extends CustomItem {
    public WandOfRestoration(int id, Rarity rarity, String name, Material material, int durability, boolean stackable, boolean oneTimeUse, boolean hasActive, List<ItemAbility> abilities, int manaCost, boolean reforgeable, ItemType itemType, boolean glowing) {
        super(id, rarity, name, material, durability, stackable, oneTimeUse, hasActive, abilities, manaCost, reforgeable, itemType, glowing);
    }

    @Override
    public void onItemStackCreate(ItemStack paramItemStack) {

    }

    @Override
    public void getSpecificLorePrefix(List<String> paramList, ItemStack paramItemStack) {

    }

    @Override
    public void getSpecificLoreSuffix(List<String> paramList, ItemStack paramItemStack) {

    }

    @Override
    public void leftClickAirAction(Player paramPlayer, ItemStack paramItemStack) {

    }

    @Override
    public void leftClickBlockAction(Player paramPlayer, PlayerInteractEvent paramPlayerInteractEvent, Block paramBlock, ItemStack paramItemStack) {

    }

    @Override
    public void rightClickAirAction(Player player, PlayerInteractEvent event, ItemStack paramItemStack) {
        if(!(ItemUtilities.enforceCooldown(player, "big_heal", 1d, paramItemStack, false))) {
            ItemUtilities.warnPlayer(player, Collections.singletonList(ChatColor.RED + "You are on cooldown!"));
            return;
        }
        Utilities.sendActionbar(player, ChatColor.AQUA + "-100 Mana (" + ChatColor.GOLD + "Big Heal" + ChatColor.AQUA + ")");
        int i = Bukkit.getScheduler().scheduleSyncRepeatingTask(Slayers.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (player.getHealth() < 15.5) {
                    player.setHealth(player.getHealth() + 4.5);
                    Utilities.sendActionbar(player, "§c+4.5❤");
                }
            }
        }, 0, 20L);

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getScheduler().cancelTask(i);
            }
        }.runTaskLater(Slayers.getPlugin(), 60L);
    }

    @Override
    public void rightClickBlockAction(Player player, PlayerInteractEvent paramPlayerInteractEvent, Block paramBlock, ItemStack paramItemStack) {
        if(!(ItemUtilities.enforceCooldown(player, "big_heal", 1d, paramItemStack, false))) {
            ItemUtilities.warnPlayer(player, Collections.singletonList(ChatColor.RED + "You are on cooldown!"));
            return;
        }
        Utilities.sendActionbar(player, ChatColor.AQUA + "-100 Mana (" + ChatColor.GOLD + "Big Heal" + ChatColor.AQUA + ")");
        int i = Bukkit.getScheduler().scheduleSyncRepeatingTask(Slayers.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (player.getHealth() < 15.5) {
                    player.setHealth(player.getHealth() + 4.5);
                    Utilities.sendActionbar(player, "§c+4.5❤");
                }
            }
        }, 0, 20L);

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getScheduler().cancelTask(i);
            }
        }.runTaskLater(Slayers.getPlugin(), 60L);
    }

    @Override
    public void shiftLeftClickAirAction(Player paramPlayer, ItemStack paramItemStack) {

    }

    @Override
    public void shiftLeftClickBlockAction(Player paramPlayer, PlayerInteractEvent paramPlayerInteractEvent, Block paramBlock, ItemStack paramItemStack) {

    }

    @Override
    public void shiftRightClickAirAction(Player paramPlayer, PlayerInteractEvent event, ItemStack paramItemStack) {

    }

    @Override
    public void shiftRightClickBlockAction(Player paramPlayer, PlayerInteractEvent paramPlayerInteractEvent, Block paramBlock, ItemStack paramItemStack) {

    }

    @Override
    public void middleClickAction(Player paramPlayer, ItemStack paramItemStack) {

    }

    @Override
    public void hitEntityAction(Player paramPlayer, EntityDamageByEntityEvent paramEntityDamageByEntityEvent, Entity paramEntity, ItemStack paramItemStack) {

    }

    @Override
    public void breakBlockAction(Player paramPlayer, BlockBreakEvent paramBlockBreakEvent, Block paramBlock, ItemStack paramItemStack) {

    }

    @Override
    public void clickedInInventoryAction(Player paramPlayer, InventoryClickEvent paramInventoryClickEvent) {

    }

    @Override
    public void activeEffect(Player paramPlayer, ItemStack paramItemStack) {

    }
}
