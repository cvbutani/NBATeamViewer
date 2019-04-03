package com.chirag.sonic.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.Disposable;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chirag.sonic.R;
import com.chirag.sonic.data.model.NBATeam;
import com.chirag.sonic.ui.adapter.TeamsAdapter;

import java.util.List;

import static com.chirag.sonic.Constant.TEAM_LOOSES;
import static com.chirag.sonic.Constant.TEAM_NAME;
import static com.chirag.sonic.Constant.TEAM_WINS;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    MainPresenter mPresenter;
    TeamsAdapter mAdapter;
    ProgressBar mProgressBar;

    private Disposable mDisposable;

    TextView mErrorMessage;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);

        mPresenter = new MainPresenter();
        mPresenter.attachView(this);
        mPresenter.getTeamData("");

        mErrorMessage = findViewById(R.id.error_message);
        mErrorMessage.setVisibility(View.GONE);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), manager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        mAdapter = new TeamsAdapter();
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new TeamsAdapter.OnItemClickListener() {
            @Override
            public void onItemCLick(NBATeam team) {
                Intent intent = new Intent(MainActivity.this, PlayerInfoActivity.class);
                intent.putExtra(TEAM_NAME, team.getName());
                intent.putExtra(TEAM_WINS, team.getWins());
                intent.putExtra(TEAM_LOOSES, team.getLoses());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_order_name:
                mPresenter.getTeamData("teams");
                return true;
            case R.id.sort_order_win:
                mPresenter.getTeamData("wins");
                return true;
            case R.id.sort_order_loose:
                mPresenter.getTeamData("loses");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void teamInfo(List<NBATeam> teams) {
        mAdapter.updateTeamList(teams);
    }

    @Override
    public void dispose(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void error(String error) {
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadProgress(boolean load) {
        if (load) {
            recyclerView.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mDisposable != null) mDisposable.dispose();
    }
}
