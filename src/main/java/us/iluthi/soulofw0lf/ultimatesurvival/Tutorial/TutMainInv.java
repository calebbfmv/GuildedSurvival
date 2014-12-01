package us.iluthi.soulofw0lf.ultimatesurvival.Tutorial;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.InventoryMaker;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/19/13
 * Time: 6:52 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class TutMainInv {
    public static Inventory inv(){
        Inventory inv = Bukkit.createInventory(null, 27, ChatUtil.color("&4Tutorial"));

        List<String> introductionLore = new ArrayList<>();
        introductionLore.add(ChatUtil.color("&bClick here to take the"));
        introductionLore.add(ChatUtil.color("&bintroduction tutorial."));
        inv.setItem(0, InventoryMaker.itemStackMaker(ChatUtil.color("&6Introduction"), Material.LOG, 1, (short)0, introductionLore));

        List<String> duelsLore = new ArrayList<>();
        duelsLore.add(ChatUtil.color("&bClick here to take the"));
        duelsLore.add(ChatUtil.color("&bduels tutorial."));
        inv.setItem(2, InventoryMaker.itemStackMaker(ChatUtil.color("&6Duels"), Material.IRON_SWORD, 1, (short)0, duelsLore));

        List<String> playersLore = new ArrayList<>();
        playersLore.add(ChatUtil.color("&bClick here to take the"));
        playersLore.add(ChatUtil.color("&bplayers tutorial."));
        inv.setItem(4, InventoryMaker.itemStackMaker(ChatUtil.color("&6Players"), Material.SKULL_ITEM, 1, (short)3, playersLore));

        List<String> customItemsLore = new ArrayList<>();
        customItemsLore.add(ChatUtil.color("&bClick here to take the"));
        customItemsLore.add(ChatUtil.color("&bcustom items tutorial."));
        inv.setItem(6, InventoryMaker.itemStackMaker(ChatUtil.color("&6Custom Items"), Material.ANVIL, 1, (short)0, customItemsLore));

        List<String> guildsLore = new ArrayList<>();
        guildsLore.add(ChatUtil.color("&bClick here to take the"));
        guildsLore.add(ChatUtil.color("&bguilds tutorial."));
        inv.setItem(8, InventoryMaker.itemStackMaker(ChatUtil.color("&6Guilds"), Material.CHAINMAIL_CHESTPLATE, 1, (short)0, guildsLore));

        List<String> chatLore = new ArrayList<>();
        chatLore.add(ChatUtil.color("&bClick here to take the"));
        chatLore.add(ChatUtil.color("&bchat tutorial."));
        inv.setItem(10, InventoryMaker.itemStackMaker(ChatUtil.color("&6Chat"), Material.BOOK, 1, (short)0, chatLore));

        List<String> commandsLore = new ArrayList<>();
        commandsLore.add(ChatUtil.color("&bClick here to take the"));
        commandsLore.add(ChatUtil.color("&bcommands tutorial."));
        inv.setItem(12, InventoryMaker.itemStackMaker(ChatUtil.color("&6Commands"), Material.COMMAND, 1, (short)0, commandsLore));

        List<String> shopLore = new ArrayList<>();
        shopLore.add(ChatUtil.color("&bClick here to take the"));
        shopLore.add(ChatUtil.color("&bshop tutorial."));
        inv.setItem(14, InventoryMaker.itemStackMaker(ChatUtil.color("&6Shop"), Material.EMERALD, 1, (short)0, shopLore));

        List<String> warpsLore = new ArrayList<>();
        warpsLore.add(ChatUtil.color("&bClick here to take the"));
        warpsLore.add(ChatUtil.color("&bwarps tutorial."));
        inv.setItem(16, InventoryMaker.itemStackMaker(ChatUtil.color("&6Warps"), Material.PAPER, 1, (short)0, warpsLore));

        List<String> tradeLore = new ArrayList<>();
        tradeLore.add(ChatUtil.color("&bClick here to take the"));
        tradeLore.add(ChatUtil.color("&btrade tutorial."));
        inv.setItem(20, InventoryMaker.itemStackMaker(ChatUtil.color("&6Trade"), Material.GOLD_INGOT, 1, (short)0, tradeLore));

        List<String> partyLore = new ArrayList<>();
        partyLore.add(ChatUtil.color("&bClick here to take the"));
        partyLore.add(ChatUtil.color("&bparty tutorial."));
        inv.setItem(22, InventoryMaker.itemStackMaker(ChatUtil.color("&6Party"), Material.CAKE, 1, (short)0, partyLore));

        List<String> settingsLore = new ArrayList<>();
        settingsLore.add(ChatUtil.color("&bClick here to take the"));
        settingsLore.add(ChatUtil.color("&bsettings tutorial."));
        inv.setItem(24, InventoryMaker.itemStackMaker(ChatUtil.color("&6Settings"), Material.BOOKSHELF, 1, (short)0, settingsLore));

        return inv;
    }
}
