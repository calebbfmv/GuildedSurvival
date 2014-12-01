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
public class DuelSteps {
    public static void play(Player p){
        switch (Maps.tutorialSteps.get(p.getName())){
            case 0:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "This server has a Duels system that is similar to duels you can have in most major MMO's"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 1:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "When two players are in a duel, no other players can damage them, and they cannot damage any other players."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "No mobs will target them and they cannot damage or be damaged by mobs."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 2:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "If a player goes more than 30 blocks away from the starting point of a duel, they will forfeit the duel and a message will be displayed saying they have run in fear from the winner "));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 3:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Duels are not to the death, and as soon as the duel is ended both participants will be returned to full health, however your items can still take durability damage while you are in a duel."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 4:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "While duelling you cannot break or place any blocks."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 5:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "To invite someone to a duel, you can either use the duel command, shift right click a player and click on invite to duel, or use the duels menu in your main menu to select a player to duel that is within 30 blocks of you."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 6:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Once a player has been invited to a duel a window will pop up for them that allows them to click either accept or deny. If the player accepts the duel, a 5 second countdown will begin to let you know when the duel actually starts."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 7:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Once a duel ends, the winner is announced to all nearby players, and both participants are immune to damage for 3 seconds after the duel ends."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 8:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "You can turn duels off in your settings menu, that is explained more under the settings tutorial."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 9:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Duels do not reward any items, coins or experience, and are just for fun and pvp practice."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "This concludes the duels tutorial, please right click to end this tutorial."));
                break;
        }
    }
}
