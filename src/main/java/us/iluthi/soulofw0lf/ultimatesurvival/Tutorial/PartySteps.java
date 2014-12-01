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
public class PartySteps {
    public static void play(Player p){
        switch (Maps.tutorialSteps.get(p.getName())){
            case 0:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&bParties:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Parties are groups of up to 6 players that are working together."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 1:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Players do not have to be in the same guild to be a part of a party. Though damage between party members is cancelled the same way guilds work."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 2:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Only Fiiishy Rank members or higher can start party's but anyone can join a party. To invite people to a party, either shift right click them and click on invite to party, invite them through the guild menu, Invite them through the main menu's party section, or use the command /party invite."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 3:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "While in a party any items that drop that are rare will be randomly rolled for between the party. The following tutorial steps explain how this system works."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 4:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "When an item drops:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "When an item drops it is automatically added to the party's roll system if it is rare. Players without autoloot on will recieve a chat message telling them the commands to use."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 5:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "the /party info command will display a window with all items in the party's loot system, left clicking on any item will roll for the first item only, right clicking will pass on the item."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "the /party roll command rolls for the current item."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "the /party pass command declines your roll for the item."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 6:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Normal players with autoroll on will be brought to the info window as soon as an item drops. "));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Fiishy rank members and higher will simply have the items auto rolled on."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 7:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Party's still work in war zones, but that is covered more in depth in the warzones tutorial"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 8:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Only party leaders can kick a player from the party or invite a new player to the party. the party leader can promote another p[layer to leader through the party's menu."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 9:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "The party menu has shortcuts for most things mentioned in this tutorial and can be found in your main menu."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "that concludes this tutorial, please right click to exit."));
                break;
        }
    }
}
