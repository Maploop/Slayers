package net.deemu.slayers.enums;

public enum Message {
    NO_PERMISSION("§cYou must be admin or higher to use this command!");


    private final String value;

    Message(String value) {
        this.value = value;
    }
}
