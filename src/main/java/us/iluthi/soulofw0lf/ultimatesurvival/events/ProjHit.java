package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/23/13
 * Time: 12:52 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ProjHit implements Listener {
    public ProjHit(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @EventHandler
    public void projLaunch(ProjectileLaunchEvent event){
        if (event.getEntity().getShooter() != null){
            if (event.getEntity().getShooter().getWorld().getName().equals(Locations.spawnLoc.getWorld().getName()) && event.getEntity().getShooter().getLocation().distance(Locations.spawnLoc) <= 150){
                event.setCancelled(true);
                return;
            }
        }
    }
    @EventHandler
    public void projHit(ProjectileHitEvent event){
        if (event.getEntity().getLocation().getWorld().getName().equals(Locations.spawnLoc.getWorld().getName()) && event.getEntity().getLocation().distance(Locations.spawnLoc) <= 150){
            return;
        }
        if (event.getEntity() instanceof Snowball){
            Snowball snow = (Snowball)event.getEntity();
            snow.getWorld().strikeLightning(snow.getLocation());
        }
    }
}
