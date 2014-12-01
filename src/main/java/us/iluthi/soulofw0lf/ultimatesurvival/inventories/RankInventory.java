package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
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
 * Time: 11:20 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class RankInventory {
    public static Inventory inv(Guild g){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("Rank Menu"));
        for (String s : g.getRanks().keySet()){
            GuildRanks gR = g.getRanks().get(s);

            ItemStack iS = new ItemStack(Material.ANVIL, 1);
            ItemMeta iM = iS.getItemMeta();
            iM.setDisplayName(ChatUtil.color(gR.getName()));
            List<String> rankLore = new ArrayList<>();
            rankLore.add(ChatUtil.color("&2Left click to modify"));
            rankLore.add(ChatUtil.color("&2this ranks permissions."));
            rankLore.add(ChatUtil.color("&4Right click to delete"));
            rankLore.add(ChatUtil.color("&4this rank. WARNING: All"));
            rankLore.add(ChatUtil.color("&4members of this rank will"));
            rankLore.add(ChatUtil.color("&4be set to the default rank."));
            rankLore.add(ChatUtil.color("&bShift Left click to set."));
            rankLore.add(ChatUtil.color("&bThis ranks weight higher."));
            rankLore.add(ChatUtil.color("&6Shift Right click to set."));
            rankLore.add(ChatUtil.color("&bThis ranks weight lower."));
            iM.setLore(rankLore);
            iS.setItemMeta(iM);
            if (gR.getWeight() >= 64){
                gR.setWeight(64);
            }
            iS.setAmount(gR.getWeight());
            inv.addItem(iS);
        }
        ItemStack placeItem = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta placeMeta = placeItem.getItemMeta();
        placeMeta.setDisplayName(ChatUtil.color("&6Create New Rank"));
        List<String> placeLore = new ArrayList<>();
        placeLore.add(ChatUtil.color("&bClick here to create a"));
        placeLore.add(ChatUtil.color("&bnew rank for your guild."));
        placeMeta.setLore(placeLore);
        placeItem.setItemMeta(placeMeta);
        inv.setItem(43, placeItem);


        ItemStack returnItem = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta returnMeta = returnItem.getItemMeta();
        returnMeta.setDisplayName(ChatUtil.color("&6Back Button"));
        List<String> returnLore = new ArrayList<>();
        returnLore.add(ChatUtil.color("&bClick here to return to"));
        returnLore.add(ChatUtil.color("&bto the main menu."));
        returnMeta.setLore(returnLore);
        returnItem.setItemMeta(returnMeta);
        inv.setItem(44, returnItem);
        return inv;
    }


}
