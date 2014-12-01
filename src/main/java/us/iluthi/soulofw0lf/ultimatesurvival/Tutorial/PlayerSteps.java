package us.iluthi.soulofw0lf.ultimatesurvival.Tutorial;

import org.bukkit.entity.Player;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Strings;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/19/13
 * Time: 7:49 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class PlayerSteps {
    public static void play(Player p){
        switch (Maps.tutorialSteps.get(p.getName())){
            case 0:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Players Tutorial:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "In an effort to make people need less time typing command guilds allows you to shift + right click on players to open up a menu specific to that player."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 1:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "From within that menu you can invite players to your guild, to a party, to a trade, to a duel, or view their statistics."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 2:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "The player statistics menu can be used to see a general overview of a players stats, such as total blocks broken, kills, coins, and time played."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 3:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "The stats menu can also be used to see detailed information about how many times a player has broken which blocks, or killed which players."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "This concludes the players tutorial. Please right click to exit"));
                break;
        }
    }
}
