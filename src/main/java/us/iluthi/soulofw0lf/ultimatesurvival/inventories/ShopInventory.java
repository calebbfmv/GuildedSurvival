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
 * Date: 11/24/13
 * Time: 11:57 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ShopInventory {
    public static Inventory inv(){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&bShopping Mall"));

        ItemStack shopping = new ItemStack(Material.BOOKSHELF, 1);
        ItemMeta shoppingMeta = shopping.getItemMeta();
        shoppingMeta.setDisplayName(ChatUtil.color("&bSkyKipz &6Shopping Mall"));
        List<String> shoppingLore = new ArrayList<>();
        shoppingLore.add(ChatUtil.color("&6Welcome to the &bSkyKipz"));
        shoppingLore.add(ChatUtil.color("&6Shopping Mall! Please browse"));
        shoppingLore.add(ChatUtil.color("&6the following categories to"));
        shoppingLore.add(ChatUtil.color("&6purchase items."));
        shoppingMeta.setLore(shoppingLore);
        shopping.setItemMeta(shoppingMeta);
        inv.setItem(4, shopping);

        ItemStack spacer = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        ItemMeta spacerM = spacer.getItemMeta();
        spacerM.setDisplayName(" ");
        spacer.setItemMeta(spacerM);
        Short dura = 15;
        spacer.setDurability(dura);
        inv.setItem(9, spacer);
        inv.setItem(10, spacer);
        inv.setItem(11, spacer);
        inv.setItem(12, spacer);
        inv.setItem(13, spacer);
        inv.setItem(14, spacer);
        inv.setItem(15, spacer);
        inv.setItem(16, spacer);
        inv.setItem(17, spacer);

        ItemStack blocks = new ItemStack(Material.DIAMOND_BLOCK, 1);
        ItemMeta blocksMeta = blocks.getItemMeta();
        blocksMeta.setDisplayName(ChatUtil.color("&bCategory: &6Blocks"));
        List<String> blocksLore = new ArrayList<>();
        blocksLore.add(ChatUtil.color("&6Click here to browse"));
        blocksLore.add(ChatUtil.color("&6the blocks category."));
        blocksMeta.setLore(blocksLore);
        blocks.setItemMeta(blocksMeta);
        inv.setItem(19, blocks);

        ItemStack food = new ItemStack(Material.GRILLED_PORK, 1);
        ItemMeta foodMeta = food.getItemMeta();
        foodMeta.setDisplayName(ChatUtil.color("&bCategory: &6Food"));
        List<String> foodLore = new ArrayList<>();
        foodLore.add(ChatUtil.color("&6Click here to browse"));
        foodLore.add(ChatUtil.color("&6the food category."));
        foodMeta.setLore(foodLore);
        food.setItemMeta(foodMeta);
        inv.setItem(21, food);

        ItemStack materials = new ItemStack(Material.WORKBENCH, 1);
        ItemMeta materialsMeta = materials.getItemMeta();
        materialsMeta.setDisplayName(ChatUtil.color("&bCategory: &6Materials"));
        List<String> materialsLore = new ArrayList<>();
        materialsLore.add(ChatUtil.color("&6Click here to browse"));
        materialsLore.add(ChatUtil.color("&6the materials category."));
        materialsMeta.setLore(materialsLore);
        materials.setItemMeta(materialsMeta);
        inv.setItem(23, materials);

        ItemStack redstone = new ItemStack(Material.DIODE, 1);
        ItemMeta redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName(ChatUtil.color("&bCategory: &6Redstone"));
        List<String> redstoneLore = new ArrayList<>();
        redstoneLore.add(ChatUtil.color("&6Click here to browse"));
        redstoneLore.add(ChatUtil.color("&6the redstone category."));
        redstoneMeta.setLore(redstoneLore);
        redstone.setItemMeta(redstoneMeta);
        inv.setItem(25, redstone);

        ItemStack tools = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta toolsMeta = tools.getItemMeta();
        toolsMeta.setDisplayName(ChatUtil.color("&bCategory: &6Tools"));
        List<String> toolsLore = new ArrayList<>();
        toolsLore.add(ChatUtil.color("&6Click here to browse"));
        toolsLore.add(ChatUtil.color("&6the tools category"));
        toolsMeta.setLore(toolsLore);
        tools.setItemMeta(toolsMeta);
        inv.setItem(28, tools);

        ItemStack weapons = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta weaponsMeta = weapons.getItemMeta();
        weaponsMeta.setDisplayName(ChatUtil.color("&bCategory: &6Weapons"));
        List<String> weaponsLore = new ArrayList<>();
        weaponsLore.add(ChatUtil.color("&6Click here to browse"));
        weaponsLore.add(ChatUtil.color("&6the weapons category"));
        weaponsMeta.setLore(weaponsLore);
        weapons.setItemMeta(weaponsMeta);
        inv.setItem(30, weapons);

        ItemStack armor = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
        ItemMeta armorMeta = armor.getItemMeta();
        armorMeta.setDisplayName(ChatUtil.color("&bCategory: &6Armor"));
        List<String> armorLore = new ArrayList<>();
        armorLore.add(ChatUtil.color("&6Click here to browse"));
        armorLore.add(ChatUtil.color("&6the armor category."));
        armorMeta.setLore(armorLore);
        armor.setItemMeta(armorMeta);
        inv.setItem(32, armor);

        ItemStack transportation = new ItemStack(Material.MINECART, 1);
        ItemMeta transportationMeta = transportation.getItemMeta();
        transportationMeta.setDisplayName(ChatUtil.color("&bCategory: &6Transportation"));
        List<String> transportationLore = new ArrayList<>();
        transportationLore.add(ChatUtil.color("&6Click here to browse the"));
        transportationLore.add(ChatUtil.color("&6transportation category."));
        transportationMeta.setLore(transportationLore);
        transportation.setItemMeta(transportationMeta);
        inv.setItem(34, transportation);

        ItemStack nether = new ItemStack(Material.GLOWSTONE, 1);
        ItemMeta netherMeta = nether.getItemMeta();
        netherMeta.setDisplayName(ChatUtil.color("&bCategory: &6Nether"));
        List<String> netherLore = new ArrayList<>();
        netherLore.add(ChatUtil.color("&6Click here to browse the"));
        netherLore.add(ChatUtil.color("&6nether category."));
        netherMeta.setLore(netherLore);
        nether.setItemMeta(netherMeta);
        inv.setItem(40, nether);

        ItemStack returnI = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta returnMeta = returnI.getItemMeta();
        returnMeta.setDisplayName(ChatUtil.color("&4Previous Menu"));
        List<String> returnLore = new ArrayList<>();
        returnLore.add(ChatUtil.color("&6Click here to go back to the"));
        returnLore.add(ChatUtil.color("&6main menu."));
        returnMeta.setLore(returnLore);
        returnI.setItemMeta(returnMeta);
        inv.setItem(44, returnI);

        return inv;
    }
}
