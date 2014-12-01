package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.GuildPlayersEdit;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.RankChange;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 12:33 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class GuildEditSpecificPlayerClick {
    public static void editSpecificClick(InventoryClickEvent event){
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player)event.getWhoClicked();
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        GuildRanks gR = g.getRanks().get(cP.getGuildRank());
        String[] args = event.getInventory().getName().split(":");
        String name = args[1].replace(" ", "");
        if (Bukkit.getPlayer(name) == null){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " The player you were trying to edit appears to have gone offline. At this time you can only edit active players."));
            p.closeInventory();
            return;
        }
        Player player = Bukkit.getPlayer(name);
        CustomPlayer cP1 = CustomPlayer.getCP(player.getName());
        GuildRanks gR1 = g.getRanks().get(cP1.getGuildRank());
        if (event.getRawSlot() == 2 && gR.getRankPermissions().get(RankPerms.KICK)){
            if (gR1.getWeight() >= gR.getWeight()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You may not kick a guild member whose rank is higher than yours!"));
                return;
            }
            cP1.setGuildRank("");
            cP1.setGuildName("");
            cP1.setInGuild(false);
            g.getPlayers().remove(player.getName());
            player.sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " has removed you from the guild!"));
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have removed " + player.getName() + " from the guild."));
            for (String players : g.getPlayers()){
                if (players.equalsIgnoreCase(p.getName())){
                    continue;
                }
                if (Bukkit.getPlayer(players) != null){
                    Bukkit.getPlayer(players).sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " has removed " + player.getName() + " from the guild."));
                }
            }
            return;
        }
        if (event.getRawSlot() == 4 && gR.getRankPermissions().get(RankPerms.PROMOTE)){
            if (gR1.getWeight() >= gR.getWeight()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You may not promote a guild member whose rank is higher than yours!"));
                return;
            }
            if (!RankChange.promote(cP1.getGuildRank(), player, gR.getName())){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " There was no rank to promote that player to."));
                return;
            } else {
                for (String players : g.getPlayers()){
                    if (Bukkit.getPlayer(players) != null){
                        Bukkit.getPlayer(players).sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " has promoted " + player.getName() + " to " + cP1.getGuildRank() + "!!!"));

                    }
                }
            }
            return;
        }
        if (event.getRawSlot() == 6 && gR.getRankPermissions().get(RankPerms.DEMOTE)){
            if (gR1.getWeight() >= gR.getWeight()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You may not demote a guild member whose rank is higher than yours!"));
                return;
            }
            if (!RankChange.demote(cP1.getGuildRank(), player)){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " There was no rank to demote that player to."));
                return;
            } else {
                for (String players : g.getPlayers()){
                    if (Bukkit.getPlayer(players) != null){
                        Bukkit.getPlayer(players).sendMessage(ChatUtil.color(Strings.guildStub + " " + p.getName() + " has demoted " + player.getName() + " to " + cP1.getGuildRank() + "."));

                    }
                }
            }
            return;
        }
        if (event.getRawSlot() == 12){
            if (gR1.getWeight() >= gR.getWeight()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You may not set the rank of a guild member whose rank is higher than yours!"));
                return;
            }
            p.sendMessage(ChatUtil.color(Strings.guildStub + " this feature will be implemented later! For now please use the promote and demote buttons!"));
            return;
        }
        if (event.getRawSlot() == 14 && (gR.getRankPermissions().get(RankPerms.SET_LEADER) || p.hasPermission("rank.bypass"))){
            String leader = g.getOwner();
            GuildRanks defaultRank = g.getDefaultRank();
            String leadRank = "";
            CustomPlayer cLeader = CustomPlayer.getCP(Bukkit.getPlayer(leader).getName());
            leadRank = cLeader.getGuildRank();
            cLeader.setGuildRank(defaultRank.getName());
            Bukkit.getPlayer(leader).sendMessage(ChatUtil.color(Strings.guildStub + " You are no longer leader of this guild. your rank has been set to " + defaultRank.getName() + "."));
            cP1.setGuildRank(leadRank);
            g.setOwner(player.getName());
            for (String players : g.getPlayers()){
                if (Bukkit.getPlayer(players) != null){
                    Bukkit.getPlayer(players).sendMessage(ChatUtil.color(Strings.guildStub + " " + player.getName() + " is now the Leader of " + g.getName()));

                }
            }
        }
        if (event.getRawSlot() == 17){
            p.closeInventory();
            p.openInventory(GuildPlayersEdit.inv(g));
            return;
        }
    }
}
