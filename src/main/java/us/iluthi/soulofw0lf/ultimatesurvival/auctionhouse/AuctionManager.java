package us.iluthi.soulofw0lf.ultimatesurvival.auctionhouse;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.AuctionCategory;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.AuctionItem;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.Category;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.CoinChange;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/24/13
 * Time: 8:41 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class AuctionManager {
    public static void saveAll(){
        //TODO add in database handling for saving all auction items.
    }
    public static void addItem(AuctionItem aI){
        Category cat = Category.getFromMat(aI.getiS().getType());
        AuctionCategory aC = Maps.categoryMap.get(cat);
        int i = (int)(Math.random() * Integer.MAX_VALUE);
        while (aC.getCatItems().keySet().contains(i)){
            i = (int)(Math.random() * Integer.MAX_VALUE);
        }
        aI.setId(i);
        aC.getCatItems().put(i, aI);
    }
    public static int checkBid(AuctionItem aI, String name, int i){
        int x;
        if (name.equalsIgnoreCase(aI.getOwner())){
            return -1;
        }
        if (name.equalsIgnoreCase(aI.getCurrentBidder())){
            return -2;
        }
        if (i > aI.getBidderMax()){
            x = aI.getBidderMax() + 1;
        } else {
            aI.setCurrentBid(i + 1);
            return -3;
        }
        aI.setCurrentBid(x);
        aI.setBidderMax(i);
        aI.setCurrentBidder(name);
        if (aI.getCurrentBid() >= aI.getBuyout()){

            removeItem(aI, Bukkit.getPlayer(name), false);
            return -4;
        }
        return x;
    }

    public static void removeItem(AuctionItem aI, Player p, boolean timeOut){
        p.getInventory().addItem(aI.getiS());
        Category cat = Category.getFromMat(aI.getiS().getType());
        AuctionCategory aC = Maps.categoryMap.get(cat);
        if (!timeOut && aI.getOwner().equalsIgnoreCase(p.getName())){
            p.sendMessage(ChatUtil.color(Strings.tradeStub + " Your auction for " + aI.getiS().getAmount() + " " + aI.getiS().getType().name().replace("_", " ").toLowerCase() + "'s has just sold for " + aI.getCurrentBid() + " to " + p.getName() + "."));
            CoinChange.add(p, aI.getCurrentBid(), false);
            CustomPlayer.getCP(p.getName()).getAuctionItems().remove(aI.getId());
            aC.getCatItems().remove(aI.getId());
            return;
        }
        if (timeOut){
            p.sendMessage(ChatUtil.color(Strings.tradeStub + " You have auction items that have timed out! They have been placed in your inventory."));
            p.getInventory().addItem(aI.getiS());
            CustomPlayer.getCP(p.getName()).getAuctionItems().remove(aI.getId());
        } else {
            CoinChange.add(p, -aI.getCurrentBid(), false);
            p.sendMessage(ChatUtil.color(Strings.tradeStub + " You have won " + aI.getiS().getAmount() + " " + aI.getiS().getType().name().replace("_", " ").toLowerCase() + "'s From " + aI.getOwner() + "."));
            Player owner;
            if ((owner = Bukkit.getPlayer(aI.getOwner())) == null){
                aI.setSold(true);
                return;
            } else {
                owner.sendMessage(ChatUtil.color(Strings.tradeStub + " Your auction for " + aI.getiS().getAmount() + " " + aI.getiS().getType().name().replace("_", " ").toLowerCase() + "'s has just sold for " + aI.getCurrentBid() + " to " + p.getName() + "."));
                CoinChange.add(owner, aI.getCurrentBid(), false);
                CustomPlayer.getCP(owner.getName()).getAuctionItems().remove(aI.getId());
            }
        }
        aC.getCatItems().remove(aI.getId());
    }
}
