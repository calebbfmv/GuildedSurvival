package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 4:21 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class EmeraldFire implements Listener {
    public EmeraldFire(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    @SuppressWarnings("deprecation")
    @EventHandler
    public void emeraldClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !event.getAction().equals(Action.RIGHT_CLICK_AIR)){
            return;
        }
        if (p.getItemInHand() == null){
            return;
        }
        if (!p.getItemInHand().getType().equals(Material.EMERALD)){
            return;
        }
        ItemStack iS = p.getItemInHand();
        if (iS.getAmount() <= 1){
            p.getInventory().setItem(p.getInventory().getHeldItemSlot(), null);
        } else {
            iS.setAmount(iS.getAmount() - 1);
        }
        p.updateInventory();
        SmallFireball fire = p.launchProjectile(SmallFireball.class);
        fire.setBounce(true);
        fire.setVelocity(fire.getVelocity().multiply(8));
        fire.setIsIncendiary(true);
    }
}
