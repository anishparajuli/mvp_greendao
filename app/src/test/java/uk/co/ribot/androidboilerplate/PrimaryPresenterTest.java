package uk.co.ribot.androidboilerplate;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import uk.co.ribot.androidboilerplate.data.DaoService;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.data.model.User;
import uk.co.ribot.androidboilerplate.test.common.TestDataFactory;
import uk.co.ribot.androidboilerplate.ui.main.MainMvpView;
import uk.co.ribot.androidboilerplate.ui.main.MainPresenter;
import uk.co.ribot.androidboilerplate.ui.primary.PrimaryMvpView;
import uk.co.ribot.androidboilerplate.ui.primary.PrimaryPresenter;
import uk.co.ribot.androidboilerplate.util.RxSchedulersOverrideRule;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrimaryPresenterTest {

    @Mock
    PrimaryMvpView mMockPrimaryMvpView;
    @Mock
    DaoService mMockDaoService;
    private PrimaryPresenter mPrimaryPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        mPrimaryPresenter=new PrimaryPresenter(mMockDaoService);
        mPrimaryPresenter.attachView(mMockPrimaryMvpView);
    }

    @After
    public void tearDown() {
        mPrimaryPresenter.detachView();
    }

    @Test
    public void testAddResult(){
        String testString="testing";
        User user=new User();
        user.setName(testString);
        when(mMockDaoService.create(user)).thenReturn(true);

        mPrimaryPresenter.add(testString);
        verify(mMockPrimaryMvpView).showResult(testString);
    }

}
