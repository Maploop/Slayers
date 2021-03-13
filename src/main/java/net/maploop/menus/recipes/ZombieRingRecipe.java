package net.maploop.menus.recipes;

import net.maploop.Slayers;
import net.maploop.item.CustomItem;
import net.maploop.menus.Menu;
import net.maploop.menus.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ZombieRingRecipe extends Menu {
    public ZombieRingRecipe(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getTitle() {
        return "Zombie Ring Recipe";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public void hadleMenu(InventoryClickEvent event) {
        event.setCancelled(true);

        switch (event.getSlot()) {
            case 49: {
                event.getWhoClicked().closeInventory();
                break;
            }
            default: {
                event.setCancelled(true);
                break;
            }
        }
    }

    @Override
    public void setItems() {
        setFilter();

        inventory.setItem(10, null);
        inventory.setItem(11, null);
        inventory.setItem(12, null);
        inventory.setItem(19, CustomItem.fromString(Slayers.getPlugin(), "revenant_flesh", 32));
        inventory.setItem(20, new ItemStack(Material.SKULL_ITEM, 1, (short) 3));
        inventory.setItem(21, CustomItem.fromString(Slayers.getPlugin(), "revenant_flesh", 32));

        inventory.setItem(25, CustomItem.fromString(Slayers.getPlugin(), "zombie_ring", 1));

        inventory.setItem(28, null);
        inventory.setItem(29, null);
        inventory.setItem(30, null);

        ItemStack close = makeItem(Material.BARRIER, "§cClose", 1, 0);
        inventory.setItem(49, close);

        ItemStack craftingTable = makeItem(Material.WORKBENCH, "§aCrafting Table", 1, 0,"§7Craft this recipe by using\n§7a crafting table.");
        inventory.setItem(23, craftingTable);
    }
}
