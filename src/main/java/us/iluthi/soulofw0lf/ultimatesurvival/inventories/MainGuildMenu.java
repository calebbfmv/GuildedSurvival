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
 * Time: 12:28 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class MainGuildMenu {
    public static Inventory inv(Guild g, Player p){
        Inventory inv = Bukkit.createInventory(null, 9, ChatUtil.color("Guild Menu"));
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        GuildRanks gR;
        if ((gR = g.getRanks().get(cP.getGuildRank())) == null){
            gR = g.getDefaultRank();
        }

        if (!g.isPeaceful()){
            int i = g.getGuildFortifications();
            ItemStack motdItem = new ItemStack(Material.GOLD_NUGGET, 1);
            ItemMeta motdMeta = motdItem.getItemMeta();
            motdMeta.setDisplayName(ChatUtil.color("&6Fortifications"));
            List<String> motdLore = new ArrayList<>();
            motdLore.add(ChatUtil.color("&bClick here to add fortification"));
            motdLore.add(ChatUtil.color("&bpoints to your  guild."));
            motdLore.add(ChatUtil.color("&bCurrent points: &6" + i));
            if (g.isHome()){
                motdLore.add(ChatUtil.color("&rRight click to transport"));
                motdLore.add(ChatUtil.color("&rto your guild home location."));
            }
            motdMeta.setLore(motdLore);
            motdItem.setItemMeta(motdMeta);
            inv.setItem(0, motdItem);
        }
        ItemStack placeItem = new ItemStack(Material.SKULL_ITEM, 1);
        ItemMeta placeMeta = placeItem.getItemMeta();
        placeMeta.setDisplayName(ChatUtil.color("&6View Players"));
        List<String> placeLore = new ArrayList<>();
        placeLore.add(ChatUtil.color("&bClick here to view"));
        placeLore.add(ChatUtil.color("&bthe players in your guild."));
        placeMeta.setLore(placeLore);
        placeItem.setItemMeta(placeMeta);
        inv.setItem(2, placeItem);

        if (g.isMessageOfTheDay()){
            ItemStack motdItem = new ItemStack(Material.BEACON, 1);
            ItemMeta motdMeta = motdItem.getItemMeta();
            motdMeta.setDisplayName(ChatUtil.color("&6Message of The Day"));
            List<String> motdLore = new ArrayList<>();
            motdLore.add(ChatUtil.color("&bClick here to view"));
            motdLore.add(ChatUtil.color("&byour guilds MOTD."));
            motdMeta.setLore(motdLore);
            motdItem.setItemMeta(motdMeta);
            inv.setItem(4, motdItem);
        }

        if (gR.getRankPermissions().get(RankPerms.GUILD_MANAGEMENT)){
            ItemStack manageItem = new ItemStack(Material.PAPER, 1);
            ItemMeta manageMeta = manageItem.getItemMeta();
            manageMeta.setDisplayName(ChatUtil.color("&6Guild Management"));
            List<String> manageLore = new ArrayList<>();
            manageLore.add(ChatUtil.color("&bClick here to edit your"));
            manageLore.add(ChatUtil.color("&bguild settings."));
            manageMeta.setLore(manageLore);
            manageItem.setItemMeta(manageMeta);
            inv.setItem(6, manageItem);
        }

        ItemStack returnItem = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta returnMeta = returnItem.getItemMeta();
        returnMeta.setDisplayName(ChatUtil.color("&6Back Button"));
        List<String> returnLore = new ArrayList<>();
        returnLore.add(ChatUtil.color("&bClick here to return to"));
        returnLore.add(ChatUtil.color("&bto the main menu."));
        returnMeta.setLore(returnLore);
        returnItem.setItemMeta(returnMeta);
        inv.setItem(8, returnItem);
        return inv;
    }
}
