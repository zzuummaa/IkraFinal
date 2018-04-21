package ru.zuma.ikrafinal.model;

/**
 * Created by sibirsky on 21.04.18.
 */

public class Achievment {

    private long id;
    private long workspaceId;
    private String workspaceName;
    private AchievementType type;
    private boolean isUnlocked;

    public Achievment()
    {
    }

    public Achievment(long id, long workspaceId, String workspaceName, AchievementType type,
                      boolean isUnlocked) {
        this.id = id;
        this.workspaceId = workspaceId;
        this.type = type;
        this.isUnlocked = isUnlocked;
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

    public AchievementType getType() {
        return type;
    }

    public void setType(AchievementType type) {
        this.type = type;
    }

    public String getWorkspaceName() {
        return workspaceName;
    }

    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }

    public String resolveName() {
        switch (type) {
            case THE_KING: return "Король";
            case UNSTOPABLE: return "Неостанавливаемый";
            case MONEY_MAKER: return "Деньгоделатель";
            case FIRST_BLOOD: return "Первая кровь";
        }
        return null;
    }

    public String resolveDescription() {
        switch (type) {
            case THE_KING: return "Король";
            case UNSTOPABLE: return "Неостанавливаемый";
            case MONEY_MAKER: return "Деньгоделатель";
            case FIRST_BLOOD: return "Первая кровь";
        }
        return null;
    }
}
