package net.maploop.quests;

public enum QuestType {
    ZOMBIE_SLAYER_TIER_1(1, 1, "§aRevenant Horror I"),
    ZOMBIE_SLAYER_TIER_2(2, 2, "§eRevenant Horror II"),
    ZOMBIE_SLAYER_TIER_3(3, 3, "§cRevenant Horror III"),
    ZOMBIE_SLAYER_TIER_4(4, 4, "§4Revenant Horror IV"),

    SPIDER_SLAYER_TIER_1(5, 1, "§aTarantula Broodfather I"),
    SPIDER_SLAYER_TIER_2(6, 2, "§eTarantula Broodfather II"),
    SPIDER_SLAYER_TIER_3(7, 3, "§cTarantula Broodfather III"),
    SPIDER_SLAYER_TIER_4(8, 4, "§4Tarantula Broodfather IV"),

    WOLF_SLAYER_TIER_1(9, 1, "§aSven Packmaster I"),
    WOLF_SLAYER_TIER_2(10, 2, "§eSven Packmaster II"),
    WOLF_SLAYER_TIER_3(11, 3, "§cSven Packmaster III"),
    WOLF_SLAYER_TIER_4(12, 4, "§4Sven Packmaster IV");



    private final String displayname;
    private final int id;
    private final int tier;

    QuestType(int id, int tier, String displayname) {
        this.id = id;
        this.tier = tier;
        this.displayname = displayname;
    }

    public int getTier() {
        return tier;
    }

    public String getDisplayname() {
        return displayname;
    }
}
