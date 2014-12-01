package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 5:13 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ItemConsume implements Listener{
    public ItemConsume(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @EventHandler
    public void playerEat(PlayerItemConsumeEvent event){
        Player p = event.getPlayer();
        if (p.getWorld().getName().equals(Locations.spawnLoc.getWorld().getName()) && p.getLocation().distance(Locations.spawnLoc) <= 150){
            event.setCancelled(true);
        }
    }
}
