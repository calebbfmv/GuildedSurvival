package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.Tutorial.TutMainInvClick;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Party;
import us.iluthi.soulofw0lf.ultimatesurvival.party.Util;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 5:12 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class PlayerQuit implements Listener{
    public PlayerQuit(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @EventHandler
    public void playerQuit(PlayerQuitEvent event){
        Player p = event.getPlayer();
        for (String key : TutMainInvClick.hiddenPlayer.keySet()){
            if (TutMainInvClick.hiddenPlayer.get(key).contains(p.getName())){
                if (Bukkit.getPlayer(key) == null){
                    continue;
                }
                p.showPlayer(Bukkit.getPlayer(key));
                Bukkit.getPlayer(key).showPlayer(p);
                TutMainInvClick.hiddenPlayer.get(key).remove(p.getName());
            }
        }
        Party party;
        if((party = Util.getParty(p.getName())) != null)
            party.removePlayer(p.getName());
        if(Lists.pcList.contains(p.getName()))
            Lists.pcList.remove(p.getName());
        CustomPlayer cP = CustomPlayer.getCP(p.getName());

        if (cP.isInGuild()){
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            for (String name : g.getPlayers()){
                if (Bukkit.getPlayer(name) != null){
                    Bukkit.getPlayer(name).sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " has gone offline."));
                }
            }
        }
        if (!Lists.savePlayers.contains(p.getName())){
            Lists.savePlayers.add(p.getName());
        }
        event.setQuitMessage(null);

    }
    @EventHandler
    public void playerKick(PlayerKickEvent event){
        Player p = event.getPlayer();
        for (String key : TutMainInvClick.hiddenPlayer.keySet()){
            if (TutMainInvClick.hiddenPlayer.get(key).contains(p.getName())){
                if (Bukkit.getPlayer(key) == null){
                    continue;
                }
                p.showPlayer(Bukkit.getPlayer(key));
                Bukkit.getPlayer(key).showPlayer(p);
                TutMainInvClick.hiddenPlayer.get(key).remove(p.getName());
            }
        }
        Party party;

        if((party = Util.getParty(p.getName())) != null)
            party.removePlayer(p.getName());
        if(Lists.pcList.contains(p.getName()))
            Lists.pcList.remove(p.getName());
        CustomPlayer cP = CustomPlayer.getCP(p.getName());

        if (cP.isInGuild()){
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            for (String name : g.getPlayers()){
                if (Bukkit.getPlayer(name) != null){
                    Bukkit.getPlayer(name).sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " has gone offline."));
                }
            }
        }
        if (!Lists.savePlayers.contains(p.getName())){
            Lists.savePlayers.add(p.getName());
        }
        event.setLeaveMessage(null);
    }
}
