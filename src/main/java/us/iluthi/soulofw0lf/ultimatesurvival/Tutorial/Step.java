package us.iluthi.soulofw0lf.ultimatesurvival.Tutorial;

import org.bukkit.entity.Player;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/19/13
 * Time: 7:47 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Step {
    public static void Check(Player p){
        switch (Maps.tutorialStages.get(p.getName())){
            case "intro":
                IntroSteps.play(p);
                break;
            case "duels":
                DuelSteps.play(p);
                break;
            case "players":
                PlayerSteps.play(p);
                break;
            case "items":
                ItemSteps.play(p);
                break;
            case "guilds":
                GuildSteps.play(p);
                break;
            case "chat":
                ChatSteps.play(p);
                break;
            case "commands":
                Commandsteps.play(p);
                break;
            case "shop":
                ShopSteps.play(p);
                break;
            case "warps":
                WarpSteps.play(p);
                break;
            case "trade":
                TradeSteps.play(p);
                break;
            case "party":
                PartySteps.play(p);
                break;
            case "settings":
                SettingSteps.play(p);
                break;
        }
    }
}
