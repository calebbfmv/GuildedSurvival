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
 * Time: 12:55 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class RedstoneCat {
    public static Inventory inv(){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&4Redstone Category"));

        ItemStack redstoneblock = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta redstoneblockMeta = redstoneblock.getItemMeta();
        redstoneblockMeta.setDisplayName(ChatUtil.color("&bRedstone block"));
        List<String> redstoneblockLore = new ArrayList<>();
        redstoneblockLore.add(ChatUtil.color("&6Add this to your"));
        redstoneblockLore.add(ChatUtil.color("&6shopping cart for"));
        redstoneblockLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.REDSTONE_BLOCK)+" &6coins."));
        redstoneblockMeta.setLore(redstoneblockLore);
        redstoneblock.setItemMeta(redstoneblockMeta);
        inv.setItem(0, redstoneblock);

        ItemStack redstone = new ItemStack(Material.REDSTONE, 1);
        ItemMeta redstoneMeta = redstone.getItemMeta();
        redstoneMeta.setDisplayName(ChatUtil.color("&bRedstone"));
        List<String> redstoneLore = new ArrayList<>();
        redstoneLore.add(ChatUtil.color("&6Add this to your"));
        redstoneLore.add(ChatUtil.color("&6shopping cart for"));
        redstoneLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.REDSTONE)+" &6coins."));
        redstoneMeta.setLore(redstoneLore);
        redstone.setItemMeta(redstoneMeta);
        inv.setItem(1, redstone);

        ItemStack redstonetorch = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
        ItemMeta redstonetorchMeta = redstonetorch.getItemMeta();
        redstonetorchMeta.setDisplayName(ChatUtil.color("&bRedstone torch"));
        List<String> redstonetorchLore = new ArrayList<>();
        redstonetorchLore.add(ChatUtil.color("&6Add this to your"));
        redstonetorchLore.add(ChatUtil.color("&6shopping cart for"));
        redstonetorchLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.REDSTONE_TORCH_ON)+" &6coins."));
        redstonetorchMeta.setLore(redstonetorchLore);
        redstonetorch.setItemMeta(redstonetorchMeta);
        inv.setItem(2, redstonetorch);

        ItemStack lever = new ItemStack(Material.LEVER, 1);
        ItemMeta leverMeta = lever.getItemMeta();
        leverMeta.setDisplayName(ChatUtil.color("&bLever"));
        List<String> leverLore = new ArrayList<>();
        leverLore.add(ChatUtil.color("&6Add this to your"));
        leverLore.add(ChatUtil.color("&6shopping cart for"));
        leverLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.LEVER)+" &6coins."));
        leverMeta.setLore(leverLore);
        lever.setItemMeta(leverMeta);
        inv.setItem(3, lever);

        ItemStack woodbutton = new ItemStack(Material.WOOD_BUTTON, 1);
        ItemMeta woodbuttonMeta = woodbutton.getItemMeta();
        woodbuttonMeta.setDisplayName(ChatUtil.color("&bWood button"));
        List<String> woodbuttonLore = new ArrayList<>();
        woodbuttonLore.add(ChatUtil.color("&6Add this to your"));
        woodbuttonLore.add(ChatUtil.color("&6shopping cart for"));
        woodbuttonLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.WOOD_BUTTON)+" &6coins."));
        woodbuttonMeta.setLore(woodbuttonLore);
        woodbutton.setItemMeta(woodbuttonMeta);
        inv.setItem(9, woodbutton);

        ItemStack button = new ItemStack(Material.STONE_BUTTON, 1);
        ItemMeta buttonMeta = button.getItemMeta();
        buttonMeta.setDisplayName(ChatUtil.color("&bButton"));
        List<String> buttonLore = new ArrayList<>();
        buttonLore.add(ChatUtil.color("&6Add this to your"));
        buttonLore.add(ChatUtil.color("&6shopping cart for"));
        buttonLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.STONE_BUTTON)+" &6coins."));
        buttonMeta.setLore(buttonLore);
        button.setItemMeta(buttonMeta);
        inv.setItem(10, button);

        ItemStack diode = new ItemStack(Material.DIODE, 1);
        ItemMeta diodeMeta = diode.getItemMeta();
        diodeMeta.setDisplayName(ChatUtil.color("&bRedstone repeater"));
        List<String> diodeLore = new ArrayList<>();
        diodeLore.add(ChatUtil.color("&6Add this to your"));
        diodeLore.add(ChatUtil.color("&6shopping cart for"));
        diodeLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.DIODE)+" &6coins."));
        diodeMeta.setLore(diodeLore);
        diode.setItemMeta(diodeMeta);
        inv.setItem(11, diode);

        ItemStack comparater = new ItemStack(Material.REDSTONE_COMPARATOR, 1);
        ItemMeta comparaterMeta = comparater.getItemMeta();
        comparaterMeta.setDisplayName(ChatUtil.color("&bRedstone Comparator"));
        List<String> comparaterLore = new ArrayList<>();
        comparaterLore.add(ChatUtil.color("&6Add this to your"));
        comparaterLore.add(ChatUtil.color("&6shopping cart for"));
        comparaterLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.REDSTONE_COMPARATOR)+" &6coins."));
        comparaterMeta.setLore(comparaterLore);
        comparater.setItemMeta(comparaterMeta);
        inv.setItem(12, comparater);

        ItemStack pressureplate = new ItemStack(Material.STONE_PLATE, 1);
        ItemMeta pressureplateMeta = pressureplate.getItemMeta();
        pressureplateMeta.setDisplayName(ChatUtil.color("&bPressure plate"));
        List<String> pressureplateLore = new ArrayList<>();
        pressureplateLore.add(ChatUtil.color("&6Add this to your"));
        pressureplateLore.add(ChatUtil.color("&6shopping cart for"));
        pressureplateLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.STONE_PLATE)+" &6coins."));
        pressureplateMeta.setLore(pressureplateLore);
        pressureplate.setItemMeta(pressureplateMeta);
        inv.setItem(18, pressureplate);

        ItemStack woodplate = new ItemStack(Material.WOOD_PLATE, 1);
        ItemMeta woodplateMeta = woodplate.getItemMeta();
        woodplateMeta.setDisplayName(ChatUtil.color("&bWood plate"));
        List<String> woodplateLore = new ArrayList<>();
        woodplateLore.add(ChatUtil.color("&6Add this to your"));
        woodplateLore.add(ChatUtil.color("&6shopping cart for"));
        woodplateLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.WOOD_PLATE)+" &6coins."));
        woodplateMeta.setLore(woodplateLore);
        woodplate.setItemMeta(woodplateMeta);
        inv.setItem(19, woodplate);

        ItemStack goldplate = new ItemStack(Material.GOLD_PLATE, 1);
        ItemMeta goldplateMeta = goldplate.getItemMeta();
        goldplateMeta.setDisplayName(ChatUtil.color("&bGold plate"));
        List<String> goldplateLore = new ArrayList<>();
        goldplateLore.add(ChatUtil.color("&6Add this to your"));
        goldplateLore.add(ChatUtil.color("&6shopping cart for"));
        goldplateLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.GOLD_PLATE)+" &6coins."));
        goldplateMeta.setLore(goldplateLore);
        goldplate.setItemMeta(goldplateMeta);
        inv.setItem(20, goldplate);

        ItemStack ironplate = new ItemStack(Material.IRON_PLATE, 1);
        ItemMeta ironplateMeta = ironplate.getItemMeta();
        ironplateMeta.setDisplayName(ChatUtil.color("&bIron plate"));
        List<String> ironplateLore = new ArrayList<>();
        ironplateLore.add(ChatUtil.color("&6Add this to your"));
        ironplateLore.add(ChatUtil.color("&6shopping cart for"));
        ironplateLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.IRON_PLATE)+" &6coins."));
        ironplateMeta.setLore(ironplateLore);
        ironplate.setItemMeta(ironplateMeta);
        inv.setItem(21, ironplate);

        ItemStack piston = new ItemStack(Material.PISTON_BASE, 1);
        ItemMeta pistonMeta = piston.getItemMeta();
        pistonMeta.setDisplayName(ChatUtil.color("&bPiston"));
        List<String> pistonLore = new ArrayList<>();
        pistonLore.add(ChatUtil.color("&6Add this to your"));
        pistonLore.add(ChatUtil.color("&6shopping cart for"));
        pistonLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.PISTON_BASE)+" &6coins."));
        pistonMeta.setLore(pistonLore);
        piston.setItemMeta(pistonMeta);
        inv.setItem(27, piston);

        ItemStack stickypiston = new ItemStack(Material.PISTON_STICKY_BASE, 1);
        ItemMeta stickypistonMeta = stickypiston.getItemMeta();
        stickypistonMeta.setDisplayName(ChatUtil.color("&bSticky piston"));
        List<String> stickypistonLore = new ArrayList<>();
        stickypistonLore.add(ChatUtil.color("&6Add this to your"));
        stickypistonLore.add(ChatUtil.color("&6shopping cart for"));
        stickypistonLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.PISTON_STICKY_BASE)+" &6coins."));
        stickypistonMeta.setLore(stickypistonLore);
        stickypiston.setItemMeta(stickypistonMeta);
        inv.setItem(28, stickypiston);

        ItemStack tripwirehook = new ItemStack(Material.TRIPWIRE_HOOK, 1);
        ItemMeta tripwirehookMeta = tripwirehook.getItemMeta();
        tripwirehookMeta.setDisplayName(ChatUtil.color("&bTripwire hook"));
        List<String> tripwirehookLore = new ArrayList<>();
        tripwirehookLore.add(ChatUtil.color("&6Add this to your"));
        tripwirehookLore.add(ChatUtil.color("&6shopping cart for"));
        tripwirehookLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.TRIPWIRE_HOOK)+" &6coins."));
        tripwirehookMeta.setLore(tripwirehookLore);
        tripwirehook.setItemMeta(tripwirehookMeta);
        inv.setItem(29, tripwirehook);

        ItemStack tnt = new ItemStack(Material.TNT, 1);
        ItemMeta tntMeta = tnt.getItemMeta();
        tntMeta.setDisplayName(ChatUtil.color("&bTnT"));
        List<String> tntLore = new ArrayList<>();
        tntLore.add(ChatUtil.color("&6Add this to your"));
        tntLore.add(ChatUtil.color("&6shopping cart for"));
        tntLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.TNT)+" &6coins."));
        tntMeta.setLore(tntLore);
        tnt.setItemMeta(tntMeta);
        inv.setItem(30, tnt);

        ItemStack daylightsensor = new ItemStack(Material.DAYLIGHT_DETECTOR, 1);
        ItemMeta daylightsensorMeta = daylightsensor.getItemMeta();
        daylightsensorMeta.setDisplayName(ChatUtil.color("&bDaylight sensor"));
        List<String> daylightsensorLore = new ArrayList<>();
        daylightsensorLore.add(ChatUtil.color("&6Add this to your"));
        daylightsensorLore.add(ChatUtil.color("&6shopping cart for"));
        daylightsensorLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.DAYLIGHT_DETECTOR)+" &6coins."));
        daylightsensorMeta.setLore(daylightsensorLore);
        daylightsensor.setItemMeta(daylightsensorMeta);
        inv.setItem(36, daylightsensor);

        ItemStack hopper = new ItemStack(Material.HOPPER, 1);
        ItemMeta hopperMeta = hopper.getItemMeta();
        hopperMeta.setDisplayName(ChatUtil.color("&bHopper"));
        List<String> hopperLore = new ArrayList<>();
        hopperLore.add(ChatUtil.color("&6Add this to your"));
        hopperLore.add(ChatUtil.color("&6shopping cart for"));
        hopperLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.HOPPER)+" &6coins."));
        hopperMeta.setLore(hopperLore);
        hopper.setItemMeta(hopperMeta);
        inv.setItem(37, hopper);

        ItemStack dropper = new ItemStack(Material.DROPPER, 1);
        ItemMeta dropperMeta = dropper.getItemMeta();
        dropperMeta.setDisplayName(ChatUtil.color("&bDropper"));
        List<String> dropperLore = new ArrayList<>();
        dropperLore.add(ChatUtil.color("&6Add this to your"));
        dropperLore.add(ChatUtil.color("&6shopping cart for"));
        dropperLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.DROPPER)+" &6coins."));
        dropperMeta.setLore(dropperLore);
        dropper.setItemMeta(dropperMeta);
        inv.setItem(38, dropper);

        ItemStack dispenser = new ItemStack(Material.DISPENSER, 1);
        ItemMeta dispenserMeta = dispenser.getItemMeta();
        dispenserMeta.setDisplayName(ChatUtil.color("&bDispenser"));
        List<String> dispenserLore = new ArrayList<>();
        dispenserLore.add(ChatUtil.color("&6Add this to your"));
        dispenserLore.add(ChatUtil.color("&6shopping cart for"));
        dispenserLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.DISPENSER)+" &6coins."));
        dispenserMeta.setLore(dispenserLore);
        dispenser.setItemMeta(dispenserMeta);
        inv.setItem(39, dispenser);

        ItemStack spacer = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        Short durab = 15;
        spacer.setDurability(durab);
        inv.setItem(4, spacer);
        inv.setItem(13, spacer);
        inv.setItem(22, spacer);
        inv.setItem(31, spacer);
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
