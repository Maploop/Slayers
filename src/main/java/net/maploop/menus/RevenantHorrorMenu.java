package net.maploop.menus;

import net.maploop.Slayers;
import net.maploop.quests.Quest;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

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
        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
            case "§aGo back":
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        new SlayersMenu(new PlayerMenuUtility(player)).open();
                    }
                }.runTaskLater(Slayers.getPlugin(), 3L);

                break;
            case "§aRevenant Horror I":
                if (Quest.quest.containsKey(player.getUniqueId())) {
                    player.sendMessage(ChatColor.RED + "You are already running a quest!");
                } else {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            new ConfirmMenu(new PlayerMenuUtility(player), "start_quest_zombie_1").open();
                        }
                    }.runTaskLater(Slayers.getPlugin(), 3);
                }
                break;
            case "§eRevenant Horror II": {
                if (Quest.quest.containsKey(player.getUniqueId())) {
                    player.sendMessage(ChatColor.RED + "You are already running a quest!");
                } else {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            new ConfirmMenu(new PlayerMenuUtility(player), "start_quest_zombie_2").open();
                        }
                    }.runTaskLater(Slayers.getPlugin(), 3);

                }
                break;
            }

            case "§cRevenant Horror III": {
                if (Quest.quest.containsKey(player.getUniqueId())) {
                    player.sendMessage(ChatColor.RED + "You are already running a quest!");
                } else {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            new ConfirmMenu(new PlayerMenuUtility(player), "start_quest_zombie_3").open();
                        }
                    }.runTaskLater(Slayers.getPlugin(), 3);

                }
                break;
            }
            case "§4Revenant Horror IV": {
                if (Quest.quest.containsKey(player.getUniqueId())) {
                    player.sendMessage(ChatColor.RED + "You are already running a quest!");
                } else {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            new ConfirmMenu(new PlayerMenuUtility(player), "start_quest_zombie_4").open();
                        }
                    }.runTaskLater(Slayers.getPlugin(), 3);
                }
                break;
            }
        }
    }

    @Override
    public void setItems() {
        setFilter();
        ItemStack tier1 = makeItem(Material.ROTTEN_FLESH, "§aRevenant Horror I", 1, 0,
                "§8Beginner",
                "",
                "§7Health: §c500❤",
                "§7Damage: §c0.5 §7per second",
                "",
                "§cLife Drain",
                "§7Drains health every few seconds.",
                "",
                "§7Reward: §d5 Zombie Slayer XP",
                " §8+ Boss Drops",
                "",
                "§7Cost to start: §8FREE!",
                "",
                "§eClick to slay! §7(FREE!)");
        inventory.setItem(11, tier1);

        ItemStack tier2 = makeItem(Material.ROTTEN_FLESH, "§eRevenant Horror II", 1, 0,
                "§8Strong",
                "",
                "§7Health: §c20,000❤",
                "§7Damage: §c0.5 §7per second",
                "",
                "§cLife Drain",
                "§7Drains health every few seconds.",
                "",
                "§aPestilence",
                "§7Deals AOE damage every second,",
                "§7shredding armor by 25%.",
                "",
                "§7Reward: §d25 Zombie Slayer XP",
                " §8+ Boss Drops",
                "",
                "§7Cost to start: §62,000 coins",
                "",
                "§eClick to slay!");
        inventory.setItem(12, tier2);

        ItemStack tier3 = makeItem(Material.ROTTEN_FLESH, "§cRevenant Horror III", 1, 0,
                "§8Challenging",
                "",
                "§7Health: §c400,000❤",
                "§7Damage: §c1 §7per second",
                "",
                "§cLife Drain",
                "§7Drains health every few seconds.",
                "",
                "§aPestilence",
                "§7Deals AOE damage every second,",
                "§7shredding armor by 25%.",
                "",
                "§cEnrage",
                "§7Gets real mad once in a while.",
                "",
                "§7Reward: §d100 Zombie Slayer XP",
                " §8+ Boss Drops",
                "",
                "§7Cost to start: §610,000 coins",
                "",
                "§eClick to slay!");

        ItemStack tier4 = makeItem(Material.ROTTEN_FLESH, "§4Revenant Horror IV", 1, 0,
                "§8Deadly\n \n§7Health: §c1,500,000❤\n§7Damage: §c2.5 §7per second\n \n§cLife Drain\n§7Drains health every few seconds\n \n§aPestilence\n§7Deals AOE damage every second,\n§7shredding armor by 25%.\n \n§cEnrage" +
                        "\n§7Gets real mad once in a while.\n \n§7Reward: §d500 Zombie Slayer XP\n§8 + Boss Drops\n \n§7Cost to start: §650,000 coins\n \n§eClick to slay!");

        ItemStack comingSoon = makeItem(Material.COAL_BLOCK, "§5Revenant Horror", 1, 0,
                "§7This unimaginably difficult",
                "§7boss tier will release at a",
                "§7later date.");

        inventory.setItem(13, tier3);
        inventory.setItem(14, tier4);
        inventory.setItem(15, comingSoon);

        ItemStack back = makeItem(Material.ARROW, "§aGo back", 1, 0, "§7To Slayer");
        inventory.setItem(49, back);

        ItemStack progress = makeItem(Material.GOLD_BLOCK, "§5Boss Leveling Rewards", 1, 0,
                ChatColor.DARK_GRAY + "Zombie Slayer LVL",
                "",
                ChatColor.DARK_PURPLE + "1. " + ChatColor.GRAY + "Kill boss to get XP",
                ChatColor.DARK_PURPLE + "2. " + ChatColor.GRAY + "Gain LVL from XP",
                ChatColor.DARK_PURPLE + "3. " + ChatColor.GRAY + "Unlock rewards per LVL",
                "",
                ChatColor.GRAY + "Current LVL: " + ChatColor.RED + "N/A",
                ChatColor.GRAY + "Zombie Slayer XP to LVL " + ChatColor.RED + "N/A" + ChatColor.GRAY + ":",
                ChatColor.WHITE + "--------------------- " + ChatColor.LIGHT_PURPLE + "0" + ChatColor.DARK_PURPLE + "/" + ChatColor.RED + "N/A",
                "",
                ChatColor.RED + "" + ChatColor.BOLD + "COMING SOON");

        ItemStack drops = makeItem(Material.GOLD_NUGGET, ChatColor.GOLD + "Boss Drops", 1, 0,
                ChatColor.DARK_GRAY + "Revenant Horror",
                "",
                ChatColor.GRAY + "Usually, the boss will drop",
                ChatColor.GREEN + "Revenant Flesh " + ChatColor.GRAY + "which",
                ChatColor.GRAY + "are used for slayer crafts.",
                "",
                ChatColor.GRAY + "If you're lucky, you may get",
                ChatColor.GRAY + "one of the " + ChatColor.LIGHT_PURPLE + "9 " + ChatColor.GRAY + "possible",
                ChatColor.GRAY + "drops from this boss!",
                "",
                ChatColor.RED + "" + ChatColor.BOLD + "COMING SOON");

        ItemStack recipes = makeItem(Material.BOOK,  ChatColor.GREEN + "Slayer Recipes", 1, 0,
                ChatColor.DARK_GRAY + "Revenant Horror",
                "",
                ChatColor.GRAY + "There are " + ChatColor.YELLOW + "19 " + ChatColor.GRAY + "recipes",
                ChatColor.GRAY + "related to the Zombie boss.",
                "",
                ChatColor.GRAY + "Unlock recipes and collect",
                ChatColor.GRAY + "rare drops in order to",
                ChatColor.GRAY + "craft powerful items.",
                "",
                ChatColor.GRAY + "Unlocked: " + ChatColor.RED + "N/A",
                "",
                ChatColor.RED + "" + ChatColor.BOLD + "COMING SOON");

        ItemStack rngesusMeter = makeItem(Material.PAINTING, "§dRNGesus Meter", 1, 0,
                "§8Revenant Horror\n \n§7Feeling §9unlucky§7? Slay\n§7bosses to fill the mefer\n§7amd guarantee an §dRNGesus\n§ddrop§7.\n \n§7Gaining an RNGesus drop\n§7will reset the meter.\n \n§dProgress:\n§f--------------------- §d0§5%");

        inventory.setItem(32, recipes);
        inventory.setItem(30, drops);
        inventory.setItem(28, progress);
        inventory.setItem(34, rngesusMeter);
    }
}
