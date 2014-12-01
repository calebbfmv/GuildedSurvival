package us.iluthi.soulofw0lf.ultimatesurvival.loaders;

import us.iluthi.soulofw0lf.ultimatesurvival.Tutorial.TutorialLoader;
import us.iluthi.soulofw0lf.ultimatesurvival.UltimateSurvival;
import us.iluthi.soulofw0lf.ultimatesurvival.auctionhouse.AuctionEvents;
import us.iluthi.soulofw0lf.ultimatesurvival.events.*;
import us.iluthi.soulofw0lf.ultimatesurvival.inventories.*;
import us.iluthi.soulofw0lf.ultimatesurvival.menuevents.*;
import us.iluthi.soulofw0lf.ultimatesurvival.nonbukkitevents.CreateGuild;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 11/21/13
 * Time: 5:14 PM
 * <p/>
 * All Rights Reserved unless otherwise exUltimateSurvival.getInstance()icitly stated.
 */
public class LoadAllEvents {
    public LoadAllEvents(){
        new OnChat(UltimateSurvival.getInstance());
        new SignEvents(UltimateSurvival.getInstance());
        new BlockBreak(UltimateSurvival.getInstance());
        new BlockPlace(UltimateSurvival.getInstance());
        new BlockUse(UltimateSurvival.getInstance());
        new DuelCommands(UltimateSurvival.getInstance());
        new GuildCommands(UltimateSurvival.getInstance());
        new InventoryClick(UltimateSurvival.getInstance());
        new ItemConsume(UltimateSurvival.getInstance());
        new ItemDrop(UltimateSurvival.getInstance());
        new ItemPickup(UltimateSurvival.getInstance());
        new PlayerJoin(UltimateSurvival.getInstance());
        new PlayerQuit(UltimateSurvival.getInstance());
        new TradeCommands(UltimateSurvival.getInstance());
        new PlayerDamaged(UltimateSurvival.getInstance());
        new FoodEvent(UltimateSurvival.getInstance());
        new MobTarget(UltimateSurvival.getInstance());
        new ProjHit(UltimateSurvival.getInstance());
        new SellCommand(UltimateSurvival.getInstance());
        new MenuInventory(UltimateSurvival.getInstance());
        new WarpMenu(UltimateSurvival.getInstance());
        new DeathEvents(UltimateSurvival.getInstance());
        new DuelInventory(UltimateSurvival.getInstance());
        new PlayerRespawn(UltimateSurvival.getInstance());
        new SettingsInventory(UltimateSurvival.getInstance());
        new CreateGuild(UltimateSurvival.getInstance());
        new GuildRankInventory(UltimateSurvival.getInstance());
        new PlayerInventory(UltimateSurvival.getInstance());
        new SignLoader();
        new ChunkUnload(UltimateSurvival.getInstance());
        new ContributionChat();
        new DeclareWar();
        new EntryMessageChat();
        new ExitMessageChat();
        new MotdChat();
        new NewRankChat();
        new ExperienceGain();
        new BandageEvent();
        new EmeraldFire();
        new EntityClick();
        new TutorialLoader();
        new InventoryClose();
        new AuctionEvents();
        new FriendPMChat();
        new ExplodeyEvents(UltimateSurvival.getInstance());
    }
}
