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
 * Date: 12/1/13
 * Time: 11:48 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class EditHQ {
    public static Inventory inv(Player p){
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        GuildRanks gR = g.getRanks().get(cP.getGuildRank());
        Inventory inv = Bukkit.createInventory(null, 18, ChatUtil.color("&4HQ"));
        if (!g.hasHq() && gR.getRankPermissions().get(RankPerms.SET_HQ)){
            ItemStack addHq = new ItemStack(Material.BRICK, 1);
            ItemMeta addMeta = addHq.getItemMeta();
            addMeta.setDisplayName(ChatUtil.color("&bPlace HQ"));
            List<String> addLore = new ArrayList<>();
            addLore.add(ChatUtil.color("&2Click here to set your guild"));
            addLore.add(ChatUtil.color("&2HQ Location."));
            addMeta.setLore(addLore);
            addHq.setItemMeta(addMeta);
            inv.setItem(1, addHq);
        } else {
            if (gR.getRankPermissions().get(RankPerms.REMOVE_HQ)){
                ItemStack addHq = new ItemStack(Material.SKULL_ITEM, 1);
                ItemMeta addMeta = addHq.getItemMeta();
                addMeta.setDisplayName(ChatUtil.color("&4Remove HQ"));
                List<String> addLore = new ArrayList<>();
                addLore.add(ChatUtil.color("&4Click here to delete your"));
                addLore.add(ChatUtil.color("&4guild HQ Location."));
                addMeta.setLore(addLore);
                addHq.setItemMeta(addMeta);
                inv.setItem(1, addHq);
            }
        }
        if (!g.getAggressors().isEmpty()){
            ItemStack addHq = new ItemStack(Material.IRON_SWORD, 1);
            ItemMeta addMeta = addHq.getItemMeta();
            addMeta.setDisplayName(ChatUtil.color("&eView Agressors"));
            List<String> addLore = new ArrayList<>();
            addLore.add(ChatUtil.color("&bClick here to view guilds"));
            addLore.add(ChatUtil.color("&bthat are actively attacking"));
            addLore.add(ChatUtil.color("&byour guild."));
            addMeta.setLore(addLore);
            addHq.setItemMeta(addMeta);
            inv.setItem(3, addHq);
        }
        if (gR.getRankPermissions().get(RankPerms.SET_HOME)){
            ItemStack addHq = new ItemStack(Material.BED, 1);
            ItemMeta addMeta = addHq.getItemMeta();
            addMeta.setDisplayName(ChatUtil.color("&2Set Home"));
            List<String> addLore = new ArrayList<>();
            addLore.add(ChatUtil.color("&6Click here to set the"));
            addLore.add(ChatUtil.color("&6home location for"));
            addLore.add(ChatUtil.color("&6your guild."));
            addMeta.setLore(addLore);
            addHq.setItemMeta(addMeta);
            inv.setItem(5, addHq);
        }
        if (gR.getRankPermissions().get(RankPerms.SET_PEACEFUL)){
            ItemStack addHq = new ItemStack(Material.FEATHER, 1);
            ItemMeta addMeta = addHq.getItemMeta();
            addMeta.setDisplayName(ChatUtil.color("&fSet Peaceful"));
            List<String> addLore = new ArrayList<>();
            addLore.add(ChatUtil.color("&fClick here to set your"));
            addLore.add(ChatUtil.color("&fguild to peaceful."));
            addLore.add(ChatUtil.color("&eWhile in peaceful mode"));
            addLore.add(ChatUtil.color("&eyou cannot have your guild"));
            addLore.add(ChatUtil.color("&eattacked, or attack others"));
            addLore.add(ChatUtil.color("&ebut you gain no guild strength."));
            addMeta.setLore(addLore);
            addHq.setItemMeta(addMeta);
            inv.setItem(7, addHq);
        }
        if (gR.getRankPermissions().get(RankPerms.USE_POINTS) && g.hasHq()){
            ItemStack addHq = new ItemStack(Material.MAP, 1);
            ItemMeta addMeta = addHq.getItemMeta();
            addMeta.setDisplayName(ChatUtil.color("&6Expand HQ"));
            List<String> addLore = new ArrayList<>();
            addLore.add(ChatUtil.color("&2Click here to expand your"));
            addLore.add(ChatUtil.color("&2guild's headquarters."));
            addMeta.setLore(addLore);
            addHq.setItemMeta(addMeta);
            inv.setItem(9, addHq);
        }
        if (gR.getRankPermissions().get(RankPerms.SET_STOREROOM)){
            ItemStack addHq = new ItemStack(Material.CHEST, 1);
            ItemMeta addMeta = addHq.getItemMeta();
            addMeta.setDisplayName(ChatUtil.color("&6Set Storeroom"));
            List<String> addLore = new ArrayList<>();
            addLore.add(ChatUtil.color("&2Click here to set your"));
            addLore.add(ChatUtil.color("&2guild's Storeroom location."));
            addLore.add(ChatUtil.color("&bYour storeroom is the only"));
            addLore.add(ChatUtil.color("&bsafe part of your HQ when"));
            addLore.add(ChatUtil.color("&ball guild members are offline."));
            addMeta.setLore(addLore);
            addHq.setItemMeta(addMeta);
            inv.setItem(13, addHq);
        }
        if (gR.getRankPermissions().get(RankPerms.SET_EXIT)){
            ItemStack addHq = new ItemStack(Material.PAPER, 1);
            ItemMeta addMeta = addHq.getItemMeta();
            addMeta.setDisplayName(ChatUtil.color("&6Set Exit Message"));
            List<String> addLore = new ArrayList<>();
            addLore.add(ChatUtil.color("&2Click here to set your"));
            addLore.add(ChatUtil.color("&2guild's Exit message."));
            addMeta.setLore(addLore);
            addHq.setItemMeta(addMeta);
            inv.setItem(15, addHq);
        }
        if (gR.getRankPermissions().get(RankPerms.SET_ENTRY)){
            ItemStack addHq = new ItemStack(Material.BOOK_AND_QUILL, 1);
            ItemMeta addMeta = addHq.getItemMeta();
            addMeta.setDisplayName(ChatUtil.color("&6Set Entry"));
            List<String> addLore = new ArrayList<>();
            addLore.add(ChatUtil.color("&2Click here to set your"));
            addLore.add(ChatUtil.color("&2guild's Entry message."));
            addMeta.setLore(addLore);
            addHq.setItemMeta(addMeta);
            inv.setItem(11, addHq);
        }

        ItemStack returnItem = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta returnMeta = returnItem.getItemMeta();
        returnMeta.setDisplayName(ChatUtil.color("&6Back Button"));
        List<String> returnLore = new ArrayList<>();
        returnLore.add(ChatUtil.color("&bClick here to return to"));
        returnLore.add(ChatUtil.color("&bto the main menu."));
        returnMeta.setLore(returnLore);
        returnItem.setItemMeta(returnMeta);
        inv.setItem(17, returnItem);
        return inv;
    }
}
