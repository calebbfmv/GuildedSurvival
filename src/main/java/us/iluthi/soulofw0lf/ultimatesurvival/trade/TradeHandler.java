package us.iluthi.soulofw0lf.ultimatesurvival.trade;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.CoinChange;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.InventoryMaker;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: links
 * Date: 12/15/13
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 */
public class TradeHandler {

    public static Map<Double, Trade> currentTrades = new HashMap<>();
    public static void requestTrade(Player p1, String request) {
        CustomPlayer cp1 = CustomPlayer.getCP(p1.getName());
        Player requestp;
        /**
         * begin checks
         */
        if (!p1.isOnline() || p1 == null)
        {
            return;
        }
        if ((requestp = Bukkit.getPlayer(request)) == null)
        {
            p1.sendMessage(ChatUtil.color("&4That player is currently offline."));
            return;
        }
        CustomPlayer requestcp = CustomPlayer.getCP(requestp.getName());
        if (!requestcp.isTradeOn()){
            p1.sendMessage(ChatUtil.color("&4That player does not accept trade requests."));
            return;
        }
        if (cp1.isInTrade() || !cp1.canTrade()) { //p1 in trade, cannot trade
            p1.sendMessage(ChatUtil.color("&4You cannot trade right now. Finish what you are doing first."));
            return;
        }
        if (requestcp.isInTrade() || !requestcp.canTrade()) { //other player in trade, cannot trade
            p1.sendMessage(ChatUtil.color("&4The other player is busy at the moment."));
            return;
        }
        if (!distanceValid(p1, requestp))
        {
            p1.sendMessage(ChatUtil.color("&4Could not initiate the trade, either the player is too far away or is otherwise pre-occupied."));
            return;
        }
        /**
         * end checks
         */
        double id = 30000 * Math.random(); //generate trade id
        while (currentTrades.get(id) != null) //check to make sure it's not in use
            id = (int)(30000 * Math.random()); //generate a new one if it is
        currentTrades.put(id, new Trade(p1, requestp, id));

        //POP UP THE REQUEST WINDOW FOR OTHER PARTY
        requestp.openInventory(TradeInventory.requestInv(p1));
    }


    /**
     * Universal check for player validity. Hopefully this will catch most
     * common errors
     * @param p1 player 1
     * @param p2 player 2
     * @return both players are able to trade
     */
    public static boolean isValid(Player p1, Player p2)
    {
        return p1 != null && p2 != null
                && !p1.isDead() && !p2.isDead()
                && p1.isOnline() && p2.isOnline()
                && !p1.isInsideVehicle() && !p2.isInsideVehicle()
                && !p1.isSleeping() && !p2.isSleeping();
    }

