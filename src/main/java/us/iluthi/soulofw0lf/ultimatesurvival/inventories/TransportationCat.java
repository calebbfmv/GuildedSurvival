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
 * Time: 4:39 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class TransportationCat {
    public static Inventory inv(){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&4Transportation"));

        ItemStack redstoneblock = new ItemStack(Material.RAILS, 1);
        ItemMeta redstoneblockMeta = redstoneblock.getItemMeta();
        redstoneblockMeta.setDisplayName(ChatUtil.color("&bMinecart Rails"));
        List<String> redstoneblockLore = new ArrayList<>();
        redstoneblockLore.add(ChatUtil.color("&6Add this to your"));
        redstoneblockLore.add(ChatUtil.color("&6shopping cart for"));
        redstoneblockLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.RAILS)+" &6coins."));
        redstoneblockMeta.setLore(redstoneblockLore);
        redstoneblock.setItemMeta(redstoneblockMeta);
        inv.setItem(0, redstoneblock);

        ItemStack redstone = new ItemStack(Material.POWERED_RAIL, 1);
        ItemMeta redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName(ChatUtil.color("&bPowered Rails"));
        List<String> redstoneLore = new ArrayList<>();
        redstoneLore.add(ChatUtil.color("&6Add this to your"));
        redstoneLore.add(ChatUtil.color("&6shopping cart for"));
        redstoneLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.POWERED_RAIL)+" &6coins."));
        redstoneMeta.setLore(redstoneLore);
        redstone.setItemMeta(redstoneMeta);
        inv.setItem(1, redstone);

        ItemStack redstonetorch = new ItemStack(Material.DETECTOR_RAIL, 1);
        ItemMeta redstonetorchMeta = redstonetorch.getItemMeta();
        redstonetorchMeta.setDisplayName(ChatUtil.color("&bDetector Rails"));
        List<String> redstonetorchLore = new ArrayList<>();
        redstonetorchLore.add(ChatUtil.color("&6Add this to your"));
        redstonetorchLore.add(ChatUtil.color("&6shopping cart for"));
        redstonetorchLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.DETECTOR_RAIL)+" &6coins."));
        redstonetorchMeta.setLore(redstonetorchLore);
        redstonetorch.setItemMeta(redstonetorchMeta);
        inv.setItem(2, redstonetorch);

        ItemStack lever = new ItemStack(Material.ACTIVATOR_RAIL, 1);
        ItemMeta leverMeta = lever.getItemMeta();
        leverMeta.setDisplayName(ChatUtil.color("&bActivator Rails"));
        List<String> leverLore = new ArrayList<>();
        leverLore.add(ChatUtil.color("&6Add this to your"));
        leverLore.add(ChatUtil.color("&6shopping cart for"));
        leverLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.ACTIVATOR_RAIL)+" &6coins."));
        leverMeta.setLore(leverLore);
        lever.setItemMeta(leverMeta);
        inv.setItem(3, lever);

        ItemStack woodbutton = new ItemStack(Material.MINECART, 1);
        ItemMeta woodbuttonMeta = woodbutton.getItemMeta();
        woodbuttonMeta.setDisplayName(ChatUtil.color("&bMinecart"));
        List<String> woodbuttonLore = new ArrayList<>();
        woodbuttonLore.add(ChatUtil.color("&6Add this to your"));
        woodbuttonLore.add(ChatUtil.color("&6shopping cart for"));
        woodbuttonLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.MINECART)+" &6coins."));
        woodbuttonMeta.setLore(woodbuttonLore);
        woodbutton.setItemMeta(woodbuttonMeta);
        inv.setItem(9, woodbutton);

        ItemStack button = new ItemStack(Material.STORAGE_MINECART, 1);
        ItemMeta buttonMeta = button.getItemMeta();
        buttonMeta.setDisplayName(ChatUtil.color("&bStorage Cart"));
        List<String> buttonLore = new ArrayList<>();
        buttonLore.add(ChatUtil.color("&6Add this to your"));
        buttonLore.add(ChatUtil.color("&6shopping cart for"));
        buttonLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.STORAGE_MINECART)+" &6coins."));
        buttonMeta.setLore(buttonLore);
        button.setItemMeta(buttonMeta);
        inv.setItem(10, button);

        ItemStack diode = new ItemStack(Material.POWERED_MINECART, 1);
        ItemMeta diodeMeta = diode.getItemMeta();
        diodeMeta.setDisplayName(ChatUtil.color("&bPowered Cart"));
        List<String> diodeLore = new ArrayList<>();
        diodeLore.add(ChatUtil.color("&6Add this to your"));
        diodeLore.add(ChatUtil.color("&6shopping cart for"));
        diodeLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.POWERED_MINECART)+" &6coins."));
        diodeMeta.setLore(diodeLore);
        diode.setItemMeta(diodeMeta);
        inv.setItem(11, diode);

        ItemStack comparater = new ItemStack(Material.HOPPER_MINECART, 1);
        ItemMeta comparaterMeta = comparater.getItemMeta();
        comparaterMeta.setDisplayName(ChatUtil.color("&bHopper Cart"));
        List<String> comparaterLore = new ArrayList<>();
        comparaterLore.add(ChatUtil.color("&6Add this to your"));
        comparaterLore.add(ChatUtil.color("&6shopping cart for"));
        comparaterLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.HOPPER_MINECART)+" &6coins."));
        comparaterMeta.setLore(comparaterLore);
        comparater.setItemMeta(comparaterMeta);
        inv.setItem(12, comparater);

        ItemStack pressureplate = new ItemStack(Material.EXPLOSIVE_MINECART, 1);
        ItemMeta pressureplateMeta = pressureplate.getItemMeta();
        pressureplateMeta.setDisplayName(ChatUtil.color("&bTnT Cart"));
        List<String> pressureplateLore = new ArrayList<>();
        pressureplateLore.add(ChatUtil.color("&6Add this to your"));
        pressureplateLore.add(ChatUtil.color("&6shopping cart for"));
        pressureplateLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.EXPLOSIVE_MINECART)+" &6coins."));
        pressureplateMeta.setLore(pressureplateLore);
        pressureplate.setItemMeta(pressureplateMeta);
        inv.setItem(18, pressureplate);

        ItemStack woodplate = new ItemStack(Material.CARROT_STICK, 1);
        ItemMeta woodplateMeta = woodplate.getItemMeta();
        woodplateMeta.setDisplayName(ChatUtil.color("&bCarrot on a stick"));
        List<String> woodplateLore = new ArrayList<>();
        woodplateLore.add(ChatUtil.color("&6Add this to your"));
        woodplateLore.add(ChatUtil.color("&6shopping cart for"));
        woodplateLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.CARROT_STICK)+" &6coins."));
        woodplateMeta.setLore(woodplateLore);
        woodplate.setItemMeta(woodplateMeta);
        inv.setItem(19, woodplate);

        ItemStack goldplate = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta goldplateMeta = goldplate.getItemMeta();
        goldplateMeta.setDisplayName(ChatUtil.color("&bTeleportation Orb"));
        List<String> goldplateLore = new ArrayList<>();
        goldplateLore.add(ChatUtil.color("&6Add this to your"));
        goldplateLore.add(ChatUtil.color("&6shopping cart for"));
        goldplateLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.ENDER_PEARL)+" &6coins."));
        goldplateMeta.setLore(goldplateLore);
        goldplate.setItemMeta(goldplateMeta);
        inv.setItem(20, goldplate);

        ItemStack ironplate = new ItemStack(Material.SADDLE, 1);
        ItemMeta ironplateMeta = ironplate.getItemMeta();
        ironplateMeta.setDisplayName(ChatUtil.color("&bSaddle"));
        List<String> ironplateLore = new ArrayList<>();
        ironplateLore.add(ChatUtil.color("&6Add this to your"));
        ironplateLore.add(ChatUtil.color("&6shopping cart for"));
        ironplateLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.SADDLE)+" &6coins."));
        ironplateMeta.setLore(ironplateLore);
        ironplate.setItemMeta(ironplateMeta);
        inv.setItem(21, ironplate);

        ItemStack piston = new ItemStack(Material.BOAT, 1);
        ItemMeta pistonMeta = piston.getItemMeta();
        pistonMeta.setDisplayName(ChatUtil.color("&bBoat"));
        List<String> pistonLore = new ArrayList<>();
        pistonLore.add(ChatUtil.color("&6Add this to your"));
        pistonLore.add(ChatUtil.color("&6shopping cart for"));
        pistonLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.BOAT)+" &6coins."));
        pistonMeta.setLore(pistonLore);
        piston.setItemMeta(pistonMeta);
        inv.setItem(27, piston);

        ItemStack stickypiston = new ItemStack(Material.LEASH, 1);
        ItemMeta stickypistonMeta = stickypiston.getItemMeta();
        stickypistonMeta.setDisplayName(ChatUtil.color("&bLeash"));
        List<String> stickypistonLore = new ArrayList<>();
        stickypistonLore.add(ChatUtil.color("&6Add this to your"));
        stickypistonLore.add(ChatUtil.color("&6shopping cart for"));
        stickypistonLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.LEASH)+" &6coins."));
        stickypistonMeta.setLore(stickypistonLore);
        stickypiston.setItemMeta(stickypistonMeta);
        inv.setItem(28, stickypiston);

        ItemStack tripwirehook = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta tripwirehookMeta = tripwirehook.getItemMeta();
        tripwirehookMeta.setDisplayName(ChatUtil.color("&bName Tag"));
        List<String> tripwirehookLore = new ArrayList<>();
        tripwirehookLore.add(ChatUtil.color("&6Add this to your"));
        tripwirehookLore.add(ChatUtil.color("&6shopping cart for"));
        tripwirehookLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.NAME_TAG)+" &6coins."));
        tripwirehookMeta.setLore(tripwirehookLore);
        tripwirehook.setItemMeta(tripwirehookMeta);
        inv.setItem(29, tripwirehook);

        ItemStack daylightsensor = new ItemStack(Material.IRON_BARDING, 1);
        ItemMeta daylightsensorMeta = daylightsensor.getItemMeta();
        daylightsensorMeta.setDisplayName(ChatUtil.color("&bIron Horse Armor"));
        List<String> daylightsensorLore = new ArrayList<>();
        daylightsensorLore.add(ChatUtil.color("&6Add this to your"));
        daylightsensorLore.add(ChatUtil.color("&6shopping cart for"));
        daylightsensorLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.IRON_BARDING)+" &6coins."));
        daylightsensorMeta.setLore(daylightsensorLore);
        daylightsensor.setItemMeta(daylightsensorMeta);
        inv.setItem(36, daylightsensor);

        ItemStack hopper = new ItemStack(Material.GOLD_BARDING, 1);
        ItemMeta hopperMeta = hopper.getItemMeta();
        hopperMeta.setDisplayName(ChatUtil.color("&bGold Horse Armor"));
        List<String> hopperLore = new ArrayList<>();
        hopperLore.add(ChatUtil.color("&6Add this to your"));
        hopperLore.add(ChatUtil.color("&6shopping cart for"));
        hopperLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.GOLD_BARDING)+" &6coins."));
        hopperMeta.setLore(hopperLore);
        hopper.setItemMeta(hopperMeta);
        inv.setItem(37, hopper);

        ItemStack dropper = new ItemStack(Material.DIAMOND_BARDING, 1);
        ItemMeta dropperMeta = dropper.getItemMeta();
        dropperMeta.setDisplayName(ChatUtil.color("&bDiamond Horse Armor"));
        List<String> dropperLore = new ArrayList<>();
        dropperLore.add(ChatUtil.color("&6Add this to your"));
        dropperLore.add(ChatUtil.color("&6shopping cart for"));
        dropperLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.DIAMOND_BARDING)+" &6coins."));
        dropperMeta.setLore(dropperLore);
        dropper.setItemMeta(dropperMeta);
        inv.setItem(38, dropper);

        ItemStack spacer = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        Short durab = 15;
        spacer.setDurability(durab);
        inv.setItem(4, spacer);
        inv.setItem(13, spacer);
        inv.setItem(22, spacer);
        inv.setItem(31, spacer);
        inv.setItem(30, spacer);
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
