package com.technocite.ERos.dto;

import com.technocite.ERos.model.Hero;

import java.sql.Timestamp;
import java.util.List;

public class MissionDto {

    //@JsonIgnore
    private int id;
    private String title;
    private double difficulty;
    private int safe_people;
    private String town;
    private double award;
    private Timestamp date;
    private Timestamp accomplished;
    private double experience;
    private List<Hero> heroes;
    private boolean isLaunch;
    private boolean isDone;
    private boolean isAccomplished;
    private boolean isTry;

    public MissionDto() {
    }

    public MissionDto(int id, String title, double difficulty, int safe_people, String town, double award,
                      Timestamp date, Timestamp accomplished, double experience, List<Hero> heroes,
                      boolean isLaunch, boolean isDone, boolean isAccomplished, boolean isTry) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.safe_people = safe_people;
        this.town = town;
        this.award = award;
        this.date = date;
        this.accomplished = accomplished;
        this.experience = experience;
        this.heroes = heroes;
        this.isLaunch = isLaunch;
        this.isDone = isDone;
        this.isAccomplished = isAccomplished;
        this.isTry = isTry;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public int getSafe_people() {
        return safe_people;
    }

    public String getTown() {
        return town;
    }

    public double getAward() {
        return award;
    }

    public Timestamp getDate() {
        return date;
    }

    public Timestamp getAccomplished() {
        return accomplished;
    }

    public double getExperience() {
        return experience;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public boolean getIsLaunch() {
        return isLaunch;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public boolean getIsAccomplished() {
        return isAccomplished;
    }

    public boolean getIsTry() {
        return isTry;
    }

}
