package us.iluthi.soulofw0lf.ultimatesurvival.customobjects;

import org.bukkit.inventory.ItemStack;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/24/13
 * Time: 2:25 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class AuctionItem {
    private String owner = "";
    private long time = 0;
    private ItemStack iS;
    private int minBid = 0;
    private int buyout = 0;
    private int currentBid = 0;
    private String currentBidder = "";
    private int id = 0;
    private int bidderMax = 0;
    private boolean sold = false;

    public AuctionItem(String owner, long time, ItemStack iS, int minBid, int buyout){
        this.owner = owner;
        this.time = time;
        this.iS = iS;
        this.minBid = minBid;
        this.currentBid = minBid;
        this.currentBidder = owner;
        this.buyout = buyout;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyout() {
        return buyout;
    }

    public void setBuyout(int buyout) {
        this.buyout = buyout;
    }

    public int getMinBid() {
        return minBid;
    }

    public void setMinBid(int minBid) {
        this.minBid = minBid;
    }

    public ItemStack getiS() {
        return iS;
    }

    public void setiS(ItemStack iS) {
        this.iS = iS;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCurrentBidder() {
        return currentBidder;
    }

    public void setCurrentBidder(String currentBidder) {
        this.currentBidder = currentBidder;
    }

    public int getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(int currentBid) {
        this.currentBid = currentBid;
    }

    public int getBidderMax() {
        return bidderMax;
    }

    public void setBidderMax(int bidderMax) {
        this.bidderMax = bidderMax;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
