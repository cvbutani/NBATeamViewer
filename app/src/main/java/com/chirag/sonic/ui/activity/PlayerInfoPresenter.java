package com.chirag.sonic.ui.activity;

import com.chirag.sonic.data.Repository;
import com.chirag.sonic.data.model.TeamPlayer;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Chirag on 4/2/2019 at 22:13.
 * Project - NBATeamViewer
 */
public class PlayerInfoPresenter implements PlayerInfoContract.Presenter {

    private PlayerInfoContract.View mCallback;

    @Override
    public void getPlayersData(String teamName) {
        mCallback.loadProgress(true);
        Repository.getInstance().getTeamPlayers(teamName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<TeamPlayer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCallback.dispose(d);
                    }

                    @Override
                    public void onSuccess(List<TeamPlayer> teamPlayers) {
                        mCallback.loadProgress(false);
                        mCallback.playersInfo(teamPlayers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mCallback.error(e.getMessage());
                    }
                });
    }

    @Override
    public void attachView(PlayerInfoContract.View view) {
        mCallback = view;
    }
}
