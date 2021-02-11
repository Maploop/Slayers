package net.deemu.slayers.MenuSystem;

import net.deemu.slayers.Quests.Quest;
import net.deemu.slayers.Quests.QuestType;
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
                    player.sendMessage("§e§lSLAYER QUEST STARTED!");
                    player.sendMessage("§e[NPC] MaddoxTheSlayer§f: Collect 100 combat experience worth of Zombies!");
                    player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 10F, 1);
                }
        }
    }

    @Override
    public void setItems() {
        setFilter();
        ItemStack tier1 = makeItem(Material.ROTTEN_FLESH, "§cRevenant Horror I", 1, 0,
                "§8Beginner",
                "",
                "§7Health: §c20,000",
                "§7Damage: §c10 §7per second",
                "",
                "§7Reward: §d10 Zombie Slayer XP §c(Not Implemented)",
                " §8+ Boss Drops",
                "",
                "§7Cost to start: §8FREE! §e(Temporarily)",
                "",
                "§eClick to start!");
        inventory.setItem(11, tier1);

        ItemStack back = makeItem(Material.ARROW, "§aGo back", 1, 0, "§7To Slayer Quest");
        inventory.setItem(49, back);
    }
}
