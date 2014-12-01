package us.iluthi.soulofw0lf.ultimatesurvival.party;

import org.bukkit.entity.Player;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/20/13
 * Time: 10:35 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class TeleInvite implements Runnable {

    private Player a;
    private Player b;

    private Thread thread = null;
    private boolean isRunning = false;

    public TeleInvite(Player a, Player b){
        isRunning = true;
        Util.sendMessage(a.getName(), "Teleport Request Sent To " + b.getName());
        Util.sendMessage(b.getName(), a.getName() + " Wants To Teleport You.");
        this.a = a;
        this.b = b;
        thread = new Thread(this);
        thread.start();
    }

    public void accept(){
        isRunning = false;
        Util.sendMessage(a.getName(), b.getName() + " Has Accepted Teleportation");
        Util.sendMessage(a.getName(), "Do Not Move For 5 Seconds");
        Util.sendMessage(b.getName(), "Do Not Move For 5 Seconds");
        TeleTimer tt = new TeleTimer(a, b);
        new Thread(tt).start();
        Lists.tiList.remove(this);
    }

    public void deny(){
        isRunning = false;
        Util.sendMessage(a.getName(), b.getName() + " Has Denied Teleportation.");
        Lists.tiList.remove(this);
    }

    public String getTargetPlayer(){ return b.getName(); }

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
            deny();
            Util.sendMessage(b.getName(), "Teleport Invitation Timed Out.");
        } catch (Exception e) {
        }
    }

}
