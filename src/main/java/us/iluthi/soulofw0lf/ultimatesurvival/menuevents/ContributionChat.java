package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.CoinChange;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Booleans;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 12:28 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ContributionChat implements Listener {
    public ContributionChat(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    @EventHandler
    public void coinTalk(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (!Lists.addingPoints.contains(p.getName())){
            return;
        }
        event.setCancelled(true);
        String msg = event.getMessage();
        if (!cP.isInGuild()){
            Lists.addingPoints.remove(p.getName());
            cP.setChatBlocked(false);
            p.sendMessage(ChatUtil.color(Strings.guildStub + "You must be in a guild to add contribution points."));
            return;
        }
        if (Booleans.isInteger(msg)){
            int coins = Integer.parseInt(msg);
            if (coins <= 0){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You must enter a valid number of coins to contribute."));
                return;
            }
            if (coins > cP.getGoldPoints()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You do not have enough coins for this contribution."));
                return;
            }
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            g.setGuildFortifications(g.getGuildFortifications() + coins);
            CoinChange.add(p, -coins, true);
            p.sendMessage(ChatUtil.color(Strings.guildStub + " you have added " + coins  + " Fortification points to your guild."));
            int i = 0;
            if (g.getPlayerContrib().containsKey(p.getName())){
                i = g.getPlayerContrib().get(p.getName()) + coins;
                g.getPlayerContrib().remove(p.getName());
            } else {
                i = coins;
            }
            g.getPlayerContrib().put(p.getName(), i);
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have contributed a total of " + i + " fortification points to your guild!"));
            Lists.addingPoints.remove(p.getName());
            cP.setChatBlocked(false);
            return;
        } else {
            p.sendMessage(ChatUtil.color(Strings.guildStub + " Please enter a valid number!"));
            return;
        }

    }
}
