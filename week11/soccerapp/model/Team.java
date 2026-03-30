package com.example.soccerapp.model;

public class Team implements SoccerEntity {
    private String name;
    private String country;
    private String league;
    private String stadium;    // 添加
    private int founded;       // 添加

    // 原有构造函数
    public Team(String name, String country, String league) {
        this.name = name;
        this.country = country;
        this.league = league;
    }

    // 新增完整构造函数
    public Team(String name, String country, String league,
                String stadium, int founded) {
        this.name = name;
        this.country = country;
        this.league = league;
        this.stadium = stadium;
        this.founded = founded;
    }

    @Override
    public String getId() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getLeague() {
        return league;
    }

    public String getCountry() {
        return country;
    }

    public String getStadium() {
        return stadium;
    }

    public int getFounded() {
        return founded;
    }
}