package us.iluthi.soulofw0lf.ultimatesurvival.inventories;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.InventoryMaker;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/20/13
 * Time: 5:33 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ExpandInventory {
    public static Inventory inv(Guild g){
        Inventory inv = Bukkit.createInventory(null, 27, ChatUtil.color("&aHQ Expansion"));

        List<String> expandNorthlore = new ArrayList<>();
        expandNorthlore.add(ChatUtil.color("&bClick here to expand"));
        expandNorthlore.add(ChatUtil.color("&byour guild Headquarters"));
        expandNorthlore.add(ChatUtil.color("&b5 blocks North."));
        expandNorthlore.add(ChatUtil.color("&bCost: " + g.getExpansions() * 250000));
        inv.setItem(4, InventoryMaker.itemStackMaker(ChatUtil.color("&6Expand North"), Material.COMPASS, 1, (short)0, expandNorthlore));

        List<String> expandEastlore = new ArrayList<>();
        expandEastlore.add(ChatUtil.color("&bClick here to expand"));
        expandEastlore.add(ChatUtil.color("&byour guild Headquarters"));
        expandEastlore.add(ChatUtil.color("&b5 blocks East."));
        expandEastlore.add(ChatUtil.color("&bCost: " + g.getExpansions() * 250000));
        inv.setItem(14, InventoryMaker.itemStackMaker(ChatUtil.color("&6Expand East"), Material.COMPASS, 1, (short)0, expandEastlore));

        List<String> expandSouthlore = new ArrayList<>();
        expandSouthlore.add(ChatUtil.color("&bClick here to expand"));
        expandSouthlore.add(ChatUtil.color("&byour guild Headquarters"));
        expandSouthlore.add(ChatUtil.color("&b5 blocks South."));
        expandSouthlore.add(ChatUtil.color("&bCost: " + g.getExpansions() * 250000));
        inv.setItem(22, InventoryMaker.itemStackMaker(ChatUtil.color("&6Expand South"), Material.COMPASS, 1, (short)0, expandSouthlore));

        List<String> expandWestlore = new ArrayList<>();
        expandWestlore.add(ChatUtil.color("&bClick here to expand"));
        expandWestlore.add(ChatUtil.color("&byour guild Headquarters"));
        expandWestlore.add(ChatUtil.color("&b5 blocks West."));
        expandWestlore.add(ChatUtil.color("&bCost: " + g.getExpansions() * 250000));
        inv.setItem(12, InventoryMaker.itemStackMaker(ChatUtil.color("&6Expand West"), Material.COMPASS, 1, (short)0, expandWestlore));

        ItemStack returnI = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta returnMeta = returnI.getItemMeta();
        returnMeta.setDisplayName(ChatUtil.color("&4Previous Menu"));
        List<String> returnLore = new ArrayList<>();
        returnLore.add(ChatUtil.color("&6Click here to go back to the"));
        returnLore.add(ChatUtil.color("&6HQ edit menu."));
        returnMeta.setLore(returnLore);
        returnI.setItemMeta(returnMeta);
        inv.setItem(26, returnI);

        return inv;
    }
}
