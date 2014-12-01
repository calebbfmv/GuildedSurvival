package us.iluthi.soulofw0lf.ultimatesurvival.customobjects;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us.iluthi.soulofw0lf.ultimatesurvival.party.Roll;
import us.iluthi.soulofw0lf.ultimatesurvival.party.RollerAPI;
import us.iluthi.soulofw0lf.ultimatesurvival.party.Util;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;

import java.util.ArrayList;
import java.util.List;

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
public class Party {
    private List<String> members = new ArrayList<>();

    public Party(String leader) {
        this.members.add(leader);
            List<String> temp = new ArrayList<>();
            for(String player : this.members)
                temp.add(player);
            RollerAPI.addRoll(new Roll(temp));

    }

    public void addPlayer(String name){
        if(Util.getPlayerFromList(this.members, name) != null)
            return;
        Util.sendMessage(this.members, name + " Has Joined The Party.");
        this.members.add(name);
        List<String> temp = new ArrayList<>();
        for(String player : this.members)
            temp.add(player);
        RollerAPI.setPlayers(temp, Lists.pList.indexOf(this));

    }

    public void addItem(ItemStack itm, String lore){
        itm.getItemMeta().getLore().remove(lore);
        RollerAPI.addItem(itm, Lists.pList.indexOf(this));
    }

    public void removePlayer(String name){
        String tmp = null;
        if((tmp = Util.getPlayerFromList(this.members, name)) == null)
            return;
        this.members.remove(tmp);
        List<String> temp = new ArrayList<>();
        for(String player : this.members)
            temp.add(player);
        RollerAPI.setPlayers(temp, Lists.pList.indexOf(this));
        Util.sendMessage(this.members, name + " Has Left The Party.");
        if(this.members.size() == 1){
            if(Lists.pcList.contains(this.members.get(0)))
                Lists.pcList.remove(this.members.get(0));

            Util.sendMessage(this.members.get(0), "Party Has Been Disbanded.");
            RollerAPI.remRoll(Lists.pList.indexOf(this));
            Lists.pList.remove(this);
        }
    }

    public void promotePlayer(String name){
        String tmp = null;
        if((tmp = Util.getPlayerFromList(this.members, name)) == null)
            return;
        this.members.remove(tmp);
        this.members.add(0, name);
        Util.sendMessage(this.members, name + " Has Been Promoted To Leader.");
    }

    public String getLeader(){return this.members.get(0);}

    public List<String> getMembers(){return this.members;}

    public static Party getParty(Player p){
        Party party;
        if((party = Util.getParty(p.getName())) != null)
            return party;
        return null;
    }
}
