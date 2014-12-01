package us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/9/13
 * Time: 5:25 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ShopProcess {
    public static Integer onClick(Inventory inv){
        Integer i = 0;

        if (inv.getItem(5) != null && !inv.getItem(5).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(5).getType()) * inv.getItem(5).getAmount());
        }
        if (inv.getItem(6) != null && !inv.getItem(6).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(6).getType()) * inv.getItem(6).getAmount());
        }
        if (inv.getItem(7) != null && !inv.getItem(7).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(7).getType()) * inv.getItem(7).getAmount());
        }
        if (inv.getItem(8) != null && !inv.getItem(8).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(8).getType()) * inv.getItem(8).getAmount());
        }
        if (inv.getItem(14) != null && !inv.getItem(14).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(14).getType()) * inv.getItem(14).getAmount());
        }
        if (inv.getItem(15) != null && !inv.getItem(15).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(15).getType()) * inv.getItem(15).getAmount());
        }
        if (inv.getItem(16) != null && !inv.getItem(16).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(16).getType()) * inv.getItem(16).getAmount());
        }
        if (inv.getItem(17) != null && !inv.getItem(17).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(17).getType()) * inv.getItem(17).getAmount());
        }
        if (inv.getItem(23) != null && !inv.getItem(23).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(23).getType()) * inv.getItem(23).getAmount());
        }
        if (inv.getItem(24) != null && !inv.getItem(24).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(24).getType()) * inv.getItem(24).getAmount());
        }
        if (inv.getItem(25) != null && !inv.getItem(25).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(25).getType()) * inv.getItem(25).getAmount());
        }
        if (inv.getItem(26) != null && !inv.getItem(26).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(26).getType()) * inv.getItem(26).getAmount());
        }
        if (inv.getItem(32) != null && !inv.getItem(32).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(32).getType()) * inv.getItem(32).getAmount());
        }
        if (inv.getItem(33) != null && !inv.getItem(33).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(33).getType())  * inv.getItem(33).getAmount());
        }
        if (inv.getItem(34) != null && !inv.getItem(34).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(34).getType()) * inv.getItem(34).getAmount());
        }
        if (inv.getItem(41) != null && !inv.getItem(41).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(41).getType()) * inv.getItem(41).getAmount());
        }
        if (inv.getItem(42) != null && !inv.getItem(42).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(42).getType()) * inv.getItem(42).getAmount());
        }
        if (inv.getItem(43) != null && !inv.getItem(43).getType().equals(Material.AIR)){
            i += (Maps.blockCosts.get(inv.getItem(43).getType()) * inv.getItem(43).getAmount());
        }

        ItemStack confirmI = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta confirmMeta = confirmI.getItemMeta();
        confirmMeta.setDisplayName(ChatUtil.color("&6Add these items to cart."));
        List<String> confirmLore = new ArrayList<>();
        confirmLore.add(ChatUtil.color("&6Click here to add these items"));
        confirmLore.add(ChatUtil.color("&6to your shopping cart."));
        confirmLore.add(ChatUtil.color("&6Current cost: &b" + i + "."));
        confirmMeta.setLore(confirmLore);
        confirmI.setItemMeta(confirmMeta);
        inv.setItem(35, confirmI);
        return i;
    }
}
