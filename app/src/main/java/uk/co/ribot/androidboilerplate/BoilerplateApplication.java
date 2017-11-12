package uk.co.ribot.androidboilerplate;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import org.greenrobot.greendao.database.Database;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.model.DaoMaster;
import uk.co.ribot.androidboilerplate.data.model.DaoSession;
import uk.co.ribot.androidboilerplate.injection.component.ApplicationComponent;
import uk.co.ribot.androidboilerplate.injection.component.DaggerApplicationComponent;
import uk.co.ribot.androidboilerplate.injection.module.ApplicationModule;

public class BoilerplateApplication extends Application  {

    ApplicationComponent mApplicationComponent;
    private DaoSession mDaoSession;
    private static BoilerplateApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        initializeDaoSession();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Fabric.with(this, new Crashlytics());
        }
    }

    public static BoilerplateApplication get(Context context) {
        return (BoilerplateApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    public static BoilerplateApplication getInstance(){ return instance;}

    public void initializeDaoSession(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "userDB.db");
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }
    public DaoSession getDaoSession(){
        if(mDaoSession ==  null){
            initializeDaoSession();
        }
        return mDaoSession;
    }
}
