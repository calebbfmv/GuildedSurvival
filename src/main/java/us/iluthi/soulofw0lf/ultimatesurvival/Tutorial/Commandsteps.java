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
 * Time: 7:50 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Commandsteps {
    public static void play(Player p){
        switch (Maps.tutorialSteps.get(p.getName())){
            case 0:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + " Since guilds uses a menu style system there are very few actual commands."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + " This tutorial will simple list the commands you can use and what they do."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 1:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "/bal"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "This command can be used to show you your current total coins.."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 2:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "/pay {player} {amount}"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Use this command to pay a specific player a certain amount of coins.."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 3:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Chat commands"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "There are many chat shortcut commands for talking in different chat channels. These can all be viewed under the Chat tutorial."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 4:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "/duel {player}:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 5:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "/spawn"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Teleports you to the spawn area after 5 seconds of not moving."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 6:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "/report {message}"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Sends in a bug report to the server admins."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 7:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "/pm {player} {message}"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Sends a private message to the player named."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 8:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "/r {message}"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Responds to the last private message you received."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 9:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "/chat"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Opens the chat configuration menu."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 10:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "/guild"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Opens the main guild menu."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 11:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "/shop"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Opens the SkyKipz Shoppping cart menu."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 12:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "That concludes the commands list for the server. More commands will likely be programmed in in the future."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to end this tutorial."));
                break;
        }
    }
}
