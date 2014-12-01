package us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.MenuInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;

import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/17/13
 * Time: 9:54 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Coin {
    @SuppressWarnings("deprecation")
	public static void update(Player p){
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        ItemStack iS1 = new ItemStack(MenuInventory.menuItem.getType());
        iS1.setItemMeta(MenuInventory.menuItem.getItemMeta());
        ItemMeta iM1 = iS1.getItemMeta();
        List<String> lores = iM1.getLore();
        Double d = (double)cP.getGoldPoints();
        String s = lores.get(3).replace("@c", d.toString());
        lores.remove(3);
        lores.add(s);
        iM1.setLore(lores);
        iS1.setItemMeta(iM1);
        iS1.setAmount(1);
        for (ItemStack iS : p.getInventory()){
            if (iS != null && !iS.getType().equals(Material.AIR) && iS.hasItemMeta() && iS.getItemMeta().hasDisplayName() && iS.getItemMeta().getDisplayName().equals(ChatUtil.color("&bSkyKipz &6Quick &bMenu"))){
                p.getInventory().remove(iS);
            }
        }
        p.getInventory().setItem(8, iS1);
        p.updateInventory();
    }
}
