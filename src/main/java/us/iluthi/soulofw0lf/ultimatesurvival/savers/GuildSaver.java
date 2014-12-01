package us.iluthi.soulofw0lf.ultimatesurvival.savers;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.bukkit.Location;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.HQ;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.WarZone;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.LocationUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/27/13
 * Time: 3:02 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class GuildSaver {
    public static void saveGuild(Guild g){
        DBCollection table = UltimateSurvival.db.getCollection("guilds");
        BasicDBObject guildFile = new BasicDBObject().append("name", g.getName());
        BasicDBObject mainGuild = new BasicDBObject().append("name", g.getName());
        guildFile.put("Guild Fortification Points", g.getGuildFortifications());
        guildFile.put("Has Storeroom", g.isHasStoreroom());
        guildFile.put("Use Rank Access", g.isRankAccess());
        guildFile.put("Name", g.getName());
        guildFile.put("Has MOTD", g.isMessageOfTheDay());
        guildFile.put("Recruiting", g.isRecruiting());
        guildFile.put("Recruit Message", g.getRecruitMessage());
        if (g.isMessageOfTheDay()){
            guildFile.put("Message of The Day", g.getMotd());
        }
        if (g.isHasStoreroom()){
            guildFile.put("Storeroom Max X", g.getMaxX());
            guildFile.put("Storeroom Max Y", g.getMaxY());
            guildFile.put("Storeroom Max Z", g.getMaxZ());
            guildFile.put("Storeroom Min X", g.getMinX());
            guildFile.put("Storeroom Min Y", g.getMinY());
            guildFile.put("Storeroom Min Z", g.getMinZ());
        }
        guildFile.put("Has Headquarters", g.hasHq());
        if (g.hasHq()){
            HQ hq = g.getHeadQuarters();
            guildFile.put("HQ MaxX", hq.getMaxX());
            guildFile.put("HQ MaxZ", hq.getMaxZ());
            guildFile.put("HQ MinX", hq.getMinX());
            guildFile.put("HQ MinZ", hq.getMinZ());
            guildFile.put("Expansions", g.getExpansions());
        }
        if (!g.getAggressors().isEmpty()){
            guildFile.put("Guild Agressors", "");
            guildFile.put("Guild Agressors", g.getAggressors());
        } else {
            guildFile.put("Guild Agressors", null);
        }
        guildFile.put("Guild Owner", g.getOwner());
        guildFile.put("Peaceful", g.isPeaceful());
        if (!g.getProtectedChests().isEmpty()){
            List<String> locs = new ArrayList<>();
            for (Location loc : g.getProtectedChests()){
                locs.add(LocationUtil.locToString(loc));
            }
            guildFile.put("Protected Chests", locs);
        }
        if (!g.getWarGuilds().isEmpty()){
            guildFile.put("War Guilds", "");
            guildFile.put("War Guilds", g.getWarGuilds());
        } else {
            guildFile.put("War Guilds", null);
        }
        guildFile.put("Players", g.getPlayers());
        if (!g.getPlayerContrib().isEmpty()){
            guildFile.put("Player Contributions", g.getPlayerContrib());
        }
        if (!g.getApplicants().isEmpty()){
            guildFile.put("Recruitment List", g.getApplicants());
        }
        List<BasicDBObject> ranks = new ArrayList<>();
        for (String key : g.getRanks().keySet()){
            GuildRanks gR = g.getRanks().get(key);
            BasicDBObject rank = new BasicDBObject();
            rank.put("Name", gR.getName());
            rank.put("Temporary", gR.isTempRank());
            if (gR.isTempRank()){
                rank.put("Temp Time", gR.getRankMinutes());
            }
            rank.put("Weight", gR.getWeight());
            Map<String, Boolean> rankPerms = new HashMap<>();
            for (RankPerms perm : gR.getRankPermissions().keySet()){
                rankPerms.put(perm.name(), gR.getRankPermissions().get(perm));
            }
            rank.put("Permission", rankPerms);
            ranks.add(rank);
        }
        guildFile.put("Ranks", ranks);
        if (g.getDefaultRank() == null){
            GuildRanks gR = new GuildRanks("Default", g.getName());
            for (RankPerms rank : RankPerms.values()){
                gR.getRankPermissions().put(rank, false);
            }
            g.setDefaultRank(gR);
        } else if (g.getDefaultRank().getName() == null){
            g.getDefaultRank().setName("Default");
        }
        guildFile.put("Default Rank", g.getDefaultRank().getName());
        guildFile.put("Has Home", g.isHome());
        guildFile.put("Has Entry Message", g.isHasEntry());
        guildFile.put("Has Exit Message", g.isHasExit());
        if (g.isHasEntry()){
            guildFile.put("Entry Message", g.getEntryMessage());
        }
        if (g.isHasExit()){
            guildFile.put("Exit Message", g.getExitMessage());
        }
        if (g.isHome()){
            guildFile.put("Home Location", LocationUtil.locToString(g.getHomeLoc()));
        }
        if (!table.find(mainGuild).hasNext()){
            table.insert(guildFile);
        }
        table.update(mainGuild, guildFile);
    }
    public static void saveWarZone(WarZone g){
        DBCollection table = UltimateSurvival.db.getCollection("War Zones");
        BasicDBObject guildFile = new BasicDBObject().append("Name", g.getOwnerName());
        BasicDBObject mainGuild = new BasicDBObject().append("Name", g.getOwnerName());
        guildFile.put("Name", g.getOwnerName());
        guildFile.put("Owner", g.getOwner());
        guildFile.put("MaxX", g.getMaxX());
        guildFile.put("MaxZ", g.getMaxZ());
        guildFile.put("MinX", g.getMinX());
        guildFile.put("MinZ", g.getMinZ());
        guildFile.put("Entry Message", g.getEntryMessage());
        guildFile.put("Exit Message", g.getExitMessage());
        if (!table.find(mainGuild).hasNext()){
            table.insert(guildFile);
        }
        table.update(mainGuild, guildFile);
    }
}