    /**
     * used to check the validity of distance for trading
     * @param p1 player 1
     * @param p2 player 2
     * @return TRUE if either player has triple.coins OR if they're within 30 blocks of eachother.
     */
    public static boolean distanceValid(Player p1, Player p2){
        return (isValid(p1, p2) && (p1.hasPermission("triple.coins")
                || p2.hasPermission("triple.coins")
                || (p1.getWorld().equals(p2.getWorld())
                && p1.getLocation().distanceSquared(p2.getLocation()) < 900))); //30^2 = 900.
    }
    public static void processConfirm(Player p){
        Trade t;
        if ((t = TradeHandler.currentTrades.get(CustomPlayer.getCP(p.getName()).getTradeID())) != null){
            if (t.isP1Confirms() && t.isP2Confirms()){
                CustomPlayer cP1 = CustomPlayer.getCP(t.getP1());
                CustomPlayer cP2 = CustomPlayer.getCP(t.getP2());
                cP1.setTradeID(-1);
                cP2.setTradeID(-1);
                Player p1 = Bukkit.getPlayer(t.getP1());
                Player p2 = Bukkit.getPlayer(t.getP2());
                for (int i : TradeInventory.slotA){
                    if (t.getP2Inv().getItem(i) != null && !t.getP2Inv().getItem(i).getType().equals(Material.AIR)){
                        p1.getInventory().addItem(t.getP2Inv().getItem(i));
                    }
                    if (t.getP1Inv().getItem(i) != null && !t.getP1Inv().getItem(i).getType().equals(Material.AIR)){
                        p2.getInventory().addItem(t.getP1Inv().getItem(i));
                    }
                }
                p1.sendMessage(ChatUtil.color(Strings.tradeStub + " Your trade request has been accepted."));
                p2.sendMessage(ChatUtil.color(Strings.tradeStub + " Your trade request has been accepted."));
                TradeHandler.currentTrades.remove(t.getiD());
                CoinChange.add(p1, -t.getP1Coins(), false);
                CoinChange.add(p2, t.getP1Coins(), false);
                CoinChange.add(p1, t.getP2Coins(), false);
                CoinChange.add(p2, -t.getP2Coins(), false);
                p1.closeInventory();
                p2.closeInventory();

            }
        }
    }
    @SuppressWarnings("deprecation")
	public static void processTrade(double id){
        Trade t = TradeHandler.currentTrades.get(id);
        if (t == null){
            return;
        }
        if (t.isOneAccepts()){
            if ((t.isP1Accepts() && !t.isP2Accepts()) || (t.isP2Accepts() && !t.isP1Accepts())){
                t.setP2Accepts(false);
                t.setP2Accepts(false);
                t.setOneAccepts(false);
            }
        } else {
            if (t.isP1Accepts() || t.isP2Accepts()){
                t.setOneAccepts(true);
            }
        }
        Player p1 = Bukkit.getPlayer(t.getP1());
        Player p2 = Bukkit.getPlayer(t.getP2());
        if (t.isP1Accepts() && t.isP2Accepts()){
            p1.closeInventory();
            p2.closeInventory();
            p1.openInventory(TradeInventory.confirmTrade(t, p1));
            p2.openInventory(TradeInventory.confirmTrade(t, p2));
            return;
        }
        Inventory inv1 = t.getP1Inv();
        Inventory inv2 = t.getP2Inv();

        for (int i : TradeInventory.slotA){
            inv1.setItem(i + 5, null);
            inv2.setItem(i + 5, null);
            if (inv1.getItem(i) != null && !inv1.getItem(i).getType().equals(Material.AIR)){
                inv2.setItem(i + 5, inv1.getItem(i));
            }
            if (inv2.getItem(i) != null && !inv2.getItem(i).getType().equals(Material.AIR)){
                inv1.setItem(i + 5, inv2.getItem(i));
            }
        }
        if (t.isP1Accepts()){
            List<String> acceptLore = new ArrayList<>();
            acceptLore.add(ChatUtil.color("&6You have Confirmed the"));
            acceptLore.add(ChatUtil.color("&6trade with " + p2.getName()));
            acceptLore.add(ChatUtil.color("&4Right click to"));
            acceptLore.add(ChatUtil.color("&4deny the trade"));
            inv1.setItem(2, InventoryMaker.itemStackMaker(ChatUtil.color("&bConfirm"), Material.STAINED_GLASS_PANE, 1, (short) 13, acceptLore));

            List<String> tacceptLore = new ArrayList<>();
            tacceptLore.add(ChatUtil.color("&6*********"));
            tacceptLore.add(ChatUtil.color("&6Trade status"));
            tacceptLore.add(ChatUtil.color("&2Confirmed."));
            tacceptLore.add(ChatUtil.color("&6*********"));
            inv2.setItem(6, InventoryMaker.itemStackMaker(ChatUtil.color("&bStatus"), Material.STAINED_GLASS_PANE, 1, (short)13, tacceptLore));
        } else {
            List<String> tacceptLore = new ArrayList<>();
            tacceptLore.add(ChatUtil.color("&6*********"));
            tacceptLore.add(ChatUtil.color("&6Trade status"));
            tacceptLore.add(ChatUtil.color("&6waiting."));
            tacceptLore.add(ChatUtil.color("&6*********"));
            inv2.setItem(6, InventoryMaker.itemStackMaker(ChatUtil.color("&bStatus"), Material.STAINED_GLASS_PANE, 1, (short)1, tacceptLore));

            List<String> acceptLore = new ArrayList<>();
            acceptLore.add(ChatUtil.color("&6Confirm trade"));
            acceptLore.add(ChatUtil.color("&6with " + p2.getName()));
            acceptLore.add(ChatUtil.color("&4Right click to"));
            acceptLore.add(ChatUtil.color("&4deny the trade"));
            inv1.setItem(2, InventoryMaker.itemStackMaker(ChatUtil.color("&bConfirm"), Material.STAINED_GLASS_PANE, 1, (short) 1, acceptLore));
        }
        if (t.isP2Accepts()){
            List<String> acceptLore = new ArrayList<>();
            acceptLore.add(ChatUtil.color("&6You have Confirmed the"));
            acceptLore.add(ChatUtil.color("&6trade with " + p1.getName()));
            acceptLore.add(ChatUtil.color("&4Right click to"));
            acceptLore.add(ChatUtil.color("&4deny the trade"));
            inv2.setItem(2, InventoryMaker.itemStackMaker(ChatUtil.color("&bConfirm"), Material.STAINED_GLASS_PANE, 1, (short) 13, acceptLore));

            List<String> tacceptLore = new ArrayList<>();
            tacceptLore.add(ChatUtil.color("&6*********"));
            tacceptLore.add(ChatUtil.color("&6Trade status"));
            tacceptLore.add(ChatUtil.color("&2Confirmed."));
            tacceptLore.add(ChatUtil.color("&6*********"));
            inv1.setItem(6, InventoryMaker.itemStackMaker(ChatUtil.color("&bStatus"), Material.STAINED_GLASS_PANE, 1, (short)13, tacceptLore));
        } else {
            List<String> tacceptLore = new ArrayList<>();
            tacceptLore.add(ChatUtil.color("&6*********"));
            tacceptLore.add(ChatUtil.color("&6Trade status"));
            tacceptLore.add(ChatUtil.color("&6waiting."));
            tacceptLore.add(ChatUtil.color("&6*********"));
            inv1.setItem(6, InventoryMaker.itemStackMaker(ChatUtil.color("&bStatus"), Material.STAINED_GLASS_PANE, 1, (short)1, tacceptLore));

            List<String> acceptLore = new ArrayList<>();
            acceptLore.add(ChatUtil.color("&6Confirm trade"));
            acceptLore.add(ChatUtil.color("&6with " + p1.getName()));
            acceptLore.add(ChatUtil.color("&4Right click to"));
            acceptLore.add(ChatUtil.color("&4deny the trade"));
            inv2.setItem(2, InventoryMaker.itemStackMaker(ChatUtil.color("&bConfirm"), Material.STAINED_GLASS_PANE, 1, (short) 1, acceptLore));
        }

        //p1
        List<String> pgoldLore = new ArrayList<>();
        pgoldLore.add(ChatUtil.color("&6*********"));
        pgoldLore.add(ChatUtil.color("&6You are offering"));
        pgoldLore.add(ChatUtil.color("&6$"+TradeInventory.prettyGold(t.getP1Coins())+" Gold"));
        pgoldLore.add(ChatUtil.color("&6*********"));
        inv1.setItem(3, InventoryMaker.itemStackMaker(ChatUtil.color("&6Gold Points"), Material.STAINED_GLASS_PANE, 1, (short)4, pgoldLore));

        List<String> tgoldLore = new ArrayList<>();
        tgoldLore.add(ChatUtil.color("&6*********"));
        tgoldLore.add(ChatUtil.color("&6" + p1.getName() + " is"));
        tgoldLore.add(ChatUtil.color("&6offering $"+TradeInventory.prettyGold(t.getP1Coins())+" gold"));
        tgoldLore.add(ChatUtil.color("&6*********"));
        inv2.setItem(5, InventoryMaker.itemStackMaker(ChatUtil.color("&6Gold Points"), Material.STAINED_GLASS_PANE, 1, (short)4, tgoldLore));


        //p2
        List<String> p2goldLore = new ArrayList<>();
        p2goldLore.add(ChatUtil.color("&6*********"));
        p2goldLore.add(ChatUtil.color("&6You are offering"));
        p2goldLore.add(ChatUtil.color("&6$"+TradeInventory.prettyGold(t.getP2Coins())+" Gold"));
        p2goldLore.add(ChatUtil.color("&6*********"));
        inv2.setItem(3, InventoryMaker.itemStackMaker(ChatUtil.color("&6Gold Points"), Material.STAINED_GLASS_PANE, 1, (short)4, p2goldLore));

        List<String> t2goldLore = new ArrayList<>();
        t2goldLore.add(ChatUtil.color("&6*********"));
        t2goldLore.add(ChatUtil.color("&6" + p2.getName() + " is"));
        t2goldLore.add(ChatUtil.color("&6offering $"+TradeInventory.prettyGold(t.getP2Coins())+" gold"));
        t2goldLore.add(ChatUtil.color("&6*********"));
        inv1.setItem(5, InventoryMaker.itemStackMaker(ChatUtil.color("&6Gold Points"), Material.STAINED_GLASS_PANE, 1, (short)4, t2goldLore));

        p1.updateInventory();
        p2.updateInventory();
    }
}

