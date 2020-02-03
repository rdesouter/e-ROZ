package com.technocite.ERos.services;

import com.technocite.ERos.dao.Impl.MissionDaoImpl;
import com.technocite.ERos.dto.MissionDto;
import com.technocite.ERos.model.Hero;
import com.technocite.ERos.model.Mission;
import com.technocite.ERos.utils.ErozUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
public class MissionsService {

    @Autowired
    private MissionDaoImpl missionDaoImpl;

    public List<MissionDto> findAll() throws SQLException {
        return missionDaoImpl.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public MissionDto find(int id) throws SQLException {
        return ofNullable(missionDaoImpl.find(id)).map(this::convertToDto).orElse(null);
        /*Mission mission = missionDao.find(id);
        if(mission != null){
            return this.convertToDTO(mission);
        }*/
    }

    public MissionDto create(MissionDto missionDto) throws SQLException {
        Mission mission = missionDaoImpl.find(missionDto.getId());
        if (mission != null) {
            throw new RuntimeException("Mission cannot be created");
        } else {
            missionDaoImpl.create(convertForCreate(missionDto));
        }
        return find(missionDto.getId());
    }

    public MissionDto update(MissionDto missionDto) throws SQLException {
        Mission mission = missionDaoImpl.find(missionDto.getId());
        if (mission == null) {
            throw new RuntimeException("Mission not found");
        } else {
            missionDaoImpl.update(convertForUpdate(missionDto));
        }
        return missionDto;
    }

    public boolean endOfMission(MissionDto missionDto) throws SQLException {
        Mission mission = missionDaoImpl.find(missionDto.getId());
        if (mission == null) {
            throw new RuntimeException("Mission not found");
        } else {
            missionDaoImpl.endOfMission(convertForUpdate(missionDto));
            return true;
        }
    }

    public boolean isSucceed(MissionDto missionDto) throws SQLException {
        Mission mission = missionDaoImpl.find(missionDto.getId());
        if (mission == null){
            throw new RuntimeException("Mission doesn't exist");
        }
        else {
            missionDaoImpl.isMissionSucceed(convertForUpdate(missionDto));
            return true;
        }
    }

    // convertor
    private MissionDto convertToDto(Mission mission) {
        return new MissionDto(
                mission.getId(),
                mission.getTitle(),
                mission.getDifficulty(),
                mission.getSafe_people(),
                mission.getTown(),
                mission.getAward(),
                mission.getDate(),
                mission.getAccomplished(),
                mission.getExperience(),
                mission.getHeroes(),
                mission.getIsLaunch(),
                mission.getIsDone(),
                mission.getIsAccomplished(),
                mission.getIsTry());
    }

    private Mission convertForCreate(MissionDto missionDto) {
        double difficulty = ErozUtils.getRandomNumber(30,100);
        return new Mission(
                missionDto.getId(),
                missionDto.getTitle(),
                difficulty,
                missionDto.getSafe_people(),
                missionDto.getTown(),
                missionDto.getAward(),
                ErozUtils.getTimeStamp(),
                ErozUtils.getRandomDateAddedByMinutesPlusBetween(1,3),
                ErozUtils.getExperience(difficulty),
                missionDto.getHeroes(),
                false,
                false,
                false,
                false
        );
    }

    private Mission convertForUpdate(MissionDto missionDto) {
        return new Mission(
                missionDto.getId(),
                missionDto.getTitle(),
                missionDto.getDifficulty(),
                missionDto.getSafe_people(),
                missionDto.getTown(),
                missionDto.getAward(),
                missionDto.getDate(),
                missionDto.getAccomplished(),
                missionDto.getExperience(),
                missionDto.getHeroes(),
                missionDto.getIsLaunch(),
                missionDto.getIsDone(),
                missionDto.getIsAccomplished(),
                missionDto.getIsTry());
    }


    //not finish and usable
    public MissionDto searchByTitle(String title) throws SQLException {
        return convertToDto(missionDaoImpl.findByTitle(title));
    }

    public MissionDto searchByDifficulty(int difficulty) throws SQLException {
        return convertToDto(missionDaoImpl.findByDifficulty(difficulty));
    }

    public MissionDto searchBySafePeople(String safePeople) throws SQLException {
        return convertToDto(missionDaoImpl.findBySafePeople(safePeople));
    }

    public MissionDto searchByTown(String town) throws SQLException {
        return convertToDto(missionDaoImpl.findByTown(town));
    }

    public MissionDto searchByAward(double award) throws SQLException {
        return convertToDto(missionDaoImpl.findByAward(award));
    }

    public MissionDto searchByDate(Date date) throws SQLException {
        return convertToDto(missionDaoImpl.findByDate(date));
    }

    public MissionDto searchByAccomplished(Date accomplished) throws SQLException {
        return convertToDto(missionDaoImpl.findByAccomplished(accomplished));
    }

    public MissionDto searchByExperience(String experience) throws SQLException {
        return convertToDto(missionDaoImpl.findByExperience(experience));
    }

}
