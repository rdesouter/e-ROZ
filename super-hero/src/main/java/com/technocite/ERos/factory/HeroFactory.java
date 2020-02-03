package com.technocite.ERos.factory;

import com.technocite.ERos.model.Hero;
import com.technocite.ERos.model.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HeroFactory {
    public Hero createFrom(ResultSet resultSet) throws SQLException {
        return new Hero(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("ability"),
                resultSet.getString("description"),
                Status.valueOf(resultSet.getString("state")),
                resultSet.getInt("strength"),
                resultSet.getDouble("balance"),
                resultSet.getDouble("death_insurance"),
                resultSet.getInt("cost"));
    }
}
