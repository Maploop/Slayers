package net.maploop.menus;

import net.maploop.quests.Quest;
import net.maploop.quests.QuestType;
import net.maploop.bosses.SlayerBoss;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ConfirmMenu extends Menu {
    private final String reason;
    public ConfirmMenu(PlayerMenuUtility playerMenuUtility, String reason) {
        super(playerMenuUtility);
        this.reason = reason;
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
            case 11: {
                if (reason.equals("cancel_quest")) {
                    Quest.cancelQuest(player);
                    if (SlayerBoss.bossMap.containsKey(player.getUniqueId())) {
                        SlayerBoss.bossMap.get(player.getUniqueId()).remove();
                    }
                    player.sendMessage("§eYour slayer quest was cancelled.");
                    player.playSound(player.getLocation(), Sound.WOOD_CLICK, 10F, 1.5f);
                    player.closeInventory();
                }
                if (reason.equals("start_quest_zombie_1")) {
                    player.closeInventory();
                    Quest.startQuest(player, QuestType.ZOMBIE_SLAYER_TIER_1);
                    player.sendMessage("§5§l  SLAYER QUEST STARTED!");
                    player.sendMessage("§5§l  » §7Slay §c100 Combat XP §7worth of Zombies.");
                    player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 10F, 2);
                }
                if (reason.equals("start_quest_zombie_2")) {
                    player.closeInventory();
                    Quest.startQuest(player, QuestType.ZOMBIE_SLAYER_TIER_2);
                    player.sendMessage("§5§l  SLAYER QUEST STARTED!");
                    player.sendMessage("§5§l  » §7Slay §c440 Combat XP §7worth of Zombies.");
                    player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 10F, 2);

                }
                if (reason.equals("start_quest_zombie_3")) {
                    player.closeInventory();
                    Quest.startQuest(player, QuestType.ZOMBIE_SLAYER_TIER_3);
                    player.sendMessage("§5§l  SLAYER QUEST STARTED!");
                    player.sendMessage("§5§l  » §7Slay §c670 Combat XP §7worth of Zombies.");
                    player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 10F, 2);

                }
                if (reason.equals("start_quest_zombie_4")) {
                    player.closeInventory();
                    Quest.startQuest(player, QuestType.ZOMBIE_SLAYER_TIER_4);
                    player.sendMessage("§5§l  SLAYER QUEST STARTED!");
                    player.sendMessage("§5§l  » §7Slay §c1440 Combat XP §7worth of Zombies.");
                    player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 10F, 2);
                }
                break;
            }
            case 15:
                player.closeInventory();
        }
    }

    @Override
    public void setItems() {
        ItemStack confirm = makeItem(Material.STAINED_CLAY, "§aConfirm", 1, 5);
        inventory.setItem(11, confirm);

        ItemStack cancel = makeItem(Material.STAINED_CLAY, "§cCancel", 1, 14);
        inventory.setItem(15, cancel);
    }
}
