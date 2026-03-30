package com.example.soccerapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.soccerapp.R;
import com.example.soccerapp.model.Player;
import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private List<Player> players = new ArrayList<>();

    public PlayerAdapter(List<Player> players) {
        this.players = players;
    }

    public void updateData(List<Player> newPlayers) {
        this.players = newPlayers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_player, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player player = players.get(position);
        holder.bind(player);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    static class PlayerViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView positionTextView;
        private TextView teamTextView;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            positionTextView = itemView.findViewById(R.id.positionTextView);
            teamTextView = itemView.findViewById(R.id.teamTextView);
        }

        public void bind(Player player) {
            nameTextView.setText(player.getName());
            positionTextView.setText("Position: " + player.getPosition());
            teamTextView.setText("Team: " + player.getTeam());
        }
    }
}