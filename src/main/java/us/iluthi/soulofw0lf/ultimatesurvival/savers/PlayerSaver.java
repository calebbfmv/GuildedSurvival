package us.iluthi.soulofw0lf.ultimatesurvival.savers;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Warp;
import us.iluthi.soulofw0lf.ultimatesurvival.events.ChatCommands;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.LocationUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 10:25 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class PlayerSaver {
    public static void saveSettings(String name){
        CustomPlayer cP = CustomPlayer.getCP(name);
        DBCollection table = UltimateSurvival.db.getCollection("players");
        BasicDBObject playerFile = new BasicDBObject().append("name", name);
        BasicDBObject mainPlayer = new BasicDBObject().append("name", name);
        if (cP.isInGuild()){
            playerFile.put("Has Guild", true);
            playerFile.put("Guild Name", cP.getGuildName());
            playerFile.put("Guild Rank", cP.getGuildRank());
        } else {
            playerFile.put("Has Guild", false);
        }

        playerFile.put("Accept Duels", cP.isDuelsOn());
        playerFile.put("Accept Trades", cP.isTradeOn());
        playerFile.put("Accept Parties", cP.isPartyOn());
        playerFile.put("Accept TP", cP.isAcceptingTP());
        playerFile.put("Do Not Disturb", cP.isdNd());
        playerFile.put("Applying", cP.isApplying());
        if (cP.isApplying()){
            playerFile.put("Applying Guild", cP.getApplyGuild());
        }
        if (cP.isdNd()){
            playerFile.put("Do Not Disturb Message", ChatCommands.dNdMap.get(name));
            ChatCommands.dNdMap.remove(name);
        }
        playerFile.put("Accept Guilds", cP.isGuildOn());
        playerFile.put("Active Channel", cP.getActiveChannel());
        playerFile.put("Auto Loot", cP.isAutoLoot());
        playerFile.put("Auto Roll", cP.isAutoRoll());
        playerFile.put("timePlayed", cP.getTimePlayed() + (System.currentTimeMillis() - cP.getTimeLoggedIn()));
        playerFile.put("dateJoined", cP.getDateJoined());
        playerFile.put("Chat Channels", cP.getChannelColors());
        if (!cP.getPlayerKills().isEmpty()){
            playerFile.put("Player Kills", cP.getPlayerKills());
        }
        if (!cP.getAuctionItems().isEmpty()){
            playerFile.put("Auction Items", cP.getAuctionItems());
        }
        if (!cP.getFriends().isEmpty()){
            playerFile.put("Friends", cP.getFriends());
        }
        if (!cP.getMobKills().isEmpty()){
            playerFile.put("Monster Kills", cP.getMobKills());
        }
        if (!cP.getBlockBreaks().isEmpty()){
            playerFile.put("Block Breaks", cP.getBlockBreaks());
        }
        if (!cP.getBlockPlacement().isEmpty()){
                playerFile.put("Blocks Placed", cP.getBlockPlacement());
        }
        if (!cP.getItemConsumed().isEmpty()){
            playerFile.put("Blocks Used", cP.getItemConsumed());
        }
        playerFile.put("Gold Points", cP.getGoldPoints());
        playerFile.put("Has Prefix", cP.hasPrefix());
        playerFile.put("Has Suffix", cP.hasSuffix());
        playerFile.put("PvP", cP.isPvp());
        if (cP.hasPrefix()){
            playerFile.put("Active Prefix", cP.getPrefix());
        } else {
            playerFile.put("Active Prefix", "");
        }
        if (cP.hasSuffix()){
            playerFile.put("Active Suffix", cP.getSuffix());
        } else {
            playerFile.put("Active Suffix", "");
        }
        if (!cP.getEarnedAchievements().isEmpty()){
            playerFile.put("Earned Achievements", cP.getEarnedAchievements());
        }
        if (!cP.getIgnoredPlayers().isEmpty()){
            playerFile.put("Ignored Players", cP.getIgnoredPlayers());
        }
        if (!cP.getWarpList().isEmpty()){
            Map<String, String> warpString = new HashMap<>();
            for (Warp warp : cP.getWarpList()){
                warpString.put(warp.getName(), LocationUtil.locToString(warp.getLoc()));
            }
            playerFile.put("Warps", warpString);
        }
        if (!cP.getInactiveChannels().isEmpty()){
            playerFile.put("Inactive Chats", cP.getInactiveChannels());
        }
        table.update(mainPlayer, playerFile);
        Maps.customPlayers.remove(name);
        Lists.savePlayers.remove(name);
    }
}
