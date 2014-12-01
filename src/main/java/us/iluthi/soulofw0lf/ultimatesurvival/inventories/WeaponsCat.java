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
 * Date: 12/9/13
 * Time: 4:13 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class WeaponsCat {
    public static Inventory inv(){
        Inventory inv = Bukkit.createInventory(null, 9, ChatUtil.color("&6Weapons Category"));

        ItemStack seeds = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta seedsMeta = seeds.getItemMeta();
        seedsMeta.setDisplayName(ChatUtil.color("&bWeapons"));
        List<String> seedsLore = new ArrayList<>();
        seedsLore.add(ChatUtil.color("&6Click here to browse"));
        seedsLore.add(ChatUtil.color("&6individual weapons."));
        seedsMeta.setLore(seedsLore);
        seeds.setItemMeta(seedsMeta);
        inv.setItem(3, seeds);

        ItemStack uility = new ItemStack(Material.ARROW, 1);
        ItemMeta uilityMeta = uility.getItemMeta();
        uilityMeta.setDisplayName(ChatUtil.color("&bAmmunition"));
        List<String> uilityLore = new ArrayList<>();
        uilityLore.add(ChatUtil.color("&6Click Here to browse"));
        uilityLore.add(ChatUtil.color("&6for ammunition."));
        uilityMeta.setLore(uilityLore);
        uility.setItemMeta(uilityMeta);
        inv.setItem(5, uility);

        ItemStack returnI = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta returnMeta = returnI.getItemMeta();
        returnMeta.setDisplayName(ChatUtil.color("&4Previous Menu"));
        List<String> returnLore = new ArrayList<>();
        returnLore.add(ChatUtil.color("&6Click here to go back to the"));
        returnLore.add(ChatUtil.color("&6main shopping menu without"));
        returnLore.add(ChatUtil.color("&6making any purchases."));
        returnMeta.setLore(returnLore);
        returnI.setItemMeta(returnMeta);
        inv.setItem(8, returnI);

        return inv;
    }
}
