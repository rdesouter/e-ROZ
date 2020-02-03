package com.technocite.ERos.dao;

import com.technocite.ERos.model.Mission;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface MissionDao {

    List<Mission> findAll() throws SQLException;

    boolean endOfMission(Mission mission) throws SQLException;

    boolean isMissionSucceed (Mission mission) throws SQLException;



    //NOT USED
    Mission findByTitle(String title) throws SQLException;

    Mission findByDifficulty(int difficulty) throws SQLException;

    Mission findBySafePeople(String findBySafePeople) throws SQLException;

    Mission findByTown(String town) throws SQLException;

    Mission findByAward(double award) throws SQLException;

    Mission findByDate(Date timestamp) throws SQLException;

    Mission findByAccomplished(Date accomplished) throws SQLException;

    Mission findByExperience(String experience) throws SQLException;
}
