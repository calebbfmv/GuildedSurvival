package us.iluthi.soulofw0lf.ultimatesurvival.menuevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.CustomPlayer;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.Guild;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.HQ;
import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.WarZone;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.EditHQ;
import us.iluthi.soulofw0lf.ultimatesurvival.signs.SignProcess;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/20/13
 * Time: 5:51 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class ExpandInvClick {
    public static void onclick(InventoryClickEvent event){
        event.setCancelled(true);
        event.setResult(Event.Result.DENY);
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player)event.getWhoClicked();
        if (event.getRawSlot() == 26){
            p.closeInventory();
            p.openInventory(EditHQ.inv(p));
            return;
        }
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        Guild g = Maps.allGuilds.get(cP.getGuildName());
        HQ hq = g.getHeadQuarters();
        boolean overLap = false;
        if (event.getRawSlot() == 4){
            if (g.getGuildFortifications() <= g.getExpansions() * 250000){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild cannot afford to expand at this time! You need " + g.getExpansions() * 250000 + " fortification points to expand farther!"));
                return;
            }
            hq.setMinZ(hq.getMinZ() - 5);
            for (String key : Maps.allGuilds.keySet()){
                Guild test = Maps.allGuilds.get(key);
                if (test.getName().equalsIgnoreCase(g.getName())){
                    continue;
                }
                if (!test.hasHq()){
                    continue;
                }

                HQ hq1 = test.getHeadQuarters();
                if (hq.overLap(hq1)){
                    overLap = true;
                    break;
                }
            }
            if (!overLap){
                for (WarZone war : Lists.warZones){
                    if (war.overLap(g.getHeadQuarters())){
                        overLap = true;
                        break;
                    }
                }
            }
            if (overLap){
                hq.setMinZ(hq.getMinZ() + 5);
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You cannot expand in that direction, another guild is blocking you!"));
                return;
            } else {
                g.setGuildFortifications(g.getGuildFortifications() - (g.getExpansions() * 250000));
                g.setExpansions(g.getExpansions() + 1);
                SignProcess.guildCheck(g.getName(), g.getGuildFortifications());
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You have expanded your guild 5 blocks north."));
                return;
            }
        }
        if (event.getRawSlot() == 12){
            if (g.getGuildFortifications() <= g.getExpansions() * 250000){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild cannot afford to expand at this time! You need " + g.getExpansions() * 250000 + " fortification points to expand farther!"));
                return;
            }
            hq.setMinX(hq.getMinX() - 5);
            for (String key : Maps.allGuilds.keySet()){
                Guild test = Maps.allGuilds.get(key);
                if (test.getName().equalsIgnoreCase(g.getName())){
                    continue;
                }
                if (!test.hasHq()){
                    continue;
                }
                HQ hq1 = test.getHeadQuarters();
                if (hq.overLap(hq1)){
                    overLap = true;
                    break;
                }
            }
            if (!overLap){
                for (WarZone war : Lists.warZones){
                    if (war.overLap(g.getHeadQuarters())){
                        overLap = true;
                        break;
                    }
                }
            }
            if (overLap){
                hq.setMinX(hq.getMinX() + 5);
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You cannot expand in that direction, another guild is blocking you!"));
                return;
            } else {
                g.setGuildFortifications(g.getGuildFortifications() - (g.getExpansions() * 250000));
                g.setExpansions(g.getExpansions() + 1);
                SignProcess.guildCheck(g.getName(), g.getGuildFortifications());
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You have expanded your guild 5 blocks west."));
                return;
            }
        }
        if (event.getRawSlot() == 14){
            if (g.getGuildFortifications() <= g.getExpansions() * 250000){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild cannot afford to expand at this time! You need " + g.getExpansions() * 250000 + " fortification points to expand farther!"));
                return;
            }
            hq.setMaxX(hq.getMaxX() + 5);
            for (String key : Maps.allGuilds.keySet()){
                Guild test = Maps.allGuilds.get(key);
                if (test.getName().equalsIgnoreCase(g.getName())){
                    continue;
                }
                if (!test.hasHq()){
                    continue;
                }
                HQ hq1 = test.getHeadQuarters();
                if (hq.overLap(hq1)){
                    overLap = true;
                    break;
                }
            }
            if (!overLap){
                for (WarZone war : Lists.warZones){
                    if (war.overLap(g.getHeadQuarters())){
                        overLap = true;
                        break;
                    }
                }
            }
            if (overLap){
                hq.setMaxX(hq.getMaxX() - 5);
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You cannot expand in that direction, another guild or war zone is blocking you!"));
                return;
            } else {
                g.setGuildFortifications(g.getGuildFortifications() - (g.getExpansions() * 250000));
                g.setExpansions(g.getExpansions() + 1);
                SignProcess.guildCheck(g.getName(), g.getGuildFortifications());
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You have expanded your guild 5 blocks east."));
                return;
            }
        }
        if (event.getRawSlot() == 22){
            if (g.getGuildFortifications() <= g.getExpansions() * 250000){
                p.sendMessage(ChatUtil.color(Strings.guildStub + " Your guild cannot afford to expand at this time! You need " + g.getExpansions() * 250000 + " fortification points to expand farther!"));
                return;
            }
            hq.setMaxZ(hq.getMaxZ() + 5);
            for (String key : Maps.allGuilds.keySet()){
                Guild test = Maps.allGuilds.get(key);
                if (test.getName().equalsIgnoreCase(g.getName())){
                    continue;
                }
                if (!test.hasHq()){
                    continue;
                }
                HQ hq1 = test.getHeadQuarters();
                if (hq.overLap(hq1)){
                    overLap = true;
                    break;
                }
            }
            if (!overLap){
                for (WarZone war : Lists.warZones){
                    if (war.overLap(g.getHeadQuarters())){
                        overLap = true;
                        break;
                    }
                }
            }
            if (overLap){
                hq.setMaxZ(hq.getMaxZ() - 5);
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You cannot expand in that direction, another guild is blocking you!"));
                return;
            } else {
                g.setGuildFortifications(g.getGuildFortifications() - (g.getExpansions() * 250000));
                g.setExpansions(g.getExpansions() + 1);
                SignProcess.guildCheck(g.getName(), g.getGuildFortifications());
                p.sendMessage(ChatUtil.color(Strings.guildStub + " You have expanded your guild 5 blocks south."));
                return;
            }
        }
    }
}
