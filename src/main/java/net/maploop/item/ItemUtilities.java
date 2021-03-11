package net.maploop.item;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import net.maploop.Slayers;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class ItemUtilities {
        private static final Set<Material> TRANSPARENT = EnumSet.of(Material.AIR, Material.CARPET, Material.IRON_FENCE);

        private static Map<Player, Long> mostRecentSelect = new HashMap<>();

        public static String toLocString(Location location) {
            if (location.equals(null))
                return "";
            return location.getWorld().getName() + "," + (int)location.getX() + "," + (int)location.getY() + "," + (int)location.getZ();
        }

        public static Location fromLocString(String locString) {
            if (locString.equals(""))
                return null;
            String[] data = locString.split(",");
            return new Location(Bukkit.getWorld(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
        }

        public static ItemStack loreItem(ItemStack item, List<String> lore) {
            ItemMeta meta = item.getItemMeta();
            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
        }

        public static ItemStack nameItem(Material item, String name) {
            return nameItem(new ItemStack(item), name);
        }

        public static ItemStack nameItem(ItemStack item, String name) {
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(name);
            item.setItemMeta(meta);
            return item;
        }

        public static void repairItem(ItemStack item) {
            ItemMeta meta = item.getItemMeta();
            if (meta instanceof Damageable) {
                ((Damageable) meta).setHealth(((Damageable) meta).getMaxHealth());
                item.setItemMeta(meta);
            }
        }

        public static void warnPlayer(CommandSender sender, List<String> messages) {
            if (sender instanceof Player) {
                Player player = (Player)sender;
                playSound(ActionSound.ERROR, player);
            }
            for (String message : messages)
                sender.sendMessage(ChatColor.RESET + "" + ChatColor.RED + message);
        }

        public static void informPlayer(CommandSender sender, List<String> messages) {
            for (String message : messages)
                sender.sendMessage(ChatColor.RESET + "" + ChatColor.GRAY + message);
        }

        public static Block getBlockLookingAt(Player player) {
            return player.getTargetBlock(TRANSPARENT, 120);
        }

        public static void playSound(ActionSound sound, Player player) {
            switch (sound) {
                case OPEN:
                    player.playSound(player.getLocation(), Sound.CHEST_OPEN, 1, 1);
                    break;
                case MODIFY:
                    player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
                    break;
                case SELECT:
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                    break;
                case CLICK:
                    player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
                    break;
                case POP:
                    player.playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                    break;
                case BREAK:
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
                    break;
                case ERROR:
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 0.5F);
                    break;
            }
        }

        public static boolean isInteger(String input) {
            try {
                Integer.parseInt(input);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public static ItemStack storeStringInItem(ItemStack host, String string, String key) {

            NBTItem nbtItem = new NBTItem(host);
            NBTCompound test = nbtItem.addCompound("sb");
            test.setString(key, string);
            return nbtItem.getItem();
        }

        public static String getStringFromItem(ItemStack host, String key) {
            if (host != null) {
                NBTItem nbtItem = new NBTItem(host);
                NBTCompound test = nbtItem.getCompound("sb");
                if (test == null) {
                    return null;
                } else
                    return test.getString(key);
            }
            return null;
        }

        public static ItemStack storeIntInItem(ItemStack host, Integer i, String key) {
            if (host == null)
                return host;
            if (!host.hasItemMeta())
                return host;

            NBTItem nbtItem = new NBTItem(host);
            NBTCompound test = nbtItem.addCompound("sb");
            test.setInteger(key, i);
            return nbtItem.getItem();
        }

        public static Integer getIntFromItem(ItemStack host, String key) {
            if (host == null)
                return null;
            if (!host.hasItemMeta())
                return null;

            NBTItem nbtItem = new NBTItem(host);
            NBTCompound test = nbtItem.getCompound("sb");
            return test.getInteger(key);
        }

        public static void scheduleTask(Runnable run, int i) {
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) Slayers.getPlugin(), run, i);
        }

        public static boolean isSBItem(ItemStack item) {
            return (getStringFromItem(item, "is-SB") != null);
        }

        public static boolean isSBItem(ItemStack item, int id) {
            if (!isSBItem(item)) {
                return false;
            }
            return (getIntFromItem(item, "SB-ID") == id);
        }

        public static CustomItem getSBItem(ItemStack item) {
            if (!isSBItem(item))
                return null;
            return SBItems.items.get(getStringFromItem(item, "SB-name"));
        }

        public static CustomItem getItem(String name) {
            for (String key : SBItems.items.keySet()) {
                CustomItem SBItemm = SBItems.items.get(key);
                if (SBItemm.getName().equals(name))
                    return SBItemm;
            }
            return null;
        }

        public static void activeEffects() {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (isSBItem(player.getInventory().getItemInHand())) {
                    CustomItem item = getSBItem(player.getInventory().getItemInHand());
                    if (item.hasActiveEffect())
                        item.activeEffect(player, player.getInventory().getItemInHand());
                }
            }
        }

        public static ItemStack searchFor(Inventory inv, int id) {
            for (ListIterator<ItemStack> listIterator = inv.iterator(); listIterator.hasNext(); ) {
                ItemStack item = listIterator.next();
                if (isSBItem(item, id))
                    return item;
            }
            return null;
        }

        public static boolean enforceCooldown(Player player, String key, double seconds, ItemStack item, boolean throwError) {
            double time = System.currentTimeMillis() / 1000.0D;
            int lastTime = getIntFromItem(item, key);
            if (lastTime == 0) {
                player.setItemInHand(storeIntInItem(item, (int) time, key));
                return true;
            }
            if (time - seconds > lastTime) {
                player.setItemInHand(storeIntInItem(item, (int) time, key));
                return true;
            }
            int timeLeft = (int)time - lastTime;
            timeLeft = (int)seconds - timeLeft;
            if (throwError)
                warnPlayer((CommandSender)player, Collections.singletonList("This ability is on cooldown for " + timeLeft + "s."));
            return false;
        }
}
