package us.iluthi.soulofw0lf.ultimatesurvival.customobjects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ... __ __ ________ .. / / ___ ________ ___ / |/ / ___/ . / _ \/ _ `/ __/ _ \/
 * _ \/ /|_/ / /__ ./_.__/\_,_/\__/\___/_//_/_/ /_/\___/ Created by: soulofw0lf
 * Date: 11/21/13 Time: 5:00 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class CustomPlayer {
    private boolean bandaging = false;
    private boolean inGuild = false;
    private boolean inDuel = false;
    private boolean autoLoot = true;
    private boolean duelsOn = true;
    private boolean partyOn = true;
    private boolean guildOn = true;
    private boolean tradeOn = true;
    private boolean inHub = true;
    private boolean dNd = false;
    private boolean acceptingTP = true;
    private boolean chatBlocked = false;
    private boolean prefix = false;
    private boolean suffix = false;
    private boolean pvp = true;
    private boolean inGuildLands = false;
    private boolean autoRoll = false;
    private boolean inWarZone = false;
    private boolean applying = false;
    private String applyGuild = "";
    private String playerName = "";
    private Map<Integer, String> auctionItems = new HashMap<>();


    private long lastChat = -1;

    private boolean canTrade = true;
    private String guildNear = "";
    private String setPrefix = "";
    private String setSuffix = "";
    private List<String> earnedAchievements = new ArrayList<>();
    private String partyName = "";
    private String duelPartner = "";
    private String guildName = "";
    private String guildRank = "";
    private String warZone = "";

    private int minutes = 0;
    private int hours = 0;
    private int days = 0;

    private long timePlayed = 0;
    private long timeLoggedIn = 0;
    private long dateJoined = 0;

    private double tradeID = -1;
    private String tradeRequestFrom = "";

    private Map<String, String> channelColors = new HashMap<>();
    private List<String> ignoredPlayers = new ArrayList<>();
    private String activeChannel = "";
    private Map<String, Integer> mobKills = new HashMap<>();
    private Map<String, Integer> playerKills = new HashMap<>();
    private Map<String, Integer> blockBreaks = new HashMap<>();
    private Map<String, Integer> blockPlacement = new HashMap<>();
    private Map<String, Integer> itemConsumed = new HashMap<>();
    private int goldPoints = 0;
    private List<String> inactiveChannels = new ArrayList<>();
    private List<Warp> warpList = new ArrayList<>();
    private Location duelLocation;

	private CustomPlayer(String name) {
		Maps.customPlayers.put(name, this);
        playerName = name;
	}

	public static CustomPlayer getCP(String name) {
		if (!Maps.customPlayers.containsKey(name)) {
			return new CustomPlayer(name);
		}
		return Maps.customPlayers.get(name);
	}

	public List<String> getFriends() {
		return friends;
	}

	public List<String> getFriendsList() {
		return friends;
	}

	public void setFriends(List<String> friendsString) {
		friends = friendsString;
	}

	private List<String> friends = new ArrayList<>();

	public String getLastFriendPM() {
		return lastFriendPM;
	}

	public void setLastFriendPM(String lastFriendPM) {
		this.lastFriendPM = lastFriendPM;
	}

	private String lastFriendPM = "";

	public void addFriend(Player p, String friendName) {
		int maxFriends = 10;
		if (p.hasPermission("triple.coins"))
			maxFriends = 44;
		else if (p.hasPermission("double.coins"))
			maxFriends = 20;
		if (Bukkit.getPlayer(friendName) == null) {
			p.sendMessage(ChatUtil.color(Strings.friendStub
					+ "&4That player appears to be offline!"));
			return;
		}
		if (friends.size() >= maxFriends) {
			p.sendMessage(ChatUtil.color(Strings.friendStub
					+ " &4Your friends list is full."));
			p.sendMessage(ChatUtil.color(Strings.friendStub
					+ " &4VIP gets 20 max friends. VIP+ gets 44."));
			return;
		}

		if (friends.contains(friendName)) {
			p.sendMessage(ChatUtil.color(Strings.friendStub + " &4"
					+ friendName + " is already on your friends list."));
			return;
		}
		friends.add(friendName);
		p.sendMessage(ChatUtil.color(Strings.friendStub + " &4Added "
				+ friendName + " to friends."));
	}

	public void delFriend(String friendName) {
		friends.remove(friendName);
	}

	public long getLastChat() {
		return lastChat;
	}

	public long setLastChat(long lastChat) {
		this.lastChat = lastChat;
		return this.lastChat;
	}

    public Player getPlayer() {
        return Bukkit.getPlayerExact(playerName);
    }

	public String getTradeRequestFrom() {
		return tradeRequestFrom;
	}

	public void setTradeRequestFrom(String tradeRequestFrom) {
		this.tradeRequestFrom = tradeRequestFrom;
	}

	public boolean isInGuild() {
		return inGuild;
	}

	public void setInGuild(boolean inGuild) {
		this.inGuild = inGuild;
	}

	public boolean isInDuel() {
		return inDuel;
	}

	public void setInDuel(boolean inDuel) {
		this.inDuel = inDuel;
	}

	public String getDuelPartner() {
		return duelPartner;
	}

	public void setDuelPartner(String duelPartner) {
		this.duelPartner = duelPartner;
	}

	public String getGuildName() {
		return guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

	public String getGuildRank() {
		return guildRank;
	}

	public void setGuildRank(String guildRank) {
		this.guildRank = guildRank;
	}

	public Map<String, String> getChannelColors() {
		return channelColors;
	}

	public void addChannelColor(String channel, String color) {
		channelColors.put(channel, color);
	}

	public double getTradeID() {
		return tradeID;
	}

	public boolean isInTrade() {
		return getTradeID() != -1;
	}

	public void setTradeID(double tradeID) {
		this.tradeID = tradeID;
	}

	public boolean canTrade() {
		return canTrade;
	}

	public void setCanTrade(boolean canTrade) {
		this.canTrade = canTrade;
	}

	public long getTimePlayed() {
		return timePlayed;
	}

	public void setTimePlayed(long timePlayed) {
		this.timePlayed = timePlayed;
	}

	public long getTimeLoggedIn() {
		return timeLoggedIn;
	}

	public void setTimeLoggedIn(long timeLoggedIn) {
		this.timeLoggedIn = timeLoggedIn;
	}

	public long getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(long dateJoined) {
		this.dateJoined = dateJoined;
	}

	public void setChannelColors(Map<String, String> channelColors) {
		this.channelColors = channelColors;
	}

	public String getActiveChannel() {
		return activeChannel;
	}

	public void setActiveChannel(String activeChannel) {
		this.activeChannel = activeChannel;
	}

	public Map<String, Integer> getPlayerKills() {
		return playerKills;
	}

	public void setPlayerKills(Map<String, Integer> playerKills) {
		this.playerKills = playerKills;
	}

	public int getGoldPoints() {
		return goldPoints;
	}

	public void setGoldPoints(int goldPoints) {
		this.goldPoints = goldPoints;
	}

	public Map<String, Integer> getBlockBreaks() {
		return blockBreaks;
	}

	public void setBlockBreaks(Map<String, Integer> blockBreaks) {
		this.blockBreaks = blockBreaks;
	}

	public Map<String, Integer> getBlockPlacement() {
		return blockPlacement;
	}

	public void setBlockPlacement(Map<String, Integer> blockPlacement) {
		this.blockPlacement = blockPlacement;
	}

	public Map<String, Integer> getItemConsumed() {
		return itemConsumed;
	}

	public void setItemConsumed(Map<String, Integer> itemConsumed) {
		this.itemConsumed = itemConsumed;
	}

	public Map<String, Integer> getMobKills() {
		return mobKills;
	}

	public void setMobKills(Map<String, Integer> mobKills) {
		this.mobKills = mobKills;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public boolean isGuildOn() {
		return guildOn;
	}

	public void setGuildOn(boolean guildOn) {
		this.guildOn = guildOn;
	}

	public boolean isPartyOn() {
		return partyOn;
	}

	public void setPartyOn(boolean partyOn) {
		this.partyOn = partyOn;
	}

	public boolean isDuelsOn() {
		return duelsOn;
	}

	public void setDuelsOn(boolean duelsOn) {
		this.duelsOn = duelsOn;
	}

	public boolean isTradeOn() {
		return tradeOn;
	}

	public void setTradeOn(boolean tradeOn) {
		this.tradeOn = tradeOn;
	}

	public List<String> getInactiveChannels() {
		return inactiveChannels;
	}

	public void setInactiveChannels(List<String> inactiveChannels) {
		this.inactiveChannels = inactiveChannels;
	}

	public List<Warp> getWarpList() {
		return warpList;
	}

	public void setWarpList(List<Warp> warpList) {
		this.warpList = warpList;
	}

	public Location getDuelLocation() {
		return duelLocation;
	}

	public void setDuelLocation(Location duelLocation) {
		this.duelLocation = duelLocation;
	}

	public boolean hasPrefix() {
		return prefix;
	}

	public void setHasPrefix(boolean prefix) {
		this.prefix = prefix;
	}

	public boolean hasSuffix() {
		return suffix;
	}

	public void setHasSuffix(boolean suffix) {
		this.suffix = suffix;
	}

	public String getSuffix() {
		return setSuffix;
	}

	public void setSuffix(String setSuffix) {
		this.setSuffix = setSuffix;
	}

	public String getPrefix() {
		return setPrefix;
	}

	public void setPrefix(String setPrefix) {
		this.setPrefix = setPrefix;
	}

	public List<String> getEarnedAchievements() {
		return earnedAchievements;
	}

	public void setEarnedAchievements(List<String> earnedAchievements) {
		this.earnedAchievements = earnedAchievements;
	}

	public boolean isPvp() {
		return pvp;
	}

	public void setPvp(boolean pvp) {
		this.pvp = pvp;
	}

	public boolean isAutoLoot() {
		return autoLoot;
	}

	public void setAutoLoot(boolean autoLoot) {
		this.autoLoot = autoLoot;
	}

	public boolean isAcceptingTP() {
		return acceptingTP;
	}

	public void setAcceptingTP(boolean acceptingTP) {
		this.acceptingTP = acceptingTP;
	}

	public boolean isdNd() {
		return dNd;
	}

	public void setdNd(boolean dNd) {
		this.dNd = dNd;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public boolean isInHub() {
		return inHub;
	}

	public void setInHub(boolean inHub) {
		this.inHub = inHub;
	}

	public boolean isInGuildLands() {
		return inGuildLands;
	}

	public void setInGuildLands(boolean inGuildLands) {
		this.inGuildLands = inGuildLands;
	}

	public String getGuildNear() {
		return guildNear;
	}

	public void setGuildNear(String guildNear) {
		this.guildNear = guildNear;
	}

	public boolean isBandaging() {
		return bandaging;
	}

	public void setBandaging(boolean bandaging) {
		this.bandaging = bandaging;
	}

	public List<String> getIgnoredPlayers() {
		return ignoredPlayers;
	}

	public void setIgnoredPlayers(List<String> ignoredPlayers) {
		this.ignoredPlayers = ignoredPlayers;
	}

	public boolean isAutoRoll() {
		return autoRoll;
	}

	public void setAutoRoll(boolean autoRoll) {
		this.autoRoll = autoRoll;
	}

	public boolean isInWarZone() {
		return inWarZone;
	}

	public void setInWarZone(boolean inWarZone) {
		this.inWarZone = inWarZone;
	}

	public String getWarZone() {
		return warZone;
	}

	public void setWarZone(String warZone) {
		this.warZone = warZone;
	}

	public Map<Integer, String> getAuctionItems() {
		return auctionItems;
	}

	public void setAuctionItems(Map<Integer, String> auctionItems) {
		this.auctionItems = auctionItems;
	}

	public boolean isChatBlocked() {
		return chatBlocked;
	}

	public void setChatBlocked(boolean chatBlocked) {
		this.chatBlocked = chatBlocked;
	}

	public String getApplyGuild() {
		return applyGuild;
	}

	public void setApplyGuild(String applyGuild) {
		this.applyGuild = applyGuild;
	}

	public boolean isApplying() {
		return applying;
	}

	public void setApplying(boolean applying) {
		this.applying = applying;
	}

    public boolean canBreak(Block block) {
        Player p = getPlayer();
        if (isInHub() && !p.hasPermission("guild.bypass")) return false;
        if (isInDuel()) return false;

        // TODO War zones should be mapped by owner name
        for (WarZone war : Lists.warZones){
            if (!war.containsBlock(block)) continue;
            else if (!playerName.equalsIgnoreCase(war.getOwner())) return false;
        }
        if (Maps.guildsLocMap.containsKey(block.getLocation())){
            Guild guild;
            if ((guild = Maps.allGuilds.get(Maps.guildsLocMap.get(block.getLocation()))).getName().equalsIgnoreCase(guildName)){
                if(!guild.getRanks().get(guildRank).getRankPermissions().get(RankPerms.BREAK)) return false;

                // Store room check
                if(guild.isHasStoreroom()) {
                    if(guild.containsBlock(block)) {
                        if(!guild.getRanks().get(guildRank).getRankPermissions().get(RankPerms.ACCESS_STOREROOM)) {
                            return false;
                        }
                    }
                }
            } else {
                Guild playerGuild = Maps.allGuilds.get(guildName);
                if (!playerGuild.getWarGuilds().contains(guild.getName()) && !playerGuild.getAggressors().contains(guild.getName())){
                    return false;
                }
            }
        }

        return true;
    }


}
