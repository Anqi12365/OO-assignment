package com.example.soccerapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SoccerPagerAdapter extends FragmentStateAdapter {

    private TeamAdapter teamAdapter;
    private PlayerAdapter playerAdapter;
    private MatchAdapter matchAdapter;

    public SoccerPagerAdapter(@NonNull FragmentActivity fragmentActivity,
                              TeamAdapter teamAdapter,
                              PlayerAdapter playerAdapter,
                              MatchAdapter matchAdapter) {
        super(fragmentActivity);
        this.teamAdapter = teamAdapter;
        this.playerAdapter = playerAdapter;
        this.matchAdapter = matchAdapter;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return TeamFragment.newInstance(teamAdapter);
            case 1:
                return PlayerFragment.newInstance(playerAdapter);
            case 2:
                return MatchFragment.newInstance(matchAdapter);
            default:
                return TeamFragment.newInstance(teamAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}