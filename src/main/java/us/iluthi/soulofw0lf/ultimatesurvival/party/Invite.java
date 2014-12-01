package us.iluthi.soulofw0lf.ultimatesurvival.party;

import org.bukkit.entity.Player;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Party;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/20/13
 * Time: 10:34 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Invite implements Runnable{
    private Player leader;
    private Player user;

    private Thread thread = null;
    private boolean isRunning = false;

    public Invite(Player leader, Player user) {
        this.leader = leader;
        this.user = user;
        Util.sendMessage(user.getName(), this.leader.getName() + " Has Invited You To A Party.");
        this.isRunning = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void acceptInvite(){
        isRunning = false;
        Party party = Util.getParty(leader.getName());
        if(party == null ){
            Util.sendMessage(user.getName(), "Party No Longer Exists");
            Lists.iList.remove(this);
            return;
        }
        party.addPlayer(user.getName());
        Util.sendMessage(user.getName(), "Accepted Invitation.");
        Lists.iList.remove(this);
    }

    public void denyInvite(){
        isRunning = false;
        Party party = Util.getParty(leader.getName());
        if(party != null){
            Util.sendMessage(party.getMembers(), user.getName() + " Denied Invitation.");
            if(party.getMembers().size() == 1 && !Util.hasPlayerInvited(this.leader.getName()))
                party.removePlayer(this.leader.getName());
        }
        Util.sendMessage(user.getName(), "Denied Invitation.");

        Lists.iList.remove(this);
    }

    public String getUser(){return this.user.getName();}

    public String getLeader(){return this.leader.getName();}

    @Override
    public void run() {
        try {
            int cd = 30;
            while(cd > 0){
                Thread.sleep(1000);
                cd--;
                if(!isRunning)
                    return;
            }
            denyInvite();
            Util.sendMessage(user.getName(), "Invitation Timed Out.");
        } catch (Exception e) {
        }
    }

}
