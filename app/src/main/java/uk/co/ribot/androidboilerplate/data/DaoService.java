package uk.co.ribot.androidboilerplate.data;

import java.util.List;

import timber.log.Timber;
import uk.co.ribot.androidboilerplate.BoilerplateApplication;
import uk.co.ribot.androidboilerplate.data.model.DaoSession;
import uk.co.ribot.androidboilerplate.data.model.User;
import uk.co.ribot.androidboilerplate.data.model.UserDao;

/**
 * Created by User on 11/12/2017.
 */

public class DaoService {

    private UserDao mUserDao;

    public DaoService(){
        DaoSession daoSession = BoilerplateApplication.getInstance().getDaoSession();
        this.mUserDao = daoSession.getUserDao();
    }

    public Boolean create(User user) {

        try {
            mUserDao.insert(user);
            return true;
        }catch(Exception e)
        {
            System.out.println("exception:"+ e.getMessage());
            Timber.d("Error",e.getMessage());
            return false;
        }
    }
    public List<User> read(){
        return mUserDao.loadAll();
    }
}
