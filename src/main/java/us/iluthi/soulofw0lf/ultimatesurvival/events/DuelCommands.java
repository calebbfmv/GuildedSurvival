package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.DuelInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.Lottery;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 *
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 10:53 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class DuelCommands implements Listener {
    public DuelCommands(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @EventHandler
    public void onDuel(PlayerCommandPreprocessEvent event){
        Player p = event.getPlayer();
        String[] args = event.getMessage().split(" ");
        String cmd = args[0].replace("/", "");
        if (cmd.equalsIgnoreCase("pl") || cmd.equalsIgnoreCase("plugin") || cmd.equalsIgnoreCase("plugins") || cmd.equalsIgnoreCase("?") || cmd.equalsIgnoreCase("help")){
            event.setCancelled(true);
            p.sendMessage(ChatUtil.color(Strings.tutStub + " This command has been disabled, but there is a tutorial sign you can click on at spawn to learn all about game play."));
            p.sendMessage(ChatUtil.color(Strings.tutStub + " Most commands in the game are done with your menu book, right click it and browse through to learn more about what it entails!"));
            return;
        }
        if (cmd.equalsIgnoreCase("code")){
            event.setCancelled(true);
            if (args.length != 2){
                return;
            }
            Lottery.useCode(p, args[1]);
            return;
        }
        if (cmd.equalsIgnoreCase("lotto")){
            event.setCancelled(true);
            Lottery.purchaseTickets(p);
            return;
        }
        if (cmd.equalsIgnoreCase("owner")){
            event.setCancelled(true);
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            if (!cP.isInGuildLands()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You are not currently in a guilds headquarters."));
                return;
            }
            p.sendMessage(ChatUtil.color(Strings.guildStub + " This guild land is owned by " + cP.getGuildNear() + "."));
            return;
        }
        if (cmd.equalsIgnoreCase("duel")){
            if (args.length == 1){
                p.openInventory(DuelInventory.nearbyPlayers(p));
                return;
            }
            event.setCancelled(true);
            String name = args[1];
            if (Bukkit.getPlayer(name) == null){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bThat player cannot be found!"));
                return;
            }
            Player duel = Bukkit.getPlayer(name);
            CustomPlayer cP = CustomPlayer.getCP(duel.getName());
            if (!cP.isDuelsOn()){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bThat player does not accept duels!"));
                return;
            }
            if (p.getWorld().getName().equals(Locations.spawnLoc.getWorld().getName()) && p.getLocation().distance(Locations.spawnLoc) <= 180){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &6You cannot start a duel this close to spawn!"));
                return;
            }
            if (cP.getIgnoredPlayers().contains(p.getName())){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bYou cannot duel a player that has you on ignore!"));
                return;
            }
            if (cP.isInDuel()){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &b That player is already in a duel."));
                return;
            }
            if (Maps.duelInvited.containsKey(duel.getName())){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &b That player is already in a duel!"));
                return;
            }
            if (Maps.duelInvite.containsKey(duel.getName())){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &b That player is already in a duel!"));
                return;
            }
            if (Maps.duelInvited.containsKey(p.getName())){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &b You cannot invite someone to a duel while you have a pending duel invite!"));
                return;
            }
            if (Maps.duelInvite.containsKey(p.getName())){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &b You have already invited someone to a duel!"));
                return;
            }
            if (Lists.duelWaiting.contains(duel.getName())){
                p.sendMessage(ChatUtil.color("&f[&eDuels&f] &b That player is already in a duel!"));
                return;
            }
            Maps.duelInvite.put(p.getName(), duel.getName());
            Maps.duelInvited.put(duel.getName(), p.getName());
            duel.openInventory(DuelInventory.duelInv(p));
            final String name1 = duel.getName();
            final String name2 = p.getName();
            p.sendMessage(ChatUtil.color("&f[&eDuels&f] &bYou have sent a duel request to " + duel.getName() + "!"));
            p.closeInventory();
            new BukkitRunnable(){
                @Override
                public void run(){
                    if (Maps.duelInvite.containsKey(name2) && Maps.duelInvited.containsKey(name1)){
                        if (Bukkit.getPlayer(name1) != null){
                            Bukkit.getPlayer(name1).sendMessage("&f[&eDuels&f] &b You have refused a duel from " + name2 + ".");
                        }
                        if (Bukkit.getPlayer(name2) != null){
                            Bukkit.getPlayer(name2).sendMessage("&f[&eDuels&f] &b Your duel request to "+name1+" has timed out.");
                        }
                        Maps.duelInvited.remove(name1);
                        Maps.duelInvite.remove(name2);
                        cancel();
                        return;
                    }
                }
            }.runTaskLater(UltimateSurvival.getInstance(), 600);
            return;
        }
    }
}
