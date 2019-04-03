package com.chirag.sonic.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Chirag on 4/1/2019 at 19:50.
 * Project - NBATeamViewer
 */
@Entity
public class TeamPlayer implements TeamPlayerInterface {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String first_name;
    public String last_name;
    public String position;
    public int number;

    @NonNull
    public String team_name = "";

    public TeamPlayer() {
    }

    @Override
    public String getFirstName() {
        return first_name;
    }

    @Override
    public String getLastName() {
        return last_name;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String getFullName() {
        return first_name + " " + last_name;
    }

    @Override
    public int hashCode() {
        return (first_name + position).hashCode();
    }
}
