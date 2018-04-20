package ru.zuma.ikrafinal.db;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.migration.BaseMigration;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import ru.zuma.ikrafinal.model.Quest;

/**
 * Created by sibirsky on 20.04.18.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "AppDatabase";

    public static final int VERSION = 1;

    private static boolean isFirstLaunch = false;

    public static boolean isIsFirstLaunch() {
        return isFirstLaunch;
    }

    @Migration(version = 0, database = AppDatabase.class)
    public static class StubMigration extends BaseMigration {

        @Override
        public void migrate(@NonNull DatabaseWrapper database) {
            isFirstLaunch = true;
        }
    }

}