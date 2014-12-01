package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 12:21 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class DeclareWar implements Listener {
    public DeclareWar(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    @EventHandler
    public void declareWar(PlayerCommandPreprocessEvent event){
        Player p = event.getPlayer();
        String[] args = event.getMessage().split(" ");
        String cmd = args[0].replace("/", "");
        if (!cmd.equalsIgnoreCase("war") && !cmd.equalsIgnoreCase("attack")){
            return;
        }
        event.setCancelled(true);
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (!cP.isInGuild()){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You must be in a guild to declare war with another guild."));
            return;
        }
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        GuildRanks gR = g.getRanks().get(cP.getGuildRank());
        if (!gR.getRankPermissions().get(RankPerms.SET_WAR)){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You do not have permission to declare war for your guild!"));
            return;
        }
        if (args.length < 2){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " Please use /war {guild name} to declare war."));
            return;
        }
        StringBuilder sB = new StringBuilder();
        int x = 1;
        while (x < args.length){
            if ( x == (args.length - 1)){
                sB.append(args[x]);
            } else {
                sB.append(args[x]).append(" ");
            }
            x++;
        }
        String name = sB.toString();
        System.out.print(name);
        if (name.equalsIgnoreCase(cP.getGuildName())){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You cannot declare war against your own guild!"));
            return;
        }
        if (!Maps.allGuilds.keySet().contains(name)){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " That guild is either not online or does not exist!"));
            return;
        }
        Guild war = Maps.allGuilds.get(name);
        if (Maps.noAttack.containsKey(g.getName())){
            if (Maps.noAttack.get(g.getName()).contains(war.getName())){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You may not declare war against this guild within the same 24 hour period that they bought peace from you."));
                return;
            }
        }
        if (!gR.getRankPermissions().get(RankPerms.USE_POINTS)){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " Your rank does not have the right to spend guild contribution points!"));
            return;
        }
        int i = (int)(war.getGuildFortifications() * 0.1);
        if (g.getGuildFortifications() < i){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild cannot currently afford to declare war against " + war.getName() + "."));
            return;
        }
        if (g.isPeaceful() || war.isPeaceful()){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " Neither guild can be in peaceful mode for a war to occur."));
            return;
        }
        g.setGuildFortifications(g.getGuildFortifications() - i);
        g.getWarGuilds().add(war.getName());
        war.getAggressors().add(g.getName());
        for (String player : g.getPlayers()){
            if (Bukkit.getPlayer(player) != null){
                Bukkit.getPlayer(player).sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " has declared war against " + war.getName() + "."));
            }
        }
        for (String player : war.getPlayers()){
            if (Bukkit.getPlayer(player) != null){
                Bukkit.getPlayer(player).sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " from "+g.getName()+" has declared war against you!"));
            }
        }
        war.setWarning(true);
    }
}
