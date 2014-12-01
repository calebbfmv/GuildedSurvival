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
 * Time: 4:16 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ArmorCat {
    public static Inventory inv(){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&4Armor Category"));

        ItemStack redstoneblock = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemMeta redstoneblockMeta = redstoneblock.getItemMeta();
        redstoneblockMeta.setDisplayName(ChatUtil.color("&bLeather Helmet"));
        List<String> redstoneblockLore = new ArrayList<>();
        redstoneblockLore.add(ChatUtil.color("&6Click here to personalize"));
        redstoneblockLore.add(ChatUtil.color("&6this item."));
        redstoneblockMeta.setLore(redstoneblockLore);
        redstoneblock.setItemMeta(redstoneblockMeta);
        inv.setItem(0, redstoneblock);

        ItemStack redstone = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemMeta redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName(ChatUtil.color("&bLeather ChestPlate"));
        List<String> redstoneLore = new ArrayList<>();
        redstoneLore.add(ChatUtil.color("&6Click here to personalize"));
        redstoneLore.add(ChatUtil.color("&6this item."));
        redstoneMeta.setLore(redstoneLore);
        redstone.setItemMeta(redstoneMeta);
        inv.setItem(1, redstone);

        ItemStack redstonetorch = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemMeta redstonetorchMeta = redstonetorch.getItemMeta();
        redstonetorchMeta.setDisplayName(ChatUtil.color("&bLeather Leggings"));
        List<String> redstonetorchLore = new ArrayList<>();
        redstonetorchLore.add(ChatUtil.color("&6Click here to personalize"));
        redstonetorchLore.add(ChatUtil.color("&6this item."));
        redstonetorchMeta.setLore(redstonetorchLore);
        redstonetorch.setItemMeta(redstonetorchMeta);
        inv.setItem(2, redstonetorch);

        ItemStack lever = new ItemStack(Material.LEATHER_BOOTS, 1);
        ItemMeta leverMeta = lever.getItemMeta();
        leverMeta.setDisplayName(ChatUtil.color("&bLeather Boots"));
        List<String> leverLore = new ArrayList<>();
        leverLore.add(ChatUtil.color("&6Click here to personalize"));
        leverLore.add(ChatUtil.color("&6this item."));
        leverMeta.setLore(leverLore);
        lever.setItemMeta(leverMeta);
        inv.setItem(3, lever);

        ItemStack woodbutton = new ItemStack(Material.IRON_HELMET, 1);
        ItemMeta woodbuttonMeta = woodbutton.getItemMeta();
        woodbuttonMeta.setDisplayName(ChatUtil.color("&bIron Helmet"));
        List<String> woodbuttonLore = new ArrayList<>();
        woodbuttonLore.add(ChatUtil.color("&6Click here to personalize"));
        woodbuttonLore.add(ChatUtil.color("&6this item."));
        woodbuttonMeta.setLore(woodbuttonLore);
        woodbutton.setItemMeta(woodbuttonMeta);
        inv.setItem(5, woodbutton);

        ItemStack button = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemMeta buttonMeta = button.getItemMeta();
        buttonMeta.setDisplayName(ChatUtil.color("&bIron Chestplate"));
        List<String> buttonLore = new ArrayList<>();
        buttonLore.add(ChatUtil.color("&6Click here to personalize"));
        buttonLore.add(ChatUtil.color("&6this item."));

        buttonMeta.setLore(buttonLore);
        button.setItemMeta(buttonMeta);
        inv.setItem(6, button);

        ItemStack diode = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemMeta diodeMeta = diode.getItemMeta();
        diodeMeta.setDisplayName(ChatUtil.color("&bIron Leggings"));
        List<String> diodeLore = new ArrayList<>();
        diodeLore.add(ChatUtil.color("&6Click here to personalize"));
        diodeLore.add(ChatUtil.color("&6this item."));
        diodeMeta.setLore(diodeLore);
        diode.setItemMeta(diodeMeta);
        inv.setItem(7, diode);

        ItemStack comparater = new ItemStack(Material.IRON_BOOTS, 1);
        ItemMeta comparaterMeta = comparater.getItemMeta();
        comparaterMeta.setDisplayName(ChatUtil.color("&bIron Boots"));
        List<String> comparaterLore = new ArrayList<>();
        comparaterLore.add(ChatUtil.color("&6Click here to personalize"));
        comparaterLore.add(ChatUtil.color("&6this item."));
        comparaterMeta.setLore(comparaterLore);
        comparater.setItemMeta(comparaterMeta);
        inv.setItem(8, comparater);

        ItemStack pressureplate = new ItemStack(Material.GOLD_HELMET, 1);
        ItemMeta pressureplateMeta = pressureplate.getItemMeta();
        pressureplateMeta.setDisplayName(ChatUtil.color("&bGolden Helm"));
        List<String> pressureplateLore = new ArrayList<>();
        pressureplateLore.add(ChatUtil.color("&6Click here to personalize"));
        pressureplateLore.add(ChatUtil.color("&6this item."));
        pressureplateMeta.setLore(pressureplateLore);
        pressureplate.setItemMeta(pressureplateMeta);
        inv.setItem(18, pressureplate);

        ItemStack woodplate = new ItemStack(Material.GOLD_CHESTPLATE, 1);
        ItemMeta woodplateMeta = woodplate.getItemMeta();
        woodplateMeta.setDisplayName(ChatUtil.color("&bGolden Chestplate"));
        List<String> woodplateLore = new ArrayList<>();
        woodplateLore.add(ChatUtil.color("&6Click here to personalize"));
        woodplateLore.add(ChatUtil.color("&6this item."));
        woodplateMeta.setLore(woodplateLore);
        woodplate.setItemMeta(woodplateMeta);
        inv.setItem(19, woodplate);

        ItemStack goldplate = new ItemStack(Material.GOLD_LEGGINGS, 1);
        ItemMeta goldplateMeta = goldplate.getItemMeta();
        goldplateMeta.setDisplayName(ChatUtil.color("&bGolden Leggings"));
        List<String> goldplateLore = new ArrayList<>();
        goldplateLore.add(ChatUtil.color("&6Click here to personalize"));
        goldplateLore.add(ChatUtil.color("&6this item."));
        goldplateMeta.setLore(goldplateLore);
        goldplate.setItemMeta(goldplateMeta);
        inv.setItem(20, goldplate);

        ItemStack ironplate = new ItemStack(Material.GOLD_BOOTS, 1);
        ItemMeta ironplateMeta = ironplate.getItemMeta();
        ironplateMeta.setDisplayName(ChatUtil.color("&bGolden Boots"));
        List<String> ironplateLore = new ArrayList<>();
        ironplateLore.add(ChatUtil.color("&6Click here to personalize"));
        ironplateLore.add(ChatUtil.color("&6this item."));
        ironplateMeta.setLore(ironplateLore);
        ironplate.setItemMeta(ironplateMeta);
        inv.setItem(21, ironplate);

        ItemStack piston = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemMeta pistonMeta = piston.getItemMeta();
        pistonMeta.setDisplayName(ChatUtil.color("&bDiamond Helmet"));
        List<String> pistonLore = new ArrayList<>();
        pistonLore.add(ChatUtil.color("&6Click here to personalize"));
        pistonLore.add(ChatUtil.color("&6this item."));
        pistonMeta.setLore(pistonLore);
        piston.setItemMeta(pistonMeta);
        inv.setItem(23, piston);

        ItemStack stickypiston = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta stickypistonMeta = stickypiston.getItemMeta();
        stickypistonMeta.setDisplayName(ChatUtil.color("&bDiamond Chestplate"));
        List<String> stickypistonLore = new ArrayList<>();
        stickypistonLore.add(ChatUtil.color("&6Click here to personalize"));
        stickypistonLore.add(ChatUtil.color("&6this item."));
        stickypistonMeta.setLore(stickypistonLore);
        stickypiston.setItemMeta(stickypistonMeta);
        inv.setItem(24, stickypiston);

        ItemStack tripwirehook = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        ItemMeta tripwirehookMeta = tripwirehook.getItemMeta();
        tripwirehookMeta.setDisplayName(ChatUtil.color("&bDiamond Leggings"));
        List<String> tripwirehookLore = new ArrayList<>();
        tripwirehookLore.add(ChatUtil.color("&6Click here to personalize"));
        tripwirehookLore.add(ChatUtil.color("&6this item."));
        tripwirehookMeta.setLore(tripwirehookLore);
        tripwirehook.setItemMeta(tripwirehookMeta);
        inv.setItem(25, tripwirehook);

        ItemStack tnt = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta tntMeta = tnt.getItemMeta();
        tntMeta.setDisplayName(ChatUtil.color("&bDiamond Boots"));
        List<String> tntLore = new ArrayList<>();
        tntLore.add(ChatUtil.color("&6Click here to personalize"));
        tntLore.add(ChatUtil.color("&6this item."));
        tntMeta.setLore(tntLore);
        tnt.setItemMeta(tntMeta);
        inv.setItem(26, tnt);

        ItemStack spacer = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        Short durab = 15;
        spacer.setDurability(durab);
        inv.setItem(4, spacer);
        inv.setItem(9, spacer);
        inv.setItem(10, spacer);
        inv.setItem(11, spacer);
        inv.setItem(12, spacer);
        inv.setItem(13, spacer);
        inv.setItem(14, spacer);
        inv.setItem(15, spacer);
        inv.setItem(16, spacer);
        inv.setItem(17, spacer);
        inv.setItem(22, spacer);
        inv.setItem(27, spacer);
        inv.setItem(28, spacer);
        inv.setItem(29, spacer);
        inv.setItem(30, spacer);
        inv.setItem(31, spacer);
        inv.setItem(32, spacer);
        inv.setItem(33, spacer);
        inv.setItem(34, spacer);
        inv.setItem(35, spacer);
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

        return inv;
    }
}
