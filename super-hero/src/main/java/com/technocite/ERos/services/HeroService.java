package com.technocite.ERos.services;

import com.technocite.ERos.dao.Impl.HeroDaoImpl;
import com.technocite.ERos.dto.HeroDto;
import com.technocite.ERos.model.Hero;
import com.technocite.ERos.model.Status;
import com.technocite.ERos.utils.ErozUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class HeroService {
    @Autowired
    private HeroDaoImpl heroesDaoImpl;

    public Hero updateHeroes (HeroDto heroDto) throws SQLException{
        Hero hero = heroesDaoImpl.find(heroDto.getId());
        if (hero != null){
            heroesDaoImpl.update(hero);
            return heroesDaoImpl.find(heroDto.getId());
        }
        return null;
    }

    public HeroDto addHeroes(HeroDto heroDto) throws SQLException {
        //not allow to add new hero with a previous same name
        heroesDaoImpl.create(convertForCreation(heroDto));
        return convertToHeroDto(heroesDaoImpl.findByName(heroDto.getName()));
    }

    public boolean deleteHeroes(int id) throws SQLException {
        return heroesDaoImpl.delete(id);
    }

    public HeroDto find(int id) throws SQLException {
        return convertToHeroDto(heroesDaoImpl.find(id));
    }
    public List<Hero> findAllHeroes() throws SQLException{
        return heroesDaoImpl.findall();
    }

    //CONVERTOR
    private HeroDto convertToHeroDto(Hero hero) {
        return new HeroDto(
                hero.getId(),
                hero.getName(),
                hero.getAbility(),
                hero.getDescription(),
                hero.getStatus(),
                hero.getStrength(),
                hero.getBalance(),
                hero.getDeathInsurrance(),
                hero.getCost());
    }
    private Hero convert(HeroDto heroDto) {
        return new Hero(
                heroDto.getId(),
                heroDto.getName(),
                heroDto.getAbility(),
                heroDto.getDescription(),
                heroDto.getStatus(),
                heroDto.getStrength(),
                heroDto.getBalance(),
                heroDto.getDeathInsurrance(),
                heroDto.getCost());
    }
    private Hero convertForCreation(HeroDto heroDto) {
        int heroStrength = ErozUtils.getRandomNumber(5,30);
        int heroCost = heroStrength*100;
        return new Hero(
                heroDto.getId(),
                heroDto.getName(),
                heroDto.getAbility(),
                heroDto.getDescription(),
                Status.ACTIV,
                heroStrength,
                0.00,
                0.00,
                heroCost);
    }





    //NOT USE
    public HeroDto findByName(String superheroes) throws SQLException {
        return convertToHeroDto(heroesDaoImpl.findByName(superheroes));
    }

    public HeroDto findByAbility(String ability) throws SQLException {
        return convertToHeroDto(heroesDaoImpl.findByAbility(ability));
    }

    public List<Hero> findByStatus(Status status) throws SQLException {
        return heroesDaoImpl.findByStatus(status);
    }

    public List<Hero> findByBalance(Double min, Double max) throws SQLException {
        return heroesDaoImpl.findByBalance(min,max);
    }

}
