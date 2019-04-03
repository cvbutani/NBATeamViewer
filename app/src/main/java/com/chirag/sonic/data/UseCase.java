package com.chirag.sonic.data;

import com.chirag.sonic.data.local.NBADatabase;
import com.chirag.sonic.data.model.NBATeam;
import com.chirag.sonic.data.model.TeamPlayer;
import com.chirag.sonic.data.network.ApiService;

import java.util.List;

import androidx.annotation.NonNull;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Chirag on 4/1/2019 at 22:41.
 * Project - NBATeamViewer
 */
class UseCase {

    private NBADatabase mDatabase;
    private ApiService mService;

    UseCase(NBADatabase database, ApiService service) {
        mDatabase = database;
        mService = service;
    }

    /***
     * Provides team information when called.
     * If mobile is connected to Internet, it will fetch data, deletes database data and
     * stores new data in database
     *
     * @param orderBy data order request
     * @return List of NBATeam
     */

    Flowable<List<NBATeam>> getTeamData(String orderBy) {
        return new NetworkBoundResource<List<NBATeam>, List<NBATeam>>() {
            @Override
            void saveCallResult(@NonNull List<NBATeam> item) {

                for (NBATeam team : item) {
                    mDatabase.getDao().deleteTeam(team.getName());
                    List<TeamPlayer> players = team.getPlayers();
                    mDatabase.getDao().deletePlayer(team.getName());
                    for (TeamPlayer player : players) {
                        player.team_name = team.getName();
                        mDatabase.getDao().addPlayers(player);
                    }
                }
                mDatabase.getDao().addTeam(item);
            }

            //  Sorts data based on orderBy String and returns flowable with list of NBATeam.
            @Override
            protected Flowable<List<NBATeam>> loadFromDb() {
                switch (orderBy) {
                    case "wins":
                        return mDatabase.getDao().getWinOrderTeam().toFlowable();
                    case "loses":
                        return mDatabase.getDao().getLoseOrderTeam().toFlowable();
                    case "teams":
                        return mDatabase.getDao().getAscOrderTeam().toFlowable();
                    default:
                        return mDatabase.getDao().getTeams().toFlowable();
                }
            }

            //  Calls Api
            @Override
            protected Flowable<List<NBATeam>> createCall() {
                return mService.getNBATeams();
            }

            //  It should fetch from network.
            @Override
            protected boolean shouldFetch() {
                return true;
            }
        }.asFlowable();
    }

    /**
     * TeamPlayer data is already available in database. We don't need to fetch again.
     * Method will get all player info in a particular team.
     *
     * @param teamName team name
     * @return List of team player
     */
    Single<List<TeamPlayer>> getPlayerData(String teamName) {
        return mDatabase.getDao().getPlayers(teamName);
    }
}
