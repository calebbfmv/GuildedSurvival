package us.iluthi.soulofw0lf.ultimatesurvival.customobjects;

import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
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
 * Time: 8:28 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class GuildRanks {
    private String name = "";
    private Map<RankPerms, Boolean> rankPermissions = new HashMap<>();
    private boolean tempRank = false;
    private int weight = 1;
    private int rankMinutes = 15;


    public GuildRanks(String name, String guild){
        this.name = name;
        for (RankPerms rank : RankPerms.values()){
            this.getRankPermissions().put(rank, true);
        }
        Guild g = Maps.allGuilds.get(guild);
        System.out.print(name);
        if (g.getRanks() == null){
            Map<String, GuildRanks> tempMap = new HashMap<>();
            tempMap.put(name, this);
            g.setRanks(tempMap);
        }
        g.getRanks().put(name, this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<RankPerms, Boolean> getRankPermissions() {
        return rankPermissions;
    }

    public void setRankPermissions(Map<RankPerms, Boolean> rankPermissions) {
        this.rankPermissions = rankPermissions;
    }

    public boolean isTempRank() {
        return tempRank;
    }

    public void setTempRank(boolean tempRank) {
        this.tempRank = tempRank;
    }

    public int getRankMinutes() {
        return rankMinutes;
    }

    public void setRankMinutes(int rankMinutes) {
        this.rankMinutes = rankMinutes;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
