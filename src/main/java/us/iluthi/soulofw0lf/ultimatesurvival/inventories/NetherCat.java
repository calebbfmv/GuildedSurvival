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
 * Date: 12/9/13
 * Time: 5:13 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class NetherCat {
    public static Inventory inv(){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&4Nether Category"));

        ItemStack redstoneblock = new ItemStack(Material.NETHERRACK, 1);
        ItemMeta redstoneblockMeta = redstoneblock.getItemMeta();
        redstoneblockMeta.setDisplayName(ChatUtil.color("&bNetherrack block"));
        List<String> redstoneblockLore = new ArrayList<>();
        redstoneblockLore.add(ChatUtil.color("&6Add this to your"));
        redstoneblockLore.add(ChatUtil.color("&6shopping cart for"));
        redstoneblockLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.NETHERRACK)+" &6coins."));
        redstoneblockMeta.setLore(redstoneblockLore);
        redstoneblock.setItemMeta(redstoneblockMeta);
        inv.setItem(0, redstoneblock);

        ItemStack redstone = new ItemStack(Material.SOUL_SAND, 1);
        ItemMeta redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName(ChatUtil.color("&bSoul sand"));
        List<String> redstoneLore = new ArrayList<>();
        redstoneLore.add(ChatUtil.color("&6Add this to your"));
        redstoneLore.add(ChatUtil.color("&6shopping cart for"));
        redstoneLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.SOUL_SAND)+" &6coins."));
        redstoneMeta.setLore(redstoneLore);
        redstone.setItemMeta(redstoneMeta);
        inv.setItem(1, redstone);

        ItemStack redstonetorch = new ItemStack(Material.GLOWSTONE, 1);
        ItemMeta redstonetorchMeta = redstonetorch.getItemMeta();
        redstonetorchMeta.setDisplayName(ChatUtil.color("&bGlowstone"));
        List<String> redstonetorchLore = new ArrayList<>();
        redstonetorchLore.add(ChatUtil.color("&6Add this to your"));
        redstonetorchLore.add(ChatUtil.color("&6shopping cart for"));
        redstonetorchLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.GLOWSTONE)+" &6coins."));
        redstonetorchMeta.setLore(redstonetorchLore);
        redstonetorch.setItemMeta(redstonetorchMeta);
        inv.setItem(2, redstonetorch);

        ItemStack lever = new ItemStack(Material.NETHER_BRICK, 1);
        ItemMeta leverMeta = lever.getItemMeta();
        leverMeta.setDisplayName(ChatUtil.color("&bNether Brick"));
        List<String> leverLore = new ArrayList<>();
        leverLore.add(ChatUtil.color("&6Add this to your"));
        leverLore.add(ChatUtil.color("&6shopping cart for"));
        leverLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.NETHER_BRICK)+" &6coins."));
        leverMeta.setLore(leverLore);
        lever.setItemMeta(leverMeta);
        inv.setItem(3, lever);

        ItemStack woodbutton = new ItemStack(Material.QUARTZ, 1);
        ItemMeta woodbuttonMeta = woodbutton.getItemMeta();
        woodbuttonMeta.setDisplayName(ChatUtil.color("&bQuartz"));
        List<String> woodbuttonLore = new ArrayList<>();
        woodbuttonLore.add(ChatUtil.color("&6Add this to your"));
        woodbuttonLore.add(ChatUtil.color("&6shopping cart for"));
        woodbuttonLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.QUARTZ)+" &6coins."));
        woodbuttonMeta.setLore(woodbuttonLore);
        woodbutton.setItemMeta(woodbuttonMeta);
        inv.setItem(9, woodbutton);

        ItemStack button = new ItemStack(Material.NETHER_STALK, 1);
        ItemMeta buttonMeta = button.getItemMeta();
        buttonMeta.setDisplayName(ChatUtil.color("&bNether Wart"));
        List<String> buttonLore = new ArrayList<>();
        buttonLore.add(ChatUtil.color("&6Add this to your"));
        buttonLore.add(ChatUtil.color("&6shopping cart for"));
        buttonLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.NETHER_STALK)+" &6coins."));
        buttonMeta.setLore(buttonLore);
        button.setItemMeta(buttonMeta);
        inv.setItem(10, button);

        ItemStack diode = new ItemStack(Material.GHAST_TEAR, 1);
        ItemMeta diodeMeta = diode.getItemMeta();
        diodeMeta.setDisplayName(ChatUtil.color("&bGhast Tears"));
        List<String> diodeLore = new ArrayList<>();
        diodeLore.add(ChatUtil.color("&6Add this to your"));
        diodeLore.add(ChatUtil.color("&6shopping cart for"));
        diodeLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.GHAST_TEAR)+" &6coins."));
        diodeMeta.setLore(diodeLore);
        diode.setItemMeta(diodeMeta);
        inv.setItem(11, diode);

        ItemStack comparater = new ItemStack(Material.BLAZE_ROD, 1);
        ItemMeta comparaterMeta = comparater.getItemMeta();
        comparaterMeta.setDisplayName(ChatUtil.color("&bBlaze Rod"));
        List<String> comparaterLore = new ArrayList<>();
        comparaterLore.add(ChatUtil.color("&6Add this to your"));
        comparaterLore.add(ChatUtil.color("&6shopping cart for"));
        comparaterLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.BLAZE_ROD)+" &6coins."));
        comparaterMeta.setLore(comparaterLore);
        comparater.setItemMeta(comparaterMeta);
        inv.setItem(12, comparater);



        ItemStack spacer = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        Short durab = 15;
        spacer.setDurability(durab);
        inv.setItem(4, spacer);
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
