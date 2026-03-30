package com.example.soccerapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.soccerapp.R;
import com.example.soccerapp.model.Match;
import java.util.ArrayList;
import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {

    private List<Match> matches = new ArrayList<>();

    public MatchAdapter(List<Match> matches) {
        this.matches = matches;
    }

    public void updateData(List<Match> newMatches) {
        this.matches = newMatches;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_match, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Match match = matches.get(position);
        holder.bind(match);
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    static class MatchViewHolder extends RecyclerView.ViewHolder {
        private TextView matchTextView;
        private TextView scoreTextView;
        private TextView leagueTextView;
        private TextView dateTextView;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            matchTextView = itemView.findViewById(R.id.matchTextView);
            scoreTextView = itemView.findViewById(R.id.scoreTextView);
            leagueTextView = itemView.findViewById(R.id.leagueTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }

        public void bind(Match match) {
            matchTextView.setText(match.getHomeTeam() + " vs " + match.getAwayTeam());
            scoreTextView.setText("Score: " + match.getScore());
            leagueTextView.setText("League: " + match.getLeague());
            dateTextView.setText("Date: " + match.getDate());
        }
    }
}