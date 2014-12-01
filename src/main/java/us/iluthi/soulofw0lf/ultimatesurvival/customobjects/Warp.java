package us.iluthi.soulofw0lf.ultimatesurvival.customobjects;

import org.bukkit.Location;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/25/13
 * Time: 11:13 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Warp {
    private String name = "";
    private Location loc;
    public Warp(String name, Location loc, CustomPlayer cP){
        this.name = name;
        this.loc = loc;
        cP.getWarpList().add(this);
    }
    public Warp(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }
}
