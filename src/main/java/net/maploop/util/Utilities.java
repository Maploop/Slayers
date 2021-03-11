package net.maploop.util;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class Utilities {
    public static void sendActionbar(Player p, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" +
                ChatColor.translateAlternateColorCodes('&', message) + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)bar);
    }

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static int getRandomInteger(int max) {
        Random ran = new Random();
        return ran.nextInt(max);
    }

    public static String formatValue(double value) {
        int power;
        String suffix = " kmbt";
        String formattedNumber = "";

        NumberFormat formatter = new DecimalFormat("#,###.#");
        power = (int)StrictMath.log10(value);
        value = value/(Math.pow(10,(power/3)*3));
        formattedNumber=formatter.format(value);
        formattedNumber = formattedNumber + suffix.charAt(power/3);
        return formattedNumber.length()>4 ?  formattedNumber.replaceAll("\\.[0-9]+", "") : formattedNumber;
    }

    public static Location getRandomLocation(Location origin, int radius) {
        int which = getRandomInteger(3);
        Location newLoc = origin;

        switch (which) {
            case 0:
                int x = getRandomInteger(radius);
                newLoc = origin.add(x, 0 ,0);
                break;
            case 1:
                int z = getRandomInteger(radius);
                newLoc = origin.add(0, 0, z);
                break;
            case 2:
                int minusX = getRandomInteger(radius);
                newLoc = origin.add(-minusX, 0, 0);
                break;
            case 3:
                int minusZ = getRandomInteger(radius);
                newLoc = origin.add(0, 0, -minusZ);
                break;
        }

        return newLoc;
    }

    public static int percent(int currentValue, int maxValue){
        float percent = (currentValue/maxValue) *100;
        return (int)percent;
    }

    public static int getRandomInrange(int min, int max) {
        Random rn = new Random();
        int range = max - min + 1;
        int randomNum =  rn.nextInt(range) + min;

        return randomNum;
    }

}
