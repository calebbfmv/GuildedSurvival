package us.iluthi.soulofw0lf.ultimatesurvival.utility;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/3/13
 * Time: 2:36 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */


import org.bukkit.inventory.ItemStack;

public class AttributeHandler {

   /* public static ItemStack addSpeed(ItemStack s, double i)
    {
        Attributes attributes = new Attributes(s);
        attributes.add(Attributes.Attribute.newBuilder().
                name("Speed").
                type(Attributes.AttributeType.GENERIC_MOVEMENT_SPEED).amount(i).
                build()
        );
        return attributes.getStack();
    }
     */
    public static ItemStack addHealth(ItemStack s, double i)
    {
        Attributes attributes = new Attributes(s);
        attributes.add(Attributes.Attribute.newBuilder().
                name("Health").
                type(Attributes.AttributeType.GENERIC_MAX_HEALTH).amount(i).
                build()
        );
        return attributes.getStack();
    }

    public static ItemStack addKnockBackRes(ItemStack s, double i)
    {
        Attributes attributes = new Attributes(s);
        attributes.add(Attributes.Attribute.newBuilder().
                name("KnockBack").
                type(Attributes.AttributeType.GENERIC_KNOCKBACK_RESISTANCE).amount(i).
                build()
        );
        return attributes.getStack();
    }

    public static ItemStack addDamage(ItemStack s, double i)
    {
        Attributes attributes = new Attributes(s);
        attributes.add(Attributes.Attribute.newBuilder().
                name("Damage").
                type(Attributes.AttributeType.GENERIC_ATTACK_DAMAGE).amount(i).
                build()
        );
        return attributes.getStack();
    }

    public static ItemStack addFollowRange(ItemStack s, double i)
    {
        Attributes attributes = new Attributes(s);
        attributes.add(Attributes.Attribute.newBuilder().
                name("FollowRange").
                type(Attributes.AttributeType.GENERIC_FOLLOW_RANGE).amount(i).
                build()
        );
        return attributes.getStack();
    }
}
