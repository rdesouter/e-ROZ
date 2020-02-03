package com.technocite.ERos.controllers;

import com.technocite.ERos.dto.MissionDto;
import com.technocite.ERos.services.MissionsService;
import com.technocite.ERos.utils.ErozUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/missions/")
public class MissionController {

    @Autowired
    private MissionsService missionsService;

    @GetMapping
    public List<MissionDto> getAll() throws SQLException {
        return missionsService.findAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<MissionDto> getId(@PathVariable int id) throws SQLException {
        MissionDto mission = missionsService.find(id);

        if (mission != null) {
            return ResponseEntity.ok().body(mission);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public MissionDto addMission(@RequestBody MissionDto mission) throws SQLException {
        return missionsService.create(mission);
    }

    @PutMapping
    public MissionDto update(@RequestBody MissionDto missionDto) throws SQLException {
        return missionsService.update(missionDto);
    }

    @PutMapping(value = "end")
    public boolean endOfMission(@RequestBody MissionDto missionDto) throws SQLException {
        return missionsService.endOfMission(missionDto);
    }

    @PutMapping(value = "succeed")
    public boolean isSucceed(@RequestBody MissionDto missionDto) throws SQLException {
        return missionsService.isSucceed(missionDto);
    }

}