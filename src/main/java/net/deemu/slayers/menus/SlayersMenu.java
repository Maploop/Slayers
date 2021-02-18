package net.deemu.slayers.menus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SlayersMenu extends Menu {
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
        if (event.getSlot() == 31) {
            player.closeInventory();
        }
        switch (event.getSlot()) {
            case 10:
                new RevenantHorrorMenu(new PlayerMenuUtility(player)).open();
                break;
            case 27:
                new QuestMenu(new PlayerMenuUtility(player)).open();
                break;
            case 30:
                player.closeInventory();
                player.sendMessage("§cThis feature is currently unavailable!");
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10F, 0);
                break;
        }
    }

    public String newline = "\n";

    @Override
    public void setItems() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        setFilter();
        List<String> revLore = new ArrayList<>();
        revLore.add("§7Abhorrant Zombie stuck");
        revLore.add(ChatColor.GRAY + "between life and death for");
        revLore.add(ChatColor.GRAY + "an eternity.");
        revLore.add("");
        revLore.add(ChatColor.GRAY + "Zombie Slayer: " + ChatColor.RED + "N/A");
        revLore.add("");
        revLore.add(ChatColor.YELLOW + "Click to view!");
        ItemStack revenantHorror = makeAdvancedItem(Material.ROTTEN_FLESH, ChatColor.RED + "☠ " + ChatColor.YELLOW + "Revenant Horror", 1, 0, revLore);
        inventory.setItem(10, revenantHorror);

        ItemStack close = makeItem(Material.BARRIER, "§cClose", 1, 0, "§7Close the menu.");
        inventory.setItem(31, close);

        List<String> trantulaLore = new ArrayList<>();
        trantulaLore.add(ChatColor.GRAY + "Monsterous Spider who poisons");
        trantulaLore.add(ChatColor.GRAY + "and devours its victims.");
        trantulaLore.add("");
        trantulaLore.add("§7Spider Slayer: §cN/A");
        trantulaLore.add("");
        trantulaLore.add(ChatColor.RED + "§lCOMING SOON");
        ItemStack tarantulaBroodfather = makeAdvancedItem(Material.WEB, ChatColor.RED + "☠ " + ChatColor.YELLOW + "Tarantula Broodfather", 1, 0, trantulaLore);
        inventory.setItem(11, tarantulaBroodfather);

        List<String> svenLore = new ArrayList<>();
        svenLore.add(ChatColor.GRAY + "Rabid Wolf genetically");
        svenLore.add(ChatColor.GRAY + "modified by a famous mad");
        svenLore.add(ChatColor.GRAY + "scientist. Eats bones and");
        svenLore.add(ChatColor.GRAY + "flesh.");
        svenLore.add("");
        svenLore.add(ChatColor.GRAY + "Wolf Slayer: " + ChatColor.RED + "N/A");
        svenLore.add("");
        svenLore.add("§c§lCOMING SOON");
        ItemStack svenPackmaster = makeAdvancedItem(Material.MUTTON, "§c☠ §eSven Packmaster", 1, 0, svenLore);
        inventory.setItem(12, svenPackmaster);

        ItemStack comingSoon = makeItem(Material.BEDROCK, "§cComing Soon!", 1, 0, "§7Coming soon in a future update!");
        inventory.setItem(13, comingSoon);
        inventory.setItem(14, comingSoon);
        inventory.setItem(15, comingSoon);
        inventory.setItem(16, comingSoon);

        ItemStack drops = makeItem(Material.MAP, "§eSlayer History", 1, 0,
                "§7View all the slayer",
                "§7quests you've done",
                "§7until today!",
                "",
                "§7Date: §a" + format.format(now),
                "",
                "§cFeature unavailable!");
        inventory.setItem(30, drops);

        ItemStack myQuest = makeCustomSkullItem("http://textures.minecraft.net/texture/1035c528036b384c53c9c8a1a125685e16bfb369c197cc9f03dfa3b835b1aa55", "§aYour Active Quests", 1,
                "§7View your current active",
                "§7Slayer Quest and cancel",
                "§7it if you wish so!",
                "",
                "§eClick to view!");
    }
}
