package com.example.soccerapp.repository;

import com.example.soccerapp.model.Team;
import java.util.List;

public class TeamRepository extends Repository<Team> {

    public List<Team> filterByLeague(String league) {
        return filter(t -> t.getLeague().equals(league));
    }
}