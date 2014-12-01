package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.MenuInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.menuevents.*;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.CreateGuild;
import us.iluthi.soulofw0lf.ultimatesurvival.party.RollClick;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 5:13 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class InventoryClick implements Listener{
    public InventoryClick(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    @SuppressWarnings("deprecation")
	@EventHandler
    public void baseClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        if (inv.getName().equals(ChatUtil.color("&bChat Channels"))){
            chatClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&4Armor Category"))){
            ArmorCatClick.invClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&4Nether Category"))
                || inv.getName().equalsIgnoreCase(ChatUtil.color("&4Transportation"))
                || inv.getName().equalsIgnoreCase(ChatUtil.color("&4Redstone Category"))
                || inv.getName().equalsIgnoreCase(ChatUtil.color("&6Materials"))
                || inv.getName().equalsIgnoreCase(ChatUtil.color("&bBlock Category"))
                || inv.getName().equalsIgnoreCase(ChatUtil.color("&6Food Category"))
                || inv.getName().equalsIgnoreCase(ChatUtil.color("&bAmmo Category"))
                || inv.getName().equalsIgnoreCase(ChatUtil.color("&bUtility Category"))){
            BlockCatClick.onClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&6Guild Aggressors"))){
            BuyPeace.buyPeace(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&4Delete Rank Confirm"))){
            DeleteRankClick.deleteClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&4Disband Confirm"))){
            DisbandClick.disbandClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&bEdit Armor"))){
            EditArmorClick.invClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&4HQ"))){
            EditHQClick.hqClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&bEdit Weapon")) || inv.getName().equalsIgnoreCase(ChatUtil.color("&bEdit Tool"))){
            EditWeaponClick.invClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&4Excavation"))){
            ExcavationCatClick.invClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&aHQ Expansion"))){
            ExpandInvClick.onclick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&bFriends"))){
            FriendInventoryClick.friendClick(event);
            return;
        }
        if (inv.getName().contains("Edit:")){
            if (!inv.getName().contains("Rank")){
                GuildEditSpecificPlayerClick.editSpecificClick(event);
                return;
            } else {
                RankEditClick.rankEditClick(event);
                return;
            }
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&bOnline Applicants"))){
            GuildManagementClick.appClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("Guild Edit Menu"))){
            GuildManagementClick.gManagementClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&6Edit &bGuild &6Players"))){
            GuildPlayerEditMenuClick.playerEditClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&bGuild &6Players"))){
            GuildPlayersViewClick.playerViewClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&bTeleport Request"))){
            IncommingTPRequestClick.teleportRequestClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("Guild Menu"))){
            MainGuildMenuClick.gMenuClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&bSkyKipz &2Menu"))){
            MainMenuClick.menuClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&bShopping Mall"))){
            MainShoppingCartClick.invClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&6Party Menu"))){
            PartyClick.onClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("Rank Menu"))){
            RankEdit.rankEdit(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&6Tools Category"))){
            ToolCatClick.invClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&6Weapons Category"))){
            WeaponCatClick.invClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&bWeapons"))){
            WeaponSubCatClick.invClick(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("Create Guild"))){
            CreateGuild.stepOne(event);
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&6Recruiting Guilds"))){
            CreateGuild.appClick(event);
            return;
        }
        if(event.getInventory().getName().equals("Rolling For:")){
            RollClick.onItemClick(event);
            return;
        }
        Player p = (Player)event.getWhoClicked();
        if (Maps.rankPerms.containsKey(p.getName())){
//            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            GuildRanks rank = Maps.rankPerms.get(p.getName());
            if (!inv.getName().equalsIgnoreCase(rank.getName())){
                return;
            }
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
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
            return;
        }
        if (event.getCurrentItem() == null){
            return;
        }
        if (!event.getCurrentItem().hasItemMeta() || !event.getCurrentItem().getItemMeta().hasDisplayName()){
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.color("&bSkyKipz &6Quick &bMenu"))){
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
        }
    }
    /**
     *
     * @param event chat inventory click listener
     */
     @SuppressWarnings("deprecation")
	public void chatClick(InventoryClickEvent event){
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        if (event.getCurrentItem() != null && !event.getCurrentItem().getType().equals(Material.AIR)){
            ItemStack iS = event.getCurrentItem();
            if (!iS.hasItemMeta()){
                return;
            }
            ItemMeta iM = iS.getItemMeta();
            if (!iM.hasDisplayName()){
                return;
            }
            if (!(event.getWhoClicked() instanceof Player)){
                return;
            }
            Player p = (Player)event.getWhoClicked();
            if (event.getRawSlot() == 8){
                p.closeInventory();
                p.openInventory(MenuInventory.menu());
                return;
            }
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            String name = iM.getDisplayName();
            if (event.isShiftClick()){
                cP.setActiveChannel(name);
                return;
            }
            if (event.isLeftClick() && iS.getType().equals(Material.WEB)){
                iS.setType(Material.WOOL);
                short dura = 0;
                iS.setDurability(dura);
                cP.getInactiveChannels().remove(name);
                cP.getChannelColors().remove(name);
                cP.getChannelColors().put(name, "&f");
                p.updateInventory();
                return;
            }
            if (event.isRightClick()){
                iS.setType(Material.WEB);
                cP.getInactiveChannels().add(name);
                p.updateInventory();
                return;
            }
            if (event.isLeftClick()){
                iS.setType(Material.WOOL);
                cP.getChannelColors().remove(name);
                short i = iS.getDurability();
                i++;
                if (i > 15){
                    i = 0;
                }
                iS.setDurability(i);
                String color = "";
                if (i == 15){
                    color = "&0";
                }
                if (i == 11){
                    color = "&1";
                }
                if (i == 13){
                    color = "&2";
                }
                if (i == 13){
                    color = "&3";
                }
                if (i == 14){
                    color = "&4";
                }
                if (i == 10){
                    color = "&5";
                }
                if (i == 1){
                    color = "&6";
                }
                if (i == 8){
                    color = "&7";
                }
                if (i == 7){
                    color = "&8";
                }
                if (i == 9){
                    color = "&9";
                }
                if (i == 5){
                    color = "&a";
                }
                if (i == 3){
                    color = "&b";
                }
                if (i == 6){
                    color = "&c";
                }
                if (i == 2){
                    color = "&d";
                }
                if (i == 4){
                    color = "&e";
                }
                if (i == 0){
                    color = "&f";
                }
                cP.getChannelColors().put(name, color);
                p.updateInventory();
            }
        }
    }

}
