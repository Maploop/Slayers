package net.maploop.slayers.MenuSystem;

import net.maploop.slayers.Bosses.SlayerBoss;
import net.maploop.slayers.Quests.Quest;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ConfirmMenu extends Menu {
    public ConfirmMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getTitle() {
        return "Confirm";
    }

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public void hadleMenu(InventoryClickEvent event) {
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();

        switch (event.getSlot()) {
            case 11:
                Quest.cancelQuest(player);
                if (SlayerBoss.bossMap.containsKey(player.getUniqueId())) {
                    SlayerBoss.bossMap.get(player.getUniqueId()).remove();
                }
                player.sendMessage("§cSuccessfully cancelled your current Slayer Quest.");
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 10F, 2);
                player.closeInventory();
                break;
            case 15:
                player.sendMessage("§aAction cancelled.");
                new QuestMenu(new PlayerMenuUtility(player)).open();
        }
    }

    @Override
    public void setItems() {
        ItemStack confirm = makeItem(Material.STAINED_CLAY, "§aConfirm", 1, 5,
                "§7Click to confirm",
                "§7this action.");
        inventory.setItem(11, confirm);

        ItemStack cancel = makeItem(Material.STAINED_CLAY, "§cCancel", 1, 14,
                "§7Click to cancel",
                "§7this action.");
        inventory.setItem(15, cancel);
    }
}
