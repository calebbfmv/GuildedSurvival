package us.iluthi.soulofw0lf.ultimatesurvival.customobjects;

import org.bukkit.Location;
import org.bukkit.block.Block;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

import java.util.*;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 5:00 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Guild {
    private String name = "";
    private Map<String, GuildRanks> ranks = new HashMap<>();
    private String owner = "";
    private String motd = "";
    private boolean messageOfTheDay = false;
    private boolean recruiting = true;
    private String recruitMessage = "We are recruiting.";
    private Map<String, String> applicants = new HashMap<>();
    private HQ headQuarters;
    private List<String> players = new ArrayList<>();
    private List<String> warGuilds = new ArrayList<>();
    private List<String> aggressors = new ArrayList<>();
    private Map<String, Integer> playerContrib = new HashMap<>();
    private int guildFortifications = 0;
    private boolean peaceful = false;
    private boolean warning = false;
    private GuildRanks defaultRank;
    private boolean hq = false;
    private boolean rankAccess = false;
    private boolean home = false;
    private boolean hasEntry = false;
    private boolean hasExit = false;
    private String entryMessage = "";
    private String exitMessage = "";
    private Location homeLoc;
    private int minX = 0;
    private int minY = 0;
    private int minZ = 0;
    private int maxX = 0;
    private int maxY = 0;
    private int maxZ = 0;
    private int expansions = 1;
    private boolean hasStoreroom = false;
    private Set<Location> protectedChests = new HashSet<>();

    public Guild(String name, String owner, String rank){
        this.name = name;
        this.owner = owner;
        this.players.add(owner);
        this.peaceful = true;
        Maps.allGuilds.put(name, this);
        GuildRanks gR = new GuildRanks(rank, name);
        for (RankPerms rankPerm : RankPerms.values()){
            gR.getRankPermissions().put(rankPerm, true);
        }
    }
    public Guild(){

    }
    public Guild(String name){
        this.name = name;
        Maps.allGuilds.put(name, this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public List<String> getWarGuilds() {
        return warGuilds;
    }

    public void setWarGuilds(List<String> warGuilds) {
        this.warGuilds = warGuilds;
    }

    public int getGuildFortifications() {
        return guildFortifications;
    }

    public void setGuildFortifications(int guildFortifications) {
        this.guildFortifications = guildFortifications;
    }

    public boolean isPeaceful() {
        return peaceful;
    }

    public void setPeaceful(boolean peaceful) {
        this.peaceful = peaceful;
    }

    public boolean isWarning() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
    }

    public boolean hasHq() {
        return hq;
    }

    public void setHq(boolean hq) {
        this.hq = hq;
    }

    public List<String> getAggressors() {
        return aggressors;
    }

    public void setAggressors(List<String> aggressors) {
        this.aggressors = aggressors;
    }

    public boolean isRankAccess() {
        return rankAccess;
    }

    public void setRankAccess(boolean rankAccess) {
        this.rankAccess = rankAccess;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMinZ() {
        return minZ;
    }

    public void setMinZ(int minZ) {
        this.minZ = minZ;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public void setMaxZ(int maxZ) {
        this.maxZ = maxZ;
    }

    public Map<String, GuildRanks> getRanks() {
        return ranks;
    }

    public void setRanks(Map<String, GuildRanks> ranks) {
        this.ranks = ranks;
    }

    public boolean isHasStoreroom() {
        return hasStoreroom;
    }

    public void setHasStoreroom(boolean hasStoreroom) {
        this.hasStoreroom = hasStoreroom;
    }

    public Set<Location> getProtectedChests() {
        return protectedChests;
    }

    public void setProtectedChests(Set<Location> protectedChests) {
        this.protectedChests = protectedChests;
    }

    public GuildRanks getDefaultRank() {
        return defaultRank;
    }

    public void setDefaultRank(GuildRanks defaultRank) {
        this.defaultRank = defaultRank;
    }

    public String getMotd() {
        return motd;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }

    public boolean isMessageOfTheDay() {
        return messageOfTheDay;
    }

    public void setMessageOfTheDay(boolean messageOfTheDay) {
        this.messageOfTheDay = messageOfTheDay;
    }

    public Map<String, Integer> getPlayerContrib() {
        return playerContrib;
    }

    public void setPlayerContrib(Map<String, Integer> playerContrib) {
        this.playerContrib = playerContrib;
    }

    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    public Location getHomeLoc() {
        return homeLoc;
    }

    public void setHomeLoc(Location homeLoc) {
        this.homeLoc = homeLoc;
    }

    public String getExitMessage() {
        return exitMessage;
    }

    public void setExitMessage(String exitMessage) {
        this.exitMessage = exitMessage;
    }

    public String getEntryMessage() {
        return entryMessage;
    }

    public void setEntryMessage(String entryMessage) {
        this.entryMessage = entryMessage;
    }

    public boolean isHasExit() {
        return hasExit;
    }

    public void setHasExit(boolean hasExit) {
        this.hasExit = hasExit;
    }

    public boolean isHasEntry() {
        return hasEntry;
    }

    public void setHasEntry(boolean hasEntry) {
        this.hasEntry = hasEntry;
    }

    public HQ getHeadQuarters() {
        return headQuarters;
    }

    public void setHeadQuarters(HQ headQuarters) {
        this.headQuarters = headQuarters;
    }

    public int getExpansions() {
        return expansions;
    }

    public void setExpansions(int expansions) {
        this.expansions = expansions;
    }

    public Map<String, String> getApplicants() {
        return applicants;
    }

    public void setApplicants(Map<String, String> applicants) {
        this.applicants = applicants;
    }

    public boolean isRecruiting() {
        return recruiting;
    }

    public void setRecruiting(boolean recruiting) {
        this.recruiting = recruiting;
    }

    public String getRecruitMessage() {
        return recruitMessage;
    }

    public void setRecruitMessage(String recruitMessage) {
        this.recruitMessage = recruitMessage;
    }

    public boolean containsBlock(Block block) {
        final Location loc = block.getLocation();
        return loc.getX() <= maxX && loc.getZ() <= maxZ && loc.getX() >= minX && loc.getZ() >= minZ;
    }
}
