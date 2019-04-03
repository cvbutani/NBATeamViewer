package com.chirag.sonic.data.local;

import android.content.Context;

import com.chirag.sonic.data.model.NBATeam;
import com.chirag.sonic.data.model.TeamPlayer;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Chirag on 4/1/2019 at 21:57.
 * Project - NBATeamViewer
 */
@Database(entities = {NBATeam.class, TeamPlayer.class}, exportSchema = false, version = 1)
public abstract class NBADatabase extends RoomDatabase {

    private static NBADatabase INSTANCE;

    public abstract NBADao getDao();

    public static synchronized NBADatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NBADatabase.class, "NBATeam.db")
                    .build();
        }
        return INSTANCE;
    }

}
