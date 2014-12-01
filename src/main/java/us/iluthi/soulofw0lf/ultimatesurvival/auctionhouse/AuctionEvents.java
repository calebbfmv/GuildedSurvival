package us.iluthi.soulofw0lf.ultimatesurvival.auctionhouse;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/24/13
 * Time: 8:42 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class AuctionEvents implements Listener{
    public AuctionEvents(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
}
