package us.iluthi.soulofw0lf.ultimatesurvival.customobjects;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/16/13
 * Time: 1:34 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class HQ {
    private String guildName = "";
    private String entryMessage = "";
    private String exitMessage = "";
    private int maxX = 0;
    private int maxZ = 0;
    private int minX = 0;
    private int minZ = 0;

    public HQ(String name, String entry, String exit, int maxX, int maxZ, int minX, int minZ){
        this.guildName = name;
        this.entryMessage = entry;
        this.exitMessage = exit;
        this.maxX = maxX;
        this.maxZ = maxZ;
        this.minX = minX;
        this.minZ = minZ;
    }
    public HQ(Player p){
        this.maxX = (int)p.getLocation().getX() + 50;
        this.maxZ = (int)p.getLocation().getZ() + 50;
        this.minX = (int)p.getLocation().getX() - 50;
        this.minZ = (int)p.getLocation().getZ() - 50;
        CustomPlayer cP = CustomPlayer.getCP(p.getName());
        this.guildName = cP.getGuildName();
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

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public boolean containsBlock(Block block) {
        final Location loc = block.getLocation();
        return loc.getX() <= maxX && loc.getZ() <= maxZ && loc.getX() >= minX && loc.getZ() >= minZ;
    }
}
