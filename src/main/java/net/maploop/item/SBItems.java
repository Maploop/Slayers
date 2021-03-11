package net.maploop.item;

import org.bukkit.entity.ArmorStand;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SBItems {
    public static String uniqueId;
    public static Map<UUID, String> id = new HashMap<>();
    public static Map<UUID, String> cooldownmn = new HashMap<>();

    public static Map<String, CustomItem> items = new HashMap<>();
    public static Map<Integer, String> itemIDs = new HashMap<>();

    public static HashMap<UUID, ArmorStand> isPlaced = new HashMap<>();
    public static HashMap<ArmorStand, Long> timer = new HashMap<>();

    public static void putItem(String name, CustomItem item) {
        items.put(name, item);
        itemIDs.put(item.getID(), name);
    }

}
