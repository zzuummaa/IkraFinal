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
public class ParentDataSet extends BaseModel {

    @PrimaryKey
    private long id;

    @Column
    private long parentId;

    @Column
    private long childId;

    @Column
    private long workspaceId;

    public ParentDataSet()
    {
    }

    public ParentDataSet(long parentId, long childId, long workspaceId) {
        this.parentId = parentId;
        this.childId = childId;
        this.workspaceId = workspaceId;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getChildId() {
        return childId;
    }

    public void setChildId(long childId) {
        this.childId = childId;
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
}
