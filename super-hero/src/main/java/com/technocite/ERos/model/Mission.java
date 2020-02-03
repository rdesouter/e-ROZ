package com.technocite.ERos.model;

import java.sql.Timestamp;
import java.util.List;

public class Mission {

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

    public Mission() {

    }

    public Mission(String title, double difficulty, int safe_people, String town, double award,
                   Timestamp date, Timestamp accomplished, double experience, List<Hero> heroes,
                   boolean isLaunch, boolean isDone, boolean isAccomplished, boolean isTry) {
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

    public Mission(int id, String title, double difficulty, int safe_people, String town, double award, Timestamp date,
                   Timestamp accomplished, double experience, List<Hero> heroes, boolean isLaunch, boolean isDone,
                   boolean isAccomplished, boolean isTry) {
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

    public Mission(int id, String title, double difficulty, int safe_people, String town, double award, Timestamp date,
                   Timestamp accomplished, double experience) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.safe_people = safe_people;
        this.town = town;
        this.award = award;
        this.date = date;
        this.accomplished = accomplished;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public int getSafe_people() {
        return safe_people;
    }

    public void setSafe_people(int safe_people) {
        this.safe_people = safe_people;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public double getAward() {
        return award;
    }

    public void setAward(double award) {
        this.award = award;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getAccomplished() {
        return accomplished;
    }

    public void setAccomplished(Timestamp accomplished) {
        this.accomplished = accomplished;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public boolean getIsLaunch() {
        return isLaunch;
    }

    public void setLaunch(boolean launch) {
        isLaunch = launch;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean getIsAccomplished() {
        return isAccomplished;
    }

    public void setAccomplished(boolean accomplished) {
        isAccomplished = accomplished;
    }

    public boolean getIsTry() {
        return isTry;
    }

    public void setTryToAccomplished(boolean tryToAccomplished) {
        isTry = tryToAccomplished;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", difficulty=" + difficulty +
                ", safe_people=" + safe_people +
                ", town='" + town + '\'' +
                ", award=" + award +
                ", date=" + date +
                ", accomplished=" + accomplished +
                ", experience=" + experience +
                ", heroes=" + heroes +
                ", isLaunch=" + isLaunch +
                ", isDone=" + isDone +
                ", isAccomplished=" + isAccomplished +
                ", isTry=" + isTry +
                '}';
    }
}
