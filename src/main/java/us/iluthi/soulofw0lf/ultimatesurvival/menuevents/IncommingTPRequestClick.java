package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 12:35 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class IncommingTPRequestClick {
    public static void teleportRequestClick(InventoryClickEvent event){
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        if (event.getRawSlot() != 4){
            return;
        }
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player)event.getWhoClicked();
        String name = event.getCurrentItem().getItemMeta().getDisplayName();
        if (Bukkit.getPlayer(name) == null){
            p.sendMessage(ChatUtil.color(Strings.guildStub + " That player seems to no longer be online."));
            p.closeInventory();
            return;
        }
        Player pl = Bukkit.getPlayer(name);
        if (event.isRightClick()){
            pl.sendMessage(ChatUtil.color(Strings.guildStub + " This player has declined your teleport request."));
            p.closeInventory();
            return;
        }
        if (event.isLeftClick()){
            pl.teleport(p.getLocation());
            p.closeInventory();
            return;
        }
    }
}
