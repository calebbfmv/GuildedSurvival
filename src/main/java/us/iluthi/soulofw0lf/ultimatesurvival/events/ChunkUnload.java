package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/30/13
 * Time: 6:43 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ChunkUnload implements Listener {
    public ChunkUnload(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @EventHandler
    public void chunkUnload(ChunkUnloadEvent event){
        Chunk chunk = event.getChunk();
        if (Locations.spawnLoc != null){
            if (chunk.getWorld().equals(Locations.spawnLoc.getWorld())){
                if (chunk.getBlock(0,0,0).getLocation().distance(Locations.spawnLoc) <= 150){
                    event.setCancelled(true);
                }
            }
        }
    }
}
