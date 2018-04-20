package ru.zuma.ikrafinal.db.util;

import ru.zuma.ikrafinal.db.dataset.QuestDataSet;
import ru.zuma.ikrafinal.db.dataset.UserDataSet;
import ru.zuma.ikrafinal.db.dataset.WorkspaceDataSet;
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
        return workspace;
    }

    public static Quest createQuest(QuestDataSet questDataSet) {
        Quest quest = new Quest();
        quest.setId(questDataSet.getId());
        quest.setWorkspaceId(questDataSet.getWorkspaceId());
        quest.setCompleted(questDataSet.isCompleted());
        quest.setDeadline(questDataSet.getDeadline());
        quest.setName(questDataSet.getName());
        quest.setPriority(questDataSet.getPriority());
        return quest;
    }

    public static User createUser(UserDataSet userDataSet) {
        User user = new User();
        user.setId(userDataSet.getId());
        user.setName(userDataSet.getName());
        user.setPassword(userDataSet.getPassword());
        return user;
    }
}
