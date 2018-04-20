package ru.zuma.ikrafinal.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sibirsky on 20.04.18.
 */

public class Quest {

    private long id;
    private long workspaceId;
    private String name;
    private String description;
    private List<String> tags;
    private long priority;
    private String deadline;
    private boolean isCompleted = false;
    private List<Quest> children;

    public Quest()
    {
        children = new ArrayList<>();
        tags = new ArrayList<>();
    }

    public Quest(long id, long workspaceId, String name, String description, List<String> tags,
                 long priority, String deadline, boolean isCompleted, List<Quest> children) {
        this.id = id;
        this.workspaceId = workspaceId;
        this.name = name;
        this.description = description;
        this.tags = new ArrayList<>(tags);
        this.priority = priority;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
        this.children = new ArrayList<>(children);
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

    public List getTags() {
        return tags;
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

    public List<Quest> getChildren() {
        return children;
    }
}
