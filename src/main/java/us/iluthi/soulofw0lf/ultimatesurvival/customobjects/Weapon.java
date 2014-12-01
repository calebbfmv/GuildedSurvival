package us.iluthi.soulofw0lf.ultimatesurvival.customobjects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.AttributeHandler;
import us.iluthi.soulofw0lf.ultimatesurvival.utility.ChatUtil;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

import java.util.ArrayList;
import java.util.List;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/17/13
 * Time: 9:56 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class Weapon {
    private String preName = "";
    private String postName = "";
    private String name = "";
    private String type = "";
    private String material = "";
    private double knockBack = 0;
    private double damage = 0;
    private double cost = 0;

    public Weapon(ItemStack iS){
        Material mat = iS.getType();
        String matName = mat.name().toLowerCase();
        String[] args = matName.split("_");
        if (args.length == 2){
            this.type = args[0];
            this.material = args[1];
        } else {
            this.material = args[0];
        }
        makeName();
    }

    public double assessCost(String mat){
        Material material = Material.getMaterial(mat);
        double cost = Maps.values.get(material) * 3;
        double knockCost = ((this.knockBack * 100) * (this.knockBack * 100) * (this.knockBack * 100)) * 100;
        double damageCost = (this.damage * this.damage * this.damage) * 100;
        this.cost = cost + knockCost + damageCost;
        return this.cost;
    }

    public ItemStack toItemStack(String name){
        makeName();
        String matName;
        if (!this.type.isEmpty()){
            matName = this.type + "_" + this.material;
        } else {
            matName = this.material;
        }
        matName = matName.toUpperCase();
        ItemStack iS = new ItemStack(Material.valueOf(matName), 1);
        ItemMeta iM = iS.getItemMeta();
        int color = (int)(Math.random() * 10);
        String colorStub;
        switch(color){
            case 0:
                colorStub = "&2";
                break;
            case 1:
                colorStub = "&4";
                break;
            case 2:
                colorStub = "&6";
                break;
            case 3:
                colorStub = "&7";
                break;
            case 4:
                colorStub = "&b";
                break;
            case 5:
                colorStub = "&e";
                break;
            case 6:
                colorStub = "&3";
                break;
            case 7:
                colorStub = "&a";
                break;
            case 8:
                colorStub = "&c";
                break;
            case 9:
                colorStub = "&d";
                break;
            default:
                colorStub = "&f";
                break;
        }
        iM.setDisplayName(ChatUtil.color(colorStub + this.preName + " " + this.name + " " + this.postName));
        List<String> lores = new ArrayList<>();
        lores.add(ChatUtil.color(colorStub + "This weapon was crafted by:"));
        lores.add(ChatUtil.color("&f--=== "+ colorStub + name + "&f==--"));
        lores.add(ChatUtil.color("Value: " + assessCost(matName)));
        iM.setLore(lores);
        iS.setItemMeta(iM);
        if (this.damage != 0){
            iS = AttributeHandler.addDamage(iS, this.damage);
        }
        if (this.knockBack != 0){
            iS = AttributeHandler.addKnockBackRes(iS, this.knockBack);
        }
        return iS;
    }
    public ItemStack toMobItemStack(){
        makeName();
        String matName;
        if (!this.type.isEmpty()){
            matName = this.type + "_" + this.material;
        } else {
            matName = this.material;
        }
        matName = matName.toUpperCase();
        ItemStack iS = new ItemStack(Material.valueOf(matName), 1);
        ItemMeta iM = iS.getItemMeta();
        int color = (int)(Math.random() * 10);
        String colorStub;
        switch(color){
            case 0:
                colorStub = "&2";
                break;
            case 1:
                colorStub = "&4";
                break;
            case 2:
                colorStub = "&6";
                break;
            case 3:
                colorStub = "&7";
                break;
            case 4:
                colorStub = "&b";
                break;
            case 5:
                colorStub = "&e";
                break;
            case 6:
                colorStub = "&3";
                break;
            case 7:
                colorStub = "&a";
                break;
            case 8:
                colorStub = "&c";
                break;
            case 9:
                colorStub = "&d";
                break;
            default:
                colorStub = "&f";
                break;
        }
        iM.setDisplayName(ChatUtil.color(colorStub + this.preName + " " + this.name + " " + this.postName));
        List<String> lores = new ArrayList<>();
        lores.add(ChatUtil.color(colorStub + "Ancient Weapon"));
        lores.add(ChatUtil.color("Roll"));
        iM.setLore(lores);
        iS.setItemMeta(iM);
        if (this.damage != 0){
            iS = AttributeHandler.addDamage(iS, this.damage);
        }
        if (this.knockBack != 0){
            iS = AttributeHandler.addKnockBackRes(iS, this.knockBack);
        }
        return iS;
    }

    public void makeName(){
        int matRand = (int)(Math.random() * 10);
        switch(this.type){
            case "wood":
                switch (matRand){
                    case 0:
                        this.preName = "Wooden";
                        break;
                    case 1:
                        this.preName = "Splintered";
                        break;
                    case 2:
                        this.preName = "Old";
                        break;
                    case 3:
                        this.preName = "Engraved";
                        break;
                    case 4:
                        this.preName = "Shattered";
                        break;
                    case 5:
                        this.preName = "Flimsy";
                        break;
                    case 6:
                        this.preName = "Brown";
                        break;
                    case 7:
                        this.preName = "Lucky";
                        break;
                    case 8:
                        this.preName = "Aged";
                        break;
                    case 9:
                        this.preName = "Withered";
                        break;
                    default:
                        break;
                }
                break;
            case "stone":
                switch (matRand){
                    case 0:
                        this.preName = "Hardened";
                        break;
                    case 1:
                        this.preName = "Stone";
                        break;
                    case 2:
                        this.preName = "Blunt";
                        break;
                    case 3:
                        this.preName = "Smooth";
                        break;
                    case 4:
                        this.preName = "Petrified";
                        break;
                    case 5:
                        this.preName = "Hobo's";
                        break;
                    case 6:
                        this.preName = "Chiseled";
                        break;
                    case 7:
                        this.preName = "Beginner's";
                        break;
                    case 8:
                        this.preName = "Craftsman's";
                        break;
                    case 9:
                        this.preName = "Dull";
                        break;
                    default:
                        break;
                }
                break;
            case "iron":
                switch (matRand){
                    case 0:
                        this.preName = "Sharp";
                        break;
                    case 1:
                        this.preName = "Slicing";
                        break;
                    case 2:
                        this.preName = "Soul's";
                        break;
                    case 3:
                        this.preName = "Aggravating";
                        break;
                    case 4:
                        this.preName = "Living";
                        break;
                    case 5:
                        this.preName = "Binding";
                        break;
                    case 6:
                        this.preName = "Bladed";
                        break;
                    case 7:
                        this.preName = "Destructive";
                        break;
                    case 8:
                        this.preName = "Sterling";
                        break;
                    case 9:
                        this.preName = "Steel";
                        break;
                    default:
                        break;
                }
                break;
            case "gold":
                switch (matRand){
                    case 0:
                        this.preName = "Shiny";
                        break;
                    case 1:
                        this.preName = "Golden";
                        break;
                    case 2:
                        this.preName = "Soft";
                        break;
                    case 3:
                        this.preName = "Dazzling";
                        break;
                    case 4:
                        this.preName = "Sparkling";
                        break;
                    case 5:
                        this.preName = "Sky's";
                        break;
                    case 6:
                        this.preName = "Lucrative";
                        break;
                    case 7:
                        this.preName = "Charismatic";
                        break;
                    case 8:
                        this.preName = "Piercing";
                        break;
                    case 9:
                        this.preName = "Enriching";
                        break;
                    default:
                        break;
                }
                break;
            case "diamond":
                switch (matRand){
                    case 0:
                        this.preName = "Desecrating";
                        break;
                    case 1:
                        this.preName = "Diamond";
                        break;
                    case 2:
                        this.preName = "Glass";
                        break;
                    case 3:
                        this.preName = "Assaulting";
                        break;
                    case 4:
                        this.preName = "Godly";
                        break;
                    case 5:
                        this.preName = "Bloody";
                        break;
                    case 6:
                        this.preName = "Silent";
                        break;
                    case 7:
                        this.preName = "Husky's";
                        break;
                    case 8:
                        this.preName = "Penetrating";
                        break;
                    case 9:
                        this.preName = "Elegant";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        int typeRand = (int)(Math.random() * 10);
        switch(this.material){
            case "sword":
                switch (typeRand){
                    case 0:
                        this.name = "Sword";
                        break;
                    case 1:
                        this.name = "Shank";
                        break;
                    case 2:
                        this.name = "Shiv";
                        break;
                    case 3:
                        this.name = "Dagger";
                        break;
                    case 4:
                        this.name = "Poker";
                        break;
                    case 5:
                        this.name = "Slicer";
                        break;
                    case 6:
                        this.name = "Blade";
                        break;
                    case 7:
                        this.name = "Katana";
                        break;
                    case 8:
                        this.name = "Rapier";
                        break;
                    case 9:
                        this.name = "Great Sword";
                        break;
                    default:
                        break;
                }
                break;
            case "axe":
                switch (typeRand){
                    case 0:
                        this.name = "Cleaver";
                        break;
                    case 1:
                        this.name = "Battle Axe";
                        break;
                    case 2:
                        this.name = "Beheader";
                        break;
                    case 3:
                        this.name = "Tomahawk";
                        break;
                    case 4:
                        this.name = "Executioner";
                        break;
                    case 5:
                        this.name = "Wood Splitter";
                        break;
                    case 6:
                        this.name = "Great Axe";
                        break;
                    case 7:
                        this.name = "War Hammer";
                        break;
                    case 8:
                        this.name = "Flail";
                        break;
                    case 9:
                        this.name = "Heavy Mace";
                        break;
                    default:
                        break;
                }
                break;
            case "pickaxe":
                switch (typeRand){
                    case 0:
                        this.name = "Scythe";
                        break;
                    case 1:
                        this.name = "Sickle";
                        break;
                    case 2:
                        this.name = "DoubleBladed Hatchet";
                        break;
                    case 3:
                        this.name = "Trident";
                        break;
                    case 4:
                        this.name = "Nose Picker";
                        break;
                    case 5:
                        this.name = "Rock Crusher";
                        break;
                    case 6:
                        this.name = "Double Bladed Axe";
                        break;
                    case 7:
                        this.name = "Pickaxe";
                        break;
                    case 8:
                        this.name = "Diamond Seeker";
                        break;
                    case 9:
                        this.name = "Fang";
                        break;
                    default:
                        break;
                }
                break;
            case "hoe":
                switch (typeRand){
                    case 0:
                        this.name = "Trowel";
                        break;
                    case 1:
                        this.name = "Hoe";
                        break;
                    case 2:
                        this.name = "Weeder";
                        break;
                    case 3:
                        this.name = "Rake";
                        break;
                    case 4:
                        this.name = "BRO-B4";
                        break;
                    case 5:
                        this.name = "Edger";
                        break;
                    case 6:
                        this.name = "Cultivator";
                        break;
                    case 7:
                        this.name = "Mower";
                        break;
                    case 8:
                        this.name = "Tiller";
                        break;
                    case 9:
                        this.name = "Glaive";
                        break;
                    default:
                        break;
                }
                break;
            case "spade":
                switch (typeRand){
                    case 0:
                        this.name = "Spade";
                        break;
                    case 1:
                        this.name = "Shovel";
                        break;
                    case 2:
                        this.name = "Spear";
                        break;
                    case 3:
                        this.name = "Gardener";
                        break;
                    case 4:
                        this.name = "Equalizer";
                        break;
                    case 5:
                        this.name = "Pilum";
                        break;
                    case 6:
                        this.name = "Digger";
                        break;
                    case 7:
                        this.name = "Lance";
                        break;
                    case 8:
                        this.name = "Scooper";
                        break;
                    case 9:
                        this.name = "Spoon";
                        break;
                    default:
                        break;
                }
                break;
            case "bow":
                switch (typeRand){
                    case 0:
                        this.name = "Compound Bow";
                        break;
                    case 1:
                        this.name = "Long Bow";
                        break;
                    case 2:
                        this.name = "Short Bow";
                        break;
                    case 3:
                        this.name = "Cross Bow";
                        break;
                    case 4:
                        this.name = "Dart Gun";
                        break;
                    case 5:
                        this.name = "Recurve";
                        break;
                    case 6:
                        this.name = "Elvish Bow";
                        break;
                    case 7:
                        this.name = "Sling";
                        break;
                    case 8:
                        this.name = "Cupid's Bow";
                        break;
                    case 9:
                        this.name = "Pain Launcher";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        double highStat = this.damage;
        String highName = "damage";
        if (this.knockBack * 100 > highStat){
            highName = "knockback";
        }
        if (this.damage == 0 && this.knockBack == 0){
            highName = "none";
        }
        int statRand = (int)(Math.random() * 10);
        switch(highName){
            case "damage":
                switch (statRand){
                    case 0:
                        this.postName = "of Damage";
                        break;
                    case 1:
                        this.postName = "of Wounding";
                        break;
                    case 2:
                        this.postName = "of Harming";
                        break;
                    case 3:
                        this.postName = "of Vengeance";
                        break;
                    case 4:
                        this.postName = "of Pain";
                        break;
                    case 5:
                        this.postName = "of Destruction";
                        break;
                    case 6:
                        this.postName = "of Killing";
                        break;
                    case 7:
                        this.postName = "of Impaling";
                        break;
                    case 8:
                        this.postName = "of Death";
                        break;
                    case 9:
                        this.postName = "of Fluffy Bunnies";
                        break;
                    default:
                        break;
                }
                break;
            case "knockback":
                switch (statRand){
                    case 0:
                        this.postName = "of Fortitude";
                        break;
                    case 1:
                        this.postName = "of Resilience";
                        break;
                    case 2:
                        this.postName = "of Steadfastness";
                        break;
                    case 3:
                        this.postName = "of Grounding";
                        break;
                    case 4:
                        this.postName = "of SureFeet";
                        break;
                    case 5:
                        this.postName = "of Standing";
                        break;
                    case 6:
                        this.postName = "of Stability";
                        break;
                    case 7:
                        this.postName = "of Constitution";
                        break;
                    case 8:
                        this.postName = "of Immobility";
                        break;
                    case 9:
                        this.postName = "of Weight";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPreName() {
        return preName;
    }

    public void setPreName(String preName) {
        this.preName = preName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getKnockBack() {
        return knockBack;
    }

    public void setKnockBack(double knockBack) {
        this.knockBack = knockBack;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
