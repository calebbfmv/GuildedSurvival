package us.iluthi.soulofw0lf.ultimatesurvival.Tutorial;

import us.iluthi.soulofw0lf.ultimatesurvival.variables.Maps;

/**
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 * Created by: soulofw0lf
 * Date: 12/19/13
 * Time: 6:47 AM
 * <p/>
 * All Rights Reserved unless otherwise explicitly stated.
 */
public class TutorialLoader {
    public TutorialLoader(){
        new TutSignClick();
        new TutMoveChecker();
        new TutChatCancel();
        new TutMainInvClick();
        new TutStageProgress();
        Maps.tutorialStageNumbers.put("intro", 4);
        Maps.tutorialStageNumbers.put("duels", 9);
        Maps.tutorialStageNumbers.put("players", 3);
        Maps.tutorialStageNumbers.put("items", 6);
        Maps.tutorialStageNumbers.put("guilds", 28);
        Maps.tutorialStageNumbers.put("chat", 15);
        Maps.tutorialStageNumbers.put("commands", 12);
        Maps.tutorialStageNumbers.put("shop", 8);
        Maps.tutorialStageNumbers.put("warps", 6);
        Maps.tutorialStageNumbers.put("trade", 9);
        Maps.tutorialStageNumbers.put("party", 9);
        Maps.tutorialStageNumbers.put("settings", 11);
    }
}
