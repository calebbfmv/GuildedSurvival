package us.iluthi.soulofw0lf.ultimatesurvival.trade;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.InventoryMaker;

import java.util.ArrayList;
import java.util.List;

/**
 * User: links
 * Date: 12/15/13
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * 00 01 02 03 04 05 06 07 08
 * 09 10 11 12 13 14 15 16 17
 * 18 19 20 21 22 23 24 25 26
 * 27 28 29 30 31 32 33 34 35
 * 36 37 38 39 40 41 42 43 44
 */
public class TradeInventory implements Listener {
    private static final Material ACCEPT_CHECKED = Material.STAINED_GLASS;
    private static final short ACCEPT_CHECKED_DURA = (short) 2;
    private static final Material DECLINE = Material.WOOL;
    private static final short DECLINE_DURA = (short) 1;

    public static Inventory requestInv(Player p1)
    {
        Inventory inv = Bukkit.createInventory(null, 9, ChatUtil.color("Trade From:" + p1.getName()));

        ItemStack accept = new ItemStack(ACCEPT_CHECKED, 1);
        accept.setDurability(ACCEPT_CHECKED_DURA);
        ItemMeta mkM = accept.getItemMeta();
        mkM.setDisplayName(ChatUtil.color("&2Accept"));
        List<String> mkLore = new ArrayList<>();
        mkLore.add(ChatUtil.color("&7Click here to accept."));
        mkM.setLore(mkLore);
        accept.setItemMeta(mkM);
        inv.setItem(3, accept);

        ItemStack decline = new ItemStack(DECLINE, 1);
        decline.setDurability(DECLINE_DURA);
        ItemMeta mkM2 = decline.getItemMeta();
        mkM2.setDisplayName(ChatUtil.color("&2Decline"));
        List<String> mkLore2 = new ArrayList<>();
        mkLore2.add(ChatUtil.color("&7Click here to decline."));
        mkM2.setLore(mkLore2);
        decline.setItemMeta(mkM2);
        inv.setItem(5, decline);
        return inv;

    }

