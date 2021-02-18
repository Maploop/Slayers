package net.deemu.slayers.menus;

import net.deemu.slayers.quests.Quest;
import net.deemu.slayers.quests.QuestType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class RevenantHorrorMenu extends Menu {
    public RevenantHorrorMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getTitle() {
        return "Revenant Horror";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public void hadleMenu(InventoryClickEvent event) {
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        switch (event.getSlot()) {
            case 49:
                new SlayersMenu(new PlayerMenuUtility(player)).open();
                break;
            case 11:
                if (Quest.quest.containsKey(player.getUniqueId())) {
                    player.sendMessage(ChatColor.RED + "You are already running a quest!");
                } else {
                    player.closeInventory();
                    Quest.startQuest(player, QuestType.ZOMBIE_SLAYER_TIER_1);
                    player.sendMessage("§5§l  SLAYER QUEST STARTED!");
                    player.sendMessage("§5§l  » §7Slay §c100 Combat XP §7worth of Zombies.");
                    player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 10F, 2);
                }
                break;
            case 12:
                if (Quest.quest.containsKey(player.getUniqueId())) {
                    player.sendMessage(ChatColor.RED + "You are already running a quest!");
                } else {
                    player.closeInventory();
                    Quest.startQuest(player, QuestType.ZOMBIE_SLAYER_TIER_2);
                    player.sendMessage("§5§l  SLAYER QUEST STARTED!");
                    player.sendMessage("§5§l  » §7Slay §c1440 Combat XP §7worth of Zombies.");
                    player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 10F, 2);
                }
        }
    }

    @Override
    public void setItems() {
        setFilter();
        ItemStack tier1 = makeItem(Material.ROTTEN_FLESH, "§aRevenant Horror I", 1, 0,
                "§8Beginner",
                "",
                "§7Health: §c500",
                "§7Damage: §c10 §7per second",
                "",
                "§7Reward: §d10 Zombie Slayer XP",
                " §8+ Boss Drops",
                "",
                "§7Cost to start: §8FREE!",
                "",
                "§eClick to start! §7(FREE!)");
        inventory.setItem(11, tier1);

        ItemStack tier2 = makeItem(Material.ROTTEN_FLESH, "§eRevenant Horror II", 1, 0,
                "§8Noob",
                "",
                "§7Health: §c10,000",
                "§7Damage: §c50 §7per second",
                "",
                "§7Reward: §d50 Zombie Slayer XP",
                " §8+Boss Drops",
                "",
                "§7Cost to start: §65,000 coins",
                "",
                "§eClick to start!");
        inventory.setItem(12, tier2);

        ItemStack comingSoon = makeItem(Material.BEDROCK, "§5Revenant Horror", 1, 0, "§7This boss is not available yet!");
        inventory.setItem(13, comingSoon);
        inventory.setItem(14, comingSoon);
        inventory.setItem(15, comingSoon);

        ItemStack back = makeItem(Material.ARROW, "§aGo back", 1, 0, "§7To Slayer Quest");
        inventory.setItem(49, back);

        ItemStack progress = makeItem(Material.GOLD_BLOCK, "§5Zombie Slayer Progress", 1, 0, "§c§lCOMING SOON!");
        inventory.setItem(28, progress);
    }
}
