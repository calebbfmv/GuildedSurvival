package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.RankInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 12:37 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class RankEditClick {
    @SuppressWarnings("deprecation")
	public static void rankEditClick(InventoryClickEvent event){
        if (event.getWhoClicked() instanceof Player){
            Player p = (Player)event.getWhoClicked();

            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            if (!cP.isInGuild()){
                return;
            }
            Inventory inv = event.getInventory();
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            if (event.getRawSlot() == 44){
                p.closeInventory();
                p.openInventory(RankInventory.inv(g));
                return;
            }

            String[] split = inv.getName().split(":");
            String rankName = split[1];
            GuildRanks rank = g.getRanks().get(rankName);

            if (event.getRawSlot() == 43){
                g.setDefaultRank(rank);
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You have made this the new default rank for your guild!"));
                return;
            }
            if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR) || !event.getCurrentItem().hasItemMeta() || !event.getCurrentItem().getItemMeta().hasDisplayName()){
                return;
            }
            ItemStack iS = event.getCurrentItem();
            String permName = iS.getItemMeta().getDisplayName().replace(" ", "_").toUpperCase();
            RankPerms gRank = RankPerms.valueOf(permName);
            if (rank.getRankPermissions().get(gRank)){
                rank.getRankPermissions().remove(gRank);
                iS.setType(Material.REDSTONE_BLOCK);
                ItemMeta iM = iS.getItemMeta();
                List<String> rankLore = new ArrayList<>();
                rankLore.add(ChatUtil.color("&bClick to set"));
                rankLore.add(ChatUtil.color("&bthis permission to true"));
                rankLore.add(ChatUtil.color("&bfor this rank."));
                iM.setLore(rankLore);
                iS.setItemMeta(iM);
                rank.getRankPermissions().put(gRank, false);
            } else {
                rank.getRankPermissions().remove(gRank);
                iS.setType(Material.EMERALD_BLOCK);
                ItemMeta iM = iS.getItemMeta();
                List<String> rankLore = new ArrayList<>();
                rankLore.add(ChatUtil.color("&bClick to set"));
                rankLore.add(ChatUtil.color("&bthis permission to false"));
                rankLore.add(ChatUtil.color("&bfor this rank."));
                iM.setLore(rankLore);
                iS.setItemMeta(iM);
                rank.getRankPermissions().put(gRank, true);
            }
            p.updateInventory();
//debug
        }
    }
}