    public static Inventory tradeInv(Player p){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&bNow Trading"));

        List<String> acceptLore = new ArrayList<>();
        acceptLore.add(ChatUtil.color("&6Confirm trade"));
        acceptLore.add(ChatUtil.color("&6with " + p.getName()));
        acceptLore.add(ChatUtil.color("&4Right click to"));
        acceptLore.add(ChatUtil.color("&4deny the trade"));
        inv.setItem(2, InventoryMaker.itemStackMaker(ChatUtil.color("&bConfirm"), Material.STAINED_GLASS_PANE, 1, (short) 1, acceptLore));

        List<String> pgoldLore = new ArrayList<>();
        pgoldLore.add(ChatUtil.color("&6*********"));
        pgoldLore.add(ChatUtil.color("&6You are offering"));
        pgoldLore.add(ChatUtil.color("&6$0 Gold"));
        pgoldLore.add(ChatUtil.color("&6*********"));
        inv.setItem(3, InventoryMaker.itemStackMaker(ChatUtil.color("&6Gold Points"), Material.STAINED_GLASS_PANE, 1, (short)4, pgoldLore));

        List<String> tgoldLore = new ArrayList<>();
        tgoldLore.add(ChatUtil.color("&6*********"));
        tgoldLore.add(ChatUtil.color("&6" + p.getName() + " is"));
        tgoldLore.add(ChatUtil.color("&6offering $0 gold"));
        tgoldLore.add(ChatUtil.color("&6*********"));
        inv.setItem(5, InventoryMaker.itemStackMaker(ChatUtil.color("&6Gold Points"), Material.STAINED_GLASS_PANE, 1, (short)4, tgoldLore));

        List<String> tacceptLore = new ArrayList<>();
        tacceptLore.add(ChatUtil.color("&6*********"));
        tacceptLore.add(ChatUtil.color("&6Trade status"));
        tacceptLore.add(ChatUtil.color("&6waiting."));
        tacceptLore.add(ChatUtil.color("&6*********"));
        inv.setItem(6, InventoryMaker.itemStackMaker(ChatUtil.color("&bStatus"), Material.STAINED_GLASS_PANE, 1, (short)1, tacceptLore));

        List<String> oneLore = new ArrayList<>();
        oneLore.add(ChatUtil.color("&6Left click to add"));
        oneLore.add(ChatUtil.color("&61 gold to the trade."));
        tgoldLore.add(ChatUtil.color("&4Right click to remove"));
        tgoldLore.add(ChatUtil.color("&61 Gold from the trade."));
        inv.setItem(43, InventoryMaker.itemStackMaker(ChatUtil.color("&e1 Gold Point"), Material.COAL, 1, (short)0, oneLore));

        List<String> tenLore = new ArrayList<>();
        tenLore.add(ChatUtil.color("&6Left click to add"));
        tenLore.add(ChatUtil.color("&610 gold to the trade."));
        tenLore.add(ChatUtil.color("&4Right click to remove"));
        tenLore.add(ChatUtil.color("&410 gold from the trade."));
        inv.setItem(42, InventoryMaker.itemStackMaker(ChatUtil.color("&e10 Gold Points"), Material.CLAY_BALL, 1, (short)0, tenLore));

        List<String> hundredLore = new ArrayList<>();
        hundredLore.add(ChatUtil.color("&6Left click to add"));
        hundredLore.add(ChatUtil.color("&6100 gold to the trade."));
        hundredLore.add(ChatUtil.color("&4Right click to remove"));
        hundredLore.add(ChatUtil.color("&4100 fold form the trade."));
        inv.setItem(41, InventoryMaker.itemStackMaker(ChatUtil.color("&e100 Gold Points"), Material.GLOWSTONE_DUST, 1, (short) 0, hundredLore));

        List<String> thouLore = new ArrayList<>();
        thouLore.add(ChatUtil.color("&6Left click to add"));
        thouLore.add(ChatUtil.color("&61K gold to the trade."));
        thouLore.add(ChatUtil.color("&4Right click to remove"));
        thouLore.add(ChatUtil.color("&41K gold from the trade."));
        inv.setItem(40, InventoryMaker.itemStackMaker(ChatUtil.color("&e1K Gold Points"), Material.IRON_INGOT, 1, (short)0, thouLore));

        List<String> tenthouLore = new ArrayList<>();
        tenthouLore.add(ChatUtil.color("&6Left click to add"));
        tenthouLore.add(ChatUtil.color("&610K gold to the trade."));
        tenthouLore.add(ChatUtil.color("&4Right click to remove"));
        tenthouLore.add(ChatUtil.color("&410K gold from the trade."));
        inv.setItem(39, InventoryMaker.itemStackMaker(ChatUtil.color("&e10K Gold Points"), Material.GOLD_INGOT, 1, (short)0, tenthouLore));

        List<String> hundredthouLore = new ArrayList<>();
        hundredthouLore.add(ChatUtil.color("&6Left click to add"));
        hundredthouLore.add(ChatUtil.color("&6100K gold to the trade."));
        hundredthouLore.add(ChatUtil.color("&4Right click to remove"));
        hundredthouLore.add(ChatUtil.color("&4100K gold from the trade."));
        inv.setItem(38, InventoryMaker.itemStackMaker(ChatUtil.color("&e100K Gold Points"), Material.EMERALD, 1, (short)0, hundredthouLore));

        List<String> milLore = new ArrayList<>();
        milLore.add(ChatUtil.color("&6Left click to add"));
        milLore.add(ChatUtil.color("&61Mil gold to the trade."));
        milLore.add(ChatUtil.color("&4Right click to remove"));
        milLore.add(ChatUtil.color("&41Mil gold from the trade."));
        inv.setItem(37, InventoryMaker.itemStackMaker(ChatUtil.color("&e1Mil Gold Points"), Material.DIAMOND, 1, (short)0, milLore));

        ItemStack devider = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        ItemMeta iM = devider.getItemMeta();
        iM.setDisplayName(ChatUtil.color("&f "));
        devider.setItemMeta(iM);
        devider.setDurability((short)15);
        inv.setItem(0, devider);
        inv.setItem(1, devider);
        inv.setItem(4, devider);
        inv.setItem(7, devider);
        inv.setItem(8, devider);
        inv.setItem(13, devider);
        inv.setItem(22, devider);
        inv.setItem(31, devider);
        inv.setItem(36, devider);
        inv.setItem(44, devider);

        return inv;
    }

