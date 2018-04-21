package ru.zuma.ikrafinal.db.dataset;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import ru.zuma.ikrafinal.db.AppDatabase;

/**
 * Created by sibirsky on 21.04.18.
 */

@Table(database = AppDatabase.class)
public class AchievementDataset extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private long workspaceId;

    @Column
    private String type;

    @Column
    private boolean isUnlocked;

    public AchievementDataset()
    {
    }

    public AchievementDataset(long id, long workspaceId, String type) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }
}
