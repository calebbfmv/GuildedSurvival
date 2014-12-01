package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
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
 * Time: 12:30 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class GuildPlayersView {
    public static Inventory inv(Guild g, Player p){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&bGuild &6Players"));
        List<String> players = new ArrayList<>();
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        GuildRanks gR = g.getRanks().get(cP.getGuildRank());
        for (String name : g.getPlayers()){
            if (Bukkit.getPlayer(name) == null){
                continue;
            }
            if (name.equalsIgnoreCase(p.getName())){
                players.add(name);
                ItemStack returnItem = new ItemStack(Material.SKULL_ITEM, 1);
                SkullMeta returnMeta = (SkullMeta)returnItem.getItemMeta();
                returnMeta.setDisplayName(name);
                List<String> returnLore = new ArrayList<>();
                returnLore.add(ChatUtil.color("&bLeft Click to view your"));
                returnLore.add(ChatUtil.color("&btotal guild contributions."));
                returnLore.add(ChatUtil.color("&4Right click to leave this guild."));
                returnLore.add(ChatUtil.color("&4WARNING: This cannot be undone!"));
                returnMeta.setLore(returnLore);
                returnItem.setItemMeta(returnMeta);
                inv.addItem(returnItem);
            } else {
                if (gR.getRankPermissions().get(RankPerms.GUILD_TP)){
                    players.add(name);
                    ItemStack returnItem = new ItemStack(Material.SKULL_ITEM, 1);
                    SkullMeta returnMeta = (SkullMeta)returnItem.getItemMeta();
                    returnMeta.setDisplayName(name);
                    List<String> returnLore = new ArrayList<>();
                    returnLore.add(ChatUtil.color("&bLeft Click to invite this player"));
                    returnLore.add(ChatUtil.color("&bto a party."));
                    returnLore.add(ChatUtil.color("&6Right click to teleport."));
                    returnLore.add(ChatUtil.color("&6to this player."));
                    returnLore.add(ChatUtil.color("&bStatus: &2Online."));
                    returnMeta.setLore(returnLore);
                    returnItem.setItemMeta(returnMeta);
                    inv.addItem(returnItem);
                } else {
                    if (gR.getRankPermissions().get(RankPerms.GUILD_TP_REQUEST)){
                        players.add(name);
                        ItemStack returnItem = new ItemStack(Material.SKULL_ITEM, 1);
                        SkullMeta returnMeta = (SkullMeta)returnItem.getItemMeta();
                        returnMeta.setDisplayName(name);
                        List<String> returnLore = new ArrayList<>();
                        returnLore.add(ChatUtil.color("&bLeft Click to invite this player"));
                        returnLore.add(ChatUtil.color("&bto a party."));
                        returnLore.add(ChatUtil.color("&6Right click to request a."));
                        returnLore.add(ChatUtil.color("&6teleport from this player."));
                        returnLore.add(ChatUtil.color("&bStatus: &2Online."));
                        returnMeta.setLore(returnLore);
                        returnItem.setItemMeta(returnMeta);
                        inv.addItem(returnItem);
                    } else {
                        players.add(name);
                        ItemStack returnItem = new ItemStack(Material.SKULL_ITEM, 1);
                        SkullMeta returnMeta = (SkullMeta)returnItem.getItemMeta();
                        returnMeta.setDisplayName(name);
                        List<String> returnLore = new ArrayList<>();
                        returnLore.add(ChatUtil.color("&bLeft Click to invite this player"));
                        returnLore.add(ChatUtil.color("&bto a party."));
                        returnLore.add(ChatUtil.color("&bStatus: &2Online."));
                        returnMeta.setLore(returnLore);
                        returnItem.setItemMeta(returnMeta);
                        inv.addItem(returnItem);
                    }
                }
            }
        }
        for (String name : g.getPlayers()){
            if (players.contains(name)){
                continue;
            }
            players.add(name);
            ItemStack returnItem = new ItemStack(Material.SKULL_ITEM, 1);
            SkullMeta returnMeta = (SkullMeta)returnItem.getItemMeta();
            returnMeta.setDisplayName(name);
            List<String> returnLore = new ArrayList<>();
            returnLore.add(ChatUtil.color("&bStatus: &4Offline."));
            returnMeta.setLore(returnLore);
            returnItem.setItemMeta(returnMeta);
            inv.addItem(returnItem);
        }

        return inv;
    }
}
