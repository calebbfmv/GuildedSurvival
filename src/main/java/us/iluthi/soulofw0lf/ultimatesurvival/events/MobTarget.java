package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/23/13
 * Time: 12:12 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class MobTarget implements Listener {
    public MobTarget(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @EventHandler
    public void mobTarget(EntityTargetEvent event){
        if (event.getEntity().getWorld().getName().equals(Locations.spawnLoc.getWorld().getName()) && event.getEntity().getLocation().distance(Locations.spawnLoc) <= 150){
            event.setTarget(null);
            event.setCancelled(true);
            return;
        }
        if (event.getTarget() != null){
            if (event.getTarget().getWorld().getName().equals(Locations.spawnLoc.getWorld().getName()) && event.getTarget().getLocation().distance(Locations.spawnLoc) <= 150){
                event.setTarget(null);
                event.setCancelled(true);
                return;
            }
        }
        if (event.getTarget() instanceof Player){
            Player p = (Player)event.getTarget();
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            if (cP.isInDuel()){
                event.setCancelled(true);
                return;
            }
            if (Lists.duelWaiting.contains(p.getName())){
                event.setCancelled(true);
                return;
            }
        }
    }
}
