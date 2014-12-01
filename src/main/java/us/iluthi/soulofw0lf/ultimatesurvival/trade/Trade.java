package us.iluthi.soulofw0lf.ultimatesurvival.trade;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/23/13
 * Time: 1:45 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Trade {
    private String p1 = "";
    private String p2 = "";
    private double iD = 0.0;
    private boolean p1Accepts = false;
    private boolean p2Accepts = false;
    private boolean p1Confirms = false;
    private boolean p2Confirms = false;
    private boolean inProgress = false;
    private boolean oneAccepts = false;
    private int p1Coins = 0;
    private int p2Coins = 0;
    private Inventory p1Inv;
    private Inventory p2Inv;
    public Trade(Player p1, Player p2, double iD){
        this.p1 = p1.getName();
        CustomPlayer cP = CustomPlayer.getCP(p1.getName());
        cP.setTradeID(iD);
        this.p2 = p2.getName();
        CustomPlayer cP2 = CustomPlayer.getCP(p2.getName());
        cP2.setTradeID(iD);
        cP2.setTradeRequestFrom(p1.getName());
        this.iD = iD;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1.getName();
    }

    public String getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2.getName();
    }

    public double getiD() {
        return iD;
    }

    public void setiD(double iD) {
        this.iD = iD;
    }

    public boolean isP2Confirms() {
        return p2Confirms;
    }

    public void setP2Confirms(boolean p2Confirms) {
        this.p2Confirms = p2Confirms;
    }

    public boolean isP1Confirms() {
        return p1Confirms;
    }

    public void setP1Confirms(boolean p1Confirms) {
        this.p1Confirms = p1Confirms;
    }

    public boolean isP2Accepts() {
        return p2Accepts;
    }

    public void setP2Accepts(boolean p2Accepts) {
        this.p2Accepts = p2Accepts;
    }

    public boolean isP1Accepts() {
        return p1Accepts;
    }

    public void setP1Accepts(boolean p1Accepts) {
        this.p1Accepts = p1Accepts;
    }

    public int getP1Coins() {
        return p1Coins;
    }

    public void setP1Coins(int p1Coins) {
        this.p1Coins = p1Coins;
    }

    public int getP2Coins() {
        return p2Coins;
    }

    public void setP2Coins(int p2Coins) {
        this.p2Coins = p2Coins;
    }

    public Inventory getP2Inv() {
        return p2Inv;
    }

    public void setP2Inv(Inventory p2Inv) {
        this.p2Inv = p2Inv;
    }

    public Inventory getP1Inv() {
        return p1Inv;
    }

    public void setP1Inv(Inventory p1Inv) {
        this.p1Inv = p1Inv;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public boolean isOneAccepts() {
        return oneAccepts;
    }

    public void setOneAccepts(boolean oneAccepts) {
        this.oneAccepts = oneAccepts;
    }
}
