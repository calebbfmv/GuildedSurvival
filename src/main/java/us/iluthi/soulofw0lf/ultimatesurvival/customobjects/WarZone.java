package us.iluthi.soulofw0lf.ultimatesurvival.customobjects;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import us.iluthi.soulofw0lf.ultimatesurvival.variables.Lists;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/21/13
 * Time: 12:34 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class WarZone {
    private String ownerName = "";
    private String owner = "";
    private String entryMessage = "";
    private String exitMessage = "";
    private int maxX = 0;
    private int maxZ = 0;
    private int minX = 0;
    private int minZ = 0;

    public WarZone(String name, String owner, String entry, String exit, int maxX, int maxZ, int minX, int minZ){
        this.ownerName = name;
        this.owner = owner;
        this.entryMessage = entry;
        this.exitMessage = exit;
        this.maxX = maxX;
        this.maxZ = maxZ;
        this.minX = minX;
        this.minZ = minZ;
        Lists.warZones.add(this);
    }
    public WarZone(Player p){
        this.owner = p.getName();
        this.entryMessage = "new entering " + p.getName() + "'s warzone.";
        this.exitMessage = "new leaving " + p.getName() + "'s warzone.";
        this.maxX = (int)p.getLocation().getX() + 20;
        this.maxZ = (int)p.getLocation().getZ() + 20;
        this.minX = (int)p.getLocation().getX() - 20;
        this.minZ = (int)p.getLocation().getZ() - 20;
        this.ownerName = p.getName();
        Lists.warZones.add(this);
    }

    public boolean overLap(HQ hq){
        if (this.maxX <= hq.getMaxX()
                && this.maxX >= hq.getMinX()
                && this.maxZ <= hq.getMaxZ()
                && this.maxZ >= hq.getMinZ())
            return true;
        if (this.minX <= hq.getMaxX()
                && this.minX >= hq.getMinX()
                && this.maxZ <= hq.getMaxZ()
                && this.maxZ >= hq.getMinZ())
            return true;
        if (this.minX <= hq.getMaxX()
                && this.minX >= hq.getMinX()
                && this.minZ <= hq.getMaxZ()
                && this.minZ >= hq.getMinZ())
            return true;
        if (this.maxX <= hq.getMaxX()
                && this.maxX >= hq.getMinX()
                && this.minZ <= hq.getMaxZ()
                && this.minZ >= hq.getMinZ())
            return true;
        if (hq.getMaxX() <= this.maxX
                && hq.getMaxX() >= this.minX
                && hq.getMaxZ() <= this.maxZ
                && hq.getMaxZ() >= this.minZ)
            return true;
        if (hq.getMinX() <= this.maxX
                && hq.getMinX() >= this.minX
                && hq.getMaxZ() <= this.maxZ
                && hq.getMaxZ() >= this.minZ)
            return true;
        if (hq.getMaxX() <= this.maxX
                && hq.getMaxX() >= this.minX
                && hq.getMinZ() <= this.maxZ
                && hq.getMinZ() >= this.minZ)
            return true;
        if (hq.getMinX() <= this.maxX
                && hq.getMinX() >= this.minX
                && hq.getMinZ() <= this.maxZ
                && hq.getMinZ() >= this.minZ)
            return true;
        return false;
    }
    public int getMinZ() {
        return minZ;
    }

    public void setMinZ(int minZ) {
        this.minZ = minZ;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public void setMaxZ(int maxZ) {
        this.maxZ = maxZ;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public String getExitMessage() {
        return exitMessage;
    }

    public void setExitMessage(String exitMessage) {
        this.exitMessage = exitMessage;
    }

    public String getEntryMessage() {
        return entryMessage;
    }

    public void setEntryMessage(String entryMessage) {
        this.entryMessage = entryMessage;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean containsBlock(Block block) {
        final Location loc = block.getLocation();
        return loc.getX() <= maxX && loc.getZ() <= maxZ && loc.getX() >= minX && loc.getZ() >= minZ;
    }
}
