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
public class ChatSteps {
    public static void play(Player p){
        switch (Maps.tutorialSteps.get(p.getName())){
            case 0:
                Maps.tutorialLocations.remove(p.getName());
                Maps.tutorialLocations.put(p.getName(), p.getLocation());
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + " Guilds uses a dynamic chat system that makes communication appear how you want it to."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 1:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "To access your chat menu, You can go into your main menu and click on the book, labelled 'Chat Menu'"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Once there you have multiple options for what to do with individual chat channels."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 2:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Chats you do not belong to will show up as a web in your chat menu, all other chats will be blocks of wool."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Left clicking a chat you are not currently in will join that chat, and turn into web into a wool block."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 3:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Left clicking on a chat that you are already in will change that chat channels color, to correspond to the color of the wool block."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "You can cycle through these colors to make every chat channel the color you want it to be. All messages you receive on that channel will show up as the color you have chosen."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 4:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "To leave a chat channel and no longer receive messages from it, simply right click that channels wool block."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "To set a channel as your active channel (The channel that your default text gets broadcast to) simply shift right click on that channel."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 5:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "There are 8 chat channels total, and the following steps of the tutorial will describe each Channel."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage, and remember at any time you can type 'exit' in chat to leave the tutorial. "));
                break;
            case 6:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "General Chat:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "General chat is a global chat channel, meaning anyone anywhere that is in that channel can see what is typed in it, and messages you send will go to all users on the server that are in the channel regardless of where they are."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 7:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Local Chat:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Local chat is a channel that only broadcasts and receives up to 50 blocks away."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 8:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Guild Chat:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Your guild chat is a private channel that will only send and receive to other members of your own guild."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 9:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Trade Chat:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Trade chat is another global channel, that is set up with the intent that people will exclusively use it for the buying selling and trading of goods."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 10:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Recruitment Chat:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "The recruitment channel is one that only officers in guilds can talk in, and is a channel that is designed to allow guilds to advertise that they are looking for more members."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 11:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Party Chat:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Party chat is a private chat that is exclusive to players you are currently in a party with."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 12:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Officer Chat:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Guild leaders can give certain ranks permission to use officer chat, this will only broadcast to other members of your own guild that can also use Officer Chat."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 13:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "HQ Chat:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "HQ chat is a channel that will only brodcast to members of your own guild that are currently within your guild headquarters."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 14:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "In order to send a quick message to a channel without switching your active channel you can preface the messages with a specific command."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "The next and final step of this tutorial will be a list of what channels are prefaced with what commands."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Example quick chat message"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "/g hey guildy's!"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 15:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Guild Chat: /g"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Party Chat: /p"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Local Chat: /l"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Recruitment Chat: /rec"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Officer Chat: /o"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Trade Chat: /t"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "General Chat: /gen"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "HQ Chat: /hq"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "That concludes the chat tutorial, please right click to finish."));
                break;
        }
    }
}
