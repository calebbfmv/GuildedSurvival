package us.iluthi.soulofw0lf.ultimatesurvival.party;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Party;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;

import java.util.List;
import java.util.Random;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/20/13
 * Time: 10:27 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Util {
    private static String stub = ChatColor.GOLD + "Party: " + ChatColor.WHITE;

    public static Party getParty(String name){
        for(Party party : Lists.pList)
            if(getPlayerFromList(party.getMembers(), name) != null)
                return party;
        return null;
    }
    private static Random rand = new Random();

    public static int getRand(){
        return rand.nextInt(100)+1;
    }

    public static Invite getInvite(String name){
        for(Invite invite : Lists.iList)
            if(invite.getUser().equals(name))
                return invite;
        return null;
    }

    public static TeleInvite getTeleInvite(String name){
        for(TeleInvite invite : Lists.tiList)
            if(invite.getTargetPlayer().equals(name))
                return invite;
        return null;
    }

    public static void sendMessage(String player, String message){
        if(player == null)
            return;
        Bukkit.getPlayer(player).sendMessage(stub + message);
    }

    public static void sendMessage(List<String> list, String message){
        if(list.size() == 0)
            return;
        for(String player : list)
            Bukkit.getPlayer(player).sendMessage(stub + message);
    }

    public static Player getPlayer(String name){
        for(Player player : Bukkit.getOnlinePlayers())
            if(player.getName().equals(name))
                return player;
        return null;
    }
    public static String getPlayerFromList(List<String> list, String name){
        for(String player : list)
            if(player.equals(name))
                return player;
        return null;
    }

    public static String combineArray(String[] args){
        String tmp = "";
        for(String str : args)
            tmp += str + " ";
        return tmp.substring(0, tmp.length() - 1);
    }

    public static boolean hasPlayerInvited(String name){
        for(Invite invite : Lists.iList)
            if(invite.getLeader().equals(name))
                return true;
        return false;
    }

    public static boolean hasPlayerTeleInvited(String name){
        for(TeleInvite invite : Lists.tiList)
            if(invite.getTargetPlayer().equals(name))
                return true;
        return false;
    }
}
