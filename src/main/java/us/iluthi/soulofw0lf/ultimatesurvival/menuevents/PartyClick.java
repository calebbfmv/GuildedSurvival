package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Party;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.MenuInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.PlayerInventory;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/21/13
 * Time: 7:40 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class PartyClick {
    public static void onClick(InventoryClickEvent event){
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player)event.getWhoClicked();
        ItemStack iS;
        if ((iS = event.getCurrentItem()) == null){
            return;
        }
        if (Party.getParty(p) == null){
            if (event.getRawSlot() == 44){
                p.closeInventory();
                p.openInventory(MenuInventory.menu());
                return;
            }
            if (iS.hasItemMeta() && iS.getItemMeta().hasDisplayName()){
                String name = iS.getItemMeta().getDisplayName();
                p.closeInventory();
                p.performCommand("party invite " + name);
                return;
            }
        }
        if (event.getRawSlot() == 8){
            p.closeInventory();
            p.openInventory(MenuInventory.menu());
            return;
        }
        if (event.getRawSlot() == 7){
            p.closeInventory();
            p.performCommand("party tpall");
            return;
        }
        if (iS.hasItemMeta() && iS.getItemMeta().hasDisplayName()){
            String name = iS.getItemMeta().getDisplayName();
            if (event.isShiftClick()){
                if (event.isLeftClick()){
                    p.closeInventory();
                    p.performCommand("party tp " + name);
                    return;
                }
                if (event.isRightClick()){
                    p.closeInventory();
                    p.performCommand("party promote " + name);
                    return;
                }
            }
            if (event.isLeftClick()){
                p.closeInventory();
                p.openInventory(PlayerInventory.playerInventory(Bukkit.getPlayer(name)));
                return;
            }
            if (event.isRightClick()){
                p.closeInventory();
                p.performCommand("party kick " + name);
                return;
            }
        }
    }
}
