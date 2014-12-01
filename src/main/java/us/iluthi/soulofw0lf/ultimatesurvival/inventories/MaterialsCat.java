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
 * Time: 12:39 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class MaterialsCat {
    public static Inventory inv(){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&6Materials"));

        ItemStack seeds = new ItemStack(Material.SEEDS, 1);
        ItemMeta seedsMeta = seeds.getItemMeta();
        seedsMeta.setDisplayName(ChatUtil.color("&bSeeds"));
        List<String> seedsLore = new ArrayList<>();
        seedsLore.add(ChatUtil.color("&6Add this to your"));
        seedsLore.add(ChatUtil.color("&6shopping cart for"));
        seedsLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.SEEDS)+" &6coins."));
        seedsMeta.setLore(seedsLore);
        seeds.setItemMeta(seedsMeta);
        inv.setItem(0, seeds);

        ItemStack pumpkinseeds = new ItemStack(Material.PUMPKIN_SEEDS, 1);
        ItemMeta pumpkinseedsMeta = pumpkinseeds.getItemMeta();
        pumpkinseedsMeta.setDisplayName(ChatUtil.color("&bPumpkin seeds"));
        List<String> pumpkinseedsLore = new ArrayList<>();
        pumpkinseedsLore.add(ChatUtil.color("&6Add this to your"));
        pumpkinseedsLore.add(ChatUtil.color("&6shopping cart for"));
        pumpkinseedsLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.PUMPKIN_SEEDS)+" &6coins."));
        pumpkinseedsMeta.setLore(pumpkinseedsLore);
        pumpkinseeds.setItemMeta(pumpkinseedsMeta);
        inv.setItem(1, pumpkinseeds);

        ItemStack melonseeds = new ItemStack(Material.MELON_SEEDS, 1);
        ItemMeta melonseedsMeta = melonseeds.getItemMeta();
        melonseedsMeta.setDisplayName(ChatUtil.color("&bMelon seeds"));
        List<String> melonseedsLore = new ArrayList<>();
        melonseedsLore.add(ChatUtil.color("&6Add this to your"));
        melonseedsLore.add(ChatUtil.color("&6shopping cart for"));
        melonseedsLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.MELON_SEEDS)+" &6coins."));
        melonseedsMeta.setLore(melonseedsLore);
        melonseeds.setItemMeta(melonseedsMeta);
        inv.setItem(2, melonseeds);

        ItemStack cocoabeans = new ItemStack(Material.INK_SACK, 1);
        ItemMeta cocoabeansMeta = cocoabeans.getItemMeta();
        cocoabeansMeta.setDisplayName(ChatUtil.color("&bCocoa beans"));
        List<String> cocoabeansLore = new ArrayList<>();
        cocoabeansLore.add(ChatUtil.color("&6Add this to your"));
        cocoabeansLore.add(ChatUtil.color("&6shopping cart for"));
        cocoabeansLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.INK_SACK)+" &6coins."));
        cocoabeansMeta.setLore(cocoabeansLore);
        cocoabeans.setItemMeta(cocoabeansMeta);
        Short dura = 3;
        cocoabeans.setDurability(dura);
        inv.setItem(9, cocoabeans);

        ItemStack sugarcanes = new ItemStack(Material.SUGAR_CANE, 1);
        ItemMeta sugarcanesMeta = sugarcanes.getItemMeta();
        sugarcanesMeta.setDisplayName(ChatUtil.color("&bSugar cane"));
        List<String> sugarcanesLore = new ArrayList<>();
        sugarcanesLore.add(ChatUtil.color("&6Add this to your"));
        sugarcanesLore.add(ChatUtil.color("&6shopping cart for"));
        sugarcanesLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.SUGAR_CANE)+" &6coins."));
        sugarcanesMeta.setLore(sugarcanesLore);
        sugarcanes.setItemMeta(sugarcanesMeta);
        inv.setItem(10, sugarcanes);

        ItemStack string = new ItemStack(Material.STRING, 1);
        ItemMeta stringMeta = string.getItemMeta();
        stringMeta.setDisplayName(ChatUtil.color("&bString"));
        List<String> stringLore = new ArrayList<>();
        stringLore.add(ChatUtil.color("&6Add this to your"));
        stringLore.add(ChatUtil.color("&6shopping cart for"));
        stringLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.STRING)+" &6coins."));
        stringMeta.setLore(stringLore);
        string.setItemMeta(stringMeta);
        inv.setItem(11, string);

        ItemStack feather = new ItemStack(Material.FEATHER, 1);
        ItemMeta featherMeta = feather.getItemMeta();
        featherMeta.setDisplayName(ChatUtil.color("&bFeather"));
        List<String> featherLore = new ArrayList<>();
        featherLore.add(ChatUtil.color("&6Add this to your"));
        featherLore.add(ChatUtil.color("&6shopping cart for"));
        featherLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.FEATHER)+" &6coins."));
        featherMeta.setLore(featherLore);
        feather.setItemMeta(featherMeta);
        inv.setItem(18, feather);

        ItemStack brick = new ItemStack(Material.CLAY_BRICK, 1);
        ItemMeta brickMeta = brick.getItemMeta();
        brickMeta.setDisplayName(ChatUtil.color("&bBrick"));
        List<String> brickLore = new ArrayList<>();
        brickLore.add(ChatUtil.color("&6Add this to your"));
        brickLore.add(ChatUtil.color("&6shopping cart for"));
        brickLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.CLAY_BRICK)+" &6coins."));
        brickMeta.setLore(brickLore);
        brick.setItemMeta(brickMeta);
        inv.setItem(19, brick);

        ItemStack leather = new ItemStack(Material.LEATHER, 1);
        ItemMeta leatherMeta = leather.getItemMeta();
        leatherMeta.setDisplayName(ChatUtil.color("&bLeather"));
        List<String> leatherLore = new ArrayList<>();
        leatherLore.add(ChatUtil.color("&6Add this to your"));
        leatherLore.add(ChatUtil.color("&6shopping cart for"));
        leatherLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.LEATHER)+" &6coins."));
        leatherMeta.setLore(leatherLore);
        leather.setItemMeta(leatherMeta);
        inv.setItem(20, leather);

        ItemStack egg = new ItemStack(Material.EGG, 1);
        ItemMeta eggMeta = egg.getItemMeta();
        eggMeta.setDisplayName(ChatUtil.color("&bEgg"));
        List<String> eggLore = new ArrayList<>();
        eggLore.add(ChatUtil.color("&6Add this to your"));
        eggLore.add(ChatUtil.color("&6shopping cart for"));
        eggLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.EGG)+" &6coins."));
        eggMeta.setLore(eggLore);
        egg.setItemMeta(eggMeta);
        inv.setItem(27, egg);

        ItemStack clay = new ItemStack(Material.CLAY_BALL, 1);
        ItemMeta clayMeta = clay.getItemMeta();
        clayMeta.setDisplayName(ChatUtil.color("&bClay"));
        List<String> clayLore = new ArrayList<>();
        clayLore.add(ChatUtil.color("&6Add this to your"));
        clayLore.add(ChatUtil.color("&6shopping cart for"));
        clayLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.CLAY_BALL)+" &6coins."));
        clayMeta.setLore(clayLore);
        clay.setItemMeta(clayMeta);
        inv.setItem(28, clay);

        ItemStack coal = new ItemStack(Material.COAL, 1);
        ItemMeta coalMeta = coal.getItemMeta();
        coalMeta.setDisplayName(ChatUtil.color("&bCoal"));
        List<String> coalLore = new ArrayList<>();
        coalLore.add(ChatUtil.color("&6Add this to your"));
        coalLore.add(ChatUtil.color("&6shopping cart for"));
        coalLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.COAL)+" &6coins."));
        coalMeta.setLore(coalLore);
        coal.setItemMeta(coalMeta);
        inv.setItem(36, coal);

        ItemStack sulphur = new ItemStack(Material.SULPHUR, 1);
        ItemMeta sulphurMeta = sulphur.getItemMeta();
        sulphurMeta.setDisplayName(ChatUtil.color("&bGun powder"));
        List<String> sulphurLore = new ArrayList<>();
        sulphurLore.add(ChatUtil.color("&6Add this to your"));
        sulphurLore.add(ChatUtil.color("&6shopping cart for"));
        sulphurLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.SULPHUR)+" &6coins."));
        sulphurMeta.setLore(sulphurLore);
        sulphur.setItemMeta(sulphurMeta);
        inv.setItem(37, sulphur);

        ItemStack flint = new ItemStack(Material.FLINT, 1);
        ItemMeta flintMeta = flint.getItemMeta();
        flintMeta.setDisplayName(ChatUtil.color("&bFlint"));
        List<String> flintLore = new ArrayList<>();
        flintLore.add(ChatUtil.color("&6Add this to your"));
        flintLore.add(ChatUtil.color("&6shopping cart for"));
        flintLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.FLINT)+" &6coins."));
        flintMeta.setLore(flintLore);
        flint.setItemMeta(flintMeta);
        inv.setItem(29, flint);

        ItemStack spacer = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        Short durab = 15;
        spacer.setDurability(durab);
        inv.setItem(4, spacer);
        inv.setItem(3, spacer);
        inv.setItem(13, spacer);
        inv.setItem(12, spacer);
        inv.setItem(22, spacer);
        inv.setItem(21, spacer);
        inv.setItem(31, spacer);
        inv.setItem(30, spacer);
        inv.setItem(40, spacer);
        inv.setItem(38, spacer);
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
