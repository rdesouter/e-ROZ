package com.technocite.ERos.controllers;

import com.technocite.ERos.dto.HeroDto;
import com.technocite.ERos.model.Hero;
import com.technocite.ERos.model.Status;
import com.technocite.ERos.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    // CRUD REQUEST
    @PostMapping
    public ResponseEntity<HeroDto> addHeroes(@RequestBody HeroDto heroDto) throws SQLException {
        HeroDto heroes = heroService.addHeroes(heroDto);
        if (heroes != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    private List<Hero> getAllHeroes() throws SQLException {
        return heroService.findAllHeroes();
    }

    @GetMapping(value = "/id/{id}")
    public HeroDto getHeroesById(@PathVariable int id) throws SQLException {
        return heroService.find(id);
    }

    @PutMapping
    public ResponseEntity<HeroDto> updateHeroe(@RequestBody HeroDto heroDto) throws SQLException {
        if (heroService.updateHeroes(heroDto) != null) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/del")
    public ResponseEntity<HeroDto> deleteHeroes(@RequestBody int id) throws SQLException {
        if (heroService.deleteHeroes(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //SPECIFIC REQUEST
    @GetMapping(value = "/name/{superheroes}")
    public HeroDto getHeroesByName(@PathVariable String superheroes) throws SQLException {
        return heroService.findByName(superheroes);
    }

    @GetMapping(value = "/status/{status}")
    public List<Hero> searchByStatus(@PathVariable Status status) throws SQLException {
        return heroService.findByStatus(status);
    }

    @GetMapping(value = "balance/min/{min}/max/{max}")
    public List<Hero> searchByMinMax(@PathVariable Double min, @PathVariable Double max) throws SQLException {
        return heroService.findByBalance(min, max);
    }

    @GetMapping(value = "/abilityName/{ability}")
    public HeroDto searchByAbility(@PathVariable String ability) throws SQLException {
        return heroService.findByAbility(ability);
    }
    
}
