package net.deemu.slayers.MenuSystem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SlayersMenu extends Menu{
    public SlayersMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getTitle() {
        return "Slayer Quest";
    }

    @Override
    public int getSize() {
        return 36;
    }

    @Override
    public void hadleMenu(InventoryClickEvent event) {
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        if(event.getSlot() == 31){
            player.closeInventory();
        }
    }

    @Override
    public void setItems() {
        setFilter();
        List<String> revLore = new ArrayList<>();
        revLore.add("§7Abhorrant Zombie stuck");
        revLore.add(ChatColor.GRAY + "between life and death for");
        revLore.add(ChatColor.GRAY + "an eternity.");
        revLore.add("");
        revLore.add(ChatColor.GRAY + "Zombie Slayer: " + ChatColor.RED + "N/A");
        revLore.add("");
        revLore.add(ChatColor.RED + "Not Available.");
        ItemStack revenantHorror = makeAdvancedItem(Material.ROTTEN_FLESH, ChatColor.RED + "☠ " + ChatColor.YELLOW + "Revenant Horror", 1, 0, revLore);
        inventory.setItem(10, revenantHorror);

        ItemStack close = makeItem(Material.BARRIER, "§cClose", 1, 0, "§7Close the menu.");
        inventory.setItem(31, close);

        List<String> trantulaLore = new ArrayList<>();
        trantulaLore.add(ChatColor.GRAY + "Monsterous Spider who poisons");
        trantulaLore.add(ChatColor.GRAY + "and devours its victims.");
        trantulaLore.add("");
        trantulaLore.add(ChatColor.RED + "Not Available.");
        ItemStack tarantulaBroodfather = makeAdvancedItem(Material.WEB, ChatColor.RED + "☠ " + ChatColor.YELLOW + "Tarantula Broodfather", 1, 0, trantulaLore);
        inventory.setItem(11, tarantulaBroodfather);
    }
}
