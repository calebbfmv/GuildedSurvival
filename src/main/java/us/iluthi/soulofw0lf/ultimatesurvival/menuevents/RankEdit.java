package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.DeleteRank;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.GuildManagement;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.RankEditInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/1/13
 * Time: 11:36 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class RankEdit {
    @SuppressWarnings("deprecation")
	public static void rankEdit(InventoryClickEvent event){
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)){
            return;
        }
        ItemStack iS = event.getCurrentItem();
        if (event.getWhoClicked() instanceof Player){
            Player p = (Player)event.getWhoClicked();
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            Guild g = Maps.allGuilds.get(cP.getGuildName());
            GuildRanks pRank = g.getRanks().get(cP.getGuildRank());
            if (event.getRawSlot() == 44){
                p.closeInventory();
                p.openInventory(GuildManagement.inv(g, p));
                return;
            }
            if (event.getRawSlot() == 43){
                if (!pRank.getRankPermissions().get(RankPerms.CREATE_RANK)){
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " You do not have permission to create new Guild Ranks"));
                    return;
                }
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Please type in the name of your new rank!"));
                Lists.addingRank.add(p.getName());
                cP.setChatBlocked(true);
                p.closeInventory();
                return;
            }
            if (!iS.hasItemMeta() || !iS.getItemMeta().hasDisplayName()){
                return;
            }

            String name = iS.getItemMeta().getDisplayName();
            GuildRanks gR = g.getRanks().get(name);

            if (pRank.getWeight() <= gR.getWeight()){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You may not edit your own rank permissions, or a rank that is higher than yours."));
                return;
            }
            if (event.isShiftClick()){
                if (!pRank.getRankPermissions().get(RankPerms.SET_WEIGHT)){
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " You do not have permission to edit rank weight."));
                    return;
                }
                if (gR.getWeight() >= pRank.getWeight() - 1){
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " You may not set a ranks weight to be equal to your own."));
                    return;
                }
                if (event.isLeftClick()){
                    gR.setWeight(gR.getWeight() + 1);
                    iS.setAmount(iS.getAmount() + 1);
                    p.updateInventory();
                    return;
                }
                if (event.isRightClick()){
                    if (gR.getWeight() <= 1){
                        gR.setWeight(1);
                        iS.setAmount(1);
                        p.updateInventory();
                        return;
                    } else {
                        gR.setWeight(gR.getWeight() - 1);
                        iS.setAmount(iS.getAmount() - 1);
                        p.updateInventory();
                        return;
                    }
                }
            }
            if (event.isLeftClick()){
                if (!pRank.getRankPermissions().get(RankPerms.EDIT_RANKS)){
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " You do not have permissions to edit rank permissions"));
                    return;
                }
                p.closeInventory();
                p.openInventory(RankEditInventory.inv(gR));
                return;
            }
            if (event.isRightClick()){
                if (!pRank.getRankPermissions().get(RankPerms.DELTE_RANK)){
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " You do not have permission to delete Guild Ranks"));
                    return;
                }
                if (g.getDefaultRank().equals(gR)){
                    p.sendMessage(ChatUtil.color(Strings.guildStub + " You cannot delete the guilds default rank! Please make another rank default before deleting this one!"));
                    return;
                }
                Maps.deletingRank.put(p.getName(), gR);
                p.closeInventory();
                p.openInventory(DeleteRank.inv(gR));
            }
        }
    }
}
