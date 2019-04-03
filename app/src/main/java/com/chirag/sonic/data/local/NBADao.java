package com.chirag.sonic.data.local;

import com.chirag.sonic.data.model.NBATeam;
import com.chirag.sonic.data.model.TeamPlayer;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Maybe;
import io.reactivex.Single;

import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * Created by Chirag on 4/1/2019 at 21:58.
 * Project - NBATeamViewer
 */

@Dao
public interface NBADao {

    /**
     * Insert NBATeams in database
     * @param teams to be added
     */
    @Insert(onConflict = REPLACE)
    void addTeam(List<NBATeam> teams);

    /**
     * Delete team from database
     * @param teamName to be deleted
     */
    @Query("DELETE FROM NBATeam WHERE team_name =:teamName")
    void deleteTeam(String teamName);

    /**
     * Insert team players in database
     * @param player to be added
     */
    @Insert(onConflict = REPLACE)
    void addPlayers(TeamPlayer player);

    /**
     * Delete players from database
     * @param teamName of the players
     */
    @Query("DELETE FROM TEAMPLAYER WHERE team_name =:teamName")
    void deletePlayer(String teamName);

    /**
     * Get all teams
     * @return List of NBATeam
     */
    @Query("SELECT * FROM NBATeam")
    Maybe<List<NBATeam>> getTeams();

    /**
     * Get all players
     * @return List of TeamPlayer
     */
    @Query("SELECT * FROM TeamPlayer WHERE team_name =:name ORDER BY number ASC")
    Single<List<TeamPlayer>> getPlayers(String name);

    /**
     * Get all teams in Ascending order
     * @return List of NBATeam
     */
    @Query("SELECT * FROM NBATeam ORDER BY team_name ASC")
    Maybe<List<NBATeam>> getAscOrderTeam();

    /**
     * Get all teams in Winning order
     * @return List of NBATeam
     */
    @Query("SELECT * FROM NBATeam ORDER BY wins DESC")
    Maybe<List<NBATeam>> getWinOrderTeam();

    /**
     * Get all teams in Losing order
     * @return List of NBATeam
     */
    @Query("SELECT * FROM NBATeam ORDER BY losses DESC")
    Maybe<List<NBATeam>> getLoseOrderTeam();
}
