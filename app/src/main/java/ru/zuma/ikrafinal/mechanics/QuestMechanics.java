package ru.zuma.ikrafinal.mechanics;

import java.util.ArrayList;
import java.util.List;

import ru.zuma.ikrafinal.db.DbManager;
import ru.zuma.ikrafinal.model.AchievementType;
import ru.zuma.ikrafinal.model.Achievment;
import ru.zuma.ikrafinal.model.Quest;
import ru.zuma.ikrafinal.model.Workspace;

/**
 * Created by sibirsky on 21.04.18.
 */

public final class QuestMechanics {

    public static boolean completeQuest(Quest quest) {
        for (Quest child : quest.getChildren()) {
            if (child.isMandatory() && !child.isCompleted()) {
                quest.setCompleted(false);
                return false;
            }
        }
        quest.setCompleted(true);
        return true;
    }

    public static void calculateAchievement(long workspaceId) {
        List<Quest> allQuests = DbManager.getInstance().getQuestsList(workspaceId);
        int count = 0;
        Quest firstQuest;
        for (Quest quest : allQuests) {
            if (quest.isCompleted()) {
                count++;
                if (count == 1) {
                    firstQuest = quest;
                }
            }
        }
        if (count == 1) {
            List<Achievment> achievments = DbManager.getInstance().getUserAchievements();
            for (Achievment achievment : achievments) {
                if (achievment.getType() == AchievementType.FIRST_BLOOD) {
                    achievment.setWorkspaceId(workspaceId);
                    achievment.setUnlocked(true);
                    DbManager.getInstance().updateAchievment(achievment);
                    break;
                }
            }
        }
    }

    //public void linkQuest(Quest parent, )
}
