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
 * Date: 12/8/13
 * Time: 11:17 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class BlockCat {
    
    public static Inventory inv(){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&bBlock Category"));

        ItemStack dirt = new ItemStack(Material.DIRT, 1);
        ItemMeta dirtMeta = dirt.getItemMeta();
        dirtMeta.setDisplayName(ChatUtil.color("&bBlock of dirt"));
        List<String> dirtLore = new ArrayList<>();
        dirtLore.add(ChatUtil.color("&6Add this to your"));
        dirtLore.add(ChatUtil.color("&6shopping cart for"));
        dirtLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.DIRT)+" &6coins."));
        dirtMeta.setLore(dirtLore);
        dirt.setItemMeta(dirtMeta);
        inv.setItem(0, dirt);

        ItemStack grass = new ItemStack(Material.GRASS, 1);
        ItemMeta grassMeta = grass.getItemMeta();
        grassMeta.setDisplayName(ChatUtil.color("&bBlock of grass"));
        List<String> grassLore = new ArrayList<>();
        grassLore.add(ChatUtil.color("&6Add this to your"));
        grassLore.add(ChatUtil.color("&6shopping cart for"));
        grassLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.GRASS)+" &6coins."));
        grassMeta.setLore(grassLore);
        grass.setItemMeta(grassMeta);
        inv.setItem(1, grass);

        ItemStack cobble = new ItemStack(Material.COBBLESTONE, 1);
        ItemMeta cobbleMeta = cobble.getItemMeta();
        cobbleMeta.setDisplayName(ChatUtil.color("&bBlock of cobble"));
        List<String> cobbleLore = new ArrayList<>();
        cobbleLore.add(ChatUtil.color("&6Add this to your"));
        cobbleLore.add(ChatUtil.color("&6shopping cart for"));
        cobbleLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.COBBLESTONE)+" &6coins."));
        cobbleMeta.setLore(cobbleLore);
        cobble.setItemMeta(cobbleMeta);
        inv.setItem(2, cobble);

        ItemStack stone = new ItemStack(Material.STONE, 1);
        ItemMeta stoneMeta = stone.getItemMeta();
        stoneMeta.setDisplayName(ChatUtil.color("&bBlock of stone"));
        List<String> stoneLore = new ArrayList<>();
        stoneLore.add(ChatUtil.color("&6Add this to your"));
        stoneLore.add(ChatUtil.color("&6shopping cart for"));
        stoneLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.STONE)+" &6coins."));
        stoneMeta.setLore(stoneLore);
        stone.setItemMeta(stoneMeta);
        inv.setItem(3, stone);

        ItemStack gravel = new ItemStack(Material.GRAVEL, 1);
        ItemMeta gravelMeta = gravel.getItemMeta();
        gravelMeta.setDisplayName(ChatUtil.color("&bBlock of gravel"));
        List<String> gravelLore = new ArrayList<>();
        gravelLore.add(ChatUtil.color("&6Add this to your"));
        gravelLore.add(ChatUtil.color("&6shopping cart for"));
        gravelLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.GRAVEL)+" &6coins."));
        gravelMeta.setLore(gravelLore);
        gravel.setItemMeta(gravelMeta);
        inv.setItem(9, gravel);

        ItemStack sand = new ItemStack(Material.SAND, 1);
        ItemMeta sandMeta = sand.getItemMeta();
        sandMeta.setDisplayName(ChatUtil.color("&bBlock of sand"));
        List<String> sandLore = new ArrayList<>();
        sandLore.add(ChatUtil.color("&6Add this to your"));
        sandLore.add(ChatUtil.color("&6shopping cart for"));
        sandLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.SAND)+" &6coins."));
        sandMeta.setLore(sandLore);
        sand.setItemMeta(sandMeta);
        inv.setItem(10, sand);

        ItemStack sandStone = new ItemStack(Material.SANDSTONE, 1);
        ItemMeta sandStoneMeta = sandStone.getItemMeta();
        sandStoneMeta.setDisplayName(ChatUtil.color("&bBlock of sandstone"));
        List<String> sandStoneLore = new ArrayList<>();
        sandStoneLore.add(ChatUtil.color("&6Add this to your"));
        sandStoneLore.add(ChatUtil.color("&6shopping cart for"));
        sandStoneLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.SANDSTONE)+" &6coins."));
        sandStoneMeta.setLore(sandStoneLore);
        sandStone.setItemMeta(sandStoneMeta);
        inv.setItem(11, sandStone);

        ItemStack podzol = new ItemStack(Material.GRASS, 1);
        ItemMeta podzolMeta = podzol.getItemMeta();
        podzolMeta.setDisplayName(ChatUtil.color("&bBlock of podzol"));
        List<String> podzolLore = new ArrayList<>();
        podzolLore.add(ChatUtil.color("&6Add this to your"));
        podzolLore.add(ChatUtil.color("&6shopping cart for"));
        podzolLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.GRASS)+" &6coins."));
        podzolMeta.setLore(podzolLore);
        podzol.setItemMeta(podzolMeta);
        short dura = 2;
        podzol.setDurability(dura);
        inv.setItem(12, podzol);

        ItemStack clay = new ItemStack(Material.CLAY, 1);
        ItemMeta clayMeta = clay.getItemMeta();
        clayMeta.setDisplayName(ChatUtil.color("&bBlock of clay"));
        List<String> clayLore = new ArrayList<>();
        clayLore.add(ChatUtil.color("&6Add this to your"));
        clayLore.add(ChatUtil.color("&6shopping cart for"));
        clayLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.CLAY)+" &6coins."));
        clayMeta.setLore(clayLore);
        clay.setItemMeta(clayMeta);
        inv.setItem(18, clay);

        ItemStack ice = new ItemStack(Material.ICE, 1);
        ItemMeta iceMeta = ice.getItemMeta();
        iceMeta.setDisplayName(ChatUtil.color("&bBlock of ice"));
        List<String> iceLore = new ArrayList<>();
        iceLore.add(ChatUtil.color("&6Add this to your"));
        iceLore.add(ChatUtil.color("&6shopping cart for"));
        iceLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.ICE)+" &6coins."));
        iceMeta.setLore(iceLore);
        ice.setItemMeta(iceMeta);
        inv.setItem(19, ice);

        ItemStack snow = new ItemStack(Material.SNOW_BLOCK, 1);
        ItemMeta snowMeta = snow.getItemMeta();
        snowMeta.setDisplayName(ChatUtil.color("&bBlock of snow"));
        List<String> snowLore = new ArrayList<>();
        snowLore.add(ChatUtil.color("&6Add this to your"));
        snowLore.add(ChatUtil.color("&6shopping cart for"));
        snowLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.SNOW_BLOCK)+" &6coins."));
        snowMeta.setLore(snowLore);
        snow.setItemMeta(snowMeta);
        inv.setItem(20, snow);

        ItemStack end = new ItemStack(Material.ENDER_STONE, 1);
        ItemMeta endMeta = end.getItemMeta();
        endMeta.setDisplayName(ChatUtil.color("&bBlock of end"));
        List<String> endLore = new ArrayList<>();
        endLore.add(ChatUtil.color("&6Add this to your"));
        endLore.add(ChatUtil.color("&6shopping cart for"));
        endLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.ENDER_STONE)+" &6coins."));
        endMeta.setLore(endLore);
        end.setItemMeta(endMeta);
        inv.setItem(21, end);

        ItemStack brick = new ItemStack(Material.BRICK, 1);
        ItemMeta brickMeta = brick.getItemMeta();
        brickMeta.setDisplayName(ChatUtil.color("&bBlock of brick"));
        List<String> brickLore = new ArrayList<>();
        brickLore.add(ChatUtil.color("&6Add this to your"));
        brickLore.add(ChatUtil.color("&6shopping cart for"));
        brickLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.BRICK)+" &6coins."));
        brickMeta.setLore(brickLore);
        brick.setItemMeta(brickMeta);
        inv.setItem(27, brick);

        ItemStack stoneBrick = new ItemStack(Material.SMOOTH_BRICK, 1);
        ItemMeta stoneBrickMeta = stoneBrick.getItemMeta();
        stoneBrickMeta.setDisplayName(ChatUtil.color("&bBlock of stone brick"));
        List<String> stoneBrickLore = new ArrayList<>();
        stoneBrickLore.add(ChatUtil.color("&6Add this to your"));
        stoneBrickLore.add(ChatUtil.color("&6shopping cart for"));
        stoneBrickLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.SMOOTH_BRICK)+" &6coins."));
        stoneBrickMeta.setLore(stoneBrickLore);
        stoneBrick.setItemMeta(stoneBrickMeta);
        inv.setItem(28, stoneBrick);

        ItemStack lapiz = new ItemStack(Material.LAPIS_BLOCK, 1);
        ItemMeta lapizMeta = lapiz.getItemMeta();
        lapizMeta.setDisplayName(ChatUtil.color("&bBlock of lapis"));
        List<String> lapizLore = new ArrayList<>();
        lapizLore.add(ChatUtil.color("&6Add this to your"));
        lapizLore.add(ChatUtil.color("&6shopping cart for"));
        lapizLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.LAPIS_BLOCK)+" &6coins."));
        lapizMeta.setLore(lapizLore);
        lapiz.setItemMeta(lapizMeta);
        inv.setItem(29, lapiz);

        ItemStack obsidian = new ItemStack(Material.WOOD, 1);
        ItemMeta obsidianMeta = obsidian.getItemMeta();
        obsidianMeta.setDisplayName(ChatUtil.color("&bBlock of Wood"));
        List<String> obsidianLore = new ArrayList<>();
        obsidianLore.add(ChatUtil.color("&6Add this to your"));
        obsidianLore.add(ChatUtil.color("&6shopping cart for"));
        obsidianLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.WOOD)+" &6coins."));
        obsidianMeta.setLore(obsidianLore);
        obsidian.setItemMeta(obsidianMeta);
        inv.setItem(30, obsidian);

        ItemStack iron = new ItemStack(Material.IRON_BLOCK, 1);
        ItemMeta ironMeta = iron.getItemMeta();
        ironMeta.setDisplayName(ChatUtil.color("&bBlock of iron"));
        List<String> ironLore = new ArrayList<>();
        ironLore.add(ChatUtil.color("&6Add this to your"));
        ironLore.add(ChatUtil.color("&6shopping cart for"));
        ironLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.IRON_BLOCK)+" &6coins."));
        ironMeta.setLore(ironLore);
        iron.setItemMeta(ironMeta);
        inv.setItem(36, iron);

        ItemStack gold = new ItemStack(Material.GOLD_BLOCK, 1);
        ItemMeta goldMeta = gold.getItemMeta();
        goldMeta.setDisplayName(ChatUtil.color("&bBlock of gold"));
        List<String> goldLore = new ArrayList<>();
        goldLore.add(ChatUtil.color("&6Add this to your"));
        goldLore.add(ChatUtil.color("&6shopping cart for"));
        goldLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.GOLD_BLOCK)+" &6coins."));
        goldMeta.setLore(goldLore);
        gold.setItemMeta(goldMeta);
        inv.setItem(37, gold);

        ItemStack diamond = new ItemStack(Material.DIAMOND_BLOCK, 1);
        ItemMeta diamondMeta = diamond.getItemMeta();
        diamondMeta.setDisplayName(ChatUtil.color("&bBlock of diamond"));
        List<String> diamondLore = new ArrayList<>();
        diamondLore.add(ChatUtil.color("&6Add this to your"));
        diamondLore.add(ChatUtil.color("&6shopping cart for"));
        diamondLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.DIAMOND_BLOCK)+" &6coins."));
        diamondMeta.setLore(diamondLore);
        diamond.setItemMeta(diamondMeta);
        inv.setItem(38, diamond);

        ItemStack spacer = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        Short durab = 15;
        spacer.setDurability(durab);
        inv.setItem(4, spacer);
        inv.setItem(13, spacer);
        inv.setItem(22, spacer);
        inv.setItem(31, spacer);
        inv.setItem(40, spacer);
        inv.setItem(39, spacer);


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
