package us.iluthi.soulofw0lf.ultimatesurvival.variables;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 5:09 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Integers {
    public static int maxPlayers = 6;
    public static int rollTimer = 60;
    public static int localDistance = 50;
    public static int strongestGuildPoints = 0;
    public static int richestPlayerPoints = 0;
    public static int hasFortune(ItemStack iS){
        if (iS == null || !iS.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)){
            return 0;
        }
        return iS.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
    }
}
