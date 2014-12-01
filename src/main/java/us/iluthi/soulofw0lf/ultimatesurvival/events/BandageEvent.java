package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/2/13
 * Time: 3:34 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class BandageEvent implements Listener {
    public BandageEvent(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.HIGHEST)
    public void bandageUse(PlayerInteractEvent event){
        final Player p = event.getPlayer();
        final CustomPlayer cP = CustomPlayer.getCP(p.getName());
        if (cP.isBandaging() || (p.getHealth() == p.getMaxHealth() && !p.hasPotionEffect(PotionEffectType.SLOW))){
            return;
        }
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !event.getAction().equals(Action.RIGHT_CLICK_AIR)){
            return;
        }
        if (p.getItemInHand() == null){
           return;
        }
        if (!p.getItemInHand().getType().equals(Material.PAPER)){
            return;
        }
        if (!p.getItemInHand().hasItemMeta() || !p.getItemInHand().getItemMeta().hasDisplayName()){
            return;
        }
        if (p.getItemInHand().getItemMeta().getDisplayName().contains("andage")){
            ItemStack iS = p.getItemInHand();
            if (iS.getAmount() <= 1){
                p.getInventory().setItem(p.getInventory().getHeldItemSlot(), null);
            } else {
                iS.setAmount(iS.getAmount() - 1);

            }
            p.updateInventory();
            p.setExp(1f);
            cP.setBandaging(true);
            if (p.hasPotionEffect(PotionEffectType.SLOW)){
                p.removePotionEffect(PotionEffectType.SLOW);
            }
            new BukkitRunnable(){
                Integer i = 10 * 20;
                double health = p.getMaxHealth()/i;
                Float f =  (1f/i);
                Integer X = (int)p.getLocation().getX();
                Integer Z = (int)p.getLocation().getZ();
                @Override
                public void run(){
                    if (p.isDead()){
                        p.setExp(0f);
                        cP.setBandaging(false);
                        cancel();
                        return;
                    }
                    if (p.getHealth() + health >= p.getMaxHealth()){
                        p.setHealth(p.getMaxHealth());
                        p.setExp(0f);
                        cP.setBandaging(false);
                        cancel();
                        return;
                    }
                    if (X != (int)p.getLocation().getX() || Z != (int)p.getLocation().getZ()){
                        p.setExp(0f);
                        cP.setBandaging(false);
                        cancel();
                        return;
                    }
                    if (i == 0){
                        p.setExp(0f);
                        cP.setBandaging(false);
                        cancel();
                        return;
                    }
                    p.setHealth(p.getHealth() + health);
                    p.setExp(p.getExp() - f);
                    i--;
                }
            }.runTaskTimer(UltimateSurvival.getInstance(), 0, 1);
        }
    }
}
