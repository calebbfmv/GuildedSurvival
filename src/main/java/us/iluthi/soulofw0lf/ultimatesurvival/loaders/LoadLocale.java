package us.iluthi.soulofw0lf.ultimatesurvival.loaders;

import org.bukkit.configuration.file.YamlConfiguration;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

import java.io.File;
import java.io.IOException;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 5:15 PM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class LoadLocale {
    public LoadLocale(){
        File f = new File("plugins/UltimateSurvival/locale.yml");
        YamlConfiguration locale = YamlConfiguration.loadConfiguration(f);
        if (locale.get("Stubs") == null){
            locale.set("Stubs.Guild", "&f[&bGuild&f]");
            locale.set("Stubs.Party", "&f[&2Party&f]");
            locale.set("Stubs.Trade", "&f[&6Trade&f]");
            locale.set("Stubs.Duel", "&f[&4Duel&f] &6");
            locale.set("Stubs.Chat", "&f[&6Chat&f] &b");
            locale.set("Commands.Guild Chat", "g");
            locale.set("Commands.Party Chat", "p");
            locale.set("Commands.Trade", "trade");
            locale.set("Commands.Party Invite", "pinvite");
            locale.set("Commands.Guild Invite", "ginvite");
            locale.set("Commands.Guild Kick", "gkick");
            locale.set("Commands.Guild Promote", "gpromote");
            locale.set("Commands.Guild Demote", "gdemote");
            locale.set("Commands.Guild Set Rank", "grank");
            locale.set("Commands.Party Kick", "pkick");
            locale.set("Commands.Party Leader", "pleader");
            locale.set("Commands.Duel Invite", "duel");
            locale.set("Commands.Guild Disband", "gdisband");
            locale.set("Commands.Party Disband", "pdisband");

            try {
                locale.save(f);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Strings.duelStub = locale.getString("Stubs.Duel");
        Strings.guildStub = locale.getString("Stubs.Guild");
        Strings.partyStub = locale.getString("Stubs.Party");
        Strings.tradeStub = locale.getString("Stubs.Trade");
        Strings.chatStub = locale.getString("Stubs.Chat");
        for (String key : locale.getConfigurationSection("Commands").getKeys(false)){
            Maps.commands.put(key, locale.getString("Commands." + key));
        }
    }
}
