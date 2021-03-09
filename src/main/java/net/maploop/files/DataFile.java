package net.maploop.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataFile {
    private static File file;
    private static FileConfiguration configuration;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Slayers").getDataFolder(), "data.yml");

        if (!(file.exists())) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // Sending error to console
                System.out.println("An error has occurred while trying to generate custom configuration.");
            }
        }
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return configuration;
    }

    public static void save() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            Bukkit.getLogger().warning("An error has occurred while trying to save data.");
        }
    }

    public static void reload() {
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public static void setDefaults() {
        configuration.set("data.one", null);
    }
}
