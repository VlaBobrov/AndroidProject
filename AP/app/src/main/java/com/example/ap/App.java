package com.example.ap;

import android.app.Application;

import com.example.ap.dao.DaoMaster;
import com.example.ap.dao.DaoSession;

import org.greenrobot.greendao.database.Database;


public class App extends Application {
    private DaoSession daoSession;



    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "ApDb");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }

}