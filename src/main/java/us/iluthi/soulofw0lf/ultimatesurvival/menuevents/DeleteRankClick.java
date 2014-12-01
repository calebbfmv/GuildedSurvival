package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.RankInventory;
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
 * Time: 12:24 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class DeleteRankClick {
    public static void deleteClick(InventoryClickEvent event){
        event.setResult(Event.Result.DENY);
        event.setCancelled(true);
        Player p = (Player)event.getWhoClicked();
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (!cP.isInGuild()){
            p.closeInventory();
            p.sendMessage(ChatUtil.color(Strings.guildStub + " I have no clue how you got in this menu but only people in guilds should be in here!"));
            return;
        }
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)){
            return;
        }
        if (!Maps.deletingRank.containsKey(p.getName())){
            p.closeInventory();
            return;
        }
        GuildRanks gR = Maps.deletingRank.get(p.getName());
        GuildRanks defaultRank = g.getDefaultRank();
        if (event.getRawSlot() == 3){
            for (String player : g.getPlayers()){
                if (Maps.customPlayers.containsKey(player)){
                    CustomPlayer rankChange = CustomPlayer.getCP(player);
                    if (rankChange.getGuildRank().equalsIgnoreCase(gR.getName())){
                        rankChange.setGuildRank(defaultRank.getName());
                        if (Bukkit.getPlayer(player) != null){
                            Bukkit.getPlayer(player).sendMessage(ChatUtil.color(Strings.guildStub + " Your rank has been deleted! You have been placed in the default rank!"));
                        }
                        continue;
                    }
                } else {
                    DBCollection table = UltimateSurvival.db.getCollection("players");
                    BasicDBObject playerFile = new BasicDBObject().append("name", player);
                    BasicDBObject mainPlayer = new BasicDBObject().append("name", player);
                    playerFile.put("Guild Rank", defaultRank.getName());
                    BasicDBObject updateObj = new BasicDBObject();
                    updateObj.put("$set", playerFile);
                    table.update(mainPlayer, updateObj);
                }
            }
            g.getRanks().remove(gR.getName());
            Maps.deletingRank.remove(p.getName());
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have successfully deleted this guild rank!"));
            p.closeInventory();
            return;
        }
        if (event.getRawSlot() == 5){
            p.closeInventory();
            p.openInventory(RankInventory.inv(g));
            Maps.deletingRank.remove(p.getName());
            return;
        }
    }
}
