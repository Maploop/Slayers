package net.maploop.listeners;

import net.maploop.Slayers;
import net.maploop.item.CustomItem;
import net.maploop.item.ItemUtilities;
import net.maploop.util.Utilities;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerUseCustomItem implements Listener {
    Slayers main;

    public PlayerUseCustomItem(Slayers main) {
        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerUse(org.bukkit.event.player.PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if (event.getItem().hasItemMeta()) {
                if (ItemUtilities.isSBItem(event.getPlayer().getInventory().getItemInHand())) {
                    useSBItem(event, event.getPlayer().getInventory().getItemInHand());
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) return;
        Player player = (Player) event.getDamager();
        ItemStack used = player.getInventory().getItemInHand();
        if (used != null) {
            if (used.getType() != Material.AIR) {
                if (ItemUtilities.isSBItem(used))
                    ItemUtilities.getSBItem(used).hitEntityAction(player, event, event.getEntity(), used);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();
        if (ItemUtilities.isSBItem(item))
            ItemUtilities.getSBItem(item).breakBlockAction(player, event, event.getBlock(), item);
    }

    private void useSBItem(PlayerInteractEvent event, ItemStack item) {
        Player player = event.getPlayer();
        CustomItem sbitem = ItemUtilities.getSBItem(item);
        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            if (!player.isSneaking()) {
                sbitem.leftClickAirAction(player, item);
            } else {
                sbitem.shiftLeftClickAirAction(player, item);
            }
        } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (!player.isSneaking()) {
                sbitem.leftClickBlockAction(player, event, event.getClickedBlock(), item);
            } else {
                sbitem.shiftLeftClickBlockAction(player, event, event.getClickedBlock(), item);
            }
        } else if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (!player.isSneaking()) {
                sbitem.rightClickAirAction(player, event, item);
            } else {
                sbitem.shiftRightClickAirAction(player, event, item);
            }
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (!player.isSneaking()) {
                sbitem.rightClickBlockAction(player, event, event.getClickedBlock(), item);
            } else {
                sbitem.shiftRightClickBlockAction(player, event, event.getClickedBlock(), item);
            }
        }
    }
}
