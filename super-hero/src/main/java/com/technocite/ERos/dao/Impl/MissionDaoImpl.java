package com.technocite.ERos.dao.Impl;

import com.technocite.ERos.dao.AbstractDao;
import com.technocite.ERos.dao.MissionDao;
import com.technocite.ERos.factory.HeroFactory;
import com.technocite.ERos.factory.MissionFactory;
import com.technocite.ERos.model.Hero;
import com.technocite.ERos.model.Mission;
import com.technocite.ERos.model.Status;
import com.technocite.ERos.utils.ErozUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
@Repository
public class MissionDaoImpl extends AbstractDao<Mission> implements MissionDao {

    private PreparedStatement createStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement updateHeroStatement;
    private PreparedStatement updateCompanyStatement;
    private PreparedStatement findAll;
    private PreparedStatement findStatementByHeroesOnMissionId;
    private PreparedStatement findStatementById;
    private PreparedStatement creatForUpdateStatement;
    //end
    private PreparedStatement updateMissionEnd;
    //succeed
    private PreparedStatement updateMissionSucceed;
    private PreparedStatement updateHeroSucced;
    private PreparedStatement updateCompanySucceed;
    //NOT USED
    private PreparedStatement findStatementByTitle;
    private PreparedStatement findStatementByDifficulty;
    private PreparedStatement findStatementBySafePeople;
    private PreparedStatement findStatementByTown;
    private PreparedStatement findStatementByAward;
    private PreparedStatement findStatementByDate;
    private PreparedStatement findStatementByAccomplished;
    private PreparedStatement findStatementByExperience;
    //
    private MissionFactory missionFactory;
    private HeroFactory heroFactory;

    @Autowired
    private HeroDaoImpl heroesDao;

    public MissionDaoImpl() throws SQLException {
        missionFactory = new MissionFactory();
        heroFactory = new HeroFactory();
    }
//       @Scheduled(fixedDelay = 5000)
//    public void scheduleTask() throws SQLException {
//        System.out.println("Excecute each 5seconds");
//        create(ErozUtils.createRandomMission());
//    }


//    spring cron expression different from regular cron expression(7values)
//    Excecute each 2minutes(starting from 0) between 8.00AM->17.00PM each months between monday to saturday
//    @Scheduled(cron = "0 0/2 8-17 ? * MON-SAT" )
//    public void scheduleTask() throws SQLException {
//        System.out.println("Excecute each 2minutes");
//        create(ErozUtils.createRandomMission());
//    }

    @Override
    public void create(Mission mission) throws SQLException {
        List<Hero> heroes = mission.getHeroes();
        for (Hero hero : heroes) {
            heroesDao.create(hero);
            System.out.println(hero.getName() + " a bien été ajouté à la mission");
        }
        if (createStatement == null) {
            createStatement = this.connection.prepareStatement("INSERT INTO missions(title, difficulty, safe_people, town, award, date, accomplished, experience, is_launched, is_done, is_accomplished, is_try) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
        }
        createStatement.setString(1, mission.getTitle());
        createStatement.setDouble(2, mission.getDifficulty());
        createStatement.setInt(3, mission.getSafe_people());
        createStatement.setString(4, mission.getTown());
        createStatement.setDouble(5, mission.getAward());
        createStatement.setTimestamp(6, mission.getDate());
        createStatement.setTimestamp(7, mission.getAccomplished());
        createStatement.setDouble(8, mission.getExperience());
        createStatement.setBoolean(9, mission.getIsLaunch());
        createStatement.setBoolean(10, mission.getIsDone());
        createStatement.setBoolean(11, mission.getIsAccomplished());
        createStatement.setBoolean(12, mission.getIsTry());
        createStatement.executeUpdate();
    }

    @Override
    public List<Mission> findAll() throws SQLException {
        findAll = this.connection.prepareStatement("SELECT * FROM missions ORDER BY id DESC");
        List<Mission> missionList = new ArrayList<>();
        ResultSet resultSet = findAll.executeQuery();
        //resultSet.next();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            missionList.add(find(id));
        }
        return missionList;
    }

