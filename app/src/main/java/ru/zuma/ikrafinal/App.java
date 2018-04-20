package ru.zuma.ikrafinal;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by sibirsky on 20.04.18.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
