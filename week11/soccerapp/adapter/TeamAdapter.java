package com.example.soccerapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.soccerapp.R;
import com.example.soccerapp.model.Team;
import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private List<Team> teams = new ArrayList<>();

    public TeamAdapter(List<Team> teams) {
        this.teams = teams;
    }

    public void updateData(List<Team> newTeams) {
        this.teams = newTeams;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_team, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        Team team = teams.get(position);
        holder.bind(team);
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    static class TeamViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView countryTextView;
        private TextView leagueTextView;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            countryTextView = itemView.findViewById(R.id.countryTextView);
            leagueTextView = itemView.findViewById(R.id.leagueTextView);
        }

        public void bind(Team team) {
            nameTextView.setText(team.getName());
            countryTextView.setText("Country: " + team.getCountry());
            leagueTextView.setText("League: " + team.getLeague());
        }
    }
}