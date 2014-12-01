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
 * Time: 7:51 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class SettingSteps {
    public static void play(Player p){
        switch (Maps.tutorialSteps.get(p.getName())){
            case 0:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Settings Tutorial:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "The settings menu located within the main menu is where you can adjust most of your personal settings."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 1:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "These settings will show as emerald blocks(green) if they are on, and redstone blocks(red) if they are off."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 2:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "To toggle your settings, simply click on the blocks that are shown."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 3:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "The rest of the tutorial will briefly go over what each setting does."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 4:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "PvP Active:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Your pvp setting determines wether players can attack you and vice versa. You are only able to turn this setting off if you are A: not in a guild or B: in a guild that is currently peaceful."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 5:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Do Not Disturb:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "After clicking on do not disturb you are prompted to set a DnD message in chat. Players that try to /pm you from that point on will see your do not disturb message, and you will not receive the private messages."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 6:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Auto Loot Setting:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Auto loot makes items dropped by creatures you have killed go straight into your inventory. When auto loot is off you will instead have a chest menu pop up when you kill something showing you the items you can choose to take."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 7:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Accept Trade Requests:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "When this option is disabled you will automatically decline all trade invites."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 8:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Auto Roll Menu:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "When you are in a party and rare loot drops, instead of going into a players inventory it is added to a roll list. This option brings up the roll menu immediately instead of waiting for you to type a command prompt."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 9:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Accept Party Invites:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "When this option is disabled you will automatically decline all party invites."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 10:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Accept Guild Invites:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "When this option is disabled you will automatically decline all guild invites."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 11:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Accept TP Requests:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "When this option is disabled you will automatically decline all incoming teleport requests."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "this concludes our settings tutorial. Please right click to exit."));
                break;
        }
    }
}
