package net.maploop.slayers.MenuSystem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SlayerShopMenu extends Menu {
    public SlayerShopMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    private HashMap<UUID, List<ItemStack>> history = new HashMap<>();

    @Override
    public String getTitle() {
        return "Slayer Items Shop";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public void hadleMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);

        switch (event.getSlot()) {
            case 49:
                if (!(history.containsKey(player.getUniqueId()))) {
                    player.sendMessage(ChatColor.RED + "You do not have anything in your history!");
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10F, 0F);
                } else {
                    ItemStack currentItem = history.get(player.getUniqueId()).get(0);
                    player.getInventory().addItem(currentItem);
                    history.get(player.getUniqueId()).remove(currentItem);
                    if (history.get(player.getUniqueId()).isEmpty()) {
                        history.remove(player.getUniqueId());
                    }
                    player.updateInventory();
                    setItems();
                    new SlayerShopMenu(new PlayerMenuUtility(player)).open();
                    player.sendMessage(ChatColor.GREEN + "You bought back §9Slayer Sack §afor §650,000 coins§a!");
                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10F, 2);
                }
                break;

            case 10:
                player.sendMessage(ChatColor.GREEN + "You bought §9Slayer Sack §afor §6100,000 coins§a!");
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 10F, 2);
                ItemStack slayerSack = makeCustomSkullItem("http://textures.minecraft.net/texture/427423d5cce4a472a760ce5e862ca35f7058d587b7eab69ebec920c773e7307", "§aSlayer Sack", 1,
                        "§7Store Slayer drops",
                        "§7in this particular sack!");
                player.getInventory().addItem(slayerSack);
                break;
        }
        if (event.getSlot() != 49) {
            if (!(event.getCurrentItem().getItemMeta().getDisplayName().equals("§aSlayer Sack"))) return;
            List<ItemStack> items;
            if (history.containsKey(player.getUniqueId())) {
                items = history.get(player.getUniqueId());
                items.add(event.getCurrentItem());
            } else {
                items = new ArrayList<>();
                items.add(event.getCurrentItem());
            }
            items.add(event.getCurrentItem());
            history.put(player.getUniqueId(), items);
            player.sendMessage(ChatColor.GREEN + "You sold §9Slayer Sack §afor §650,000 coins§a!");
            player.getInventory().removeItem(event.getCurrentItem());
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 10F, 2);
            for (ItemStack i : history.get(player.getUniqueId())) {
                player.sendMessage(i.getItemMeta().getDisplayName() + " §ex" + i.getAmount());
            }

            player.updateInventory();
            setItems();
        }
    }

    @Override
    public void setItems() {
        fillBorder();

        ItemStack historyItem;
        if (history.containsKey(playerMenuUtility.getOwner().getUniqueId())) {
            if (history.get(playerMenuUtility.getOwner().getUniqueId()).isEmpty()) {
                historyItem = makeItem(Material.BEDROCK, "§cError Occurred", 1, 0, "§7An error occurred while", "§7trying to proccess an action");
            } else {
                historyItem = history.get(playerMenuUtility.getOwner().getUniqueId()).get(0);
            }
        } else {
            historyItem = makeItem(Material.MAP, "§eSell History", 1, 0,
                    "§7Your sell history",
                    "§7does not contain anything",
                    "§7at the moment!",
                    "",
                    "§cWARNING: Your sell history",
                    "§cwill be cleared after closing",
                    "§cthis menu!");
        }

        inventory.setItem(49, historyItem);

        ItemStack slayerSack = makeCustomSkullItem("http://textures.minecraft.net/texture/427423d5cce4a472a760ce5e862ca35f7058d587b7eab69ebec920c773e7307", "§aSlayer Sack §ex1", 1,
                "§7Store Slayer drops",
                "§7in this particular sack!",
                "§8§m---------------------",
                "§7Cost: §6100,000 coins",
                "",
                "§aClick to purchase!");
        inventory.setItem(10, slayerSack);
    }
}
