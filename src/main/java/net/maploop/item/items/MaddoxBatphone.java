package net.maploop.item.items;

import net.maploop.Slayers;
import net.maploop.enums.ItemType;
import net.maploop.enums.Rarity;
import net.maploop.item.CustomItem;
import net.maploop.item.ItemAbility;
import net.maploop.item.SBItems;
import net.maploop.util.Utilities;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.UUID;

public class MaddoxBatphone extends CustomItem {
    public MaddoxBatphone(int id, Rarity rarity, String name, Material material, int durability, boolean stackable, boolean oneTimeUse, boolean hasActive, List<ItemAbility> abilities, int manaCost, boolean reforgeable, ItemType itemType, String url) {
        super(id, rarity, name, material, durability, stackable, oneTimeUse, hasActive, abilities, manaCost, reforgeable, itemType, url);
    }

    @Override
    public void onItemStackCreate(ItemStack paramItemStack) {
        this.init("maddox_batphone", paramItemStack);
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
        event.setCancelled(true);

        SBItems.uniqueId = UUID.randomUUID().toString();
        SBItems.id.put(player.getUniqueId(), SBItems.uniqueId);

        new BukkitRunnable() {

            @Override
            public void run() {
                SBItems.uniqueId = "none";
                SBItems.id.remove(player.getUniqueId());

            }
        }.runTaskLater(Slayers.getPlugin(), 200L);

        event.setCancelled(true);
        sendMessageWithDelay("&e✆ Ring...", player, 0);
        sendMessageWithDelay("&e✆ Ring... Ring...", player, 20);
        sendMessageWithDelay("&e✆ Ring... Ring... Ring...", player, 40);

        TextComponent text = new TextComponent("§a✆ Someone answers! ");
        TextComponent text1 = new TextComponent("§2§l[OPEN MENU]");
        text1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click!").color(net.md_5.bungee.api.ChatColor.YELLOW).create()));
        text1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/cb " + SBItems.uniqueId));
        new BukkitRunnable() {

            @Override
            public void run() {
                if (!(SBItems.cooldownmn.containsKey(player.getUniqueId()))) {
                    player.spigot().sendMessage(text, text1);
                    SBItems.cooldownmn.put(player.getUniqueId(), SBItems.uniqueId);

                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            SBItems.cooldownmn.remove(player.getUniqueId());
                        }
                    }.runTaskLater(Slayers.getPlugin(), 600L);

                } else {
                    player.sendMessage(ChatColor.RED + "✆ No answer.");
                }
                player.playSound(player.getLocation(), Sound.WOOD_CLICK, 1f, 1.5f);
            }
        }.runTaskLater(Slayers.getPlugin(), 60);
        onItemUse(player, paramItemStack);
    }

    @Override
    public void rightClickBlockAction(Player player, PlayerInteractEvent event, Block paramBlock, ItemStack paramItemStack) {
        event.setCancelled(true);

        SBItems.uniqueId = UUID.randomUUID().toString();
        SBItems.id.put(player.getUniqueId(), SBItems.uniqueId);

        new BukkitRunnable() {

            @Override
            public void run() {
                SBItems.uniqueId = "none";
                SBItems.id.remove(player.getUniqueId());

            }
        }.runTaskLater(Slayers.getPlugin(), 200L);

        event.setCancelled(true);
        sendMessageWithDelay("&e✆ Ring...", player, 0);
        sendMessageWithDelay("&e✆ Ring... Ring...", player, 20);
        sendMessageWithDelay("&e✆ Ring... Ring... Ring...", player, 40);

        TextComponent text = new TextComponent("§a✆ Someone answers! ");
        TextComponent text1 = new TextComponent("§2§l[OPEN MENU]");
        text1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click!").color(net.md_5.bungee.api.ChatColor.YELLOW).create()));
        text1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/cb " + SBItems.uniqueId));
        new BukkitRunnable() {

            @Override
            public void run() {
                if (!(SBItems.cooldownmn.containsKey(player.getUniqueId()))) {
                    player.spigot().sendMessage(text, text1);
                    SBItems.cooldownmn.put(player.getUniqueId(), SBItems.uniqueId);

                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            SBItems.cooldownmn.remove(player.getUniqueId());
                        }
                    }.runTaskLater(Slayers.getPlugin(), 600L);

                } else {
                    player.sendMessage(ChatColor.RED + "✆ No answer.");
                }
                player.playSound(player.getLocation(), Sound.WOOD_CLICK, 1f, 1.5f);
            }
        }.runTaskLater(Slayers.getPlugin(), 60);
        onItemUse(player, paramItemStack);
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

    private void sendMessageWithDelay(String msg, Player player, long delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage(Utilities.color(msg));
                for (int i = 0; i < 10; i++) {
                    playSoundWithDelay(player, i);
                }
            }
        }.runTaskLater(Slayers.getPlugin(), delay);
    }

    private void playSoundWithDelay(Player player, long delay) {
        new BukkitRunnable() {

            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1f, 1.5f);
            }
        }.runTaskLater(Slayers.getPlugin(), delay);
    }
}
