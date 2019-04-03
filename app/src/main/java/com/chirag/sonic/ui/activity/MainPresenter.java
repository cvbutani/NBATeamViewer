package com.chirag.sonic.ui.activity;

import com.chirag.sonic.data.Repository;
import com.chirag.sonic.data.model.NBATeam;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Chirag on 4/2/2019 at 19:10.
 * Project - NBATeamViewer
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mCallback;

    @Override
    public void getTeamData(String orderBy) {
        mCallback.loadProgress(true);
        Repository.getInstance()
                .getData(orderBy)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<List<NBATeam>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCallback.dispose(d);
                    }

                    @Override
                    public void onNext(List<NBATeam> nbaTeams) {
                        mCallback.loadProgress(false);
                        mCallback.teamInfo(nbaTeams);
                    }

                    @Override
                    public void onError(Throwable t) {
                        mCallback.error(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void attachView(MainContract.View view) {
        mCallback = view;
    }
}
