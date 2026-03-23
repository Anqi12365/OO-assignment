package com.example.moviedb;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    TextView textTitle;
    TextView textYear;
    TextView textGenre;

    public MovieViewHolder(View itemView) {
        super(itemView);
        textTitle = itemView.findViewById(R.id.text_title);
        textYear = itemView.findViewById(R.id.text_year);
        textGenre = itemView.findViewById(R.id.text_genre);
    }

    public void bind(Movie movie) {
        textTitle.setText(movie.getTitle());

        // Handle invalid year
        if (movie.getYear() > 0) {
            textYear.setText("Year: " + movie.getYear());
        } else {
            textYear.setText("Year: N/A");
        }

        textGenre.setText("Genre: " + movie.getGenre());
    }
}