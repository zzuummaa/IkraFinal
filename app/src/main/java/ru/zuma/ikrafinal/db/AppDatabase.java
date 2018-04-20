package ru.zuma.ikrafinal.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by sibirsky on 20.04.18.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "AppDatabase";

    public static final int VERSION = 1;
}
