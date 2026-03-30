package com.example.soccerapp.model;

public class Match implements SoccerEntity {
    private String homeTeam;
    private String awayTeam;
    private String score;
    private String league;      // 添加
    private String date;         // 添加
    private String venue;        // 添加

    // 原有构造函数
    public Match(String homeTeam, String awayTeam, String score) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
    }

    // 新增完整构造函数
    public Match(String homeTeam, String awayTeam, String score,
                 String league, String date, String venue) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
        this.league = league;
        this.date = date;
        this.venue = venue;
    }

    @Override
    public String getId() {
        return homeTeam + " vs " + awayTeam;
    }

    @Override
    public String getName() {
        return homeTeam + " vs " + awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getScore() {
        return score;
    }

    public String getLeague() {
        return league;
    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }
}