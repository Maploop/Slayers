package net.maploop.item;

import net.maploop.enums.AbilityType;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Requirement {
    private String name;
    private RequirementType type;
    private String text = "This item wasn't given a description!";

    public Requirement(String name, RequirementType type, String text) {
        this.name = name;
        this.type = type;
        this.text = text;
    }

    public List<String> toLore() {
        List<String> lore = new ArrayList<>();
        if (type == RequirementType.SLAYER)
            lore.add(type.getIcon() + "§cRequires " + ChatColor.DARK_PURPLE + text);
        if (type == RequirementType.CATACOMBS || type == RequirementType.COMBAT)
            lore.add(type.getIcon() + "§cRequires " + ChatColor.GREEN + text);

        return lore;
    }
}
