package net.maploop.data;

import net.maploop.files.DataFile;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class VaultData {
    private static final HashMap<String, ItemStack[]> vaultData = new HashMap<>();

    public static HashMap<String, ItemStack[]> getData() {
        return vaultData;
    }

    public static void save() {
        if (vaultData.isEmpty()) return;
        for (HashMap.Entry<String, ItemStack[]> entry : vaultData.entrySet()) {
            DataFile.get().set("data." + entry.getKey(), entry.getValue());
        }
        DataFile.save();
    }

    public static void restore() {
        DataFile.get().getConfigurationSection("data").getKeys(false).forEach(key -> {
            @SuppressWarnings("unchecked")
            ItemStack[] content = ((List<ItemStack>) DataFile.get().get("data." + key)).toArray(new ItemStack[0]);
            vaultData.put(key, content);
        });
        DataFile.get().set("data", null);
        DataFile.save();
    }
}
