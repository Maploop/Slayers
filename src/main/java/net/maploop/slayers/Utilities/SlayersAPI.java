package net.maploop.slayers.Utilities;

import net.maploop.slayers.Listeners.EntityDeathEvent;
import org.bukkit.entity.Player;

/**
 * All rights to the Hypixel Network for the plugin idea.
 * This is only a re-creation.
 * @author Maploop
 *
 * By using this API you can get player kills and change player stats,
 * pretty easy!
 *
 * API powered by Maploop.
 */

public class SlayersAPI {
    public static int getKills(Player player) {
        return (EntityDeathEvent.combat_exp.get(player.getUniqueId()) / 5);
    }

}
