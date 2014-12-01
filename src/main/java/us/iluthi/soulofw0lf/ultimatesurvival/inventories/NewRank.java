package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/1/13
 * Time: 11:32 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class NewRank {
    public static Inventory inv(GuildRanks g){
        Inventory inv = Bukkit.createInventory(null, 45, g.getName());
        for (RankPerms rank : g.getRankPermissions().keySet()){
            ItemStack iS = new ItemStack(Material.EMERALD_BLOCK, 1);
            ItemMeta iM = iS.getItemMeta();
            iM.setDisplayName(rank.name().toLowerCase().replaceAll("_", " "));
            List<String> rankLore = new ArrayList<>();
            rankLore.add(ChatUtil.color("&bClick to set"));
            rankLore.add(ChatUtil.color("&bthis permission to false"));
            rankLore.add(ChatUtil.color("&bfor this rank."));
            iM.setLore(rankLore);
            iS.setItemMeta(iM);
            inv.addItem(iS);
        }
        return inv;
    }
}
