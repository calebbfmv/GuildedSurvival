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
public class IntroSteps {
    public static void play(Player p){
        switch (Maps.tutorialSteps.get(p.getName())){
            case 0:
                p.sendMessage(ChatUtil.color(Strings.tutStub + " Welcome to the &6SkyKipz &6tutorial. &bAt anytime you can right click to skip to the next step, or type exit in chat to leave the tutorial."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + " Please right click now to move onto the next introduction tutorial step."));
                break;
            case 1:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Guilds is a factions like game where the point of play is to play survival with multiple people."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "At it's core however Guilds is a PvP (Player Versus Player) game, but we'll get more into those aspects of the game with different tutorials."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 2:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "This is the main spawn area for guilds."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "While in spawn you cannot be attacked or targeted by players or mobs."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "You cannot break or place blocks, and cannot use items."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 3:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Before real game play starts you must go 150 blocks away from spawn."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "After crossing this point you are vulnerable to attacks."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 4:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Instead of commands this game mode primarily runs off of inventory menu's."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "To access the game menu right click with the enchanted book in your inventory."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "This will bring up a menu with many options. These options are all covered under their own tutorial sections."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "That concludes the introduction to guilds. Please right click to end this tutorial."));
                break;
        }
    }
}
