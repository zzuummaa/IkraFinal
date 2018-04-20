package ru.zuma.ikrafinal.db;

/**
 * Created by sibirsky on 20.04.18.
 */

class DbManager {
    private static final DbManager ourInstance = new DbManager();

    static DbManager getInstance() {
        return ourInstance;
    }

    private DbManager() {
    }
}
