package net.maploop.enums;

public enum ItemStats {
    SPEED("Speed"),
    DAMAGE("Damage"),
    STRENGTH("Strength"),
    HEALTH("Health"),
    DEFENSE("Defense"),
    INTELLIGENCE("Intelligence"),
    CRIT_CHANCE("CritChance"),
    CRIT_DAMAGE("CritDamage"),
    MINIG_SPEED("MiningSpeed"),
    BONUS_ATTACK_SPEED("BonusAttackSpeed"),
    SEA_CHANCE("SeaChance"),
    MAGIC_FIND("MagicFind"),
    PET_LUCK("PetLuck"),
    FEROCITY("Ferocity"),
    ABILITY_DAMAGE("AbilityDamage"),
    MINING_FORTUNE("MiningFortune"),
    FARMING_FORTUNE("FarmingFortune"),
    FORAGING_FORTUNE("ForagingFortune");

    String stat;

    ItemStats(String stat) {
        this.stat = stat;
    }
}
