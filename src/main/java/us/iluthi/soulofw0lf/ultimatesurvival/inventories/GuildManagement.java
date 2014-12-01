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

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 12:27 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class GuildManagement {
    public static Inventory inv(Guild g, Player p){
        Inventory inv = Bukkit.createInventory(null, 18, ChatUtil.color("Guild Edit Menu"));
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        GuildRanks gR = g.getRanks().get(cP.getGuildRank());
        if (gR.getRankPermissions().get(RankPerms.EDIT_RANKS)){
            ItemStack placeItem = new ItemStack(Material.ANVIL, 1);
            ItemMeta placeMeta = placeItem.getItemMeta();
            placeMeta.setDisplayName(ChatUtil.color("&6Edit Ranks"));
            List<String> placeLore = new ArrayList<>();
            placeLore.add(ChatUtil.color("&bClick here to edit"));
            placeLore.add(ChatUtil.color("&bthe ranks in your guild."));
            placeMeta.setLore(placeLore);
            placeItem.setItemMeta(placeMeta);
            inv.setItem(2, placeItem);
        }

        if (gR.getRankPermissions().get(RankPerms.DEMOTE) || gR.getRankPermissions().get(RankPerms.PROMOTE) || gR.getRankPermissions().get(RankPerms.KICK) || gR.getRankPermissions().get(RankPerms.SET_RANK)){
            ItemStack playerItem = new ItemStack(Material.SKULL_ITEM, 1);
            ItemMeta playerMeta = playerItem.getItemMeta();
            playerMeta.setDisplayName(ChatUtil.color("&6Edit Guild Players"));
            List<String> playerLore = new ArrayList<>();
            playerLore.add(ChatUtil.color("&bClick here to edit the"));
            playerLore.add(ChatUtil.color("&bplayers in your guild."));
            playerMeta.setLore(playerLore);
            playerItem.setItemMeta(playerMeta);
            inv.setItem(4, playerItem);
        }

        if (gR.getRankPermissions().get(RankPerms.BUY_PEACE) || gR.getRankPermissions().get(RankPerms.REMOVE_HQ) || gR.getRankPermissions().get(RankPerms.SET_HOME) || gR.getRankPermissions().get(RankPerms.SET_HQ) || gR.getRankPermissions().get(RankPerms.SET_PEACEFUL) || gR.getRankPermissions().get(RankPerms.SET_STOREROOM)){
            ItemStack hqItem = new ItemStack(Material.BED, 1);
            ItemMeta hqMeta = hqItem.getItemMeta();
            hqMeta.setDisplayName(ChatUtil.color("&6Manage HQ"));
            List<String> hqLore = new ArrayList<>();
            hqLore.add(ChatUtil.color("&bClick here to modify"));
            hqLore.add(ChatUtil.color("&bguild HQ settings and"));
            hqLore.add(ChatUtil.color("&bwar settings."));
            hqMeta.setLore(hqLore);
            hqItem.setItemMeta(hqMeta);
            inv.setItem(6, hqItem);
        }

        if (gR.getRankPermissions().get(RankPerms.RECRUITMENT)){
            ItemStack motdItem = new ItemStack(Material.APPLE, 1);
            ItemMeta motdMeta = motdItem.getItemMeta();
            motdMeta.setDisplayName(ChatUtil.color("&6Toggle Recruitment"));
            List<String> motdLore = new ArrayList<>();
            motdLore.add(ChatUtil.color("&bLeft click to turn on"));
            motdLore.add(ChatUtil.color("&bguild recruitment."));
            motdLore.add(ChatUtil.color("&4right click to turn it off."));
            motdLore.add(ChatUtil.color("&6Shift left click to set the"));
            motdLore.add(ChatUtil.color("&6guilds recruitment message."));
            motdLore.add(ChatUtil.color("&2Shift right click to view"));
            motdLore.add(ChatUtil.color("&2guild applicants."));
            motdMeta.setLore(motdLore);
            motdItem.setItemMeta(motdMeta);
            inv.setItem(10, motdItem);
        }

        if (gR.getRankPermissions().get(RankPerms.SET_MOTD)){
            ItemStack motdItem = new ItemStack(Material.PAPER, 1);
            ItemMeta motdMeta = motdItem.getItemMeta();
            motdMeta.setDisplayName(ChatUtil.color("&6Edit Motd"));
            List<String> motdLore = new ArrayList<>();
            motdLore.add(ChatUtil.color("&bClick here to edit your"));
            motdLore.add(ChatUtil.color("&bguild message of the day."));
            motdMeta.setLore(motdLore);
            motdItem.setItemMeta(motdMeta);
            inv.setItem(12, motdItem);
        }

        if (gR.getRankPermissions().get(RankPerms.DISBAND)){
            ItemStack motdItem = new ItemStack(Material.TNT, 1);
            ItemMeta motdMeta = motdItem.getItemMeta();
            motdMeta.setDisplayName(ChatUtil.color("&6Disband Guild"));
            List<String> motdLore = new ArrayList<>();
            motdLore.add(ChatUtil.color("&bClick here to disband your"));
            motdLore.add(ChatUtil.color("&bguild. &4WARNING: &bThis cannot."));
            motdLore.add(ChatUtil.color("&bbe undone!!!!"));
            motdMeta.setLore(motdLore);
            motdItem.setItemMeta(motdMeta);
            inv.setItem(14, motdItem);
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
