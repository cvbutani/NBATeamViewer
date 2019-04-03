package com.chirag.sonic.app;

import android.app.Application;

import com.chirag.sonic.data.Repository;
import com.facebook.stetho.Stetho;

/**
 * Created by Chirag on 4/1/2019 at 22:58.
 * Project - NBATeamViewer
 */
public class NBAApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Repository.init(this);
        Stetho.initializeWithDefaults(this);
    }
}
