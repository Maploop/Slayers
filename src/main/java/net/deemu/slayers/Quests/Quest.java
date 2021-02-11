package net.deemu.slayers.Quests;

import net.deemu.slayers.Listeners.EntityDeathEvent;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Quest {
    private static String type;
    public static HashMap<UUID, QuestType> quest = new HashMap<>();

    public static HashMap<UUID, QuestType> getQuest(Player player) {
        return quest;
    }

    public static void startQuest(Player player, QuestType type) {
        quest.put(player.getUniqueId(), type);
    }

    public QuestDifficulty getDifficulty(QuestType questType) {
        if (questType.toString().contains("ZOMBIE_SLAYER"))
            return QuestDifficulty.EASY;
        if (questType.toString().contains("SPIDER_SLAYER"))
            return QuestDifficulty.MEDIUM;
        if (questType.toString().contains("WOLF_SLAYER"))
            return QuestDifficulty.HARD;

        return null;
    }

    public QuestType getType(Player player) {
        if (quest.containsKey(player.getUniqueId())) {
            switch (quest.get(player.getUniqueId())) {
                case ZOMBIE_SLAYER_TIER_1:
                    return QuestType.ZOMBIE_SLAYER_TIER_1;
                case ZOMBIE_SLAYER_TIER_2:
                    return QuestType.ZOMBIE_SLAYER_TIER_2;
                case ZOMBIE_SLAYER_TIER_3:
                    return QuestType.ZOMBIE_SLAYER_TIER_3;
                case ZOMBIE_SLAYER_TIER_4:
                    return QuestType.ZOMBIE_SLAYER_TIER_4;

                case SPIDER_SLAYER_TIER_1:
                    return QuestType.SPIDER_SLAYER_TIER_1;
                case SPIDER_SLAYER_TIER_2:
                    return QuestType.SPIDER_SLAYER_TIER_2;
                case SPIDER_SLAYER_TIER_3:
                    return QuestType.SPIDER_SLAYER_TIER_3;
                case SPIDER_SLAYER_TIER_4:
                    return QuestType.SPIDER_SLAYER_TIER_4;

                case WOLF_SLAYER_TIER_1:
                    return QuestType.WOLF_SLAYER_TIER_1;
                case WOLF_SLAYER_TIER_2:
                    return QuestType.WOLF_SLAYER_TIER_2;
                case WOLF_SLAYER_TIER_3:
                    return QuestType.WOLF_SLAYER_TIER_3;
                case WOLF_SLAYER_TIER_4:
                    return QuestType.WOLF_SLAYER_TIER_4;
            }
        } else {
            return null;
        }
        return null;
    }

    public static void cancelQuest(Player player) {
        quest.remove(player.getUniqueId());
        EntityDeathEvent.combat_exp.remove(player.getUniqueId());
    }
}
