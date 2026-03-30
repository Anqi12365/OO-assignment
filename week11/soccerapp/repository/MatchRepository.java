package com.example.soccerapp.repository;

import com.example.soccerapp.model.Match;
import java.util.*;
import java.util.function.Predicate;

public class MatchRepository {

    private List<Match> matches = new ArrayList<>();

    public void add(Match m) {
        matches.add(m);
    }

    public List<Match> getAll() {
        return matches;
    }

    public List<Match> filterByTeam(String team) {
        List<Match> result = new ArrayList<>();
        for (Match match : matches) {
            if (match.getHomeTeam().equals(team) || match.getAwayTeam().equals(team)) {
                result.add(match);
            }
        }
        return result;
    }

    // 手动实现filter方法，不使用Stream API
    public List<Match> filter(Predicate<Match> predicate) {
        List<Match> result = new ArrayList<>();
        for (Match match : matches) {
            if (predicate.test(match)) {
                result.add(match);
            }
        }
        return result;
    }
}