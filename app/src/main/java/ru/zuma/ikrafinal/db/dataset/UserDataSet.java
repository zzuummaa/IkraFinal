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
public class UserDataSet extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private String name;

    @Column
    private String password;

    public UserDataSet()
    {
    }

    public UserDataSet(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public UserDataSet(String name, String password) {
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
