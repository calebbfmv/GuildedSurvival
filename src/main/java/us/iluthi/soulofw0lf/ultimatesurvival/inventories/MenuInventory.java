package us.iluthi.soulofw0lf.ultimatesurvival.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 6:48 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class MenuInventory implements Listener{
    public static ItemStack menuItem;
    public MenuInventory(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
        ItemStack iS = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta iM = iS.getItemMeta();
        iM.setDisplayName(ChatUtil.color("&bSkyKipz &6Quick &bMenu"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatUtil.color("&6Use this item to open"));
        lore.add(ChatUtil.color("&6your quick access menu!"));
        lore.add(ChatUtil.color("&6Http://galaxiesmc.com"));
        lore.add(ChatUtil.color("&6Current Coins: &2@c"));
        iM.setLore(lore);
        iS.setItemMeta(iM);
        menuItem = iS;
    }
    @EventHandler
    public void playerClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if (p.getItemInHand() == null || p.getItemInHand().getType().equals(Material.AIR)){
            return;
        }
        if (event.getAction().equals(Action.PHYSICAL) || event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
            return;
        }
        ItemStack iS = p.getItemInHand();
        if (!iS.hasItemMeta() || !iS.getItemMeta().hasDisplayName()){
            return;
        }
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if (p.isSneaking()){
                p.sendMessage(ChatUtil.color(Strings.tutStub + " visit http://galaxiesmc.com to view our forums or our shop!"));
            }
            if (iS.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.color("&bSkyKipz &6Quick &bMenu"))){
                event.setCancelled(true);
                p.openInventory(menu());
            }
        }
    }

    public static Inventory menu(){
        Inventory inv = Bukkit.createInventory(null, 18, ChatUtil.color("&bSkyKipz &2Menu"));
        ItemStack shop = new ItemStack(Material.EMERALD);
        ItemMeta shopMeta = shop.getItemMeta();
        shopMeta.setDisplayName(ChatUtil.color("&eShopKeeper"));
        List<String> shopLore = new ArrayList<>();
        shopLore.add(ChatUtil.color("&2Click here to open the"));
        shopLore.add(ChatUtil.color("&6SkyKipz &2Item Shop"));
        shopMeta.setLore(shopLore);
        shop.setItemMeta(shopMeta);
        inv.setItem(1, shop);

        ItemStack chat = new ItemStack(Material.BOOK);
        ItemMeta chatMeta = chat.getItemMeta();
        chatMeta.setDisplayName(ChatUtil.color("&eChat Menu"));
        List<String> chatLore = new ArrayList<>();
        chatLore.add(ChatUtil.color("&2Click here to open the"));
        chatLore.add(ChatUtil.color("&6SkyKipz &2Chat Menu"));
        chatMeta.setLore(chatLore);
        chat.setItemMeta(chatMeta);
        inv.setItem(3, chat);

        ItemStack guild = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta guildMeta = guild.getItemMeta();
        guildMeta.setDisplayName(ChatUtil.color("&eGuild Menu"));
        List<String> guildLore = new ArrayList<>();
        guildLore.add(ChatUtil.color("&2Click here to open the"));
        guildLore.add(ChatUtil.color("&6SkyKipz &2Guild Menu"));
        guildMeta.setLore(guildLore);
        guild.setItemMeta(guildMeta);
        inv.setItem(5, guild);

        ItemStack warp = new ItemStack(Material.PAPER);
        ItemMeta warpMeta = warp.getItemMeta();
        warpMeta.setDisplayName(ChatUtil.color("&eWarp List"));
        List<String> warpLore = new ArrayList<>();
        warpLore.add(ChatUtil.color("&2Click here to open your"));
        warpLore.add(ChatUtil.color("&2warp list."));
        warpMeta.setLore(warpLore);
        warp.setItemMeta(warpMeta);
        inv.setItem(7, warp);

        ItemStack warzone = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta warzoneMeta = warzone.getItemMeta();
        warzoneMeta.setDisplayName(ChatUtil.color("&eWar Zone Menu"));
        List<String> warzoneLore = new ArrayList<>();
        warzoneLore.add(ChatUtil.color("&2Click here to open your"));
        warzoneLore.add(ChatUtil.color("&2War Zone menu."));
        warzoneMeta.setLore(warzoneLore);
        warzone.setItemMeta(warzoneMeta);
        inv.setItem(9, warzone);

        Short dura = 3;
        ItemStack stats = new ItemStack(Material.SKULL_ITEM);
        ItemMeta statsMeta = stats.getItemMeta();
        statsMeta.setDisplayName(ChatUtil.color("&eDuel Manager"));
        List<String> statsLore = new ArrayList<>();
        statsLore.add(ChatUtil.color("&2Click here to open your"));
        statsLore.add(ChatUtil.color("&2duel manager."));
        statsMeta.setLore(statsLore);
        stats.setItemMeta(statsMeta);
        stats.setDurability(dura);
        inv.setItem(11, stats);



        ItemStack party = new ItemStack(Material.CAKE);
        ItemMeta partyMeta = party.getItemMeta();
        partyMeta.setDisplayName(ChatUtil.color("&eParty Menu"));
        List<String> partyLore = new ArrayList<>();
        partyLore.add(ChatUtil.color("&2Click here to access"));
        partyLore.add(ChatUtil.color("&2party commands!"));
        partyMeta.setLore(partyLore);
        party.setItemMeta(partyMeta);
        inv.setItem(13, party);

        ItemStack settings = new ItemStack(Material.BOOKSHELF);
        ItemMeta settingsMeta = settings.getItemMeta();
        settingsMeta.setDisplayName(ChatUtil.color("&eSettings Menu"));
        List<String> settingsLore = new ArrayList<>();
        settingsLore.add(ChatUtil.color("&2Click here to access"));
        settingsLore.add(ChatUtil.color("&2your personal settings."));
        settingsMeta.setLore(settingsLore);
        settings.setItemMeta(settingsMeta);
        inv.setItem(15, settings);
        
        return inv;
    }
}
