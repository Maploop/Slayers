package net.maploop.enums;

public enum ItemType {
    SWORD("SWORD"),
    BOW("BOW"),
    BOOTS("BOOTS"),
    LEGGINGS("LEGGINGS"),
    CHESPLATE("CHESTPLATE"),
    HELMET("HELMET"),
    ITEM("");

    private String s;

    ItemType(String s) {
        this.s = s;
    }

    public String getValue() {
        return s;
    }
}
