package com.chirag.sonic.ui.activity;

import com.chirag.sonic.data.model.NBATeam;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Chirag on 4/2/2019 at 19:10.
 * Project - NBATeamViewer
 */
public interface MainContract {

    interface View {
        void teamInfo(List<NBATeam> teams);

        void dispose(Disposable disposable);

        void error(String error);

        void loadProgress(boolean load);
    }

    interface Presenter {
        void getTeamData(String orderBy);

        void attachView(MainContract.View view);
    }

}
