package net.maploop.item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.maploop.Slayers;
import net.maploop.enums.ItemType;
import net.maploop.enums.Rarity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public abstract class CustomItem {

    private final int id;
    private final Rarity rarity;
    private final String name;
    private final Material material;
    private List<String> defaultLore;
    private boolean stackable = false;
    private boolean oneTimeUse = false;
    private boolean hasActive = false;
    private boolean reforgeable = false;
    private final ItemType itemType;
    private int manaCost = 0;
    private List<ItemAbility> abilities = new ArrayList<>();
    private String url;
    private final int durability;
    private final boolean glowing;



    public CustomItem(int id, Rarity rarity, String name, Material material, int durability, boolean stackable, boolean oneTimeUse, boolean hasActive, List<ItemAbility> abilities, int manaCost, boolean reforgeable, ItemType itemType, boolean glowing) {
        this.id = id;
        this.rarity = rarity;
        this.name = name;
        this.material = material;
        this.stackable = stackable;
        this.oneTimeUse = oneTimeUse;
        this.hasActive = hasActive;
        this.abilities = abilities;
        this.manaCost = manaCost;
        this.reforgeable = reforgeable;
        this.itemType = itemType;
        this.durability = durability;
        this.glowing = glowing;
    }

    public CustomItem(int id, Rarity rarity, String name, Material material, int durability, boolean stackable, boolean oneTimeUse, boolean hasActive, List<ItemAbility> abilities, int manaCost, boolean reforgeable, ItemType itemType, String url, boolean glowing) {
        this.id = id;
        this.rarity = rarity;
        this.name = name;
        this.material = material;
        this.stackable = stackable;
        this.oneTimeUse = oneTimeUse;
        this.hasActive = hasActive;
        this.abilities = abilities;
        this.manaCost = manaCost;
        this.reforgeable = reforgeable;
        this.itemType = itemType;
        this.url = url;
        this.durability = durability;
        this.glowing = glowing;
    }

    public List<String> getLore(ItemStack item) {
        List<String> lore = new ArrayList<>();
        if (this.rarity == Rarity.UNFINISHED) {
            lore.add(ChatColor.RED + "This item is WIP");
            lore.add(ChatColor.RED + "It has not been completed");
            lore.add(ChatColor.RED + "and might be broken");
        }
        getSpecificLorePrefix(lore, item);
        if (abilities != null) {
            for (ItemAbility ability : this.abilities) {
                lore.addAll(ability.toLore());
            }
        }
        if (this.manaCost != 0) {
            lore.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + manaCost);
        }
        if (this.reforgeable) {
            lore.add("");
            lore.add(ChatColor.DARK_GRAY + "This item can be reforged!");
        }
        getSpecificLoreSuffix(lore, item);
        if (this.oneTimeUse)
            lore.add(ChatColor.DARK_GRAY + "(consumed on use)");
        if (!this.reforgeable) {
            lore.add("");
        }
        lore.add("" + this.rarity.getColor() + ChatColor.BOLD + this.rarity.toString() + " " + itemType.getValue());
        return lore;
    }

    public void updateLore(ItemStack item) {
        if (item == null)
            return;
        ItemUtilities.loreItem(item, getLore(item));
    }

    public void changeMaterial(ItemStack item, Material material) {
        if (item == null)
            return;
        item.setType(material);
    }

    public void enforceStackability(ItemStack item) {
        if (item == null)
            return;
        if(!(this.stackable)) {
            ItemUtilities.storeStringInItem(item, UUID.randomUUID().toString(), "UUID");
        }
    }

    public void applyTexture(ItemStack item) {
        if(item == null) return;

        SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = itemMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(itemMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        item.setItemMeta(itemMeta);
    }

    public void init(String key, ItemStack item) {
        if (key.isEmpty()) return;
        if (key.equals("maddox_batphone")) {
            setDurability(item);
            applyTexture(item);
        }
    }

    public void setDurability(ItemStack item) {
        item.setDurability((short) durability);
    }

    public void onItemUse(Player player, ItemStack item) {
        if (this.oneTimeUse && player.getGameMode() != GameMode.CREATIVE)
            destroy(item, 1);
    }

    public abstract void onItemStackCreate(ItemStack paramItemStack);

    public abstract void getSpecificLorePrefix(List<String> paramList, ItemStack paramItemStack);

    public abstract void getSpecificLoreSuffix(List<String> paramList, ItemStack paramItemStack);

    public abstract void leftClickAirAction(Player paramPlayer, ItemStack paramItemStack);

    public abstract void leftClickBlockAction(Player paramPlayer, PlayerInteractEvent paramPlayerInteractEvent, Block paramBlock, ItemStack paramItemStack);

    public abstract void rightClickAirAction(Player paramPlayer, PlayerInteractEvent event, ItemStack paramItemStack);

    public abstract void rightClickBlockAction(Player paramPlayer, PlayerInteractEvent paramPlayerInteractEvent, Block paramBlock, ItemStack paramItemStack);

    public abstract void shiftLeftClickAirAction(Player paramPlayer, ItemStack paramItemStack);

    public abstract void shiftLeftClickBlockAction(Player paramPlayer, PlayerInteractEvent paramPlayerInteractEvent, Block paramBlock, ItemStack paramItemStack);

    public abstract void shiftRightClickAirAction(Player paramPlayer, PlayerInteractEvent event, ItemStack paramItemStack);

    public abstract void shiftRightClickBlockAction(Player paramPlayer, PlayerInteractEvent paramPlayerInteractEvent, Block paramBlock, ItemStack paramItemStack);

    public abstract void middleClickAction(Player paramPlayer, ItemStack paramItemStack);

    public abstract void hitEntityAction(Player paramPlayer, EntityDamageByEntityEvent paramEntityDamageByEntityEvent, Entity paramEntity, ItemStack paramItemStack);

    public abstract void breakBlockAction(Player paramPlayer, BlockBreakEvent paramBlockBreakEvent, Block paramBlock, ItemStack paramItemStack);

    public abstract void clickedInInventoryAction(Player paramPlayer, InventoryClickEvent paramInventoryClickEvent);

    public abstract void activeEffect(Player paramPlayer, ItemStack paramItemStack);

    public int getID() {
        return this.id;
    }

    public Rarity getRarity() {
        return this.rarity;
    }

    public String getName() {
        return this.name;
    }

    public Material getMaterial() {
        return this.material;
    }

    public boolean isStackable() {
        return this.stackable;
    }

    public boolean hasActiveEffect() {
        return this.hasActive;
    }

    public static ItemStack fromString(Slayers main, String name, int stackSize) {

        CustomItem item;
        String effectiveName = name;
        if (ItemUtilities.isInteger(name)) {
            item = SBItems.items.get(SBItems.itemIDs.get(Integer.parseInt(name)));
            effectiveName = SBItems.itemIDs.get(Integer.parseInt(name));
            Bukkit.getLogger().info(item.getName());
        } else {
            item = SBItems.items.get(name);
        }
        if (item == null)
            return null;
        ItemStack newItemStack = new ItemStack(item.getMaterial());
        ItemUtilities.nameItem(newItemStack, item.getRarity().getColor() + item.getName());

        ItemStack step1 = ItemUtilities.storeStringInItem(newItemStack, "true", "is-SB");

        ItemStack step2 = ItemUtilities.storeStringInItem(step1, effectiveName, "SB-name");

        ItemStack step3 = ItemUtilities.storeIntInItem(step2, item.getID(), "SB-ID");

        item.enforceStackability(step3);
        item.onItemStackCreate(step3);
        ItemUtilities.loreItem(step3, item.getLore(step3));
        if (item.isStackable())
            step3.setAmount(stackSize);
        ItemMeta meta = step3.getItemMeta();
        if (item.glowing) {
            meta.addEnchant(Enchantment.LUCK,1, false);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        step3.setItemMeta(meta);
        return step3;
    }

    public static void destroy(ItemStack item, int quantity) {
        if (item.getAmount() <= quantity) {
            item.setAmount(0);
        } else {
            item.setAmount(item.getAmount() - quantity);
        }
    }
}
