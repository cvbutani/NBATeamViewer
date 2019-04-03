package com.chirag.sonic.data;

import android.content.Context;

import com.chirag.sonic.data.local.NBADatabase;
import com.chirag.sonic.data.model.NBATeam;
import com.chirag.sonic.data.model.TeamPlayer;
import com.chirag.sonic.data.network.ApiService;
import com.chirag.sonic.data.network.RetrofitClient;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Chirag on 4/1/2019 at 22:28.
 * Project - NBATeamViewer
 */
public class Repository {

    private NBADatabase mDatabase;
    private static Repository sRepository;
    private UseCase mUseCase;

    private Repository(Context context){
        mDatabase = NBADatabase.getInstance(context.getApplicationContext());
        ApiService service = RetrofitClient.getApiService();
        mUseCase = new UseCase(mDatabase, service);
    }

    public static Repository getInstance(){
        if(sRepository == null){
            throw new RuntimeException("Initialize repository with Repository.init(Context)");
        }
        return sRepository;
    }

    public static void init(Context context){
        if(sRepository == null) {
            sRepository = new Repository(context);
        }
    }

    public Flowable<List<NBATeam>> getData(String orderBy){
       return mUseCase.getTeamData(orderBy);
    }

    public Single<List<TeamPlayer>> getTeamPlayers(String teamName) {
        return mUseCase.getPlayerData(teamName);
    }
}
