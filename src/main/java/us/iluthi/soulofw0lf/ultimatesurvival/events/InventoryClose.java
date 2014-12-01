package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.GuildRanks;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.CoinChange;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/22/13
 * Time: 10:18 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class InventoryClose implements Listener {
    public InventoryClose(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onClose(InventoryCloseEvent event){
        Player p = (Player)event.getPlayer();
        p.updateInventory();
        Inventory inv = event.getInventory();
        if (Maps.rankPerms.containsKey(p.getName())){
            GuildRanks rank = Maps.rankPerms.get(p.getName());
            if (inv.getName().equalsIgnoreCase(rank.getName())){
                Maps.rankPerms.remove(p.getName());
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You have finished creating your new Guild Rank!"));
                return;
            }
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&bEdit Armor"))){
            if (Maps.editArmor.containsKey(p.getName())){
                Maps.editArmor.remove(p.getName());
            }
            return;
        }
        if (inv.getName().equalsIgnoreCase(ChatUtil.color("&bEdit Weapon")) || inv.getName().equalsIgnoreCase(ChatUtil.color("&bEdit Tool"))){
            if (Maps.editItem.containsKey(p.getName())){
                Maps.editItem.remove(p.getName());
            }
            return;
        }
        if (!event.getInventory().getType().equals(InventoryType.ENDER_CHEST)){
            return;
        }
        for (ItemStack iS : event.getInventory().getContents()){
            if (iS == null || iS.getType().equals(Material.AIR)){
                continue;
            }
            Material m = iS.getType();
            if (!Maps.values.containsKey(m)){
                continue;
            }
            int i = iS.getAmount();
            int amount = i * Maps.values.get(m);
            if (!iS.getEnchantments().isEmpty()){
                for (Enchantment ench : iS.getEnchantments().keySet()){
                    amount = amount + (10*iS.getEnchantments().get(ench) * 10);
                }
            }
            CoinChange.add(p, amount, false);
            p.sendMessage(ChatUtil.color("&f[&2ShopKeeper&f] &bYou have sold "+ iS.getAmount()+" "+ iS.getType().name().toLowerCase().replace("_", " ") +" for "+amount+"!"));
        }
        inv.clear();
    }
}
