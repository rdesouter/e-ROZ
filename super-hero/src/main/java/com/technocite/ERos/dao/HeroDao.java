package com.technocite.ERos.dao;

import com.technocite.ERos.model.Hero;
import com.technocite.ERos.model.Status;

import java.sql.SQLException;
import java.util.List;

public interface HeroDao {

    Hero findByAbility(String name) throws SQLException;

    Hero findByName(String superheroes) throws SQLException;

    List<Hero> findByStatus(Status status) throws SQLException;

    List<Hero> findByBalance(Double min, Double max) throws SQLException;

    boolean deleteByName(String name) throws SQLException;

    List<Hero> findall() throws SQLException;

}