    @Override
    public Mission find(int id) throws SQLException {
        List<Hero> heroes = new ArrayList<>();
        if (findStatementById == null) {
            findStatementById = this.connection.prepareStatement("SELECT * FROM missions WHERE id = ?");
            findStatementByHeroesOnMissionId = this.connection.prepareStatement("SELECT heroes.* FROM missions_heroes \n" +
                    "INNER JOIN heroes ON heroes.id = missions_heroes.id_hero\n" +
                    "INNER JOIN missions ON missions.id = missions_heroes.id_mission WHERE missions.id = ?");

        }
        findStatementById.setInt(1, id);
        ResultSet resultSetMission = findStatementById.executeQuery();
        resultSetMission.next();

        // for creation of mission id is null due to serial in postgres
        if (id == 0) {
            System.out.println("empty data");
            return null;
        }
        findStatementByHeroesOnMissionId.setInt(1, id);
        ResultSet resultSetHeroesOnMission = findStatementByHeroesOnMissionId.executeQuery();

        while (resultSetHeroesOnMission.next()) {
            //String nameOfHero = resultSetHeroesOnMission.getString(2);
            //System.out.println(nameOfHero);
            Hero hero = heroFactory.createFrom(resultSetHeroesOnMission);
            heroes.add(hero);
        }
        return missionFactory.createFrom(resultSetMission, heroes);
    }

