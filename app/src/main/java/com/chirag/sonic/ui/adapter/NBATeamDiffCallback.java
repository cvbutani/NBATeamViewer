package com.chirag.sonic.ui.adapter;

import com.chirag.sonic.data.model.NBATeam;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by Chirag on 4/2/2019 at 20:39.
 * Project - NBATeamViewer
 */
public class NBATeamDiffCallback extends DiffUtil.Callback {

    private final List<NBATeam> mOldTeam;
    private final List<NBATeam> mNewTeam;

    public NBATeamDiffCallback(List<NBATeam> mOldTeam, List<NBATeam> mNewTeam) {
        this.mOldTeam = mOldTeam;
        this.mNewTeam = mNewTeam;
    }

    @Override
    public int getOldListSize() {
        return mOldTeam.size();
    }

    @Override
    public int getNewListSize() {
        return mNewTeam.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldTeam.get(oldItemPosition).hashCode() == mNewTeam.get(newItemPosition).hashCode();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldTeam.get(oldItemPosition).getName().equals(mNewTeam.get(newItemPosition).getName()) &&
                mOldTeam.get(oldItemPosition).getWins() == mNewTeam.get(newItemPosition).getWins() &&
                mOldTeam.get(oldItemPosition).getLoses() == mNewTeam.get(newItemPosition).getLoses() &&
                mOldTeam.get(oldItemPosition).getPlayers().equals(mNewTeam.get(newItemPosition).getPlayers());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
