package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/23/13
 * Time: 12:00 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class FoodEvent implements Listener {
    public FoodEvent(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @EventHandler
    public void foodLevel(FoodLevelChangeEvent event){
        if (event.getEntity() instanceof Player){
            Player p = (Player)event.getEntity();
            if (p.getWorld().getName().equals(Locations.spawnLoc.getWorld().getName()) && p.getLocation().distance(Locations.spawnLoc) <= 150){
                event.setCancelled(true);
            }
        }
    }
}
