package com.example.testsqlite;

import android.app.Application;

import com.example.testsqlite.entity.DaoMaster;
import com.example.testsqlite.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by qweenhool on 2017/8/25.
 */

public class AppApplication extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "House-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
