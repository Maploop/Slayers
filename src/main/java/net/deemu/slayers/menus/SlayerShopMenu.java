package net.deemu.slayers.menus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SlayerShopMenu extends Menu{
    public SlayerShopMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

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

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
            case "§cClose":
                player.closeInventory();
                break;
            case "§aSlayer Sack":
                player.sendMessage(ChatColor.GREEN + "You bought 1x Slayer Sack for §6100,000 coins§a!");
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 10F, 2);
                ItemStack slayerSack = makeCustomSkullItem("http://textures.minecraft.net/texture/427423d5cce4a472a760ce5e862ca35f7058d587b7eab69ebec920c773e7307", "§aSlayer Sack", 1,
                        "§7Store Slayer drops",
                        "§7in this particular sack!");
                player.getInventory().addItem(slayerSack);
        }
    }

    @Override
    public void setItems() {
        fillBorder();

        ItemStack close = makeItem(Material.BARRIER, "§cClose", 1, 0, "§7Close the menu.");
        inventory.setItem(49, close);

        ItemStack slayerSack = makeCustomSkullItem("http://textures.minecraft.net/texture/427423d5cce4a472a760ce5e862ca35f7058d587b7eab69ebec920c773e7307", "§aSlayer Sack", 1,
                "§7Store Slayer drops",
                "§7in this particular sack!",
                "§8§m---------------------",
                "§7Cost: §6100,000 coins",
                "",
                "§aClick to purchase!");
        inventory.addItem(slayerSack);
    }
}
