package com.chirag.sonic.data.network;

import com.chirag.sonic.data.model.NBATeam;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by Chirag on 4/1/2019 at 19:42.
 * Project - NBATeamViewer
 */
public interface ApiService {

    @GET("lpf56")
    Flowable<List<NBATeam>> getNBATeams();
}
