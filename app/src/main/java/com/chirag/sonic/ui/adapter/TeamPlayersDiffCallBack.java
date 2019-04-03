package com.chirag.sonic.ui.adapter;

import com.chirag.sonic.data.model.TeamPlayer;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by Chirag on 4/2/2019 at 21:16.
 * Project - NBATeamViewer
 */
public class TeamPlayersDiffCallBack extends DiffUtil.Callback {

    private final List<TeamPlayer> mOldPlayerList;
    private final List<TeamPlayer> mNewPlayerList;

    public TeamPlayersDiffCallBack(List<TeamPlayer> mOldTeam, List<TeamPlayer> mNewTeam) {
        this.mOldPlayerList = mOldTeam;
        this.mNewPlayerList = mNewTeam;
    }

    @Override
    public int getOldListSize() {
        return mOldPlayerList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewPlayerList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldPlayerList.get(oldItemPosition).hashCode() == mNewPlayerList.get(newItemPosition).hashCode();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldPlayerList.get(oldItemPosition).getFirstName()
                .equals(mNewPlayerList.get(newItemPosition).getFirstName()) &&
                mOldPlayerList.get(oldItemPosition).getLastName()
                        .equals(mNewPlayerList.get(newItemPosition).getLastName()) &&
                mOldPlayerList.get(oldItemPosition).getNumber()
                        == mNewPlayerList.get(newItemPosition).getNumber() &&
                mOldPlayerList.get(oldItemPosition).getPosition()
                        .equals(mNewPlayerList.get(newItemPosition).getPosition());
    }
}
