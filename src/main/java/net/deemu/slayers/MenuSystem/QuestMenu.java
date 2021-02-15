package net.deemu.slayers.MenuSystem;

import net.deemu.slayers.Listeners.EntityDeathEvent;
import net.deemu.slayers.Quests.Quest;
import net.deemu.slayers.Quests.QuestType;
import net.deemu.slayers.Slayers;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class QuestMenu extends Menu {
    public QuestMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getTitle() {
        return "Active Slayer Quest";
    }

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public void hadleMenu(InventoryClickEvent event) {
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        String[] lore = event.getInventory().getItem(13).getItemMeta().getLore().toArray(new String[0]);
        if (event.getSlot() == 13) {
            if (Quest.questComplete.containsKey(player.getUniqueId())) {
                player.sendMessage(ChatColor.GREEN + "REWARDS CLAIMED!");
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 10F, 1.5F);
                Quest.cancelQuest(player);
                Quest.questComplete.remove(player.getUniqueId());
            } else {
                new ConfirmMenu(new PlayerMenuUtility(player)).open();
            }
        }
    }

    @Override
    public void setItems() {
        Player player = playerMenuUtility.getOwner();

        setFilter();
        Quest quest = new Quest();
        ItemStack q = makeCustomSkullItem("http://textures.minecraft.net/texture/297d6d7be985d0622a48e90698e9073f7ff8813292812ebd1730dba0e01cf18f", "§cLoading...", 1, "§7Please wait a bit...");
        inventory.setItem(13, q);
        new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack slayerQuest;
                try {
                    if (!(Quest.questComplete.containsKey(player.getUniqueId()))) {
                        if (quest.getType(player) == null) {
                            slayerQuest = makeItem(Material.BEDROCK, "§cError!", 1, 0,
                                    "§7You do not currently have",
                                    "§7an active slayer quest!");
                            inventory.setItem(13, slayerQuest);
                            return;
                        }
                        if (quest.getType(player).equals(QuestType.ZOMBIE_SLAYER_TIER_1)) {
                            slayerQuest = makeItem(Material.ROTTEN_FLESH, "§eRunning Slayer Quest", 1, 0,
                                    "§7Boss: §eRevenant Horror I",
                                    "§7Progress: §e" + EntityDeathEvent.combat_exp.get(player.getUniqueId()) / 5 + "§7/§e21",
                                    "",
                                    "§eClick to cancel!");
                            inventory.setItem(13, slayerQuest);
                        }
                        if (quest.getType(player).equals(QuestType.ZOMBIE_SLAYER_TIER_2)) {
                            slayerQuest = makeItem(Material.ROTTEN_FLESH, "§eRunning Slayer Quest", 1, 0,
                                    "§7Boss: §eRevenant Horror II",
                                    "§7Progress: §e" + EntityDeathEvent.combat_exp.get(player.getUniqueId()) / 5 + "§7/§e200",
                                    "",
                                    "§eClick to cancel!");
                            inventory.setItem(13, slayerQuest);
                        }
                    } else {
                        if (quest.getType(player).equals(QuestType.ZOMBIE_SLAYER_TIER_1)) {
                            slayerQuest = makeItem(Material.ROTTEN_FLESH, "§aSlayer Quest Complete!", 1, 0,
                                    "§7Boss: §eRevenant Horror I",
                                    "§7Progress: §aCOMPLETE!",
                                    "",
                                    "§eClick to cancel!");
                            inventory.setItem(13, slayerQuest);
                        }
                        if (quest.getType(player).equals(QuestType.ZOMBIE_SLAYER_TIER_2)) {
                            slayerQuest = makeItem(Material.ROTTEN_FLESH, "§aSlayer Quest Complete!", 1, 0,
                                    "§7Boss: §eRevenant Horror II",
                                    "§7Progress: §aCOMPLETE!",
                                    "",
                                    "§eClick to cancel!");
                            inventory.setItem(13, slayerQuest);
                        }
                    }
                } catch (NullPointerException e) {
                    player.sendMessage(ChatColor.RED + "An error occurred while loading an item, please report this to an adminisrator.");
                    slayerQuest = makeItem(Material.BEDROCK, "§cError!", 1, 0, "§7An error occurred while", "§7loading this item/information.");
                    inventory.setItem(13, slayerQuest);
                }
            }
        }.runTaskLater(Slayers.getPlugin(), 10);
    }
}