    @Override
    public void update(Mission mission) throws SQLException {
        List<Hero> heroes = mission.getHeroes();
        String inDutyString = String.valueOf(Status.IN_DUTY);
        double sumHeroCost = 0;

        if (creatForUpdateStatement == null && updateHeroStatement == null && updateCompanyStatement == null && updateStatement == null) {
            creatForUpdateStatement = this.connection.prepareStatement(
                    "INSERT INTO missions_heroes VALUES (?, ?);");
            updateHeroStatement = this.connection.prepareStatement(
                    "UPDATE heroes " +
                            "SET state = ?, balance = balance + ? " +
                            "WHERE id = ?;");
            updateCompanyStatement = this.connection.prepareStatement(
                    "UPDATE company SET assets = assets - ?");
            updateStatement = this.connection.prepareStatement(
                    "UPDATE missions " +
                            "SET is_launched=?, is_done=?, is_accomplished=? " +
                            "WHERE id = ?");
        }

        for (Hero hero : heroes) {
            sumHeroCost += hero.getCost();
            creatForUpdateStatement.setInt(1, mission.getId());
            creatForUpdateStatement.setInt(2, hero.getId());
            creatForUpdateStatement.executeUpdate();
            System.out.println(hero.getName() + " a bien été ajouté à la mission '" + mission.getTitle() + "'");

            updateHeroStatement.setString(1, inDutyString);
            updateHeroStatement.setDouble(2, hero.getCost());
            updateHeroStatement.setInt(3, hero.getId());
            updateHeroStatement.executeUpdate();
            System.out.println("le status de " + hero.getName() + " a bien été modifié à " + inDutyString);

        }
        //update the company assets
        updateCompanyStatement.setDouble(1, sumHeroCost);
        updateCompanyStatement.executeUpdate();

        //update the mission is_launched to true
        updateStatement.setBoolean(1, mission.getIsLaunch());
        updateStatement.setBoolean(2, mission.getIsDone());
        updateStatement.setBoolean(3, mission.getIsAccomplished());
        updateStatement.setInt(4, mission.getId());
        updateStatement.executeUpdate();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean endOfMission(Mission mission) throws SQLException {
        if (updateMissionEnd == null) {
            updateMissionEnd = this.connection.prepareStatement(
                    "UPDATE missions SET is_done=? WHERE id=?");
        }
        updateMissionEnd.setBoolean(1, mission.getIsDone());
        updateMissionEnd.setInt(2, mission.getId());
        updateMissionEnd.executeUpdate();
        System.out.println("is mission " + mission.getTitle() + " finished ?" + mission.getIsDone());
        return true;
    }

    @Override
    public boolean isMissionSucceed(Mission mission) throws SQLException {
        //TODO share experience to each hero so they can grow them level
        // add to postgresl column first mission has already sum of exp
        double shareXP = mission.getExperience() / mission.getHeroes().size();
        double sumHeroesStrength = 0;
        List<Hero> heroes = mission.getHeroes();
        for (Hero hero : heroes) {
            sumHeroesStrength += hero.getStrength();
        }
        // example if sumHeroes = 12 and difficulty = 13 percentage of succeed chance = 92.3%
        double percentageOfSucceed = sumHeroesStrength / mission.getDifficulty();

        if (updateHeroSucced == null && updateMissionSucceed == null) {
            updateMissionSucceed = this.connection.prepareStatement(
                    "UPDATE missions SET is_accomplished = ?, is_try = ? WHERE id = ?");
            updateHeroSucced = this.connection.prepareStatement(
                    "UPDATE heroes SET state = ? WHERE id = ?");
            updateCompanySucceed = this.connection.prepareStatement("UPDATE company SET assets = assets + ?");
        }
        if (sumHeroesStrength > mission.getDifficulty()) {
            updateMissionAndCompanyWhenSucceed(mission);
            updateHeroes(heroes);
            System.out.println(mission.getTitle() + " of course is successful ");
            return true;
        } else if (ErozUtils.successChance(percentageOfSucceed)) {
            updateMissionAndCompanyWhenSucceed(mission);
            updateHeroes(heroes);
            System.out.println(mission.getTitle() + " is successful, percentage of success was " + percentageOfSucceed + " %");
            return true;
        } else {
            updateMissionSucceed.setBoolean(1, false);
            updateMissionSucceed.setBoolean(2, true);
            updateMissionSucceed.setInt(3, mission.getId());
            updateMissionSucceed.executeUpdate();
            updateHeroes(heroes);
            System.out.println(mission.getTitle() + " is failed, percentage of success was " + percentageOfSucceed + " %");
            return false;
        }
    }

    private void updateMissionAndCompanyWhenSucceed(Mission mission) throws SQLException {
        updateMissionSucceed.setBoolean(1, true);
        updateMissionSucceed.setBoolean(2, true);
        updateMissionSucceed.setInt(3, mission.getId());
        updateMissionSucceed.executeUpdate();
        updateCompanySucceed.setDouble(1, mission.getAward());
        updateCompanySucceed.executeUpdate();
    }

    private void updateHeroes(List<Hero> heroes) throws SQLException {
        for (Hero hero : heroes) {
            String status = String.valueOf(hero.getStatus());
            updateHeroSucced.setString(1, status);
            updateHeroSucced.setInt(2, hero.getId());
            updateHeroSucced.executeUpdate();
            System.out.println(hero.getName() + " son status est passé à " + status);
        }
    }


    //NOT USED
    @Override
    public Mission findByTitle(String title) throws SQLException {
        if (findStatementByTitle == null) {
            findStatementByTitle = this.connection.prepareStatement("SELECT * FROM missions WHERE title=?;");
        }
        findStatementByTitle.setString(1, title);
        ResultSet resultSet = findStatementByTitle.executeQuery();
        resultSet.next();
        return missionFactory.createFrom(resultSet);
    }

    public Mission findByDifficulty(int difficulty) throws SQLException {
        if (findStatementByDifficulty == null) {
            findStatementByDifficulty = this.connection.prepareStatement("SELECT * FROM missions WHERE difficulty =?;");
        }
        findStatementByDifficulty.setInt(3, difficulty);
        ResultSet resultSet = findStatementByDifficulty.executeQuery();
        resultSet.next();

        return missionFactory.createFrom(resultSet);
    }

    public Mission findBySafePeople(String safePeople) throws SQLException {
        if (findStatementBySafePeople == null) {
            findStatementBySafePeople = this.connection.prepareStatement("SELECT * FROM missions WHERE safe_people =?;");
        }
        findStatementBySafePeople.setString(4, safePeople);
        ResultSet resultSet = findStatementBySafePeople.executeQuery();
        resultSet.next();

        return missionFactory.createFrom(resultSet);
    }

    public Mission findByTown(String town) throws SQLException {
        if (findStatementByTown == null) {
            findStatementByTown = this.connection.prepareStatement("SELECT * FROM missions WHERE town=?;");
        }
        findStatementByTown.setString(5, town);
        ResultSet resultSet = findStatementByTown.executeQuery();
        resultSet.next();
        return missionFactory.createFrom(resultSet);
    }

    public Mission findByAward(double award) throws SQLException {
        if (findStatementByAward == null) {
            findStatementByAward = this.connection.prepareStatement("SELECT * FROM missions WHERE award=?;");
        }
        findStatementByAward.setDouble(6, award);
        ResultSet resultSet = findStatementByAward.executeQuery();
        resultSet.next();
        return missionFactory.createFrom(resultSet);
    }

    public Mission findByDate(Date timestamp) throws SQLException {
        if (findStatementByDate == null) {
            findStatementByDate = this.connection.prepareStatement("SELECT * FROM missions WHERE date=?;");
        }
        findStatementByDate.setDate(7, timestamp);
        ResultSet resultSet = findStatementByDate.executeQuery();
        resultSet.next();

        return missionFactory.createFrom(resultSet);
    }

    public Mission findByAccomplished(Date accomplished) throws SQLException {
        if (findStatementByAccomplished == null) {
            findStatementByAccomplished = this.connection.prepareStatement("SELECT * FROM missions WHERE accomplished=?;");
        }
        findStatementByAccomplished.setDate(8, accomplished);
        ResultSet resultSet = findStatementByAccomplished.executeQuery();
        resultSet.next();

        return missionFactory.createFrom(resultSet);
    }

    public Mission findByExperience(String experience) throws SQLException {
        if (findStatementByExperience == null) {
            findStatementByExperience = this.connection.prepareStatement("SELECT * FROM missions WHERE experience = ?");
        }
        findStatementByExperience.setString(9, experience);
        ResultSet resultSet = findStatementByExperience.executeQuery();
        resultSet.next();
        return missionFactory.createFrom(resultSet);
    }

}
