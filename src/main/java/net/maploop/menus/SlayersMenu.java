package net.maploop.menus;

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
        return "Slayer";
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
            case 30:
                player.closeInventory();
                player.sendMessage("§cThis feature is currently unavailable!");
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10F, 0);
                break;
            case 32: {
                player.sendMessage(ChatColor.RED + "This feature is currently unavailable!");
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10F, 0);
                break;
            }
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

        ItemStack comingSoon = makeItem(Material.COAL_BLOCK, "§cNot released yet!", 1, 0, "§7This boss is still in", "§7development!");
        inventory.setItem(13, comingSoon);
        inventory.setItem(14, comingSoon);
        inventory.setItem(15, comingSoon);
        inventory.setItem(16, comingSoon);

        ItemStack drops = makeItem(Material.WATCH, "§aRandom Slayer Quest", 1, 0,
                "§8Extra Rewards",
                "",
                "§7Start a slayer quest for a",
                "§7random boss.",
                "",
                "§7Quests started this way reward",
                "§7more items and §dXP§7.",
                "",
                "§cFeature unavailable!");
        inventory.setItem(30, drops);

        ItemStack boost = makeItem(Material.WHEAT, "§aGlobal Combat XP Buff", 1, 0,
                ChatColor.DARK_GRAY + "Slayer Bonus",
                "",
                ChatColor.GRAY + "Total Buff: " + ChatColor.AQUA + "+11%",
                "",
                ChatColor.GRAY + "Earn extra combat XP based on",
                ChatColor.GRAY + "your unique slayer boss kills.",
                "",
                ChatColor.DARK_GRAY + "Highest slain tiers",
                ChatColor.RED + "No data available!",
                "",
                ChatColor.GRAY + "Tier I, II, III grant " + ChatColor.AQUA + "+1% XP" + ChatColor.GRAY + ".",
                ChatColor.GRAY + "Tier IV grants " + ChatColor.AQUA + "+2% Combat XP" + ChatColor.GRAY + ".");
        inventory.setItem(32, boost);

        ItemStack autoslayer = makeItem(Material.INK_SACK, "§cAuto Slayer", 1, 1,
                "§7Upon defeating a boss,",
                "§aautomatically §7completes",
                "§7quest and starts",
                "§7another of same type if",
                "§7you have enough §6coins",
                "§7in your purse or bank.",
                "",
                "§cRequires LVL 6 in all bosses");
        inventory.setItem(28, autoslayer);

        ItemStack bonus = makeItem(Material.POWERED_RAIL, "§aSlayer Bonus Rewards", 1, 0,
                "§7Unlock bonuses by\n§7reaching a LVL on all\n§7bosses.\n \n§c✖ LVL 6\n§7Earn §a+3§7 of any boss's\n§7main token drop when\n§7slaying mini-bosses.\n \n§c✖ LVL 7\n§7Slayers are §64% cheaper§7.");
        inventory.setItem(33, bonus);

        ItemStack myQuest = makeCustomSkullItem("http://textures.minecraft.net/texture/1035c528036b384c53c9c8a1a125685e16bfb369c197cc9f03dfa3b835b1aa55", "§aYour Active Quests", 1,
                "§7View your current active",
                "§7Slayer Quest and cancel",
                "§7it if you wish so!",
                "",
                "§eClick to view!");
    }
}
