package com.example.soccerapp.model;

public class Player implements SoccerEntity {
    private String name;
    private int age;              // 添加
    private String nationality;   // 添加
    private String position;
    private String team;
    private int number;           // 添加

    // 原有构造函数
    public Player(String name, String position, String team) {
        this.name = name;
        this.position = position;
        this.team = team;
    }

    // 新增完整构造函数
    public Player(String name, int age, String nationality,
                  String position, String team, int number) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
        this.position = position;
        this.team = team;
        this.number = number;
    }

    @Override
    public String getId() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

    public int getNumber() {
        return number;
    }
}