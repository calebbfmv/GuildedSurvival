package us.iluthi.soulofw0lf.ultimatesurvival.loaders;

import org.bukkit.block.Sign;
import org.bukkit.configuration.file.YamlConfiguration;
import us.iluthi.soulofw0lf.ultimatesurvival.runnables.MiscTimers;
import us.iluthi.soulofw0lf.ultimatesurvival.runnables.Signs;
import us.iluthi.soulofw0lf.ultimatesurvival.signs.SignBlocks;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.LocationUtil;

import java.io.File;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/24/13
 * Time: 1:37 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class SignLoader {
    public SignLoader(){
        loadAll();
    }
    public void loadAll(){
        File f = new File("plugins/UltimateSurvival/signs.yml");
        YamlConfiguration signs = YamlConfiguration.loadConfiguration(f);
        if (signs.get("Signs") != null){
            SignBlocks.currentGuilds = (Sign)LocationUtil.stringToLoc(signs.getString("Signs.Current Guilds")).getBlock().getState();
            SignBlocks.currentPlayers = (Sign)LocationUtil.stringToLoc(signs.getString("Signs.Current Players")).getBlock().getState();
            SignBlocks.display = (Sign)LocationUtil.stringToLoc(signs.getString("Signs.Display")).getBlock().getState();
            SignBlocks.guildLeader = (Sign)LocationUtil.stringToLoc(signs.getString("Signs.Lead Guild")).getBlock().getState();
            SignBlocks.playerLeader = (Sign)LocationUtil.stringToLoc(signs.getString("Signs.Lead Player")).getBlock().getState();
            SignBlocks.tutorial = (Sign)LocationUtil.stringToLoc(signs.getString("Signs.Tutorial")).getBlock().getState();
            Signs.run();
            MiscTimers.run();
        }
    }
}
