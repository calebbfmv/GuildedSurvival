package us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/22/13
 * Time: 1:19 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class RankChange {

    public static void adjust(String newRank, Player p){
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        GuildRanks gR = g.getRanks().get(newRank);
        if (gR.isTempRank()){
            final String name = p.getName();
            Maps.tempRanks.put(p.getName(), cP.getGuildRank());
            final int i = 20 * 60 * gR.getRankMinutes();
            new BukkitRunnable(){
                @Override
                public void run(){
                    if (Bukkit.getPlayer(name) == null){
                        cancel();
                        return;
                    }
                    adjust(Maps.tempRanks.get(name), Bukkit.getPlayer(name));
                }
            }.runTaskLater(UltimateSurvival.getInstance(), i);
        }
        cP.setGuildRank(newRank);
        p.sendMessage(ChatUtil.color(Strings.guildStub + "Your guild rank has been set to &6" + newRank));
    }
    public static Boolean promote(String oldRank, Player p, String promoterRank){
        boolean found = false;
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        GuildRanks gR = g.getRanks().get(oldRank);
        int i = 100;
        String newRank = "";
        for (String rank : g.getRanks().keySet()){
            GuildRanks gNew = g.getRanks().get(rank);
            if (gNew.getWeight() > gR.getWeight() && gNew.getWeight() < i){
                newRank = gNew.getName();
                i = gNew.getWeight();
                found = true;
            }
        }
        if (found){
            if (promoterRank.equalsIgnoreCase(newRank)){
                return false;
            }
            cP.setGuildRank(newRank);
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have been promoted to " + newRank + "!!!"));
            for (String s : g.getPlayers()){
                if (Bukkit.getPlayer(s) != null){
                    Bukkit.getPlayer(s).sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " is now a " + newRank + "!!!"));
                }
            }
        }
        return found;
    }
    public static Boolean demote(String oldRank, Player p){
        boolean found = false;
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        GuildRanks gR = g.getRanks().get(oldRank);
        int i = 0;
        String newRank = "";
        for (String rank : g.getRanks().keySet()){
            GuildRanks gNew = g.getRanks().get(rank);
            if (gNew.getWeight() < gR.getWeight() && gNew.getWeight() > i){
                newRank = gNew.getName();
                i = gNew.getWeight();
                found = true;
            }
        }
        if (found){
            cP.setGuildRank(newRank);
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have been demoted to " + newRank + "."));
            for (String s : g.getPlayers()){
                if (Bukkit.getPlayer(s) != null){
                    Bukkit.getPlayer(s).sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " has been demoted to " + newRank + "."));
                }
            }
        }
        return found;
    }
}
