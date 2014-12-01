package us.iluthi.soulofw0lf.ultimatesurvival.loaders;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.*;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.LocationUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

import java.util.List;
import java.util.Map;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 5:15 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class LoadGuilds {
    @SuppressWarnings("unchecked")
    public static void LoadGuild(String gName, String player){
        CustomPlayer cP = CustomPlayer.getCP(player);
        DBCollection table = UltimateSurvival.db.getCollection("guilds");
        BasicDBObject entry = new BasicDBObject();
        entry.put("name", gName);
        if (!table.find(entry).hasNext()){
            if (player == null){
                return;
            }
            if (Bukkit.getPlayer(player) == null){
                return;
            }
            Player p = Bukkit.getPlayer(player);
            cP.setGuildName("");
            cP.setGuildRank("");
            cP.setInGuild(false);
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have either been removed from your guild or it no longer exists!"));
            return;
        }
        BasicDBObject guildGet = new BasicDBObject();
        guildGet.put("name", gName);
        DBObject guildFile = table.findOne(guildGet);
        Guild g = new Guild(gName);
        g.setGuildFortifications((int)guildFile.get("Guild Fortification Points"));
        g.setHasStoreroom((boolean)guildFile.get("Has Storeroom"));
        g.setRankAccess((boolean)guildFile.get("Use Rank Access"));
        g.setMessageOfTheDay((boolean)guildFile.get("Has MOTD"));
        g.setHome((boolean)guildFile.get("Has Home"));
        g.setHasEntry((boolean)guildFile.get("Has Entry Message"));
        g.setHasExit((boolean)guildFile.get("Has Exit Message"));
        if (guildFile.get("Recruiting") != null){
            g.setRecruiting((boolean)guildFile.get("Recruiting"));
        }
        if (g.isRecruiting()){
            g.setRecruitMessage((String)guildFile.get("Recruit Message"));
        }
        if (g.isHasEntry()){
            g.setEntryMessage((String)guildFile.get("Entry Message"));
        }
        if (g.isHasExit()){
            g.setExitMessage((String)guildFile.get("Exit Message"));
        }
        if (g.isHome()){
            g.setHomeLoc(LocationUtil.stringToLoc((String)guildFile.get("Home Location")));
        }
        if (g.isMessageOfTheDay()){
            g.setMotd((String)guildFile.get("Message of The Day"));
        }
        if (g.isHasStoreroom()){
            g.setMaxX((int)guildFile.get("Storeroom Max X"));
            g.setMaxY((int)guildFile.get("Storeroom Max Y"));
            g.setMaxZ((int)guildFile.get("Storeroom Max Z"));
            g.setMinX((int)guildFile.get("Storeroom Min X"));
            g.setMinY((int)guildFile.get("Storeroom Min Y"));
            g.setMinZ((int)guildFile.get("Storeroom Min Z"));
        }
        g.setHq((boolean)guildFile.get("Has Headquarters"));
        if (g.hasHq()){
            HQ hq = new HQ(g.getName(),
            g.getEntryMessage(),
            g.getExitMessage(),
            (int)guildFile.get("HQ MaxX"),
            (int)guildFile.get("HQ MaxZ"),
            (int)guildFile.get("HQ MinX"),
            (int)guildFile.get("HQ MinZ"));
            g.setExpansions((int)guildFile.get("Expansions"));
            g.setHeadQuarters(hq);
            int minX = g.getHeadQuarters().getMinX();
            int minZ = g.getHeadQuarters().getMinZ();
            int maxX = g.getHeadQuarters().getMaxX();
            int maxZ = g.getHeadQuarters().getMaxZ();
            int i = 0;
            String world = Locations.spawnLoc.getWorld().getName();
            while (i < 255){
                int x = minX;
                while (x <= maxX){
                    int z = minZ;
                    while (z <= maxZ){
                        Location loc = new Location(Bukkit.getWorld(world), x, i, z);
                        Maps.guildsLocMap.put(loc, g.getName());
                        z++;
                    }
                    x++;
                }
                i++;
            }
        }
        if (guildFile.get("Guild Agressors") != null){
            g.setAggressors((List<String>)guildFile.get("Guild Agressors"));
            g.setWarning(true);
        }
        g.setOwner((String)guildFile.get("Guild Owner"));
        g.setPeaceful((boolean)guildFile.get("Peaceful"));

        if (guildFile.get("Protected Chests") != null){
            for (String key : (List<String>)guildFile.get("Protected Chests")){
                g.getProtectedChests().add(LocationUtil.stringToLoc(key));
            }
        }
        if (guildFile.get("War Guilds") != null){
            g.setWarGuilds((List<String>)guildFile.get("War Guilds"));
        }
        if (guildFile.get("Player Contributions") != null){
            Map<String, Integer> playerKills = (Map<String, Integer>)guildFile.get("Player Contributions");
            for (String key : playerKills.keySet()){
                g.getPlayerContrib().put(key, playerKills.get(key));
            }
        }
        if (guildFile.get("Recruitment List") != null){
            Map<String, String> playerKills = (Map<String, String>)guildFile.get("Recruitment List");
            for (String key : playerKills.keySet()){
                g.getApplicants().put(key, playerKills.get(key));
            }
        }
        g.setPlayers((List<String>)guildFile.get("Players"));
        Maps.allGuilds.put(g.getName(), g);
        List<BasicDBObject> ranksList = (List<BasicDBObject>)guildFile.get("Ranks");
        for (BasicDBObject rank : ranksList){
           GuildRanks r = new GuildRanks(rank.getString("Name"), g.getName());
            r.setTempRank(rank.getBoolean("Temporary"));
            r.setWeight(rank.getInt("Weight"));
            if (r.isTempRank()){
                r.setRankMinutes(rank.getInt("Temp Time"));
            }
            Map<String, Boolean> rankPerms = (Map<String, Boolean>)rank.get("Permission");
            for (String key : rankPerms.keySet()){
                r.getRankPermissions().put(RankPerms.valueOf(key), rankPerms.get(key));
            }
        }
        for (String key : g.getRanks().keySet()){
            GuildRanks rank = g.getRanks().get(key);
            if (rank.getName().equalsIgnoreCase((String)guildFile.get("Default Rank"))){
                g.setDefaultRank(rank);
                break;
            }
        }
    }
    public static void LoadWarZone(String gName){
        DBCollection table = UltimateSurvival.db.getCollection("War Zones");
        BasicDBObject entry = new BasicDBObject();
        entry.put("Name", gName);
        if (!table.find(entry).hasNext()){
            return;
        }
        BasicDBObject guildGet = new BasicDBObject();
        guildGet.put("Name", gName);
        DBObject guildFile = table.findOne(guildGet);
        new WarZone((String)guildFile.get("Name"),
                (String)guildFile.get("Owner"),
                (String)guildFile.get("Entry Message"),
                (String)guildFile.get("Exit Message"),
                (int)guildFile.get("MaxX"),
                (int)guildFile.get("MaxZ"),
                (int)guildFile.get("MinX"),
                (int)guildFile.get("MinZ"));
    }
}
