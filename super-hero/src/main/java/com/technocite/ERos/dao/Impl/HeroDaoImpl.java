package com.technocite.ERos.dao.Impl;

import com.technocite.ERos.dao.AbstractDao;
import com.technocite.ERos.dao.HeroDao;
import com.technocite.ERos.factory.HeroFactory;
import com.technocite.ERos.model.Hero;
import com.technocite.ERos.model.Status;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HeroDaoImpl extends AbstractDao<Hero> implements HeroDao {

    private PreparedStatement createStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement findAllStattement;
    private PreparedStatement findStattement;
    private PreparedStatement findByNameStattement;
    private PreparedStatement findByAbilityStattement;
    private PreparedStatement findByBalanceStattement;
    private PreparedStatement findByStatusStattement;
    private PreparedStatement deleteByNameStattement;
    private HeroFactory heroFactory;

    public HeroDaoImpl() throws SQLException {
        heroFactory = new HeroFactory();
    }

    @Override
    public void create(Hero hero) throws SQLException {
        if (createStatement == null) {
            createStatement = this.connection.prepareStatement("INSERT INTO heroes(name, ability, description, strength, balance, death_insurance, state, cost) " +
                    "VALUES (?,?,?,?,?,?,?,?);");
        }
        createStatement.setString(1, hero.getName());
        createStatement.setString(2, hero.getAbility());
        createStatement.setString(3, hero.getDescription());
        createStatement.setInt(4, hero.getStrength());
        createStatement.setDouble(5, hero.getBalance());
        createStatement.setDouble(6, hero.getDeathInsurrance());
        createStatement.setString(7, String.valueOf(hero.getStatus()));
        createStatement.setInt(8,hero.getCost());
        createStatement.executeUpdate();
    }

    @Override
    public List<Hero> findall() throws SQLException {
        if (findAllStattement == null) {
            findAllStattement = this.connection.prepareStatement("SELECT * from heroes");
        }
        findAllStattement.executeQuery();
        ResultSet resultSet = findAllStattement.getResultSet();
        List<Hero> heroes = new ArrayList<>();
        while (resultSet.next()) {
            heroes.add(heroFactory.createFrom(resultSet));
        }
        return heroes;
    }

    @Override
    public Hero find(int id) throws SQLException {
        if (findStattement == null) {
            findStattement = this.connection.prepareStatement("SELECT * FROM heroes where id=?");
        }
        findStattement.setInt(1, id);
        ResultSet resultSet = findStattement.executeQuery();
        resultSet.next();
        return heroFactory.createFrom(resultSet);
    }

    @Override
    public void update(Hero hero) throws SQLException {
        if (updateStatement == null) {
            updateStatement = this.connection.prepareStatement("UPDATE heroes SET name=?, ability=?, description=?, strength=?, balance=?, death_insurance=?, state=?, cost=? WHERE id=?;");
        }
        updateStatement.setString(1, hero.getName());
        updateStatement.setString(2, hero.getAbility());
        updateStatement.setString(3, hero.getDescription());
        updateStatement.setInt(4, hero.getStrength());
        updateStatement.setDouble(5, hero.getBalance());
        updateStatement.setDouble(6, hero.getDeathInsurrance());
        updateStatement.setString(7, String.valueOf(hero.getStatus()));
        updateStatement.setInt(8, hero.getCost());
        updateStatement.setInt(9, hero.getId());
        updateStatement.executeUpdate();
    }

    @Override
    public boolean delete(int id) throws SQLException {
        if (deleteStatement == null) {
            deleteStatement = this.connection.prepareStatement("DELETE FROM heroes WHERE id=?;");
        }
        deleteStatement.setInt(1, id);
        return deleteStatement.executeUpdate() == 1;
    }










    //NOT USE
    @Override
    public Hero findByAbility(String name) throws SQLException {
        if (findByAbilityStattement == null) {
            findByAbilityStattement = this.connection.prepareStatement("SELECT * FROM heroes where ability=?");
        }
        findByAbilityStattement.setString(1, name);
        ResultSet resultSet = findByAbilityStattement.executeQuery();
        resultSet.next();
        return heroFactory.createFrom(resultSet);
    }

    @Override
    public Hero findByName(String superheroes) throws SQLException {
        if (findByNameStattement == null) {
            findByNameStattement = this.connection.prepareStatement("SELECT * FROM heroes where name=?");
        }
        findByNameStattement.setString(1, superheroes);
        ResultSet resultSet = findByNameStattement.executeQuery();
        resultSet.next();

        return heroFactory.createFrom(resultSet);
    }

    @Override
    public List<Hero> findByStatus(Status status) throws SQLException {
        if (findByStatusStattement == null) {
            findByStatusStattement = this.connection.prepareStatement("SELECT * FROM heroes where state=?");
        }
        findByStatusStattement.setString(1, String.valueOf(status));
        ResultSet resultSet = findByStatusStattement.executeQuery();
        List<Hero> heroes = new ArrayList<>();
        while (resultSet.next()) {
            heroes.add(heroFactory.createFrom(resultSet));
        }
        return heroes;
    }

    @Override
    public List<Hero> findByBalance(Double min, Double max) throws SQLException {
        if (findByBalanceStattement == null) {
            findByBalanceStattement = this.connection.prepareStatement("SELECT * FROM heroes where balance  >= ? AND balance <= ? ");
        }
        findByBalanceStattement.setDouble(1, min);
        findByBalanceStattement.setDouble(2, max);
        ResultSet resultSet = findByBalanceStattement.executeQuery();
        List<Hero> heroes = new ArrayList<>();
        while (resultSet.next()) {
            heroes.add(heroFactory.createFrom(resultSet));
        }
        return heroes;
    }

    @Override
    public boolean deleteByName(String name) throws SQLException {
        if (deleteByNameStattement == null) {
            deleteByNameStattement = this.connection.prepareStatement("DELETE FROM heroes where name=?");
        }
        deleteByNameStattement.setString(1, name);
        return deleteByNameStattement.executeUpdate() == 1;
    }


}
