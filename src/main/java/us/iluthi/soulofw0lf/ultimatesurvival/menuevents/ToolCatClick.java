package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.ExcavationCat;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.ShopInventory;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.UtilityInv;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/19/13
 * Time: 3:39 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ToolCatClick {
    public static void invClick(InventoryClickEvent event){
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        Player p = (Player)event.getWhoClicked();
        if (event.getRawSlot() == 3){
            p.closeInventory();
            p.openInventory(ExcavationCat.inv());
            return;
        }
        if (event.getRawSlot() == 5){
            p.closeInventory();
            p.openInventory(UtilityInv.inv());
            return;
        }
        if (event.getRawSlot() == 8){
            p.closeInventory();
            p.openInventory(ShopInventory.inv());
            return;
        }
    }
}
