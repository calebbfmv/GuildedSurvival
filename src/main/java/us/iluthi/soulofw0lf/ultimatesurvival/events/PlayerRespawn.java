package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/26/13
 * Time: 2:24 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class PlayerRespawn implements Listener {
    public PlayerRespawn(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @EventHandler (priority = EventPriority.HIGHEST)
    public void playerREspawn(PlayerRespawnEvent event){
        Player p = event.getPlayer();
        if (DeathEvents.playerLevels.containsKey(p.getName())){
            p.setLevel(DeathEvents.playerLevels.get(p.getName()));
            DeathEvents.playerLevels.remove(p.getName());
        }
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (cP.isInGuild()){
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            if (g == null){
                p.sendMessage(ChatUtil.color(Strings.guildStub + "Your guild seems to have been abandoned!"));
                cP.setInGuild(false);
                event.setRespawnLocation(Locations.spawnLoc);
                return;
            }
            if (g.hasHq() && g.isHome() && p.hasPermission("double.coins")){
                event.setRespawnLocation(g.getHomeLoc());
                return;
            }
        }
        event.setRespawnLocation(Locations.spawnLoc);
    }
}
