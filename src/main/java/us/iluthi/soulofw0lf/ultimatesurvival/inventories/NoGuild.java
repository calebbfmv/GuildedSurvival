package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
 * Time: 11:35 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class NoGuild {
    public static Inventory inv(){
        
        
        
        Inventory inv = Bukkit.createInventory(null, 9, ChatUtil.color("Create Guild"));

        ItemStack listItem = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta listMeta = listItem.getItemMeta();
        listMeta.setDisplayName(ChatUtil.color("&6View Recruiting Guilds"));
        List<String> listLore = new ArrayList<>();
        listLore.add(ChatUtil.color("&bClick here to view all"));
        listLore.add(ChatUtil.color("&bguilds that are openly."));
        listLore.add(ChatUtil.color("&brecruiting."));
        listMeta.setLore(listLore);
        listItem.setItemMeta(listMeta);
        inv.setItem(3, listItem);
        
        ItemStack placeItem = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta placeMeta = placeItem.getItemMeta();
        placeMeta.setDisplayName(ChatUtil.color("&6Create New Guild"));
        List<String> placeLore = new ArrayList<>();
        placeLore.add(ChatUtil.color("&bClick here to create a"));
        placeLore.add(ChatUtil.color("&bnew guild."));
        placeLore.add(ChatUtil.color("&bCost: &42,000."));
        placeMeta.setLore(placeLore);
        placeItem.setItemMeta(placeMeta);
        inv.setItem(5, placeItem);


        ItemStack returnItem = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta returnMeta = returnItem.getItemMeta();
        returnMeta.setDisplayName(ChatUtil.color("&6Back Button"));
        List<String> returnLore = new ArrayList<>();
        returnLore.add(ChatUtil.color("&bClick here to return to"));
        returnLore.add(ChatUtil.color("&bto the main menu."));
        returnMeta.setLore(returnLore);
        returnItem.setItemMeta(returnMeta);
        inv.setItem(8, returnItem);
        return inv;
    }
}
