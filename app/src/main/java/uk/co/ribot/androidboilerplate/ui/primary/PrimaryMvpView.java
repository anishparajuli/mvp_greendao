package uk.co.ribot.androidboilerplate.ui.primary;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface PrimaryMvpView extends MvpView {


    void showResult(String added);

    void showError();
}
