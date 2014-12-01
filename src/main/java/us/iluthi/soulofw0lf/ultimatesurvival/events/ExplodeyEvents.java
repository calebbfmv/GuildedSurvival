package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/30/13
 * Time: 6:01 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ExplodeyEvents implements Listener {
    public ExplodeyEvents(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        if (event.getCause().equals(IgniteCause.LIGHTNING)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onExplode(EntityExplodeEvent event){
        Location loc = event.getLocation();
        if (loc.getWorld().getName().equalsIgnoreCase(Locations.spawnLoc.getWorld().getName())){
        if (loc.distance(Locations.spawnLoc) <= 180){
            event.setCancelled(true);
            return;
        }
        }
        for (String key : Maps.allGuilds.keySet()){
            Guild g = Maps.allGuilds.get(key);
            if (g.hasHq()){
                if (g.isHasStoreroom()){
                    if (loc.getX() <= g.getMaxX() && loc.getY() <= g.getMaxY() && loc.getZ() <= g.getMaxZ() && loc.getX() >= g.getMinX() && loc.getY() >= g.getMinY() && loc.getZ() >= g.getMinZ()){
                        event.setCancelled(true);
                        return;
                    }
                }
                if (loc.getX() <= g.getHeadQuarters().getMaxX()
                        && loc.getZ() <= g.getHeadQuarters().getMaxZ()
                        && loc.getX() >= g.getHeadQuarters().getMinX()
                        && loc.getZ() >= g.getHeadQuarters().getMinZ()
                        && !g.isWarning()){
                    event.setCancelled(true);
                    return;
                }

            }
        }
    }
}
