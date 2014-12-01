package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Weapon;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.EditWeapon;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.WeaponsCat;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/18/13
 * Time: 2:22 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class WeaponSubCatClick {
    public static void invClick (InventoryClickEvent event){
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        Player p = (Player)event.getWhoClicked();
        if (event.getRawSlot() == 8){
            p.closeInventory();
            p.openInventory(WeaponsCat.inv());
            return;
        }
        if (event.getRawSlot() == 2 || event.getRawSlot() == 3 || event.getRawSlot() == 4 || event.getRawSlot() == 5 || event.getRawSlot() == 6){
            p.closeInventory();
            Maps.editItem.put(p.getName(), new Weapon(event.getCurrentItem()));
            p.openInventory(EditWeapon.inv(p));
        }
    }
}
