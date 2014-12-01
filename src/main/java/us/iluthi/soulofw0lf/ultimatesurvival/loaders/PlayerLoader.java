package us.iluthi.soulofw0lf.ultimatesurvival.loaders;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.auctionhouse.AuctionManager;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.*;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.Category;
import us.iluthi.soulofw0lf.ultimatesurvival.events.ChatCommands;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.LocationUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 6:48 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class PlayerLoader {
    @SuppressWarnings("unchecked")
    public static CustomPlayer loadSettings(Player p){
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        DBCollection table = UltimateSurvival.db.getCollection("players");
        BasicDBObject entry = new BasicDBObject();
        Object tmpLoad;
        entry.put("name", p.getName());
        if (!table.find(entry).hasNext()){
            p.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET, 1));
            p.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS, 1));
            p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
            p.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS, 1));
            p.getInventory().addItem(new ItemStack(Material.WOOD_SWORD, 1));
            p.getInventory().addItem(new ItemStack(Material.WOOD_AXE, 1));
            p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 12));
             BasicDBObject newPlayer = new BasicDBObject("name", p.getName())
            .append("Has Guild", false)
            .append("Active Channel", "Local")
            .append("Gold Points", 0)
            .append("Accept Duels", true)
            .append("Accept Trades", true)
            .append("Accept Parties", true)
            .append("Accept Guilds", true)
            .append("Accept TP", true)
            .append("Do Not Disturb", false)
            .append("Auto Loot", true)
            .append("Auto Roll", true)
            .append("Has Prefix", false)
            .append("Has Suffix", false)
            .append("dateJoined", System.currentTimeMillis())
            .append("timePlayed", (long)0)
            .append("PvP", false);
            p.teleport(Locations.spawnLoc);
            Map<String, String> colors = new HashMap<>();
            for (String channel : Lists.chatChannels){
                colors.put(channel, "&f");
            }
            newPlayer.put("Chat Channels", colors);
            table.insert(newPlayer);

        }
        BasicDBObject playerGet = new BasicDBObject();
        playerGet.put("name", p.getName());
        DBObject playerFile = table.findOne(playerGet);

        if ((boolean)playerFile.get("Has Guild")){
            cP.setGuildName((String)playerFile.get("Guild Name"));
            cP.setGuildRank((String)playerFile.get("Guild Rank"));
            cP.setInGuild(true);
            if (Maps.allGuilds.get(cP.getGuildName()) == null){
                p.sendMessage(ChatUtil.color(Strings.guildStub + "Your guild seems to have been abandoned!"));
                cP.setInGuild(false);
            }
        }
        cP.setDuelsOn((boolean)playerFile.get("Accept Duels"));
        cP.setTradeOn((boolean) playerFile.get("Accept Trades"));
        cP.setPartyOn((boolean) playerFile.get("Accept Parties"));
        cP.setGuildOn((boolean) playerFile.get("Accept Guilds"));
        cP.setAcceptingTP((boolean) playerFile.get("Accept TP"));
        cP.setTimePlayed((long) playerFile.get("timePlayed"));
        cP.setDateJoined((long) playerFile.get("dateJoined"));
        cP.setdNd((boolean)playerFile.get("Do Not Disturb"));
        if (playerFile.get("Applying") != null){
            cP.setApplying((boolean)playerFile.get("Applying"));
        }
        if (cP.isApplying()){
            cP.setApplyGuild((String)playerFile.get("Applying Guild"));
        }
        if (cP.isdNd()){
            ChatCommands.dNdMap.put(p.getName(), (String)playerFile.get("Do Not Disturb Message"));
        }
        cP.setActiveChannel((String)playerFile.get("Active Channel"));
        cP.setAutoLoot((boolean)playerFile.get("Auto Loot"));
        cP.setAutoRoll((boolean)playerFile.get("Auto Roll"));
        Map<String, String> chats = (Map<String, String>)playerFile.get("Chat Channels");
        for (String key : chats.keySet()){
            cP.addChannelColor(key, chats.get(key));
        }
        if ((tmpLoad=playerFile.get("Monster Kills")) != null){
            Map<String, Integer> mobKills = (Map<String, Integer>)tmpLoad;
            for (String key : mobKills.keySet()){
                cP.getMobKills().put(key, mobKills.get(key));
            }
        }
        if ((tmpLoad=playerFile.get("Player Kills")) != null){
            Map<String, Integer> playerKills = (Map<String, Integer>)tmpLoad;
            for (String key : playerKills.keySet()){
                cP.getPlayerKills().put(key, playerKills.get(key));
            }
        }
        if ((tmpLoad=playerFile.get("Blocks Broken")) != null){
            Map<String, Integer> mobKills = (Map<String, Integer>)tmpLoad;
            for (String key : mobKills.keySet()){
                cP.getBlockBreaks().put(key, mobKills.get(key));
            }
        }
        if ((tmpLoad=playerFile.get("Blocks Placed")) != null){
            Map<String, Integer> mobKills = (Map<String, Integer>)tmpLoad;
            for (String key : mobKills.keySet()){
                cP.getBlockPlacement().put(key, mobKills.get(key));
            }
        }
        if ((tmpLoad=playerFile.get("Blocks Used")) != null){
            Map<String, Integer> mobKills = (Map<String, Integer>)tmpLoad;
            for (String key : mobKills.keySet()){
                cP.getItemConsumed().put(key, mobKills.get(key));
            }
        }
        if ((tmpLoad=playerFile.get("Warps")) != null){
            Map<String, String> warpMap = (Map<String, String>)tmpLoad;
            for (String key : warpMap.keySet())
                new Warp(key, LocationUtil.stringToLoc(warpMap.get(key)), cP);
        }
        if ((tmpLoad=playerFile.get("Inactive Chats")) != null){
            cP.setInactiveChannels((List<String>)tmpLoad);
        }
        if ((tmpLoad=playerFile.get("Auction Items")) != null){
            Map<Integer, String> aIMap = (Map<Integer, String>)tmpLoad;
            for (Integer key : aIMap.keySet()){
                cP.getAuctionItems().put(key, aIMap.get(key));
            }
        }
        if ((tmpLoad=playerFile.get("Friends")) != null){
            cP.setFriends((List<String>)tmpLoad);
        }
        for (int key : cP.getAuctionItems().keySet()){
            AuctionCategory aC = Maps.categoryMap.get(Category.fromName(cP.getAuctionItems().get(key)));
            AuctionItem aI = aC.getCatItems().get(key);
            if (System.currentTimeMillis() - aI.getTime() > 86400000 || aI.isSold()){
                AuctionManager.removeItem(aI, p, true);
            }
        }
        cP.setGoldPoints((int)playerFile.get("Gold Points"));
        cP.setHasPrefix((boolean)playerFile.get("Has Prefix"));
        cP.setHasSuffix((boolean)playerFile.get("Has Suffix"));
        cP.setPvp((boolean)playerFile.get("PvP"));
        if (cP.isInGuild()){
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            if (!g.isPeaceful()){
                cP.setPvp(true);
            }
            if (!g.getRanks().containsKey(cP.getGuildRank())){
                cP.setGuildRank(g.getDefaultRank().getName());
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild rank was deleted. You are now set as the default rank for your guild."));
            }
        }
        if (cP.hasPrefix()){
            cP.setPrefix((String)playerFile.get("Active Prefix"));
        }
        if (cP.hasSuffix()){
            cP.setSuffix((String)playerFile.get("Active Suffix"));
        }
        if ((tmpLoad=playerFile.get("Earned Achievements")) != null){
            cP.setEarnedAchievements((List<String>)tmpLoad);
        }
        if ((tmpLoad=playerFile.get("Ignored Players")) != null){
            cP.setIgnoredPlayers((List<String>)tmpLoad);
        }
        return cP;
    }
}
