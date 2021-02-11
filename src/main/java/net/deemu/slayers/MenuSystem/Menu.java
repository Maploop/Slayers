package net.deemu.slayers.MenuSystem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public abstract class Menu implements InventoryHolder {

    protected PlayerMenuUtility playerMenuUtility;
    protected Inventory inventory;
    protected ItemStack FILLER_GLASS = makeItem(Material.STAINED_GLASS_PANE, " ", 1, 15);

    public Menu(PlayerMenuUtility playerMenuUtility) {
        this.playerMenuUtility = playerMenuUtility;
    }

    public abstract String getTitle();
    public abstract int getSize();
    public abstract void hadleMenu(InventoryClickEvent event);
    public abstract void setItems();

    public void open() {
        inventory = Bukkit.createInventory(this, getSize(), getTitle());
        this.setItems();
        playerMenuUtility.getOwner().openInventory(inventory);
    }

    @Override
    public Inventory getInventory(){
        return inventory;
    }

    public void setFilter(){
        for(int slots = 0; slots < getSize(); slots++){
            if(inventory.getItem(slots) == null){
                inventory.setItem(slots, FILLER_GLASS);
            }
        }
    }
    public ItemStack makeItem(Material material, String displayName, int amount, int durability, String... lore){
        ItemStack item = new ItemStack(material, amount, (short) durability);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);

        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack makeAdvancedItem(Material material, String displayName, int amount, int durability, List<String> lore){
        ItemStack item = new ItemStack(material, amount, (short) durability);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
}
