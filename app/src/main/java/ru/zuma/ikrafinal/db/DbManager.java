package ru.zuma.ikrafinal.db;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.zuma.ikrafinal.db.dataset.ParentDataSet;
import ru.zuma.ikrafinal.db.dataset.ParentDataSet_Table;
import ru.zuma.ikrafinal.db.dataset.QuestDataSet;
import ru.zuma.ikrafinal.db.dataset.QuestDataSet_Table;
import ru.zuma.ikrafinal.db.dataset.UserDataSet;
import ru.zuma.ikrafinal.db.dataset.WorkspaceDataSet;
import ru.zuma.ikrafinal.db.dataset.WorkspaceDataSet_Table;
import ru.zuma.ikrafinal.db.util.ObjectConverter;
import ru.zuma.ikrafinal.model.Quest;
import ru.zuma.ikrafinal.model.User;
import ru.zuma.ikrafinal.model.Workspace;

/**
 * Created by sibirsky on 20.04.18.
 */

public class DbManager {
    private static final DbManager ourInstance = new DbManager();

    public static DbManager getInstance() {
        return ourInstance;
    }

    private DbManager() {
    }

    public List<Workspace> getWorkspaces() {
        List<WorkspaceDataSet> dataSets = SQLite.select()
                .from(WorkspaceDataSet.class)
                .queryList();

        List<Workspace> result = new ArrayList<>();
        for (WorkspaceDataSet dataSet : dataSets) {
            result.add(ObjectConverter.createWorkspace(dataSet));
        }

        return result;
    }

    public Workspace addWorkspace(Workspace workspace) {
        WorkspaceDataSet workspaceDataSet = new WorkspaceDataSet();
        workspaceDataSet.setName(workspace.getName());
        long workspaceId = workspaceDataSet.insert();
        workspaceDataSet.setId(workspaceId);

        QuestDataSet questDataSet = new QuestDataSet();
        questDataSet.setWorkspaceRoot(true);
        questDataSet.setWorkspaceId(workspaceId);
        long questId = questDataSet.insert();

        workspaceDataSet.setRootQuestId(questId);
        workspaceDataSet.update();

        return new Workspace(workspaceId, questId, workspace.getName());
    }

    public Workspace getWorkspace(long id) {
        WorkspaceDataSet workspaceDataSet = SQLite.select()
                .from(WorkspaceDataSet.class)
                .where(WorkspaceDataSet_Table.id.eq(id))
                .querySingle();
        if (workspaceDataSet != null) {
            return ObjectConverter.createWorkspace(workspaceDataSet);
        }
        return new Workspace();
    }

    public Quest getQuestsGraph(final long workspaceId) {
        List<QuestDataSet> questDataSets = SQLite.select()
                .from(QuestDataSet.class)
                .where(QuestDataSet_Table.workspaceId.eq(workspaceId))
                .queryList();
        List<Quest> quests = new ArrayList<>();
        for (QuestDataSet dataSet : questDataSets) {
            quests.add(ObjectConverter.createQuest(dataSet));
        }

       List<ParentDataSet> parentRelations = SQLite.select()
               .from(ParentDataSet.class)
               .where(ParentDataSet_Table.workspaceId.eq(workspaceId))
               .queryList();

        return createQuestGraph(quests, parentRelations);
    }

    public List<Quest> getQuestsList(final long workspaceId) {
        List<QuestDataSet> questDataSets = SQLite.select()
                .from(QuestDataSet.class)
                .where(QuestDataSet_Table.workspaceId.eq(workspaceId))
                .queryList();
        List<Quest> quests = new ArrayList<>();
        for (QuestDataSet dataSet : questDataSets) {
            quests.add(ObjectConverter.createQuest(dataSet));
        }

        List<ParentDataSet> parentRelations = SQLite.select()
                .from(ParentDataSet.class)
                .where(ParentDataSet_Table.workspaceId.eq(workspaceId))
                .queryList();

        return createQuestList(quests, parentRelations);
    }

    public long addQuest(final Quest quest) {
        QuestDataSet questDataSet = new QuestDataSet();
        questDataSet.setCompleted(quest.isCompleted());
        questDataSet.setMandatory(quest.isMandatory());
        questDataSet.setDeadline(quest.getDeadline());
        questDataSet.setDescription(quest.getDescription());
        questDataSet.setName(quest.getName());
        questDataSet.setPriority(quest.getPriority());
        questDataSet.setTagString("");
        questDataSet.setWorkspaceId(quest.getWorkspaceId());
        questDataSet.setWorkspaceRoot(quest.isWorkspaceRoot());
        return questDataSet.insert();
    }

    public long addQuest(final Quest quest, long... parents) {
        QuestDataSet questDataSet = new QuestDataSet();
        questDataSet.setCompleted(quest.isCompleted());
        questDataSet.setMandatory(quest.isMandatory());
        questDataSet.setDeadline(quest.getDeadline());
        questDataSet.setDescription(quest.getDescription());
        questDataSet.setName(quest.getName());
        questDataSet.setPriority(quest.getPriority());
        questDataSet.setTagString("");
        questDataSet.setWorkspaceId(quest.getWorkspaceId());
        questDataSet.setWorkspaceRoot(quest.isWorkspaceRoot());

        long questId = questDataSet.insert();

        for (long parent : parents) {
            ParentDataSet parentDataSet = new ParentDataSet();
            parentDataSet.setWorkspaceId(quest.getWorkspaceId());
            parentDataSet.setParentId(parent);
            parentDataSet.setChildId(questId);
            parentDataSet.insert();
        }

        return questId;
    }

