package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.enums.RankPerms;
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
 * Date: 12/2/13
 * Time: 12:34 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class EditSpecificGuildPlayerInventory {
    public static Inventory inv(Player p, Player edit){
        Inventory inv = Bukkit.createInventory(null, 18, ChatUtil.color("Edit: " + edit.getName()));
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        GuildRanks gR = g.getRanks().get(cP.getGuildRank());

        if (gR.getRankPermissions().get(RankPerms.KICK)){
            ItemStack iS = new ItemStack(Material.SKULL_ITEM, 1);
            ItemMeta iM = iS.getItemMeta();
            iM.setDisplayName(ChatUtil.color("&4Kick Player"));
            List<String> lore = new ArrayList<>();
            lore.add(ChatUtil.color("&2Click here to kick"));
            lore.add(ChatUtil.color("&2this player from"));
            lore.add(ChatUtil.color("&2your guild."));
            iM.setLore(lore);
            iS.setItemMeta(iM);
            inv.setItem(2, iS);
        }

        if (gR.getRankPermissions().get(RankPerms.PROMOTE)){
            ItemStack iS = new ItemStack(Material.EXP_BOTTLE, 1);
            ItemMeta iM = iS.getItemMeta();
            iM.setDisplayName(ChatUtil.color("&6Promote Player"));
            List<String> lore = new ArrayList<>();
            lore.add(ChatUtil.color("&2Click here to promote"));
            lore.add(ChatUtil.color("&2this player."));
            iM.setLore(lore);
            iS.setItemMeta(iM);
            inv.setItem(4, iS);
        }

        if (gR.getRankPermissions().get(RankPerms.DEMOTE)){
            ItemStack iS = new ItemStack(Material.COAL_BLOCK, 1);
            ItemMeta iM = iS.getItemMeta();
            iM.setDisplayName(ChatUtil.color("&eDemote Player"));
            List<String> lore = new ArrayList<>();
            lore.add(ChatUtil.color("&2Click here to demote"));
            lore.add(ChatUtil.color("&2this player."));
            iM.setLore(lore);
            iS.setItemMeta(iM);
            inv.setItem(6, iS);
        }

        if (gR.getRankPermissions().get(RankPerms.SET_RANK)){
            ItemStack iS = new ItemStack(Material.BOOK_AND_QUILL, 1);
            ItemMeta iM = iS.getItemMeta();
            iM.setDisplayName(ChatUtil.color("&bSet Rank"));
            List<String> lore = new ArrayList<>();
            lore.add(ChatUtil.color("&2Click here to set"));
            lore.add(ChatUtil.color("&2this player to a"));
            lore.add(ChatUtil.color("&2specific rank."));
            iM.setLore(lore);
            iS.setItemMeta(iM);
            inv.setItem(12, iS);
        }

        if (gR.getRankPermissions().get(RankPerms.SET_LEADER)){
            ItemStack iS = new ItemStack(Material.DIAMOND_HELMET, 1);
            ItemMeta iM = iS.getItemMeta();
            iM.setDisplayName(ChatUtil.color("&bSet Leader"));
            List<String> lore = new ArrayList<>();
            lore.add(ChatUtil.color("&2Click here to set"));
            lore.add(ChatUtil.color("&2this player as the"));
            lore.add(ChatUtil.color("&2new Guild Leader."));
            lore.add(ChatUtil.color("&4WARNING: This cannot be undone."));
            iM.setLore(lore);
            iS.setItemMeta(iM);
            inv.setItem(14, iS);
        }
        ItemStack returnItem = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta returnMeta = returnItem.getItemMeta();
        returnMeta.setDisplayName(ChatUtil.color("&6Back Button"));
        List<String> returnLore = new ArrayList<>();
        returnLore.add(ChatUtil.color("&bClick here to return to"));
        returnLore.add(ChatUtil.color("&bto the Player Selection Window."));
        returnMeta.setLore(returnLore);
        returnItem.setItemMeta(returnMeta);
        inv.setItem(17, returnItem);
        return inv;
    }
}
