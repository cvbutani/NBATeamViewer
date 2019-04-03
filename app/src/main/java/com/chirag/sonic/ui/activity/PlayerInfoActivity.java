package com.chirag.sonic.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chirag.sonic.R;
import com.chirag.sonic.data.model.TeamPlayer;
import com.chirag.sonic.ui.adapter.PlayerAdapter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.Disposable;


import static com.chirag.sonic.Constant.TEAM_LOOSES;
import static com.chirag.sonic.Constant.TEAM_NAME;
import static com.chirag.sonic.Constant.TEAM_WINS;

/**
 * Created by Chirag on 4/2/2019 at 18:08.
 * Project - NBATeamViewer
 */

/**
 * Team Player information will be displayed when user clicks on any team in MainActivity.
 */

public class PlayerInfoActivity extends AppCompatActivity implements PlayerInfoContract.View {

    private TextView mTeamWins;
    private TextView mTeamLooses;
    private TextView mErrorMessage;

    private ProgressBar mProgressBar;

    private Disposable mDisposable;

    PlayerAdapter mPlayerAdapter;
    PlayerInfoPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_player);

        viewHolder();

        String teamName = "";
        int teamWins = 0;
        int teamLosses = 0;

        if (getIntent().hasExtra(TEAM_NAME)) {
            teamName = getIntent().getStringExtra(TEAM_NAME);
        }
        if (getIntent().hasExtra(TEAM_WINS)) {
            teamWins = getIntent().getIntExtra(TEAM_WINS, 0);
        }
        if (getIntent().hasExtra(TEAM_LOOSES)) {
            teamLosses = getIntent().getIntExtra(TEAM_LOOSES, 0);
        }
        getSupportActionBar().setTitle(teamName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter = new PlayerInfoPresenter();
        mPresenter.attachView(this);
        mPresenter.getPlayersData(teamName);

        mTeamWins.setText("Wins: " + String.valueOf(teamWins));
        mTeamLooses.setText("Looses: " + String.valueOf(teamLosses));
        mErrorMessage.setVisibility(View.GONE);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), manager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        mPlayerAdapter = new PlayerAdapter();
        recyclerView.setAdapter(mPlayerAdapter);

    }

    private void viewHolder() {
        mTeamWins = findViewById(R.id.team_wins);
        mTeamLooses = findViewById(R.id.team_looses);
        mErrorMessage = findViewById(R.id.error_message);
        mProgressBar = findViewById(R.id.progress_bar);
    }

    /**
     * Dispose Disposable in onStop.
     */
    @Override
    protected void onStop() {
        super.onStop();
        if (mDisposable != null) mDisposable.dispose();
    }

    /**
     * PlayerAdapter is updated with @teamPlayer list.
     *
     * @param teamPlayers list of players in a team.
     */
    @Override
    public void playersInfo(List<TeamPlayer> teamPlayers) {
        mPlayerAdapter.updateTeamList(teamPlayers);
    }

    @Override
    public void dispose(Disposable disposable) {
        this.mDisposable = disposable;
    }

    /**
     * Display error if something went wrong
     */
    @Override
    public void error(String error) {
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadProgress(boolean load) {
        if (load) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
