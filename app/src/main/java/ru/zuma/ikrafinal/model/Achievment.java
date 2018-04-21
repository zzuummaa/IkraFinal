package ru.zuma.ikrafinal.model;

/**
 * Created by sibirsky on 21.04.18.
 */

public class Achievment {

    private long id;
    private long workspaceId;
    private AchievmentType type;

    public Achievment()
    {
    }

    public Achievment(long id, long workspaceId, AchievmentType type) {
        this.id = id;
        this.workspaceId = workspaceId;
        this.type = type;
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

    public AchievmentType getType() {
        return type;
    }

    public void setType(AchievmentType type) {
        this.type = type;
    }


}
