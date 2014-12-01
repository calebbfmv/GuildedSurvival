package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
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
 * Time: 12:31 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class GuildPlayersEdit {
    public static Inventory inv(Guild g){
        Inventory inv = Bukkit.createInventory(null, 45, ChatUtil.color("&6Edit &bGuild &6Players"));
        List<String> players = new ArrayList<>();
        for (String name : g.getPlayers()){
            if (Bukkit.getPlayer(name) == null){
                continue;
            }
            players.add(name);
            ItemStack returnItem = new ItemStack(Material.SKULL_ITEM, 1);
            SkullMeta returnMeta = (SkullMeta)returnItem.getItemMeta();
            returnMeta.setDisplayName(name);
            List<String> returnLore = new ArrayList<>();
            returnLore.add(ChatUtil.color("&bClick here to adjust"));
            returnLore.add(ChatUtil.color("&bthis players guild settings."));
            returnLore.add(ChatUtil.color("&6Rank: &b" + CustomPlayer.getCP(name).getGuildRank()));
            returnLore.add(ChatUtil.color("&bStatus: &2Online."));
            returnMeta.setLore(returnLore);
            returnItem.setItemMeta(returnMeta);
            inv.addItem(returnItem);
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
            returnLore.add(ChatUtil.color("&bClick here to adjust"));
            returnLore.add(ChatUtil.color("&bthis players guild settings."));
            returnLore.add(ChatUtil.color("&bStatus: &4Offline."));
            returnMeta.setLore(returnLore);
            returnItem.setItemMeta(returnMeta);
            inv.addItem(returnItem);
        }

        return inv;
    }
}
