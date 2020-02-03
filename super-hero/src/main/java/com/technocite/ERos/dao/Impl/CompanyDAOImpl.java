package com.technocite.ERos.dao.Impl;

import com.technocite.ERos.dao.AbstractDao;
import com.technocite.ERos.dao.CompanyDAO;
import com.technocite.ERos.factory.CompanyFactory;
import com.technocite.ERos.model.Company;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CompanyDAOImpl extends AbstractDao<Company> implements CompanyDAO {

    private PreparedStatement createStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement findStatement;
    private PreparedStatement findByNameStatement;
    private PreparedStatement existStatement;
    private PreparedStatement truncateMissionHeroes;
    private PreparedStatement truncateMissions;
    private PreparedStatement truncateHeroes;
    private CompanyFactory companyFactory;

    public CompanyDAOImpl() throws SQLException {
        companyFactory = new CompanyFactory();
    }

    @Override
    public void create(Company obj) throws SQLException {
        if (createStatement == null) {
            createStatement = this.connection.prepareStatement("INSERT INTO company(name, assets, town) VALUES(?,?,?);");
        }
        createStatement.setString(1, obj.getName());
        createStatement.setDouble(2, obj.getAssets());
        createStatement.setString(3, obj.getTown());
        createStatement.executeUpdate();
    }

    @Override
    public boolean delete(int id) throws SQLException {
        if (deleteStatement == null && truncateMissions == null && truncateHeroes == null && truncateMissionHeroes == null) {
            deleteStatement = this.connection.prepareStatement("DELETE FROM company WHERE id=?;");
            truncateMissionHeroes = this.connection.prepareStatement("TRUNCATE missions_heroes CASCADE");
            truncateHeroes = this.connection.prepareStatement("TRUNCATE heroes RESTART identity CASCADE ");
            truncateMissions = this.connection.prepareStatement("TRUNCATE missions CASCADE");
        }
        truncateMissions.executeUpdate();
        truncateMissionHeroes.executeUpdate();
        truncateHeroes.executeUpdate();
        deleteStatement.setInt(1, id);
        int number = deleteStatement.executeUpdate();
        System.out.println("La company a bien été supprimé ainsi que les missions et heros");
        return deleteStatement.executeUpdate() == number;

    }

    @Override
    public void update(Company obj) throws SQLException {
        if (updateStatement == null) {
            updateStatement = this.connection.prepareStatement("UPDATE company SET assets=? WHERE id=?;");
        }
//        updateStatement.setString(1, obj.getName());
        updateStatement.setDouble(1, obj.getAssets());
//        updateStatement.setString(3, obj.getTown());
        updateStatement.setInt(2, obj.getId());
        updateStatement.executeUpdate();
    }

    @Override
    public Company find() throws SQLException {
        if (findStatement == null) {
            findStatement = this.connection.prepareStatement("SELECT * FROM company;");
        }
        //findStatement.setInt(1, id);
        ResultSet resultSet = findStatement.executeQuery();
        resultSet.next();
        return companyFactory.createFrom(resultSet);
    }

    @Override
    public Company find(int id) {
        return null;
    }

    @Override
    public boolean companyExist() throws SQLException {
        if (existStatement == null) {
            existStatement = this.connection.prepareStatement("SELECT COUNT(*) AS count FROM company;");
        }
        ResultSet resultSet = existStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("count") > 0;
    }

    @Override
    public Company findByName(String name) throws SQLException {
        if (findByNameStatement == null) {
            findByNameStatement = this.connection.prepareStatement("SELECT * FROM company WHERE name=?;");
        }
        findByNameStatement.setString(1, name);
        ResultSet resultSet = findByNameStatement.executeQuery();
        resultSet.next();
        return companyFactory.createFrom(resultSet);
    }
}