    public void updateQuest(Quest quest) {
        QuestDataSet questDataSet = new QuestDataSet();
        questDataSet.setId(quest.getId());
        questDataSet.setCompleted(quest.isCompleted());
        questDataSet.setMandatory(quest.isMandatory());
        questDataSet.setDeadline(quest.getDeadline());
        questDataSet.setDescription(quest.getDescription());
        questDataSet.setName(quest.getName());
        questDataSet.setPriority(quest.getPriority());
        questDataSet.setTagString("");
        questDataSet.setWorkspaceId(quest.getWorkspaceId());
        questDataSet.setWorkspaceRoot(quest.isWorkspaceRoot());
        questDataSet.update();
    }

    public void addQuestParent(Quest quest, long... parents) {
        for (long parent : parents) {
            ParentDataSet parentDataSet = new ParentDataSet();
            parentDataSet.setWorkspaceId(quest.getWorkspaceId());
            parentDataSet.setParentId(parent);
            parentDataSet.setChildId(quest.getId());
            parentDataSet.insert();
        }
    }

    /**
    public List<Quest> getUnlinkedQuests(long workspaceId) {
        List<ParentDataSet> parentDataSets = SQLite.select()
                .from(ParentDataSet.class)
                .where(ParentDataSet_Table.workspaceId.eq(workspaceId))

    } **/

    public void addQuestChildren(Quest quest, long... children) {
        for (long child : children) {
            ParentDataSet parentDataSet = new ParentDataSet();
            parentDataSet.setWorkspaceId(quest.getWorkspaceId());
            parentDataSet.setParentId(quest.getId());
            parentDataSet.setChildId(child);
            parentDataSet.insert();
        }
    }

    public void removeQuest(Quest quest) {
        QuestDataSet questDataSet = new QuestDataSet();
        questDataSet.setId(quest.getId());
        List<ParentDataSet> parentDataSets = SQLite.select()
                .from(ParentDataSet.class)
                .where(ParentDataSet_Table.childId.eq(quest.getId()))
                .queryList();
        for (ParentDataSet parentDataSet : parentDataSets) {
            parentDataSet.delete();
        }
        questDataSet.delete();
    }

    public long addUser(final User user) {
        UserDataSet userDataSet = new UserDataSet();
        userDataSet.setName(user.getName());
        userDataSet.setPassword(user.getPassword());
        return userDataSet.insert();
    }

    public User getUser() {
        List<UserDataSet> userDataSets = SQLite.select()
                .from(UserDataSet.class)
                .queryList();

        if (!userDataSets.isEmpty()) {
            return ObjectConverter.createUser(userDataSets.get(0));
        }
        return null;
    }

    private Quest createQuestGraph(final List<Quest> allQuests,
                                   final List<ParentDataSet> parentList) {

        Map<Long, Quest> questMap = new HashMap<>();
        Map<Long, Quest> parentQuests = new HashMap<>();
        for (Quest quest : allQuests) {
            questMap.put(quest.getId(), quest);
            parentQuests.put(quest.getId(), quest);
        }

        Map<Long, List<Long>> parentRelations = new HashMap<>();
        for (ParentDataSet dataSet : parentList) {
            if (!parentRelations.containsKey(dataSet.getParentId())) {
                List<Long> list = new ArrayList<>();
                list.add(dataSet.getChildId());
                parentRelations.put(dataSet.getParentId(), list);
            } else {
                parentRelations.get(dataSet.getParentId()).add(dataSet.getChildId());
            }
            parentQuests.remove(dataSet.getChildId());
        }

        for (Quest quest : allQuests) {
            List<Long> children = parentRelations.get(quest.getId());
            if (children != null) {
                for (Long child : children) {
                    Quest childQuest = questMap.get(child);
                    quest.getChildren().add(childQuest);
                }
            }
        }

        for (Quest quest : parentQuests.values()) {
            if (quest.isWorkspaceRoot()) {
                return quest;
            }
        }

        return new Quest(true);
    }

    private List<Quest> createQuestList(final List<Quest> allQuests,
                                        final List<ParentDataSet> parentList) {

        List<Quest> root = new ArrayList<>();

        Map<Long, Quest> questMap = new HashMap<>();
        for (Quest quest : allQuests) {
            questMap.put(quest.getId(), quest);
        }

        Map<Long, List<Long>> parentRelations = new HashMap<>();
        for (ParentDataSet dataSet : parentList) {
            if (!parentRelations.containsKey(dataSet.getParentId())) {
                List<Long> list = new ArrayList<>();
                list.add(dataSet.getChildId());
                parentRelations.put(dataSet.getParentId(), list);
            } else {
                parentRelations.get(dataSet.getParentId()).add(dataSet.getChildId());
            }
        }

        for (Quest quest : allQuests) {
            if (quest.isWorkspaceRoot()) {
                continue;
            }
            List<Long> children = parentRelations.get(quest.getId());
            if (children != null) {
                for (Long child : children) {
                    Quest childQuest = questMap.get(child);
                    quest.getChildren().add(childQuest);
                }
            }
            root.add(quest);
        }

        return root;
    }
}
