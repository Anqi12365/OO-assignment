package com.example.moviedb;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load movies with error handling
        loadMovies();
    }

    private void loadMovies() {
        try {
            List<Movie> movies = JSONUtility.loadMoviesFromJson(this);

            if (movies == null || movies.isEmpty()) {
                Toast.makeText(this, "No movies found. Check JSON file.", Toast.LENGTH_LONG).show();
                return;
            }

            movieAdapter = new MovieAdapter(movies);
            recyclerView.setAdapter(movieAdapter);

            // Count valid movies and show message
            int validCount = 0;
            for (Movie movie : movies) {
                if (movie.isValid()) {
                    validCount++;
                }
            }

            if (validCount < movies.size()) {
                Toast.makeText(this, "Loaded " + validCount + " valid movies out of " +
                        movies.size() + " (some entries had errors)", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Loaded " + movies.size() + " movies successfully", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error loading movies: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}