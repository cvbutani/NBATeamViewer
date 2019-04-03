package com.chirag.sonic.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chirag.sonic.R;
import com.chirag.sonic.data.model.TeamPlayer;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Chirag on 4/2/2019 at 18:19.
 * Project - NBATeamViewer
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    List<TeamPlayer> mTeamPlayerList;

    public PlayerAdapter() {
        mTeamPlayerList = new ArrayList<>();
    }

    public void updateTeamList(List<TeamPlayer> teamPlayers) {

        final TeamPlayersDiffCallBack diffCallback =
                new TeamPlayersDiffCallBack(this.mTeamPlayerList, teamPlayers);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.mTeamPlayerList.clear();
        this.mTeamPlayerList.addAll(teamPlayers);

        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                               int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_info,
                parent, false);
        return new PlayerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.PlayerViewHolder holder, int position) {
        TeamPlayer teamPlayer = mTeamPlayerList.get(position);
        holder.playerName.setText(teamPlayer.getFullName());
        holder.playerPosition.setText(teamPlayer.getPosition());
        holder.playerNumber.setText(String.valueOf(teamPlayer.getNumber()));
    }

    @Override
    public int getItemCount() {
        return mTeamPlayerList.size();
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder {
        private TextView playerName;
        private TextView playerNumber;
        private TextView playerPosition;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.player_name);
            playerNumber = itemView.findViewById(R.id.player_number);
            playerPosition = itemView.findViewById(R.id.player_position);
        }
    }
}
