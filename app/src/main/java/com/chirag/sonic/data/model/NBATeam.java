package com.chirag.sonic.data.model;

import java.util.ArrayList;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by Chirag on 4/1/2019 at 19:49.
 * Project - NBATeamViewer
 */
@Entity
public class NBATeam implements NBATeamInterface {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int wins;
    public int losses;

    public NBATeam(){}

    @ColumnInfo(name = "team_name")
    public String full_name;

    @Ignore
    public List<TeamPlayer> players = new ArrayList<>();

    @Override
    public String getName() {
        return full_name;
    }

    @Override
    public int getWins() {
        return wins;
    }

    @Override
    public int getLoses() {
        return losses;
    }

    @Override
    public List<TeamPlayer> getPlayers() {
        return players;
    }

    @Override
    public int hashCode() {
        return full_name.hashCode();
    }
}
