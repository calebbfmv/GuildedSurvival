package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.*;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 12:46 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class MainMenuClick {
    public static void menuClick(InventoryClickEvent event){
        if (event.getWhoClicked() instanceof Player){
            Player p = (Player)event.getWhoClicked();
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            if (event.getRawSlot() == 1){
                p.closeInventory();
                p.openInventory(ShopInventory.inv());
                return;
            }
            if (event.getRawSlot() == 3){
                p.closeInventory();
                p.openInventory(ChatInventory.build(p));
                return;
            }
            if (event.getRawSlot() == 5){
                if (!cP.isInGuild()){
                    p.closeInventory();
                    p.openInventory(NoGuild.inv());
                } else {
                    Guild g = Maps.allGuilds.get(cP.getGuildName());
                    p.closeInventory();
                    p.openInventory(MainGuildMenu.inv(g, p));
                }
                return;
            }
            if (event.getRawSlot() == 7){
                p.closeInventory();
                p.openInventory(WarpMenu.warpInv(p));
                return;
            }
            if (event.getRawSlot() == 11){
                p.closeInventory();
                p.openInventory(DuelInventory.duelMain(p));
                return;
            }

            if (event.getRawSlot() == 13){
                p.closeInventory();
                p.openInventory(PartyInventory.inv(p));
                return;
            }
            if (event.getRawSlot() == 15){
                p.closeInventory();
                p.openInventory(SettingsInventory.settingsMenu(p));
                return;
            }
            if (event.getRawSlot() == 9){
                if (!p.hasPermission("guilds.warzones")){
                    p.sendMessage(ChatUtil.color("&4Warzones can only be made or changed by VIP ranked members or higher!"));
                } else {
                    p.sendMessage(ChatUtil.color("&6This feature is coming soon!"));
                }
            }
        }
    }
}
