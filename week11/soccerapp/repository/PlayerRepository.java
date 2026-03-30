package com.example.soccerapp.repository;

import com.example.soccerapp.model.Player;
import java.util.List;

public class PlayerRepository extends Repository<Player> {

    public List<Player> filterByTeam(String team) {
        return filter(p -> p.getTeam().equals(team));
    }
}