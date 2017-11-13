package uk.co.ribot.androidboilerplate.ui.primary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.data.model.User;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.main.RibotsAdapter;

public class PrimaryActivity extends BaseActivity implements PrimaryMvpView{

    @Inject
    PrimaryPresenter mPrimaryPresenter;
    @Inject
    UsersAdapter mUsersAdapter;

    @BindView(R.id.input_name)
    EditText mInputName;
    @BindView(R.id.text_results)
    TextView mTextResults;

    @BindView(R.id.recycler_view_users)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_primary);
        ButterKnife.bind(this);

        mRecyclerView.setAdapter(mUsersAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        if(users.size()>0){
            mTextResults.setText(String.valueOf(users.size())+" records found");
            mUsersAdapter.setUsers(users);
            mUsersAdapter.notifyDataSetChanged();
        }
        else{
            mTextResults.setText("No records found");
        }
    }

    @Override
    public void showError(){
        Toast.makeText(this,"Record creation unsuccessful", Toast.LENGTH_SHORT).show();
    }

}
