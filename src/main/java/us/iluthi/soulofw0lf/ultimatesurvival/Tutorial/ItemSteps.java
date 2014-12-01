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
public class ItemSteps {
    public static void play(Player p){
        switch (Maps.tutorialSteps.get(p.getName())){
            case 0:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "This server has a few unique uses of items. Those will all be covered in this tutorial."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 1:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&2Snow Balls:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Snow Balls in this game are still thrown like normal, but cause lightning bolts to strike where they hit."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&4WARNING: &eThis can damage players in your guild / party!!!"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&bHow to obtain: &fSnowballs can not be collected from snow, and can only be purchased from the ammo section of the item shop, or found on rare mobs."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 2:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&2Emeralds:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Emeralds are not used for trading with villagers, and instead are right clicked to launch a fireball"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&4WARNING: &eThis can damage players in your guild / party!!!"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&bHow to obtain: &fEmeralds can be mined normally, drop off rare mobs, can be found in abandoned mineshaft chests, and purchased from the ammo section of the Store."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 3:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&2Mob Spawners:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Mob spawners work as delayed explosive charges. When you place a mob spawner it will disappear and 5 seconds later an explosion will go off where it was placed."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&4WARNING: &eThis is a large explosion that damages land as well as any players or creatures within it's blast radius!"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&bHow to obtain: &fThese can be purchased through the ammo section of the item shop, or mined with silk pickaxes."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 4:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&2Bandages:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Bandages are unique items that heal you for your max health over a period of 10 seconds. If you have sprained your ankle or broken your leg, bandages will also mend those afflictions."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&4WARNING: &eThese only work while you are standing in one place, using them while moving will consume them without doing any healing, other than repairing sprained or broken limbs"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&bHow to obtain: &fThese can be crafted by renaming paper with an anvil to include the word bandage in the name. Examples: 'My personal bandage', 'Uber Bandage', 'Bandage of healing'."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));
                break;
            case 5:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&2Ender Chests:"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Ender Chests are sell chests. Anything you place in an ender chest will automatically be sold be when you close the chest, and you will receive the coins from the sale."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&4WARNING: &eAnything put in the ender chest will be sold, if it has no value it is simply deleted. This cannot be undone!"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "&bHow to obtain: &fEnder chests can be crafted with 8 obsidian and 1 eye of ender."));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "Please right click to move onto the next tutorial stage."));

                break;
            case 6:
                p.sendMessage(ChatUtil.color("&e================================"));
                p.sendMessage(ChatUtil.color(Strings.tutStub + "This concludes the Custom Items tutorial. Please right click to end the tutorial."));
                break;
        }
    }
}
