package uk.co.ribot.androidboilerplate.ui.primary;

import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.DaoService;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.data.model.User;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.ui.main.MainMvpView;
import uk.co.ribot.androidboilerplate.util.RxUtil;

@ConfigPersistent
public class PrimaryPresenter extends BasePresenter<PrimaryMvpView> {

    public DaoService mDaoservice;

    @Inject
    public PrimaryPresenter(DaoService daoService) {
        mDaoservice=daoService;
    }

    @Override
    public void attachView(PrimaryMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    public void add(String name) {
        User user= new User();
        user.setName(name);
        if(mDaoservice.create(user)){
            getMvpView().showResult(name);
        }
        else{
            getMvpView().showError();
        }

    }
}
