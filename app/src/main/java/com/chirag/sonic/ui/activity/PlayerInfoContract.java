package com.chirag.sonic.ui.activity;

import com.chirag.sonic.data.model.TeamPlayer;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Chirag on 4/2/2019 at 22:13.
 * Project - NBATeamViewer
 */
public interface PlayerInfoContract {
    interface View {
        void playersInfo(List<TeamPlayer> teamPlayers);

        void dispose(Disposable disposable);

        void error(String error);

        void loadProgress(boolean load);
    }

    interface Presenter {
        void getPlayersData(String teamName);

        void attachView(PlayerInfoContract.View view);
    }
}
