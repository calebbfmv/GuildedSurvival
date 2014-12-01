package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
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
 * Date: 12/19/13
 * Time: 4:22 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class UtilityInv {
    public static Inventory inv(){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&bUtility Category"));

        ItemStack dirt = new ItemStack(Material.SHEARS, 1);
        ItemMeta dirtMeta = dirt.getItemMeta();
        dirtMeta.setDisplayName(ChatUtil.color("&bShears"));
        List<String> dirtLore = new ArrayList<>();
        dirtLore.add(ChatUtil.color("&6Add this to your"));
        dirtLore.add(ChatUtil.color("&6shopping cart for"));
        dirtLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.SHEARS)+" &6coins."));
        dirtMeta.setLore(dirtLore);
        dirt.setItemMeta(dirtMeta);
        inv.setItem(0, dirt);

        ItemStack grass = new ItemStack(Material.FLINT_AND_STEEL, 1);
        ItemMeta grassMeta = grass.getItemMeta();
        grassMeta.setDisplayName(ChatUtil.color("&bFlint and Steel"));
        List<String> grassLore = new ArrayList<>();
        grassLore.add(ChatUtil.color("&6Add this to your"));
        grassLore.add(ChatUtil.color("&6shopping cart for"));
        grassLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.FLINT_AND_STEEL)+" &6coins."));
        grassMeta.setLore(grassLore);
        grass.setItemMeta(grassMeta);
        inv.setItem(1, grass);

        ItemStack cobble = new ItemStack(Material.COMPASS, 1);
        ItemMeta cobbleMeta = cobble.getItemMeta();
        cobbleMeta.setDisplayName(ChatUtil.color("&bCompass"));
        List<String> cobbleLore = new ArrayList<>();
        cobbleLore.add(ChatUtil.color("&6Add this to your"));
        cobbleLore.add(ChatUtil.color("&6shopping cart for"));
        cobbleLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.COMPASS)+" &6coins."));
        cobbleMeta.setLore(cobbleLore);
        cobble.setItemMeta(cobbleMeta);
        inv.setItem(2, cobble);

        ItemStack stone = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta stoneMeta = stone.getItemMeta();
        stoneMeta.setDisplayName(ChatUtil.color("&bFishing Rod"));
        List<String> stoneLore = new ArrayList<>();
        stoneLore.add(ChatUtil.color("&6Add this to your"));
        stoneLore.add(ChatUtil.color("&6shopping cart for"));
        stoneLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.FISHING_ROD)+" &6coins."));
        stoneMeta.setLore(stoneLore);
        stone.setItemMeta(stoneMeta);
        inv.setItem(3, stone);

        ItemStack spacer = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        ItemMeta spacerM = spacer.getItemMeta();
        spacerM.setDisplayName(" ");
        spacer.setItemMeta(spacerM);
        Short durab = 15;
        spacer.setDurability(durab);
        inv.setItem(4, spacer);
        inv.setItem(9, spacer);
        inv.setItem(10, spacer);
        inv.setItem(11, spacer);
        inv.setItem(12, spacer);
        inv.setItem(13, spacer);
        inv.setItem(18, spacer);
        inv.setItem(19, spacer);
        inv.setItem(20, spacer);
        inv.setItem(21, spacer);
        inv.setItem(22, spacer);
        inv.setItem(27, spacer);
        inv.setItem(28, spacer);
        inv.setItem(29, spacer);
        inv.setItem(30, spacer);
        inv.setItem(31, spacer);
        inv.setItem(36, spacer);
        inv.setItem(37, spacer);
        inv.setItem(38, spacer);
        inv.setItem(39, spacer);
        inv.setItem(40, spacer);

        ItemStack returnI = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta returnMeta = returnI.getItemMeta();
        returnMeta.setDisplayName(ChatUtil.color("&4Previous Menu"));
        List<String> returnLore = new ArrayList<>();
        returnLore.add(ChatUtil.color("&6Click here to go back to the"));
        returnLore.add(ChatUtil.color("&6main shopping menu without"));
        returnLore.add(ChatUtil.color("&6making any purchases."));
        returnMeta.setLore(returnLore);
        returnI.setItemMeta(returnMeta);
        inv.setItem(44, returnI);

        ItemStack confirmI = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta confirmMeta = confirmI.getItemMeta();
        confirmMeta.setDisplayName(ChatUtil.color("&6Add these items to cart."));
        List<String> confirmLore = new ArrayList<>();
        confirmLore.add(ChatUtil.color("&6Click here to add these items"));
        confirmLore.add(ChatUtil.color("&6to your shopping cart."));
        confirmLore.add(ChatUtil.color("&6Current cost: &b@c."));
        confirmMeta.setLore(confirmLore);
        confirmI.setItemMeta(confirmMeta);
        inv.setItem(35, confirmI);

        return inv;
    }
}
