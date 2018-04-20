package ru.zuma.ikrafinal.db.dataset;

import com.raizlabs.android.dbflow.annotation.Column;

/**
 * Created by sibirsky on 20.04.18.
 */

public class ParentDataSet {

    @Column
    private long parentId;

    @Column
    private long childId;

    public ParentDataSet()
    {
    }

    public ParentDataSet(long parentId, long childId) {
        this.parentId = parentId;
        this.childId = childId;
    }

    public long getParentId(
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
}
