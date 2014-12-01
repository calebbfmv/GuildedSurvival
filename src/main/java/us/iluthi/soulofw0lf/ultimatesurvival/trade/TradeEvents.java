package us.iluthi.soulofw0lf.ultimatesurvival.trade;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/23/13
 * Time: 1:46 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class TradeEvents implements Listener {
    public TradeEvents(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    /**
     * main click handler. redirects ICE events to the proper method
     * @param event
     */
    @EventHandler
    public void playerClickInv(InventoryClickEvent event) {
        if (!event.getInventory().getName().contains("Now Trading")
                && !event.getInventory().getName().contains("Trade Confirm")
                && !event.getInventory().getName().contains("Trade From:")) {
            return;
        }
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
//        Player p = (Player) event.getWhoClicked();

        //handle dis shit
//        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (event.getInventory().getName().contains("Now Trading"))
        {
            tradeClick(event);
        }
        else if (event.getInventory().getName().contains("Trade Confirm"))
        {
            confirmClick(event);
        }
        else if (event.getInventory().getName().contains("Trade From:"))
        {
            acceptClick(event);
        }
    }
    private void acceptClick(InventoryClickEvent e){
        e.setCancelled(true);
        e.setResult(Event.Result.DENY);
        Trade t = TradeHandler.currentTrades.get(CustomPlayer.getCP(((Player) e.getWhoClicked()).getName()).getTradeID());
        if (e.getRawSlot() == 3){
            t.setInProgress(true);
            Player p = Bukkit.getPlayer(t.getP1());
            p.closeInventory();
            Player p1 = Bukkit.getPlayer(t.getP2());
            p1.closeInventory();
            t.setP1Inv((p.openInventory(TradeInventory.tradeInv(p1))).getTopInventory());
            t.setP2Inv((p1.openInventory(TradeInventory.tradeInv(p))).getTopInventory());
            return;
        }
        if (e.getRawSlot() == 5){
            e.getWhoClicked().closeInventory();
        }
    }
    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if (event.getInventory().getName().contains("Trade From:"))
        {
            Trade t = TradeHandler.currentTrades.get(CustomPlayer.getCP(event.getPlayer().getName()).getTradeID());
            if (!t.isInProgress()){
                CustomPlayer cP1 = CustomPlayer.getCP(t.getP1());
                CustomPlayer cP2 = CustomPlayer.getCP(t.getP2());
                cP1.setTradeID(-1);
                cP2.setTradeID(-1);
                Bukkit.getPlayer(t.getP1()).sendMessage(ChatUtil.color(Strings.tradeStub + " Your trade request has been denied."));
                TradeHandler.currentTrades.remove(t.getiD());
            }
        }
        if (event.getInventory().getName().contains("Trade Confirm") || event.getInventory().getName().contains("Now Trading"))
        {
            Trade t;
            if ((t = TradeHandler.currentTrades.get(CustomPlayer.getCP(event.getPlayer().getName()).getTradeID())) != null){
                if (!(t.isP1Accepts() && t.isP2Accepts())){
                    CustomPlayer cP1 = CustomPlayer.getCP(t.getP1());
                    CustomPlayer cP2 = CustomPlayer.getCP(t.getP2());
                    cP1.setTradeID(-1);
                    cP2.setTradeID(-1);
                    Player p1 = Bukkit.getPlayer(t.getP1());
                    Player p2 = Bukkit.getPlayer(t.getP2());
                    for (int i : TradeInventory.slotA){
                        if (t.getP1Inv().getItem(i) != null && !t.getP1Inv().getItem(i).getType().equals(Material.AIR)){
                            p1.getInventory().addItem(t.getP1Inv().getItem(i));
                        }
                        if (t.getP2Inv().getItem(i) != null && !t.getP2Inv().getItem(i).getType().equals(Material.AIR)){
                            p2.getInventory().addItem(t.getP2Inv().getItem(i));
                        }
                    }
                    p1.sendMessage(ChatUtil.color(Strings.tradeStub + " Your trade request has been cancelled."));
                    p2.sendMessage(ChatUtil.color(Strings.tradeStub + " Your trade request has been cancelled."));
                    TradeHandler.currentTrades.remove(t.getiD());
                    p1.closeInventory();
                    p2.closeInventory();
                }
            }
        }
    }
    private void confirmClick(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        if (!inv.getName().contains("Trade Confirm")){
            return;
        }
        e.setCancelled(true);
        e.setResult(Event.Result.DENY);
        Player p = (Player)e.getWhoClicked();
        if (e.getRawSlot() == 3){
            Trade t;
            if ((t = TradeHandler.currentTrades.get(CustomPlayer.getCP(p.getName()).getTradeID())) != null){
                if (t.getP1().equalsIgnoreCase(p.getName())){
                    t.setP1Confirms(true);
                } else {
                    t.setP2Confirms(true);
                }
            }
            TradeHandler.processConfirm(p);
            return;
        }
        if (e.getRawSlot() == 5){
            e.getWhoClicked().closeInventory();
        }
    }
    private void tradeClick(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        if (!inv.getName().equalsIgnoreCase(ChatUtil.color("&bNow Trading"))){
            return;
        }
        if (e.isShiftClick()){
            e.setCancelled(true);
            e.setResult(Event.Result.DENY);
            return;
        }
        if (e.getRawSlot() >= 45){
            return;
        }
        if (e.getRawSlot() == 0 ||
                e.getRawSlot() == 1 ||
                e.getRawSlot() == 1 ||
                e.getRawSlot() == 3 ||
                e.getRawSlot() == 4 ||
                e.getRawSlot() == 5 ||
                e.getRawSlot() == 6 ||
                e.getRawSlot() == 7 ||
                e.getRawSlot() == 8 ||
                e.getRawSlot() == 13 ||
                e.getRawSlot() == 22 ||
                e.getRawSlot() == 31 ||
                e.getRawSlot() == 16 ||
                e.getRawSlot() == 44){
            e.setCancelled(true);
            e.setResult(Event.Result.DENY);
            return;
        }
        Player p = (Player) e.getWhoClicked();
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Trade t = TradeHandler.currentTrades.get(cP.getTradeID());
        if (TradeInventory.arrayContains(TradeInventory.slotA, e.getRawSlot()) < 0){
            e.setCancelled(true);
            e.setResult(Event.Result.DENY);
            if (e.getRawSlot() == 2){
                if (t.getP1().equalsIgnoreCase(p.getName())){
                    if (e.isLeftClick()){
                       t.setP1Accepts(true);
                    } else {
                        t.setP1Accepts(false);
                    }
                } else {
                    if (e.isLeftClick()){
                        t.setP2Accepts(true);
                    } else {
                        t.setP2Accepts(false);
                    }
                }
            }
            if (e.getRawSlot() == 37){
                if (t.getP1().equalsIgnoreCase(p.getName())){
                    if (e.isLeftClick()){
                        int i = t.getP1Coins() + 1000000;
                        if (i >= cP.getGoldPoints()){
                            i = cP.getGoldPoints();
                        }
                        t.setP1Coins(i);
                    } else {
                        int i = t.getP1Coins() - 1000000;
                        if (i <= 0){
                            i = 0;
                        }
                        t.setP1Coins(i);
                    }
                } else {
                    if (e.isLeftClick()){
                        int i = t.getP2Coins() + 1000000;
                        if (i >= cP.getGoldPoints()){
                            i = cP.getGoldPoints();
                        }
                        t.setP2Coins(i);
                    } else {
                        int i = t.getP2Coins() - 1000000;
                        if (i <= 0){
                            i = 0;
                        }
                        t.setP2Coins(i);
                    }
                }
            }
            if (e.getRawSlot() == 38){
                if (t.getP1().equalsIgnoreCase(p.getName())){
                    if (e.isLeftClick()){
                        int i = t.getP1Coins() + 100000;
                        if (i >= cP.getGoldPoints()){
                            i = cP.getGoldPoints();
                        }
                        t.setP1Coins(i);
                    } else {
                        int i = t.getP1Coins() - 100000;
                        if (i <= 0){
                            i = 0;
                        }
                        t.setP1Coins(i);
                    }
                } else {
                    if (e.isLeftClick()){
                        int i = t.getP2Coins() + 100000;
                        if (i >= cP.getGoldPoints()){
                            i = cP.getGoldPoints();
                        }
                        t.setP2Coins(i);
                    } else {
                        int i = t.getP2Coins() - 100000;
                        if (i <= 0){
                            i = 0;
                        }
                        t.setP2Coins(i);
                    }
                }
            }
            if (e.getRawSlot() == 39){
                if (t.getP1().equalsIgnoreCase(p.getName())){
                    if (e.isLeftClick()){
                        int i = t.getP1Coins() + 10000;
                        if (i >= cP.getGoldPoints()){
                            i = cP.getGoldPoints();
                        }
                        t.setP1Coins(i);
                    } else {
                        int i = t.getP1Coins() - 10000;
                        if (i <= 0){
                            i = 0;
                        }
                        t.setP1Coins(i);
                    }
                } else {
                    if (e.isLeftClick()){
                        int i = t.getP2Coins() + 10000;
                        if (i >= cP.getGoldPoints()){
                            i = cP.getGoldPoints();
                        }
                        t.setP2Coins(i);
                    } else {
                        int i = t.getP2Coins() - 10000;
                        if (i <= 0){
                            i = 0;
                        }
                        t.setP2Coins(i);
                    }
                }
            }
            if (e.getRawSlot() == 40){
                if (t.getP1().equalsIgnoreCase(p.getName())){
                    if (e.isLeftClick()){
                        int i = t.getP1Coins() + 1000;
                        if (i >= cP.getGoldPoints()){
                            i = cP.getGoldPoints();
                        }
                        t.setP1Coins(i);
                    } else {
                        int i = t.getP1Coins() - 1000;
                        if (i <= 0){
                            i = 0;
                        }
                        t.setP1Coins(i);
                    }
                } else {
                    if (e.isLeftClick()){
                        int i = t.getP2Coins() + 1000;
                        if (i >= cP.getGoldPoints()){
                            i = cP.getGoldPoints();
                        }
                        t.setP2Coins(i);
                    } else {
                        int i = t.getP2Coins() - 1000;
                        if (i <= 0){
                            i = 0;
                        }
                        t.setP2Coins(i);
                    }
                }
            }
            if (e.getRawSlot() == 41){
                if (t.getP1().equalsIgnoreCase(p.getName())){
                    if (e.isLeftClick()){
                        int i = t.getP1Coins() + 100;
                        if (i >= cP.getGoldPoints()){
                            i = cP.getGoldPoints();
                        }
                        t.setP1Coins(i);
                    } else {
                        int i = t.getP1Coins() - 100;
                        if (i <= 0){
                            i = 0;
                        }
                        t.setP1Coins(i);
                    }
                } else {
                    if (e.isLeftClick()){
                        int i = t.getP2Coins() + 100;
                        if (i >= cP.getGoldPoints()){
                            i = cP.getGoldPoints();
                        }
                        t.setP2Coins(i);
                    } else {
                        int i = t.getP2Coins() - 100;
                        if (i <= 0){
                            i = 0;
                        }
                        t.setP2Coins(i);
                    }
                }
            }
            if (e.getRawSlot() == 42){
                if (t.getP1().equalsIgnoreCase(p.getName())){
                    if (e.isLeftClick()){
                        int i = t.getP1Coins() + 10;
                        if (i >= cP.getGoldPoints()){
                            i = cP.getGoldPoints();
                        }
                        t.setP1Coins(i);
                    } else {
                        int i = t.getP1Coins() - 10;
                        if (i <= 0){
                            i = 0;
                        }
                        t.setP1Coins(i);
                    }
                } else {
                    if (e.isLeftClick()){
                        int i = t.getP2Coins() + 10;
                        if (i >= cP.getGoldPoints()){
                            i = cP.getGoldPoints();
                        }
                        t.setP2Coins(i);
                    } else {
                        int i = t.getP2Coins() - 10;
                        if (i <= 0){
                            i = 0;
                        }
                        t.setP2Coins(i);
                    }
                }
            }
            if (e.getRawSlot() == 43){
                if (t.getP1().equalsIgnoreCase(p.getName())){
                    if (e.isLeftClick()){
                        int i = t.getP1Coins() + 1;
                        if (i >= cP.getGoldPoints()){
                            i = cP.getGoldPoints();
                        }
                        t.setP1Coins(i);
                    } else {
                        int i = t.getP1Coins() - 1;
                        if (i <= 0){
                            i = 0;
                        }
                        t.setP1Coins(i);
                    }
                } else {
                    if (e.isLeftClick()){
                        int i = t.getP2Coins() + 1;
                        if (i >= cP.getGoldPoints()){
                            i = cP.getGoldPoints();
                        }
                        t.setP2Coins(i);
                    } else {
                        int i = t.getP2Coins() - 1;
                        if (i <= 0){
                            i = 0;
                        }
                        t.setP2Coins(i);
                    }
                }
            }
        }
        final double id = t.getiD();
        new BukkitRunnable(){
            @Override
            public void run(){
            TradeHandler.processTrade(id);
            }
        }.runTaskLater(UltimateSurvival.getInstance(), 1);
    }
}
