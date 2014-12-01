package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.EditSpecificGuildPlayerInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.MainGuildMenu;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 12:32 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class GuildPlayerEditMenuClick {
    public static void playerEditClick(InventoryClickEvent event){
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)){
            return;
        }
        ItemStack iS = event.getCurrentItem();
        if (!iS.hasItemMeta() || !iS.getItemMeta().hasDisplayName()){
            return;
        }
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player)event.getWhoClicked();
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        if (event.getRawSlot() == 44){
            p.closeInventory();
            p.openInventory(MainGuildMenu.inv(g, p));
            return;
        }
        String online = "online";
        for (String lore : iS.getItemMeta().getLore()){
            if (lore.contains("offline")){
                online = "offline";
            }
        }
        if (online.equalsIgnoreCase("offline")){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " You may only perform commands on online players."));
            return;
        }
        String name = iS.getItemMeta().getDisplayName();
        if (Bukkit.getPlayer(name) == null){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " It seems that player has logged offline!"));
            return;
        }
        Player player = Bukkit.getPlayer(name);
        p.closeInventory();
        p.openInventory(EditSpecificGuildPlayerInventory.inv(p, player));
        return;

    }
}
