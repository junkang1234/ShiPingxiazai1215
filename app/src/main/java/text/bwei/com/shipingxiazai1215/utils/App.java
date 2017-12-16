package text.bwei.com.shipingxiazai1215.utils;

import android.app.Application;

import text.bwei.com.shipingxiazai1215.gen.DaoMaster;
import text.bwei.com.shipingxiazai1215.gen.DaoSession;
import text.bwei.com.shipingxiazai1215.gen.UserDao;

/**
 * Created by dell on 2017/12/15.
 */

public class App extends Application {
    public static UserDao userDao;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "lenvess.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }
}