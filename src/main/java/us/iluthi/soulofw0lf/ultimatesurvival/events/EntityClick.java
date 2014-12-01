package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/19/13
 * Time: 6:20 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class EntityClick implements Listener {
    public EntityClick(){
        Bukkit.getPluginManager().registerEvents(this, UltimateSurvival.getInstance());
    }
    @EventHandler
    public void villageClick(PlayerInteractEntityEvent event){
        Player p = event.getPlayer();
        if (event.getRightClicked() instanceof Villager){
            Villager villager = (Villager)event.getRightClicked();
            event.setCancelled(true);
            Zombie zomb = (Zombie)(villager.getLocation().getWorld().spawnEntity(villager.getLocation(), EntityType.ZOMBIE));
            zomb.setVillager(true);
            zomb.setMaxHealth(60);
            zomb.setHealth(60);
            zomb.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 3), false);
            zomb.setCustomName(p.getName() + "'s killer");
            zomb.setCustomNameVisible(true);
            for (Player player : Bukkit.getOnlinePlayers()){
                if (player.getWorld().getName().equalsIgnoreCase(p.getWorld().getName())){
                    if (player.getLocation().distance(zomb.getLocation()) <= 30){
                        player.sendMessage(ChatUtil.color("&f[&4" + p.getName() + "'s Killer&f] &2I shall have my vengeance! How dare you expose me!!"));
                    }
                }
            }
            villager.remove();
        }
    }
}
