package uk.co.ribot.androidboilerplate.ui.primary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.User;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;

public class PrimaryActivity extends BaseActivity implements PrimaryMvpView{

    @Inject
    PrimaryPresenter mPrimaryPresenter;

    @BindView(R.id.input_name)
    EditText mInputName;
    @BindView(R.id.text_results)
    TextView mTextResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_primary);
        ButterKnife.bind(this);
        mPrimaryPresenter.attachView(this);

        refreshList();
    }

    @Override
    protected void onDestroy(){
        mPrimaryPresenter.detachView();
        super.onDestroy();
    }
    @OnClick(R.id.button_add)
    public void onButtonAdd(){
        //Toast.makeText(this,"TODO: add the name to database",Toast.LENGTH_SHORT).show();
        String name=mInputName.getText().toString();
        mPrimaryPresenter.add(name);
    }

    @Override
    public void showResult(String added) {
        refreshList();
        Toast.makeText(this, added+" is added to the database", Toast.LENGTH_SHORT).show();
    }

    private void refreshList() {
        List<User> users=mPrimaryPresenter.mDaoservice.read();
        String results="";

        if(users.size()>0){
            for(User user:users){
                results+=" "+user.getName()+"\n";
            }
        }
        else{
            results="No record exists";
        }

        mTextResults.setText(results);
    }

    @Override
    public void showError(){
        Toast.makeText(this,"Record creation unsuccessful", Toast.LENGTH_SHORT).show();
    }

}
