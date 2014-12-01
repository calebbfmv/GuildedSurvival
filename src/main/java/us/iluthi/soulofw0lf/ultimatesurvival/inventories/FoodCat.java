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
 * Time: 12:04 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class FoodCat {
    public static Inventory inv (){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&6Food Category"));

        ItemStack bread = new ItemStack(Material.BREAD, 1);
        ItemMeta breadMeta = bread.getItemMeta();
        breadMeta.setDisplayName(ChatUtil.color("&bLoaf of bread"));
        List<String> breadLore = new ArrayList<>();
        breadLore.add(ChatUtil.color("&6Add this to your"));
        breadLore.add(ChatUtil.color("&6shopping cart for"));
        breadLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.BREAD)+" &6coins."));
        breadMeta.setLore(breadLore);
        bread.setItemMeta(breadMeta);
        inv.setItem(0, bread);

        ItemStack cookie = new ItemStack(Material.COOKIE, 1);
        ItemMeta cookieMeta = cookie.getItemMeta();
        cookieMeta.setDisplayName(ChatUtil.color("&bChocolate Chip Cookie"));
        List<String> cookieLore = new ArrayList<>();
        cookieLore.add(ChatUtil.color("&6Add this to your"));
        cookieLore.add(ChatUtil.color("&6shopping cart for"));
        cookieLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.COOKIE)+" &6coins."));
        cookieMeta.setLore(cookieLore);
        cookie.setItemMeta(cookieMeta);
        inv.setItem(1, cookie);

        ItemStack cake = new ItemStack(Material.CAKE, 1);
        ItemMeta cakeMeta = cake.getItemMeta();
        cakeMeta.setDisplayName(ChatUtil.color("&bRed velvet cake"));
        List<String> cakeLore = new ArrayList<>();
        cakeLore.add(ChatUtil.color("&6Add this to your"));
        cakeLore.add(ChatUtil.color("&6shopping cart for"));
        cakeLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.CAKE)+" &6coins."));
        cakeMeta.setLore(cakeLore);
        cake.setItemMeta(cakeMeta);
        inv.setItem(2, cake);

        ItemStack carrot = new ItemStack(Material.CARROT_ITEM, 1);
        ItemMeta carrotMeta = carrot.getItemMeta();
        carrotMeta.setDisplayName(ChatUtil.color("&bCarrot"));
        List<String> carrotLore = new ArrayList<>();
        carrotLore.add(ChatUtil.color("&6Add this to your"));
        carrotLore.add(ChatUtil.color("&6shopping cart for"));
        carrotLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.CARROT_ITEM)+" &6coins."));
        carrotMeta.setLore(carrotLore);
        carrot.setItemMeta(carrotMeta);
        inv.setItem(9, carrot);

        ItemStack goldcarrot = new ItemStack(Material.GOLDEN_CARROT, 1);
        ItemMeta goldcarrotMeta = goldcarrot.getItemMeta();
        goldcarrotMeta.setDisplayName(ChatUtil.color("&bGolden carrot"));
        List<String> goldcarrotLore = new ArrayList<>();
        goldcarrotLore.add(ChatUtil.color("&6Add this to your"));
        goldcarrotLore.add(ChatUtil.color("&6shopping cart for"));
        goldcarrotLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.GOLDEN_CARROT)+" &6coins."));
        goldcarrotMeta.setLore(goldcarrotLore);
        goldcarrot.setItemMeta(goldcarrotMeta);
        inv.setItem(10, goldcarrot);

        ItemStack bakedpotato = new ItemStack(Material.BAKED_POTATO, 1);
        ItemMeta bakedpotatoMeta = bakedpotato.getItemMeta();
        bakedpotatoMeta.setDisplayName(ChatUtil.color("&bBaked potato"));
        List<String> bakedpotatoLore = new ArrayList<>();
        bakedpotatoLore.add(ChatUtil.color("&6Add this to your"));
        bakedpotatoLore.add(ChatUtil.color("&6shopping cart for"));
        bakedpotatoLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.BAKED_POTATO)+" &6coins."));
        bakedpotatoMeta.setLore(bakedpotatoLore);
        bakedpotato.setItemMeta(bakedpotatoMeta);
        inv.setItem(11, bakedpotato);

        ItemStack apple = new ItemStack(Material.APPLE, 1);
        ItemMeta appleMeta = apple.getItemMeta();
        appleMeta.setDisplayName(ChatUtil.color("&bGranny smith apple"));
        List<String> appleLore = new ArrayList<>();
        appleLore.add(ChatUtil.color("&6Add this to your"));
        appleLore.add(ChatUtil.color("&6shopping cart for"));
        appleLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.APPLE)+" &6coins."));
        appleMeta.setLore(appleLore);
        apple.setItemMeta(appleMeta);
        inv.setItem(18, apple);

        ItemStack goldenapple = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta goldenappleMeta = goldenapple.getItemMeta();
        goldenappleMeta.setDisplayName(ChatUtil.color("&bGolden apple"));
        List<String> goldenappleLore = new ArrayList<>();
        goldenappleLore.add(ChatUtil.color("&6Add this to your"));
        goldenappleLore.add(ChatUtil.color("&6shopping cart for"));
        goldenappleLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.GOLDEN_APPLE)+" &6coins."));
        goldenappleMeta.setLore(goldenappleLore);
        goldenapple.setItemMeta(goldenappleMeta);
        inv.setItem(19, goldenapple);

        ItemStack melon = new ItemStack(Material.MELON, 1);
        ItemMeta melonMeta = melon.getItemMeta();
        melonMeta.setDisplayName(ChatUtil.color("&bMelon slice"));
        List<String> melonLore = new ArrayList<>();
        melonLore.add(ChatUtil.color("&6Add this to your"));
        melonLore.add(ChatUtil.color("&6shopping cart for"));
        melonLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.MELON)+" &6coins."));
        melonMeta.setLore(melonLore);
        melon.setItemMeta(melonMeta);
        inv.setItem(20, melon);

        ItemStack pork = new ItemStack(Material.GRILLED_PORK, 1);
        ItemMeta porkMeta = pork.getItemMeta();
        porkMeta.setDisplayName(ChatUtil.color("&bBacon!!!!!!!!!!!!"));
        List<String> porkLore = new ArrayList<>();
        porkLore.add(ChatUtil.color("&6Add this to your"));
        porkLore.add(ChatUtil.color("&6shopping cart for"));
        porkLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.GRILLED_PORK)+" &6coins."));
        porkMeta.setLore(porkLore);
        pork.setItemMeta(porkMeta);
        inv.setItem(27, pork);

        ItemStack cookedchicken = new ItemStack(Material.COOKED_CHICKEN, 1);
        ItemMeta cookedchickenMeta = cookedchicken.getItemMeta();
        cookedchickenMeta.setDisplayName(ChatUtil.color("&bGenerals fried chicken"));
        List<String> cookedchickenLore = new ArrayList<>();
        cookedchickenLore.add(ChatUtil.color("&6Add this to your"));
        cookedchickenLore.add(ChatUtil.color("&6shopping cart for"));
        cookedchickenLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.COOKED_CHICKEN)+" &6coins."));
        cookedchickenMeta.setLore(cookedchickenLore);
        cookedchicken.setItemMeta(cookedchickenMeta);
        inv.setItem(28, cookedchicken);

        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 1);
        ItemMeta steakMeta = steak.getItemMeta();
        steakMeta.setDisplayName(ChatUtil.color("&bPrime Rib"));
        List<String> steakLore = new ArrayList<>();
        steakLore.add(ChatUtil.color("&6Add this to your"));
        steakLore.add(ChatUtil.color("&6shopping cart for"));
        steakLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.COOKED_BEEF)+" &6coins."));
        steakMeta.setLore(steakLore);
        steak.setItemMeta(steakMeta);
        inv.setItem(29, steak);

        ItemStack cookedfish = new ItemStack(Material.COOKED_FISH, 1);
        ItemMeta cookedfishMeta = cookedfish.getItemMeta();
        cookedfishMeta.setDisplayName(ChatUtil.color("&bSmoked salmon"));
        List<String> cookedfishLore = new ArrayList<>();
        cookedfishLore.add(ChatUtil.color("&6Add this to your"));
        cookedfishLore.add(ChatUtil.color("&6shopping cart for"));
        cookedfishLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.COOKED_FISH)+" &6coins."));
        cookedfishMeta.setLore(cookedfishLore);
        cookedfish.setItemMeta(cookedfishMeta);
        inv.setItem(36, cookedfish);

        ItemStack mushroomstew = new ItemStack(Material.MUSHROOM_SOUP, 1);
        ItemMeta mushroomstewMeta = mushroomstew.getItemMeta();
        mushroomstewMeta.setDisplayName(ChatUtil.color("&bMiso soup"));
        List<String> mushroomstewLore = new ArrayList<>();
        mushroomstewLore.add(ChatUtil.color("&6Add this to your"));
        mushroomstewLore.add(ChatUtil.color("&6shopping cart for"));
        mushroomstewLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.MUSHROOM_SOUP)+" &6coins."));
        mushroomstewMeta.setLore(mushroomstewLore);
        mushroomstew.setItemMeta(mushroomstewMeta);
        inv.setItem(37, mushroomstew);

        ItemStack pumpkinpie = new ItemStack(Material.PUMPKIN_PIE, 1);
        ItemMeta pumpkinpieMeta = pumpkinpie.getItemMeta();
        pumpkinpieMeta.setDisplayName(ChatUtil.color("&bPumpkin pie"));
        List<String> pumpkinpieLore = new ArrayList<>();
        pumpkinpieLore.add(ChatUtil.color("&6Add this to your"));
        pumpkinpieLore.add(ChatUtil.color("&6shopping cart for"));
        pumpkinpieLore.add(ChatUtil.color("&b"+ Maps.blockCosts.get(Material.PUMPKIN_PIE)+" &6coins."));
        pumpkinpieMeta.setLore(pumpkinpieLore);
        pumpkinpie.setItemMeta(pumpkinpieMeta);
        inv.setItem(38, pumpkinpie);

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
