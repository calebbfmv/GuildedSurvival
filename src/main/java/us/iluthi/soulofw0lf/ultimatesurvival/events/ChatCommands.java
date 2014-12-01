package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Party;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.*;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.CoinChange;
import us.iluthi.soulofw0lf.ultimatesurvival.party.Util;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/22/13
 * Time: 5:07 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ChatCommands {
	
	
    public static Map<String, String> dNdMap = new HashMap<>();
    public static List<String> bugReports = new ArrayList<>();
    private static Map<String, String> friendRequest = new HashMap<>();

    public static boolean process(Player p, String command, String[] args){
        String message = "";
        if (args.length >= 1){
            StringBuilder sB = new StringBuilder();
            int i = 0;
            while (i < args.length){
                sB.append(" ").append(args[i]);
                i++;
            }
            message = sB.toString();
        }
        if (command.equalsIgnoreCase("friends"))
        {
            p.openInventory(FriendInventory.inv(p));
            return true;
        }
        if (command.equalsIgnoreCase("addfriend"))
        {
            if (args.length == 1) {
                Player friend;
                if ((friend = Bukkit.getPlayer(args[0])) != null){
                    friend.sendMessage(ChatUtil.color(Strings.friendStub + "&6" + p.getName() + " &b has sent you a friend request. type &6/accept &bor &6/deny."));
                    p.sendMessage(ChatUtil.color(Strings.friendStub + "&bYou have sent a friend request to &6" + args[0] + "."));
                    friendRequest.put(args[0], p.getName());
                    return true;
                }
            } else {
                p.sendMessage(ChatUtil.color(Strings.friendStub + "&4Please type &6/addfriend {name} &4to add a person to your friends list."));
            }
            return true;
        }

        if (command.equalsIgnoreCase("accept")){
            if (!friendRequest.containsKey(p.getName())){
                p.sendMessage(ChatUtil.color(Strings.friendStub + "&4You do not have any pending friend requests."));
                return true;
            }
            CustomPlayer cp = CustomPlayer.getCP(p.getName());
            cp.addFriend(p, friendRequest.get(p.getName()));
            if (Bukkit.getPlayer(friendRequest.get(p.getName())) != null){
                Player play = Bukkit.getPlayer(friendRequest.get(p.getName()));
                play.sendMessage(ChatUtil.color(Strings.friendStub + "your friend request to " + p.getName() + " has been accepted."));
                CustomPlayer cPlayer = CustomPlayer.getCP(play.getName());
                cPlayer.addFriend(play, p.getName());
            }
            friendRequest.remove(p.getName());
            return true;
        }
        if (command.equalsIgnoreCase("deny")){
            if (!friendRequest.containsKey(p.getName())){
                p.sendMessage(ChatUtil.color(Strings.friendStub + "&4You do not have any pending friend requests."));
                return true;
            }
            p.sendMessage(ChatUtil.color(Strings.friendStub + "You have denied a friend request from " + friendRequest.get(p.getName()) + "."));
            if (Bukkit.getPlayer(friendRequest.get(p.getName())) != null){
                Bukkit.getPlayer(friendRequest.get(p.getName())).sendMessage(ChatUtil.color(Strings.friendStub + "your friend request to " + p.getName() + " has been denied."));
            }
            friendRequest.remove(p.getName());
            return true;
        }
        if (command.equalsIgnoreCase("r"))
        {

            CustomPlayer cp = CustomPlayer.getCP(p.getName());
            if (!cp.getLastFriendPM().isEmpty())
                p.performCommand("pm "+cp.getLastFriendPM()+" "+message);
            return true;
        }
        if (command.equalsIgnoreCase("addcoins")){
            if (!p.isOp()){
                return true;
            }
            if (Booleans.isInteger(args[0])){
                CoinChange.add(p, Integer.parseInt(args[0]), true);
                p.sendMessage(args[0] + " coins added.");
            }
            return true;
        }
        if (command.equalsIgnoreCase("makespawner")){
            if (!p.isOp()){
                return false;
            }
            Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
            b.setType(Material.MOB_SPAWNER);
            return true;
        }
        if (command.equalsIgnoreCase("spawn")){
            final String name = p.getName();
            final int x = (int)p.getLocation().getX();
            final int z = (int)p.getLocation().getZ();
            new BukkitRunnable(){
                int timer = 5;
                @Override
                public void run(){
                    if (Bukkit.getPlayer(name) == null){
                        cancel();
                        return;
                    }
                    if (timer == 0){

                        Bukkit.getPlayer(name).teleport(Locations.spawnLoc);
                        cancel();
                        return;
                    }

                    Bukkit.getPlayer(name).sendMessage(ChatUtil.color("&bTransporting to spawn in &4" + timer + " &bseconds, please stand still!"));
                    if ((int)Bukkit.getPlayer(name).getLocation().getX() != x || (int)Bukkit.getPlayer(name).getLocation().getZ() != z){
                        Bukkit.getPlayer(name).sendMessage(ChatUtil.color("&4You must stay still to use this command!!! Please retry later."));
                        cancel();
                        return;
                    }
                    timer--;
                }
            }.runTaskTimer(UltimateSurvival.getInstance(), 1, 20);
            return true;
        }
        if (command.equalsIgnoreCase("report")){
            p.sendMessage(ChatUtil.color(Strings.chatStub + " Your bug report has been submitted!!"));
            bugReports.add(p.getName() + " : " + message);
            File f = new File("bugReports.yml");
            YamlConfiguration bugs = YamlConfiguration.loadConfiguration(f);
            if (bugs.get("Bug Reports") != null){
                @SuppressWarnings("unchecked")
				List<String> oldBugs = (List<String>)bugs.getList("Bug Reports");
                for (String bug : oldBugs){
                    if (bugReports.contains(bug)){
                        continue;
                    }
                    bugReports.add(bug);
                }
            }
            bugs.set("Bug Reports", bugReports);
            try {
                bugs.save(f);
            } catch (IOException e){
                e.printStackTrace();
            }
            return true;
        }
        if (command.equalsIgnoreCase("kickall")){
            if (!p.isOp()){
                return false;
            }
            for (Player pl : Bukkit.getOnlinePlayers()){
                pl.kickPlayer("The Server is restarting for a quick feature update!");
            }
            return true;
        }

        if (command.equalsIgnoreCase("pm")){
            if (args.length < 2){
                p.sendMessage(ChatUtil.color(Strings.chatStub + " You must include a player name and a message to pm someone!"));
                return true;
            }
            if (Bukkit.getPlayer(args[0]) == null){
                p.sendMessage(ChatUtil.color(Strings.chatStub + " That player could not be found"));
                return true;
            }
            Player pl = Bukkit.getPlayer(args[0]);
            CustomPlayer cPlayer = CustomPlayer.getCP(args[0]);
            if (cPlayer.getIgnoredPlayers().contains(p.getName())){
                p.sendMessage(ChatUtil.color(Strings.chatStub + " You cannot whisper a player that has you on ignore."));
                return true;
            }
            if (cPlayer.isdNd()){
                p.sendMessage(ChatUtil.color(Strings.chatStub + " That player is set to Do Not Disturb"));
                p.sendMessage(ChatUtil.color(Strings.chatStub + " Message: " + dNdMap.get(pl.getName())));
                return true;
            }
            StringBuilder sB = new StringBuilder();
            int i = 1;
            while (i < args.length){
                sB.append(" ").append(args[i]);
                i++;
            }
            String messages = sB.toString();
            pl.sendMessage(ChatUtil.color("&f[&bPM From&f: &6" + p.getName() + "&f] &b" + messages));
            CustomPlayer cl2 = CustomPlayer.getCP(pl.getName());
            cl2.setLastFriendPM(p.getName());
            p.sendMessage(ChatUtil.color("&f[&6PM To&f: &b" + pl.getName() + "&f] &6" + messages));
            //playerRespond.put(pl.getName(), p.getName());
            return true;
        }
        /*if (command.equalsIgnoreCase("r")){
            event.setCancelled(true);
            if (!playerRespond.containsKey(p.getName())){
                p.sendMessage(ChatUtil.color(Strings.chatStub + " You have no players to respond to."));
                return;
            }
            if (Bukkit.getPlayer(playerRespond.get(p.getName())) == null){
                playerRespond.remove(p.getName());
                p.sendMessage(ChatUtil.color(Strings.chatStub + " You have no players to respond to."));
                return;
            }
            Player pl = Bukkit.getPlayer(playerRespond.get(p.getName()));
            if (dNdMap.containsKey(pl.getName())){
                p.sendMessage(ChatUtil.color(Strings.chatStub + " That player is set to Do Not Disturb"));
                p.sendMessage(ChatUtil.color(Strings.chatStub + " Message: " + dNdMap.get(pl.getName())));
                return;
            }
            CustomPlayer cPlayer = CustomPlayer.getCP(args[1]);
            if (cPlayer.getIgnoredPlayers().contains(p.getName())){
                p.sendMessage(ChatUtil.color(Strings.chatStub + " You cannot whisper a player that has you on ignore."));
                return;
            }
            pl.sendMessage(ChatUtil.color("&f[&bPM From&f: &6" + p.getName() + "&f] &b" + message));
            p.sendMessage(ChatUtil.color("&f[&6PM To&f: &b" + pl.getName() + "&f] &6" + message));
            playerRespond.put(pl.getName(), p.getName());
            return;
        }*/
        if (command.equalsIgnoreCase("chat")){
            p.openInventory(ChatInventory.build(p));
            return true;
        }
        if (command.equalsIgnoreCase("shop")){
            p.openInventory(ShopInventory.inv());
            return true;
        }
        if (command.equalsIgnoreCase("guild")){
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            if (!cP.isInGuild()){
                p.openInventory(NoGuild.inv());
            } else {
                Guild g = Maps.allGuilds.get(cP.getGuildName());
                p.openInventory(MainGuildMenu.inv(g, p));
            }
            return true;
        }
     /*
        */
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (message.isEmpty()){
            return false;
        }
        if (cP.isChatBlocked()){
            return false;
        }
        if (!Maps.shortChat.containsKey(command)){
            return false;
        }
        if (message.contains("&") && !p.hasPermission("triple.coins")){
            message = message.replace("&", "and");
        }
        message = message.replace("%", "percent");
        String activeChat = Maps.shortChat.get(command);
        if (activeChat.equalsIgnoreCase("General")){
            for (Player player : Bukkit.getOnlinePlayers()){
                CustomPlayer cPlayer = CustomPlayer.getCP(player.getName());
                if (cPlayer.isChatBlocked()){
                    continue;
                }
                if (cPlayer.getInactiveChannels().contains(activeChat)){
                    continue;
                }
                if (cPlayer.getIgnoredPlayers().contains(p.getName())){
                    continue;
                }
                player.sendMessage(ChatUtil.color(OnChat.prefix(p, "general")) + ChatUtil.chatColor("General", message, player));
            }
            return true;
        }
        if (activeChat.equalsIgnoreCase("guild")){
            if (!cP.isInGuild()){
                p.sendMessage(ChatUtil.color(Strings.chatStub + "You are not in a guild so may not talk in this channel. Please switch your active chat channel to talk."));
                return true;
            }
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            GuildRanks gR = g.getRanks().get(cP.getGuildRank());
            if (!gR.getRankPermissions().get(RankPerms.CHAT)){
                p.sendMessage(ChatUtil.color(Strings.guildStub + "You do not have permission to talk in your guild!"));
                return true;
            }
            for (String name : g.getPlayers()){
                if (Bukkit.getPlayer(name) != null){
                    Player player = Bukkit.getPlayer(name);
                    CustomPlayer cPlayer = CustomPlayer.getCP(player.getName());
                    if (cPlayer.isChatBlocked()){
                        continue;
                    }
                    if (cPlayer.getInactiveChannels().contains(activeChat)){
                        continue;
                    }
                    if (cPlayer.getIgnoredPlayers().contains(p.getName())){
                        continue;
                    }
                    player.sendMessage(ChatUtil.color(OnChat.prefix(p, "guild")) + ChatUtil.chatColor("Guild", message, player));
                }
            }
            return true;
        }
        if (activeChat.equalsIgnoreCase("HQ")){
            if (!cP.isInGuild()){
                p.sendMessage(ChatUtil.color(Strings.chatStub + "You are not in a guild so may not talk in this channel. Please switch your active chat channel to talk."));
                return true;
            }
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            GuildRanks gR = g.getRanks().get(cP.getGuildRank());
            if (!gR.getRankPermissions().get(RankPerms.CHAT)){
                p.sendMessage(ChatUtil.color(Strings.guildStub + "You do not have permission to talk in your guild!"));
                return true;
            }
            for (String name : g.getPlayers()){
                if (Bukkit.getPlayer(name) != null){
                    Player player = Bukkit.getPlayer(name);
                    CustomPlayer cPlayer = CustomPlayer.getCP(player.getName());
                    if (cPlayer.isChatBlocked()){
                       continue;
                    }
                    if (cPlayer.getInactiveChannels().contains(activeChat)){
                        continue;
                    }
                    if (cPlayer.getIgnoredPlayers().contains(p.getName())){
                        continue;
                    }
                    player.sendMessage(ChatUtil.color(OnChat.prefix(p, "hq")) + ChatUtil.chatColor("HQ", message, player));
                }
            }
            return true;
        }
        if (activeChat.equalsIgnoreCase("officer")){
            if (!cP.isInGuild()){
                p.sendMessage(ChatUtil.color(Strings.chatStub + "You are not in a guild so may not talk in this channel. Please switch your active chat channel to talk."));
                return true;
            }
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            GuildRanks gR = g.getRanks().get(cP.getGuildRank());
            if (!gR.getRankPermissions().get(RankPerms.OFFICER_CHAT)){
                p.sendMessage(ChatUtil.color(Strings.guildStub + "You do not have permission to talk in your guild!"));
                return true;
            }
            for (String name : g.getPlayers()){
                if (Bukkit.getPlayer(name) != null){
                    Player player = Bukkit.getPlayer(name);
                    CustomPlayer cPlayer = CustomPlayer.getCP(player.getName());
                    if (cPlayer.isChatBlocked()){
                        continue;
                    }
                    if (cPlayer.getInactiveChannels().contains(activeChat)){
                        continue;
                    }
                    GuildRanks gRank = g.getRanks().get(cPlayer.getGuildRank());
                    if (!gRank.getRankPermissions().get(RankPerms.OFFICER_CHAT)){
                        continue;
                    }
                    if (cPlayer.getIgnoredPlayers().contains(p.getName())){
                        continue;
                    }
                    player.sendMessage(ChatUtil.color(OnChat.prefix(p, "officer")) + ChatUtil.chatColor("Officer", message, player));
                }
            }
            return true;
        }
        if (activeChat.equalsIgnoreCase("recruitment")){
            if (!cP.isInGuild()){
                p.sendMessage(ChatUtil.color(Strings.chatStub + "You are not in a guild so may not talk in this channel. Please switch your active chat channel to talk."));
                return true;
            }
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            GuildRanks gR = g.getRanks().get(cP.getGuildRank());
            if (!gR.getRankPermissions().get(RankPerms.RECRUITMENT)){
                p.sendMessage(ChatUtil.color(Strings.guildStub + "You do not have permission to recruit for your guild!"));
                return true;
            }
            for (Player player : Bukkit.getOnlinePlayers()){
                CustomPlayer cPlayer = CustomPlayer.getCP(player.getName());
                if (cPlayer.isChatBlocked()){
                    continue;
                }
                if (cPlayer.getInactiveChannels().contains(activeChat)){
                    continue;
                }
                if (cPlayer.getIgnoredPlayers().contains(p.getName())){
                    continue;
                }
                if (cPlayer.isInGuild() && !cPlayer.equals(cP)){
                   continue;
                }
                player.sendMessage(ChatUtil.color(OnChat.prefix(p, "recruitment")) + ChatUtil.chatColor("Recruitment", message, player));
            }
            return true;
        }
        if (activeChat.equalsIgnoreCase("Local")){
            for (Player player : Bukkit.getOnlinePlayers()){
                CustomPlayer cPlayer = CustomPlayer.getCP(player.getName());
                if (cPlayer.isChatBlocked()){
                    continue;
                }
                if (cPlayer.getInactiveChannels().contains(activeChat)){
                    continue;
                }
                if (cPlayer.getIgnoredPlayers().contains(p.getName())){
                    continue;
                }
                if (player.getWorld().getName().equals(p.getWorld().getName()) && player.getLocation().distance(p.getLocation()) <= Integers.localDistance){
                    player.sendMessage(ChatUtil.color(OnChat.prefix(p, "local")) + ChatUtil.chatColor("Local", message, player));
                }
            }
            return true;
        }
        if (activeChat.equalsIgnoreCase("Party")){
            Party party;
            if((party = Util.getParty(p.getName())) != null){
                for (String player : party.getMembers()){
                    if (Bukkit.getPlayer(player) != null){
                        Player pl = Bukkit.getPlayer(player);
                        CustomPlayer cPlayer = CustomPlayer.getCP(pl.getName());
                        if (cPlayer.isChatBlocked()){
                            continue;
                        }
                        if (cPlayer.getInactiveChannels().contains(activeChat)){
                            continue;
                        }
                        if (cPlayer.getIgnoredPlayers().contains(p.getName())){
                            continue;
                        }
                        Bukkit.getPlayer(player).sendMessage(ChatUtil.color(OnChat.prefix(p, "party")) + ChatUtil.chatColor("Party", message, p));
                    }
                }
            } else {
                p.sendMessage(ChatUtil.color(Strings.partyStub + "You are not currently in a party"));
                return true;
            }
            return true;
        }
        if (activeChat.equalsIgnoreCase("trade")){
            for (Player player : Bukkit.getOnlinePlayers()){
                CustomPlayer cPlayer = CustomPlayer.getCP(player.getName());
                if (cPlayer.isChatBlocked()){
                    continue;
                }
                if (cPlayer.getInactiveChannels().contains(activeChat)){
                    continue;
                }
                if (cPlayer.getIgnoredPlayers().contains(p.getName())){
                    continue;
                }
                player.sendMessage(ChatUtil.color(OnChat.prefix(p, "trade")) + ChatUtil.chatColor("Trade", message, player));
            }
        }
        return true;
    }
}
