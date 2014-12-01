package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.InventoryMaker;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/18/13
 * Time: 2:12 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class WeaponSubCat {
    public static Inventory inv (){
        Inventory inv = Bukkit.createInventory(null, 9, ChatUtil.color("&bWeapons"));

        List<String> woodLores = new ArrayList<>();
        woodLores.add(ChatUtil.color("&2Click here to customize and"));
        woodLores.add(ChatUtil.color("&2purchase your wood sword."));
        woodLores.add(ChatUtil.color("&2Base Cost: " + Maps.values.get(Material.WOOD_SWORD) * 3));
        ItemStack wood = InventoryMaker.itemStackMaker((ChatUtil.color("&bWooden Sword")), Material.WOOD_SWORD, 1, (short)0, woodLores);
        inv.setItem(2, wood);

        List<String> stoneLores = new ArrayList<>();
        stoneLores.add(ChatUtil.color("&2Click here to customize and"));
        stoneLores.add(ChatUtil.color("&2purchase your stone sword."));
        stoneLores.add(ChatUtil.color("&2Base Cost: " + Maps.values.get(Material.STONE_SWORD) * 3));
        ItemStack stone = InventoryMaker.itemStackMaker((ChatUtil.color("&bstone Sword")), Material.STONE_SWORD, 1, (short)0, stoneLores);
        inv.setItem(3, stone);

        List<String> ironLores = new ArrayList<>();
        ironLores.add(ChatUtil.color("&2Click here to customize and"));
        ironLores.add(ChatUtil.color("&2purchase your iron sword."));
        ironLores.add(ChatUtil.color("&2Base Cost: " + Maps.values.get(Material.IRON_SWORD) * 3));
        ItemStack iron = InventoryMaker.itemStackMaker((ChatUtil.color("&biron Sword")), Material.IRON_SWORD, 1, (short)0, ironLores);
        inv.setItem(4, iron);

        List<String> goldLores = new ArrayList<>();
        goldLores.add(ChatUtil.color("&2Click here to customize and"));
        goldLores.add(ChatUtil.color("&2purchase your gold sword."));
        goldLores.add(ChatUtil.color("&2Base Cost: " + Maps.values.get(Material.GOLD_SWORD) * 3));
        ItemStack gold = InventoryMaker.itemStackMaker((ChatUtil.color("&bgolden Sword")), Material.GOLD_SWORD, 1, (short)0, goldLores);
        inv.setItem(5, gold);

        List<String> diamondLores = new ArrayList<>();
        diamondLores.add(ChatUtil.color("&2Click here to customize and"));
        diamondLores.add(ChatUtil.color("&2purchase your diamond sword."));
        diamondLores.add(ChatUtil.color("&2Base Cost: " + Maps.values.get(Material.DIAMOND_SWORD) * 3));
        ItemStack diamond = InventoryMaker.itemStackMaker((ChatUtil.color("&bdiamond Sword")), Material.DIAMOND_SWORD, 1, (short)0, diamondLores);
        inv.setItem(6, diamond);

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