    public static Inventory confirmTrade(Trade t, Player p){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&6Trade Confirm"));
        Inventory inv1 = t.getP1Inv();
        Inventory inv2 = t.getP2Inv();

        if (p.getName().equalsIgnoreCase(t.getP1())){
            ItemStack iS = new ItemStack(Material.WOOL, 1);
            ItemMeta iM = iS.getItemMeta();
            iM.setDisplayName(ChatUtil.color("&2Confirm this trade"));
            List<String> lores = new ArrayList<>();
            lores.add(ChatUtil.color("If you click here to"));
            lores.add(ChatUtil.color("confirm this trade you"));
            lores.add(ChatUtil.color("will receive the items "));
            lores.add(ChatUtil.color("on the left and pay &6" + prettyGold(t.getP1Coins()) + " gold."));
            lores.add(ChatUtil.color("You will give away the items"));
            lores.add(ChatUtil.color("on the right and receive &6" + prettyGold(t.getP2Coins()) + " gold."));
            iM.setLore(lores);
            iS.setItemMeta(iM);
            iS.setDurability((short)13);
            inv.setItem(3, iS);

            List<String> deny = new ArrayList<>();
            deny.add(ChatUtil.color("&4Click here to cancel"));
            deny.add(ChatUtil.color("&4this trade."));
            inv.setItem(5, InventoryMaker.itemStackMaker(ChatUtil.color("&eDecline"), Material.WOOL, 1, (short)14, deny));

            for (int i : TradeInventory.slotA){
                if (inv1.getItem(i) != null && !inv1.getItem(i).getType().equals(Material.AIR)){
                    inv.setItem(i + 5, inv1.getItem(i));
                }
                if (inv2.getItem(i) != null && !inv2.getItem(i).getType().equals(Material.AIR)){
                    inv.setItem(i, inv2.getItem(i));
                }
            }
        } else {
            ItemStack iS = new ItemStack(Material.WOOL, 1);
            ItemMeta iM = iS.getItemMeta();
            iM.setDisplayName(ChatUtil.color("&2Confirm this trade"));
            List<String> lores = new ArrayList<>();
            lores.add(ChatUtil.color("If you click here to"));
            lores.add(ChatUtil.color("confirm this trade you"));
            lores.add(ChatUtil.color("will receive the items "));
            lores.add(ChatUtil.color("on the left and pay &6" + prettyGold(t.getP2Coins()) + " gold."));
            lores.add(ChatUtil.color("You will give away the items"));
            lores.add(ChatUtil.color("on the right and receive &6" + prettyGold(t.getP1Coins()) + " gold."));
            iM.setLore(lores);
            iS.setItemMeta(iM);
            iS.setDurability((short)13);
            inv.setItem(3, iS);

            List<String> deny = new ArrayList<>();
            deny.add(ChatUtil.color("&4Click here to cancel"));
            deny.add(ChatUtil.color("&4this trade."));
            inv.setItem(5, InventoryMaker.itemStackMaker(ChatUtil.color("&eDecline"), Material.WOOL, 1, (short)14, deny));
            for (int i : TradeInventory.slotA){
                if (inv1.getItem(i) != null && !inv1.getItem(i).getType().equals(Material.AIR)){
                    inv.setItem(i, inv1.getItem(i));
                }
                if (inv2.getItem(i) != null && !inv2.getItem(i).getType().equals(Material.AIR)){
                    inv.setItem(i + 5, inv2.getItem(i));
                }
            }

        }
        ItemStack devider = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        ItemMeta iM = devider.getItemMeta();
        iM.setDisplayName(ChatUtil.color("&f "));
        devider.setItemMeta(iM);
        devider.setDurability((short) 15);
        inv.setItem(4, devider);
        inv.setItem(13, devider);
        inv.setItem(22, devider);
        inv.setItem(31, devider);
        return inv;
    }
    /**
     * 150500000 to 150M500K
     * 40444 to 40k
     * @param i integer
     * @return pretty formated gold string
     */
    public static String prettyGold(int i) {
        String out = "";
        if (i < 1_000)
            out = i + "";
        if (i > 1_000) {
            int k = i / 1_000;
            out = ("&1" + k + "k").concat(out);
        }
        if (i > 1_000_000) {
            int mil = i / 1_000_000;
            out = ("&3" + mil + "M").concat(out);
        }
        return ChatUtil.color(out);
    }
    public static int arrayContains(int[] arr, int k)
    {
        for (int i = 0; i < arr.length; i++) if (arr[i] == k) return i;
        return -1;
    }
    public final static int slotA[] = {9, 10, 11, 12, 18, 19, 20, 21, 27, 28, 29, 30};
}
