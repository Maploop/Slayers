package net.maploop.listeners;

import net.maploop.data.VaultData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {
    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getInventory().getTitle().contains(event.getPlayer().getName() + "'s Private Vault"))
            VaultData.getData().put(event.getPlayer().getUniqueId().toString(), event.getInventory().getContents());
    }
}
