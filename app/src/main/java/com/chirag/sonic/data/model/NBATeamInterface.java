package com.chirag.sonic.data.model;

import java.util.List;

/**
 * Created by Chirag on 4/1/2019 at 21:01.
 * Project - NBATeamViewer
 */
public interface NBATeamInterface {
    String getName();

    int getWins();

    int getLoses();

    List<TeamPlayer> getPlayers();
}
