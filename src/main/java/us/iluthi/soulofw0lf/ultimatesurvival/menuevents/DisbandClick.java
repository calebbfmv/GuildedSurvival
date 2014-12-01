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
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.GuildManagement;
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
 * Time: 12:23 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class DisbandClick {
    public static void disbandClick(InventoryClickEvent event){
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
        if (event.getRawSlot() == 3){

            DBCollection table = UltimateSurvival.db.getCollection("guilds");
            BasicDBObject guildFile = new BasicDBObject().append("name", g.getName());
            table.remove(guildFile);

            for (Player player : Bukkit.getOnlinePlayers()){
                if (player.getName().equalsIgnoreCase(p.getName())){
                    continue;
                }
                CustomPlayer cPlayer = CustomPlayer.getCP(player.getName());
                if (cPlayer.isInGuild() && cPlayer.getGuildName().equalsIgnoreCase(g.getName())){
                    player.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild has been disbanded!!!!"));
                    cPlayer.setInGuild(false);
                    cPlayer.setGuildRank("");
                    cPlayer.setGuildName("");
                }
            }
            Maps.allGuilds.remove(cP.getGuildName());
            cP.setInGuild(false);
            cP.setGuildRank("");
            cP.setGuildName("");
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You have successfully disbanded your guild!"));
            p.closeInventory();
            return;
        }
        if (event.getRawSlot() == 5){
            p.closeInventory();
            p.openInventory(GuildManagement.inv(g, p));
            return;
        }
    }
}
