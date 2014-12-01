package us.iluthi.soulofw0lf.ultimatesurvival.variables;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 5:10 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Booleans {

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {

        }
        return false;
    }
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {

        }
        return false;
    }
    public static boolean inSpawn(Player p){
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (cP.isInGuildLands()){
            return false;
        }
        if (p.getWorld().getName().contains("nether") || p.getWorld().getName().contains("end")){
            return false;
        }
        if (p.getLocation().getX() > Locations.spawnLoc.getX() + 150){
            return false;
        }
        if (p.getLocation().getY() > Locations.spawnLoc.getY() + 150){
            return false;
        }
        if (p.getLocation().getZ() > Locations.spawnLoc.getZ() + 150){
            return false;
        }
        if (p.getLocation().getX() < Locations.spawnLoc.getX() - 150){
            return false;
        }
        if (p.getLocation().getY() < Locations.spawnLoc.getY() - 150){
            return false;
        }
        if (p.getLocation().getZ() < Locations.spawnLoc.getZ() - 150){
            return false;
        }
        return true;
    }
    public static boolean weatherCheck = false;
    public static boolean hasSilk(ItemStack iS){
        if (iS == null){
            return false;
        }
        return iS.containsEnchantment(Enchantment.SILK_TOUCH);
    }

}
