package net.deemu.slayers.MenuSystem;

import net.deemu.slayers.Quests.Quest;
import net.deemu.slayers.Quests.QuestType;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class QuestMenu extends Menu{
    public QuestMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getTitle() {
        return "Active Slayer Quest";
    }

    @Override
    public int getSize() {
        return 36;
    }

    @Override
    public void hadleMenu(InventoryClickEvent event) {
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        switch (event.getSlot()) {
            case 31:
                new SlayersMenu(new PlayerMenuUtility(player)).open();
                break;
            case 13:
                if(event.getCurrentItem().getItemMeta().getDisplayName().contains("§eRevenant")) {
                    player.closeInventory();
                    Quest.cancelQuest(player);
                    player.sendMessage("§cSlayer quest cancelled.");
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10F, 0);
                } else {
                    player.sendMessage("§cYou do not have an active quest!");
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10F, 0);
                }
        }
    }

    @Override
    public void setItems() {
        Player player = playerMenuUtility.getOwner();

        setFilter();
        ItemStack back = makeItem(Material.ARROW, "§aGo back", 1, 0, "§7To Slayer Quest");
        inventory.setItem(31, back);

        ItemStack slayerQuest;
        Quest quest = new Quest();
        if(quest.getType(player) == null) {
            slayerQuest = makeItem(Material.BEDROCK, "§cError!", 1, 0,
                    "§7You do not currently have",
                    "§7active slayer quest!",
                    "§7Activate a quest via",
                    "§e/slayer menu§.");
            inventory.setItem(13, slayerQuest);
            return;
        }
        if(quest.getType(player).equals(QuestType.ZOMBIE_SLAYER_TIER_1)) {
            slayerQuest = makeItem(Material.ROTTEN_FLESH, "§eRevenant Horror I", 1, 0, "§cClick to cancel!");
            inventory.setItem(13, slayerQuest);
        }

    }
}
