package ru.zuma.ikrafinal.db.dataset;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import ru.zuma.ikrafinal.db.AppDatabase;

/**
 * Created by sibirsky on 20.04.18.
 */

@Table(database = AppDatabase.class)
public class QuestDataSet extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private long workspaceId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String tagString;

    @Column
    private long priority;

    @Column
    private String deadline;

    @Column
    private boolean isCompleted;

    public QuestDataSet()
    {
    }

    public QuestDataSet(long id, long workspaceId, String name, String description,
                        String tagString, long priority, String deadline, boolean isCompleted) {
        this.id = id;
        this.workspaceId = workspaceId;
        this.name = name;
        this.description = description;
        this.tagString = tagString;
        this.priority = priority;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTagString() {
        return tagString;
    }

    public void setTagString(String tagString) {
        this.tagString = tagString;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
