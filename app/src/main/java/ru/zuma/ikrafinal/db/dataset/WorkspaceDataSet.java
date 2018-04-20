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
public class WorkspaceDataSet extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private long rootQuestId;

    @Column
    private String name;

    public WorkspaceDataSet()
    {
    }

    public WorkspaceDataSet(long id, long rootQuestId, String name) {
        this.id = id;
        this.rootQuestId = rootQuestId;
        this.name = name;
    }

    public WorkspaceDataSet(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRootQuestId() {
        return rootQuestId;
    }

    public void setRootQuestId(long rootQuestId) {
        this.rootQuestId = rootQuestId;
    }
}
