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
 * Date: 12/2/13
 * Time: 12:36 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class RankEditInventory {
    public static Inventory inv(GuildRanks r){
        Inventory inv = Bukkit.createInventory(null, 45, "Rank Edit:" + r.getName());
        for (RankPerms rank : r.getRankPermissions().keySet()){
            ItemStack iS = new ItemStack(Material.AIR, 1);
            if (!r.getRankPermissions().get(rank)){
                iS.setType(Material.REDSTONE_BLOCK);
                ItemMeta iM = iS.getItemMeta();
                iM.setDisplayName(rank.name().replace("_", " ").toLowerCase());
                List<String> rankLore = new ArrayList<>();
                rankLore.add(ChatUtil.color("&bClick to set"));
                rankLore.add(ChatUtil.color("&bthis permission to true"));
                rankLore.add(ChatUtil.color("&bfor this rank."));
                iM.setLore(rankLore);
                iS.setItemMeta(iM);
            } else {
                iS.setType(Material.EMERALD_BLOCK);
                ItemMeta iM = iS.getItemMeta();
                iM.setDisplayName(rank.name().replace("_", " ").toLowerCase());
                List<String> rankLore = new ArrayList<>();
                rankLore.add(ChatUtil.color("&bClick to set"));
                rankLore.add(ChatUtil.color("&bthis permission to false"));
                rankLore.add(ChatUtil.color("&bfor this rank."));
                iM.setLore(rankLore);
                iS.setItemMeta(iM);
            }
            inv.addItem(iS);
        }
        ItemStack defRankItem = new ItemStack(Material.STONE, 1);
        ItemMeta defRankMeta = defRankItem.getItemMeta();
        defRankMeta.setDisplayName(ChatUtil.color("&6Set Default"));
        List<String> defRankLore = new ArrayList<>();
        defRankLore.add(ChatUtil.color("&bClick here to set this"));
        defRankLore.add(ChatUtil.color("&brank as your default rank."));
        defRankMeta.setLore(defRankLore);
        defRankItem.setItemMeta(defRankMeta);
        inv.setItem(43, defRankItem);
        
        ItemStack returnItem = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta returnMeta = returnItem.getItemMeta();
        returnMeta.setDisplayName(ChatUtil.color("&6Back Button"));
        List<String> returnLore = new ArrayList<>();
        returnLore.add(ChatUtil.color("&bClick here to return to"));
        returnLore.add(ChatUtil.color("&bto the Rank Selection Window."));
        returnMeta.setLore(returnLore);
        returnItem.setItemMeta(returnMeta);
        inv.setItem(44, returnItem);
        return inv;
    }
}
