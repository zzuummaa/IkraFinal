package ru.zuma.ikrafinal.db.util;

import ru.zuma.ikrafinal.db.dataset.AchievementDataset;
import ru.zuma.ikrafinal.db.dataset.QuestDataSet;
import ru.zuma.ikrafinal.db.dataset.UserDataSet;
import ru.zuma.ikrafinal.db.dataset.WorkspaceDataSet;
import ru.zuma.ikrafinal.model.Achievment;
import ru.zuma.ikrafinal.model.AchievmentType;
import ru.zuma.ikrafinal.model.Quest;
import ru.zuma.ikrafinal.model.User;
import ru.zuma.ikrafinal.model.Workspace;

/**
 * Created by sibirsky on 20.04.18.
 */

public final class ObjectConverter {

    private ObjectConverter()
    {
    }

    public static Workspace createWorkspace(WorkspaceDataSet workspaceDataSet) {
        Workspace workspace = new Workspace();
        workspace.setId(workspaceDataSet.getId());
        workspace.setName(workspaceDataSet.getName());
        workspace.setRootQuestId(workspaceDataSet.getRootQuestId());
        return workspace;
    }

    public static Quest createQuest(QuestDataSet questDataSet) {
        Quest quest = new Quest(questDataSet.isWorkspaceRoot());
        quest.setId(questDataSet.getId());
        quest.setWorkspaceId(questDataSet.getWorkspaceId());
        quest.setCompleted(questDataSet.isCompleted());
        quest.setDeadline(questDataSet.getDeadline());
        quest.setName(questDataSet.getName());
        quest.setPriority(questDataSet.getPriority());
        quest.setLinked(questDataSet.isLinked());
        return quest;
    }

    public static User createUser(UserDataSet userDataSet) {
        User user = new User();
        user.setId(userDataSet.getId());
        user.setName(userDataSet.getName());
        user.setPassword(userDataSet.getPassword());
        return user;
    }

    public static Achievment createAchievement(AchievementDataset achievementDataset) {
        Achievment achievment = new Achievment();
        achievment.setId(achievementDataset.getId());
        achievment.setType(AchievmentType.valueOf(achievementDataset.getType()));
        achievment.setWorkspaceId(achievementDataset.getWorkspaceId());
        return achievment;
    }
}
