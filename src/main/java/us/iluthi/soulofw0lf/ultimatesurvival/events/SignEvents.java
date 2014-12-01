package us.iluthi.soulofw0lf.ultimatesurvival.events;

import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import us.iluthi.soulofw0lf.ultimatesurvival.runnables.Signs;
import us.iluthi.soulofw0lf.ultimatesurvival.signs.SignBlocks;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.LocationUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/30/13
 * Time: 5:34 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class SignEvents implements Listener {

    public SignEvents(Plugin pl){
        Bukkit.getPluginManager().registerEvents(this, pl);
    }
    private List<String> mainSign = new ArrayList<>();
    private List<String> currentPlayers = new ArrayList<>();
    private List<String> bestPlayer = new ArrayList<>();
    private List<String> currentGuilds = new ArrayList<>();
    private List<String> bestGuild = new ArrayList<>();
    private List<String> tutorial = new ArrayList<>();
    @EventHandler
    public void signMake(PlayerCommandPreprocessEvent event){
        Player p = event.getPlayer();
        if (event.getMessage().replace("/", "").equalsIgnoreCase("signwall")){
            if (!p.isOp()){
                return;
            }
            event.setCancelled(true);
            mainSign.add(p.getName());
            p.sendMessage(ChatUtil.color("&bPlease right click the main display sign."));
            return;
        }
    }
    @EventHandler
    public void playerClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if (mainSign.contains(p.getName())){
            if (event.getClickedBlock() == null){
                return;
            }
            if (!(event.getClickedBlock().getState() instanceof Sign)){
                return;
            }
            Sign sign = (Sign)event.getClickedBlock().getState();
            SignBlocks.display = sign;
            p.sendMessage(ChatUtil.color("&b you have placed the display sign, please click on the current players sign."));
            mainSign.remove(p.getName());
            currentPlayers.add(p.getName());
            return;
        }
        if (currentPlayers.contains(p.getName())){
            if (event.getClickedBlock() == null){
                return;
            }
            if (!(event.getClickedBlock().getState() instanceof Sign)){
                return;
            }
            Sign sign = (Sign)event.getClickedBlock().getState();
            SignBlocks.currentPlayers = sign;
            p.sendMessage(ChatUtil.color("&b you have placed the current players sign, please click on the best players sign."));
            currentPlayers.remove(p.getName());
            bestPlayer.add(p.getName());
            return;
        }
        if (bestPlayer.contains(p.getName())){
            if (event.getClickedBlock() == null){
                return;
            }
            if (!(event.getClickedBlock().getState() instanceof Sign)){
                return;
            }
            Sign sign = (Sign)event.getClickedBlock().getState();
            SignBlocks.playerLeader = sign;
            p.sendMessage(ChatUtil.color("&b you have placed the best player sign, please click on the current guilds sign."));
            bestPlayer.remove(p.getName());
            currentGuilds.add(p.getName());
            return;
        }
        if (currentGuilds.contains(p.getName())){
            if (event.getClickedBlock() == null){
                return;
            }
            if (!(event.getClickedBlock().getState() instanceof Sign)){
                return;
            }
            Sign sign = (Sign)event.getClickedBlock().getState();
            SignBlocks.currentGuilds = sign;
            p.sendMessage(ChatUtil.color("&b you have placed the current sign, please click on the best guild sign."));
            currentGuilds.remove(p.getName());
            bestGuild.add(p.getName());
            return;
        }
        if (bestGuild.contains(p.getName())){
            if (event.getClickedBlock() == null){
                return;
            }
            if (!(event.getClickedBlock().getState() instanceof Sign)){
                return;
            }
            Sign sign = (Sign)event.getClickedBlock().getState();
            SignBlocks.guildLeader = sign;
            p.sendMessage(ChatUtil.color("&b you have placed the best guild sign, please click on the tutorial sign."));
            bestGuild.remove(p.getName());
            tutorial.add(p.getName());
            return;
        }
        if (tutorial.contains(p.getName())){
            if (event.getClickedBlock() == null){
                return;
            }
            if (!(event.getClickedBlock().getState() instanceof Sign)){
                return;
            }
            Sign sign = (Sign)event.getClickedBlock().getState();
            SignBlocks.tutorial = sign;
            p.sendMessage(ChatUtil.color("&b You have placed the tutorial sign, all signs now saved!"));
            File f = new File("plugins/UltimateSurvival/signs.yml");
            YamlConfiguration signs = YamlConfiguration.loadConfiguration(f);
            signs.set("Signs.Current Guilds", LocationUtil.locToString(SignBlocks.currentGuilds.getLocation()));
            signs.set("Signs.Current Players", LocationUtil.locToString(SignBlocks.currentPlayers.getLocation()));
            signs.set("Signs.Display", LocationUtil.locToString(SignBlocks.display.getLocation()));
            signs.set("Signs.Lead Guild", LocationUtil.locToString(SignBlocks.guildLeader.getLocation()));
            signs.set("Signs.Lead Player", LocationUtil.locToString(SignBlocks.playerLeader.getLocation()));
            signs.set("Signs.Tutorial", LocationUtil.locToString(SignBlocks.tutorial.getLocation()));
            try {
                signs.save(f);
            } catch (IOException e){
                e.printStackTrace();
            }
            Signs.run();
            tutorial.remove(p.getName());
            return;
        }
    }
}
