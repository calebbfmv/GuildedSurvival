package us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.loaders.LoadGuilds;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/16/13
 * Time: 5:20 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class DatabaseLoader {
    public static void loadGuilds(){
        DBCollection table = UltimateSurvival.db.getCollection("guilds");
        DBCursor name = table.find();
        while (name.hasNext()){
            DBObject bDo = name.next();
            String guild = (String)bDo.get("Name");
            long time = System.currentTimeMillis();
            System.out.print("Loading guild " + guild + ".");
            LoadGuilds.LoadGuild(guild, null);
            long newTime = System.currentTimeMillis() - time;
            System.out.print("Guild loaded in " + newTime + "ms.");

        }
    }
    public static void loadWarZones(){
        DBCollection table = UltimateSurvival.db.getCollection("War Zones");
        DBCursor name = table.find();
        while (name.hasNext()){
            DBObject bDo = name.next();
            String guild = (String)bDo.get("Name");
            long time = System.currentTimeMillis();
            System.out.print("Loading war zone " + guild + ".");
            LoadGuilds.LoadWarZone(guild);
            long newTime = System.currentTimeMillis() - time;
            System.out.print("War zone loaded in " + newTime + "ms.");

        }
    }

}
