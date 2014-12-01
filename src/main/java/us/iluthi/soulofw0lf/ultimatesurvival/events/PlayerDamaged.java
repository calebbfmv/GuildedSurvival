package us.iluthi.soulofw0lf.ultimatesurvival.events;

import net.minecraft.server.v1_7_R1.DamageSource;
import net.minecraft.server.v1_7_R1.EntityLiving;
import net.minecraft.server.v1_7_R1.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_7_R1.util.CraftDamageSource;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Party;
import us.iluthi.soulofw0lf.ultimatesurvival.party.Util;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Locations;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

import java.lang.reflect.Constructor;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/23/13
 * Time: 11:58 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class PlayerDamaged implements Listener {
    public PlayerDamaged(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    private DamageSource dS(EntityDamageEvent.DamageCause dC){
        DamageSource dS = null;
        try {

            Constructor<?> cs = CraftDamageSource.class.getDeclaredConstructor(String.class);
            cs.setAccessible(true);
            dS = (net.minecraft.server.v1_7_R1.DamageSource)cs.newInstance(dC.name().toLowerCase());

        } catch (Exception e){
            e.printStackTrace();
        }
        return dS;
    }
    @EventHandler
    public void playerHurt(EntityDamageEvent event){
        if (event.getEntity().getWorld().getName().equals(Locations.spawnLoc.getWorld().getName()) && event.getEntity().getLocation().distance(Locations.spawnLoc) <= 150){
            event.setCancelled(true);
            return;
        }
        if (event.getEntity() instanceof Snowman){
            if (event.getCause().equals(EntityDamageEvent.DamageCause.LIGHTNING)){
                event.setDamage(0);
                event.setCancelled(true);
                return;
            }
        }
        if (event.getEntity() instanceof Player){
            Player p = (Player)event.getEntity();
            final String name = p.getName();

            CraftPlayer cp = (CraftPlayer)p;
            EntityPlayer ep = cp.getHandle();
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            if (event.getCause().equals(EntityDamageEvent.DamageCause.LIGHTNING)){
                if (!cP.isPvp()){
                    event.setDamage(0);
                    event.setCancelled(true);
                    return;
                }
            }
            if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
                if (f(dS(event.getCause()), (float)event.getDamage(), ep)  > 4 && f(dS(event.getCause()), (float)event.getDamage(), ep) < 12){
                    if (!p.hasPotionEffect(PotionEffectType.SLOW)){
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1200, 1), true);
                        p.sendMessage(ChatUtil.color("&f[&4Injury&f] &bYou have sprained your ankle."));
                    }
                }
                if (f(dS(event.getCause()), (float) event.getDamage(), ep) >= 12){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 9999999, 2), true);
                    p.sendMessage(ChatUtil.color("&f[&4Injury&f] &bYou have broken your leg, you should seek out medical aid immediately."));
                }
            }
            if (Lists.duelWaiting.contains(p.getName()) || Lists.recentlyduelled.contains(p.getName())){
                event.setCancelled(true);
                if (Lists.recentlyduelled.contains(p.getName())){
                    new BukkitRunnable(){
                        @Override
                        public void run(){
                            if (Lists.recentlyduelled.contains(name)){
                                Lists.recentlyduelled.remove(name);
                                CustomPlayer cp = CustomPlayer.getCP(name);
                                if (cp.isInDuel()){
                                    cp.setInDuel(false);
                                }
                            }
                        }
                    }.runTaskLater(UltimateSurvival.getInstance(), 81);
                }
                return;
            }
        }
    }
    private float f(net.minecraft.server.v1_7_R1.DamageSource d, float f, EntityLiving el)
    {
        try {
        java.lang.reflect.Method b = EntityLiving.class.getDeclaredMethod("b", DamageSource.class, float.class);
        java.lang.reflect.Method c = EntityLiving.class.getDeclaredMethod("c", DamageSource.class, float.class);
        b.setAccessible(true);
        c.setAccessible(true);
        f = (float)b.invoke(el, d, f);
        f = (float)c.invoke(el, d, f);
        } catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
    @EventHandler (priority = EventPriority.HIGH)
    public void onPlayerDmg(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player){
            Player a = (Player)event.getEntity();
            Player b = null;
            if(event.getDamager() instanceof Player)
                b = (Player)event.getDamager();
            if(event.getDamager() instanceof Arrow)
                if (((Arrow)event.getDamager()).getShooter() instanceof Player)
                    b = (Player)((Arrow)event.getDamager()).getShooter();
            Party p = null;
            if (b == null){
                return;
            }
            if((p = Util.getParty(a.getName())) == null)
                return;
            if(p.getMembers().contains(b.getName())){
                CustomPlayer cP1 = CustomPlayer.getCP(a.getName());
                CustomPlayer cP2 = CustomPlayer.getCP(b.getName());
                if (cP1.isInDuel() && cP2.isInDuel()){
                    if (cP1.getDuelPartner().equalsIgnoreCase(b.getName())){
                        return;
                    }
                }
                event.setCancelled(true);
            }
        }
    }
    @EventHandler (priority = EventPriority.HIGHEST)
    public void PlayerHit(EntityDamageByEntityEvent event){

        if (event.getEntity() instanceof Player){
            Player p = (Player)event.getEntity();
            CustomPlayer cP = CustomPlayer.getCP(p.getName());
            if (cP.isInWarZone()){
                return;
            }
            if (cP.isInDuel()){
                float fl = (float)event.getDamage();
                CraftPlayer cp = (CraftPlayer)p;
                EntityPlayer ep = cp.getHandle();
                if (p.getHealth() - f(dS(event.getCause()), fl, ep) <= 1){
                    event.setCancelled(true);
                    event.setDamage(0);
                    final String name = cP.getDuelPartner();
                    for (Player player : Bukkit.getOnlinePlayers()){
                        if (player.getWorld().getName().equals(p.getWorld().getName()) && player.getLocation().distance(p.getLocation()) <= 50){
                            player.sendMessage(ChatUtil.color("&f[&eDuels&f] &b" + name + " Has just defeated " + p.getName() + " in a duel!"));
                        }
                    }
                    final Player duel = Bukkit.getPlayer(name);
                    p.setHealth(p.getMaxHealth());
                    duel.setHealth(duel.getMaxHealth());
                    Lists.recentlyduelled.add(p.getName());
                    Lists.recentlyduelled.add(cP.getDuelPartner());
                    cP.setInDuel(false);
                    cP.setDuelPartner("");
                    CustomPlayer cD = CustomPlayer.getCP(duel.getName());
                    final String name2 = cD.getDuelPartner();
                    new BukkitRunnable(){
                        @Override
                        public void run(){
                            Lists.recentlyduelled.remove(name);
                            Lists.recentlyduelled.remove(name2);
                        }
                    }.runTaskLater(UltimateSurvival.getInstance(), 80);
                    cD.setDuelPartner("");
                    cD.setInDuel(false);
                    return;
                }
            }
            if (event.getDamager() instanceof Arrow){
                Arrow arrow = (Arrow)event.getDamager();
                if (arrow.getShooter() instanceof Player){
                    CustomPlayer cD = CustomPlayer.getCP(((Player)arrow.getShooter()).getName());
                    if (cP.isInGuild() && cD.isInGuild()){
                        if (cP.isInDuel() && cP.getDuelPartner().equalsIgnoreCase(((Player) arrow.getShooter()).getName())) {
                        } else {
                            if (cP.getGuildName().equalsIgnoreCase(cD.getGuildName())){
                                event.setCancelled(true);
                                return;
                            }
                        }
                        if (cP.getGuildRank().equalsIgnoreCase(cD.getGuildName())){
                            event.setCancelled(true);
                            return;
                        }
                        Guild g = Maps.allGuilds.get(cP.getGuildName());
                        Guild g2 = Maps.allGuilds.get(cD.getGuildName());
                        if (cP.isInGuildLands() && cP.getGuildNear().equalsIgnoreCase(g.getName())){
                            if (!g.getAggressors().contains(g2.getName())){
                                event.setCancelled(true);
                                return;
                            }
                        }
                    } else {
                        if (cP.isInGuild()){
                            Guild g = Maps.allGuilds.get(cP.getGuildName());
                            if (cP.isInGuildLands() && cP.getGuildNear().equalsIgnoreCase(g.getName())){
                                event.setCancelled(true);
                                return;
                            }
                        }
                    }
                    if (!cP.isPvp() && !cP.isInDuel()){
                        event.setCancelled(true);
                        return;
                    }
                    if (!cD.isPvp() && !cP.isInDuel()){
                        event.setCancelled(true);
                        return;
                    }
                }
            }
            if (event.getDamager() instanceof Player){
                CustomPlayer cD = CustomPlayer.getCP(((Player)event.getDamager()).getName());
                if (cP.isInGuild() && cD.isInGuild()){
                    if (cP.isInDuel() && cP.getDuelPartner().equalsIgnoreCase(((Player) event.getDamager()).getName())) {

                    } else {
                        if (cP.getGuildName().equalsIgnoreCase(cD.getGuildName())){
                            event.setCancelled(true);
                            return;
                        }
                        Guild g = Maps.allGuilds.get(cP.getGuildName());
                        Guild g2 = Maps.allGuilds.get(cD.getGuildName());
                        if (cP.isInGuildLands() && cP.getGuildNear().equalsIgnoreCase(g.getName())){
                            if (!g.getAggressors().contains(g2.getName())){
                                event.setCancelled(true);
                                return;
                            }
                        }
                    }
                } else {
                    if (cP.isInGuild()){
                        Guild g = Maps.allGuilds.get(cP.getGuildName());
                        if (cP.isInGuildLands() && cP.getGuildNear().equalsIgnoreCase(g.getName())){
                            event.setCancelled(true);
                            return;
                        }
                    }
                }
                if (!cP.isPvp() && !cP.isInDuel()){
                    event.setCancelled(true);
                    return;
                }
                if (!cD.isPvp() && !cP.isInDuel()){
                    event.setCancelled(true);
                    return;
                }
            }
            if (cP.isInDuel()){
                if (event.getDamager() instanceof Arrow){
                    Arrow arrow = (Arrow)event.getDamager();
                    if (arrow.getShooter() instanceof Player){
                        Player shooter = (Player)arrow.getShooter();
                        if (!cP.getDuelPartner().equalsIgnoreCase(shooter.getName())){
                            event.setCancelled(true);
                            return;
                        }
                    } else {
                        event.setCancelled(true);
                        return;
                    }
                }
                if (event.getDamager() instanceof Player){
                    Player dam = (Player)event.getDamager();
                    if (Bukkit.getPlayer(cP.getDuelPartner()) == null){
                        cP.setInDuel(false);
                    }
                    CustomPlayer cD = CustomPlayer.getCP(cP.getDuelPartner());
                    if (!cD.isInDuel()){
                        cP.setInDuel(false);
                    } else {
                        if (!cD.getDuelPartner().equalsIgnoreCase(p.getName())){
                            cP.setInDuel(false);
                        }
                    }

                    if (!cP.getDuelPartner().equalsIgnoreCase(dam.getName())){
                        event.setCancelled(true);
                        return;
                    }
                }
            }
            if (event.getDamager() instanceof Arrow){
                Arrow arrow = (Arrow)event.getDamager();
                if (arrow.getShooter() instanceof Player){
                    Player shooter = (Player)arrow.getShooter();
                    CustomPlayer cD = CustomPlayer.getCP(shooter.getName());
                    if (cD.isInDuel()){
                        if (!cD.getDuelPartner().equalsIgnoreCase(p.getName())){
                            event.setCancelled(true);
                            return;
                        }
                    }
                }
            }
            if (event.getDamager() instanceof SmallFireball){
                SmallFireball arrow = (SmallFireball)event.getDamager();
                if (arrow.getShooter() instanceof Player){
                    Player shooter = (Player)arrow.getShooter();
                    CustomPlayer cD = CustomPlayer.getCP(shooter.getName());
                    if (cD.isInDuel()){
                        if (!cD.getDuelPartner().equalsIgnoreCase(p.getName())){
                            event.setCancelled(true);
                            return;
                        }
                    }
                }
            }
            if (event.getDamager() instanceof Player){
                Player dam = (Player)event.getDamager();
                CustomPlayer cD = CustomPlayer.getCP(dam.getName());
                if (cD.isInDuel()){
                    if (!cD.getDuelPartner().equalsIgnoreCase(p.getName())){
                        event.setCancelled(true);
                        return;
                    }
                }
            }
            final short boots;
            final short chest;
            final short helm;
            final short legs;
            if (p.getInventory().getBoots() != null){
                boots = p.getInventory().getBoots().getDurability();
                p.getInventory().getBoots().setDurability((short)-500);
            } else {
                boots = -1;
            }
            if (p.getInventory().getLeggings() != null){
                legs = p.getInventory().getLeggings().getDurability();
                p.getInventory().getLeggings().setDurability((short)-500);
            } else {
                legs = -1;
            }
            if (p.getInventory().getChestplate() != null){
                chest = p.getInventory().getChestplate().getDurability();
                p.getInventory().getChestplate().setDurability((short)-500);
            } else {
                chest = -1;
            }
            if (p.getInventory().getHelmet() != null){
                helm = p.getInventory().getHelmet().getDurability();
                p.getInventory().getHelmet().setDurability((short)-500);
            } else {
                helm = -1;
            }
            final String name = p.getName();
            new BukkitRunnable(){
                @SuppressWarnings("deprecation")
				@Override
                public void run(){
                    Player pl;
                    if ((pl = Bukkit.getPlayer(name)) == null){
                        return;
                    }
                    if (boots != -1 && pl.getInventory().getBoots() != null){
                        pl.getInventory().getBoots().setDurability((short)(boots + 1));
                        if (pl.getInventory().getBoots().getDurability() >= pl.getInventory().getBoots().getType().getMaxDurability()){
                            pl.getInventory().setBoots(null);
                        }
                    }
                    if (legs != -1 && pl.getInventory().getLeggings() != null){
                        pl.getInventory().getLeggings().setDurability((short)(legs + 1));
                        if (pl.getInventory().getLeggings().getDurability() >= pl.getInventory().getLeggings().getType().getMaxDurability()){
                            pl.getInventory().setLeggings(null);
                        }
                    }
                    if (chest != -1 && pl.getInventory().getChestplate() != null){
                        pl.getInventory().getChestplate().setDurability((short)(chest + 1));
                        if (pl.getInventory().getChestplate().getDurability() >= pl.getInventory().getChestplate().getType().getMaxDurability()){
                            pl.getInventory().setChestplate(null);
                        }
                    }
                    if (helm != -1 && pl.getInventory().getHelmet() != null){
                        pl.getInventory().getHelmet().setDurability((short)(helm + 1));
                        if (pl.getInventory().getHelmet().getDurability() >= pl.getInventory().getHelmet().getType().getMaxDurability()){
                            pl.getInventory().setHelmet(null);
                        }
                    }
                    pl.updateInventory();
                }
            }.runTaskLater(UltimateSurvival.getInstance(), 1);
        }
    }
}
