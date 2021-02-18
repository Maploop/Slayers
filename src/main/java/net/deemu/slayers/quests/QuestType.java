package net.deemu.slayers.quests;

public enum QuestType {
    ZOMBIE_SLAYER_TIER_1(1, 1),
    ZOMBIE_SLAYER_TIER_2(2, 2),
    ZOMBIE_SLAYER_TIER_3(3, 3),
    ZOMBIE_SLAYER_TIER_4(4, 4),

    SPIDER_SLAYER_TIER_1(5, 1),
    SPIDER_SLAYER_TIER_2(6, 2),
    SPIDER_SLAYER_TIER_3(7, 3),
    SPIDER_SLAYER_TIER_4(8, 4),

    WOLF_SLAYER_TIER_1(9, 1),
    WOLF_SLAYER_TIER_2(10, 2),
    WOLF_SLAYER_TIER_3(11, 3),
    WOLF_SLAYER_TIER_4(12, 4);




    private final int id;
    private final int tier;

    QuestType(int id, int tier) {
        this.id = id;
        this.tier = tier;
    }

    public int getTier() {
        return tier;
    }
}
