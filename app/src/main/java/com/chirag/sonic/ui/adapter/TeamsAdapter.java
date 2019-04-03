package com.chirag.sonic.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chirag.sonic.R;
import com.chirag.sonic.data.model.NBATeam;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Chirag on 4/2/2019 at 17:38.
 * Project - NBATeamViewer
 */
public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamViewHolder> {

    private OnItemClickListener listener;
    private List<NBATeam> mTeams;

    public TeamsAdapter() {
        mTeams = new ArrayList<>();
    }

    public void updateTeamList(List<NBATeam> teams) {
        final NBATeamDiffCallback diffCallback = new NBATeamDiffCallback(this.mTeams, teams);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.mTeams.clear();
        this.mTeams.addAll(teams);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_items, parent, false);
        return new TeamViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsAdapter.TeamViewHolder holder, int position) {
        NBATeam team = mTeams.get(position);
        holder.teamName.setText(team.getName());
        holder.teamWins.setText(String.valueOf(team.getWins()));
        holder.teamLooses.setText(String.valueOf(team.getLoses()));
    }

    @Override
    public int getItemCount() {
        return mTeams.size();
    }


    class TeamViewHolder extends RecyclerView.ViewHolder {

        private TextView teamName;
        private TextView teamWins;
        private TextView teamLooses;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.team_name);
            teamWins = itemView.findViewById(R.id.team_wins);
            teamLooses = itemView.findViewById(R.id.team_looses);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemCLick(mTeams.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemCLick(NBATeam team);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
