package com.example.soccerapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.example.soccerapp.adapter.*;
import com.example.soccerapp.data.DataProvider;
import com.example.soccerapp.model.*;
import com.example.soccerapp.repository.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private EditText searchEditText;
    private Spinner sortSpinner;

    // Repositories
    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;
    private MatchRepository matchRepository;

    // Adapters
    private TeamAdapter teamAdapter;
    private PlayerAdapter playerAdapter;
    private MatchAdapter matchAdapter;

    private DataProvider dataProvider;
    private int currentTabPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRepositories();
        initViews();
        setupTabLayout();
        setupSearchAndSort();
    }

    private void initRepositories() {
        // Create repositories
        teamRepository = new TeamRepository();
        playerRepository = new PlayerRepository();
        matchRepository = new MatchRepository();

        // Initialize data provider
        dataProvider = new DataProvider();

        // Load sample data
        loadSampleData();
    }

    private void loadSampleData() {
        // Load teams
        List<Team> teams = dataProvider.createSampleTeams();
        for (Team team : teams) {
            teamRepository.add(team);
        }

        // Load players
        List<Player> players = dataProvider.createSamplePlayers();
        for (Player player : players) {
            playerRepository.add(player);
        }

        // Load matches
        List<Match> matches = dataProvider.createSampleMatches();
        for (Match match : matches) {
            matchRepository.add(match);
        }
    }

    private void initViews() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        searchEditText = findViewById(R.id.searchEditText);
        sortSpinner = findViewById(R.id.sortSpinner);

        // Initialize adapters with empty lists
        teamAdapter = new TeamAdapter(new ArrayList<>());
        playerAdapter = new PlayerAdapter(new ArrayList<>());
        matchAdapter = new MatchAdapter(new ArrayList<>());
    }

    private void setupTabLayout() {
        // Setup ViewPager with FragmentStateAdapter
        SoccerPagerAdapter pagerAdapter = new SoccerPagerAdapter(this,
                teamAdapter, playerAdapter, matchAdapter);
        viewPager.setAdapter(pagerAdapter);

        // Connect TabLayout with ViewPager2
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Teams");
                            break;
                        case 1:
                            tab.setText("Players");
                            break;
                        case 2:
                            tab.setText("Matches");
                            break;
                    }
                }
        ).attach();

        // Listen for tab changes
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentTabPosition = position;
                refreshCurrentData();
            }
        });

        // Initial data load
        refreshCurrentData();
    }

    private void setupSearchAndSort() {
        // Setup search with lambda expression
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                filterData(s.toString());
            }
        });

        // Setup sort spinner
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.sort_options, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(spinnerAdapter);

        // Sort selection listener
        sortSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                sortData(position);
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });
    }

    private void filterData(String query) {
        if (currentTabPosition == 0) {
            List<Team> filteredTeams;
            if (query.isEmpty()) {
                filteredTeams = teamRepository.getAll();
            } else {
                filteredTeams = teamRepository.filter(team ->
                        team.getName().toLowerCase().contains(query.toLowerCase()) ||
                                team.getLeague().toLowerCase().contains(query.toLowerCase())
                );
            }
            teamAdapter.updateData(filteredTeams);

        } else if (currentTabPosition == 1) {
            List<Player> filteredPlayers;
            if (query.isEmpty()) {
                filteredPlayers = playerRepository.getAll();
            } else {
                filteredPlayers = playerRepository.filter(player ->
                        player.getName().toLowerCase().contains(query.toLowerCase()) ||
                                player.getPosition().toLowerCase().contains(query.toLowerCase()) ||
                                player.getTeam().toLowerCase().contains(query.toLowerCase())
                );
            }
            playerAdapter.updateData(filteredPlayers);

        } else if (currentTabPosition == 2) {
            List<Match> filteredMatches;
            if (query.isEmpty()) {
                filteredMatches = matchRepository.getAll();
            } else {
                filteredMatches = matchRepository.filter(match ->
                        match.getHomeTeam().toLowerCase().contains(query.toLowerCase()) ||
                                match.getAwayTeam().toLowerCase().contains(query.toLowerCase()) ||
                                match.getScore().contains(query)
                );
            }
            matchAdapter.updateData(filteredMatches);
        }
    }

    private void sortData(int sortOption) {
        if (currentTabPosition == 0) {
            List<Team> teams = new ArrayList<>(teamRepository.getAll());
            switch (sortOption) {
                case 0: // Name ascending
                    Collections.sort(teams, (t1, t2) -> t1.getName().compareTo(t2.getName()));
                    break;
                case 1: // Name descending
                    Collections.sort(teams, (t1, t2) -> t2.getName().compareTo(t1.getName()));
                    break;
                case 2: // League ascending
                    Collections.sort(teams, (t1, t2) -> t1.getLeague().compareTo(t2.getLeague()));
                    break;
            }
            teamAdapter.updateData(teams);

        } else if (currentTabPosition == 1) {
            List<Player> players = new ArrayList<>(playerRepository.getAll());
            switch (sortOption) {
                case 0: // Name ascending
                    Collections.sort(players, (p1, p2) -> p1.getName().compareTo(p2.getName()));
                    break;
                case 1: // Name descending
                    Collections.sort(players, (p1, p2) -> p2.getName().compareTo(p1.getName()));
                    break;
                case 2: // Position ascending
                    Collections.sort(players, (p1, p2) -> p1.getPosition().compareTo(p2.getPosition()));
                    break;
            }
            playerAdapter.updateData(players);

        } else if (currentTabPosition == 2) {
            List<Match> matches = new ArrayList<>(matchRepository.getAll());
            switch (sortOption) {
                case 0: // Home team ascending
                    Collections.sort(matches, (m1, m2) -> m1.getHomeTeam().compareTo(m2.getHomeTeam()));
                    break;
                case 1: // Home team descending
                    Collections.sort(matches, (m1, m2) -> m2.getHomeTeam().compareTo(m1.getHomeTeam()));
                    break;
                case 2: // Score ascending
                    Collections.sort(matches, (m1, m2) -> m1.getScore().compareTo(m2.getScore()));
                    break;
            }
            matchAdapter.updateData(matches);
        }
    }

    private void refreshCurrentData() {
        // Using iterator pattern to traverse data
        if (currentTabPosition == 0) {
            List<Team> teams = new ArrayList<>();
            Iterator<Team> teamIterator = teamRepository.getAll().iterator();
            while (teamIterator.hasNext()) {
                teams.add(teamIterator.next());
            }
            teamAdapter.updateData(teams);

        } else if (currentTabPosition == 1) {
            List<Player> players = new ArrayList<>();
            Iterator<Player> playerIterator = playerRepository.getAll().iterator();
            while (playerIterator.hasNext()) {
                players.add(playerIterator.next());
            }
            playerAdapter.updateData(players);

        } else if (currentTabPosition == 2) {
            List<Match> matches = new ArrayList<>();
            Iterator<Match> matchIterator = matchRepository.getAll().iterator();
            while (matchIterator.hasNext()) {
                matches.add(matchIterator.next());
            }
            matchAdapter.updateData(matches);
        }

        // Clear search when refreshing
        if (searchEditText != null) {
            searchEditText.setText("");
        }
    }
}