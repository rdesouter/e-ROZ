package com.technocite.ERos.factory;

import com.technocite.ERos.model.Hero;
import com.technocite.ERos.model.Mission;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MissionFactory {

    public Mission createFrom(ResultSet resultSet, List<Hero> heroes) throws SQLException {

        return new Mission(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getDouble("difficulty"),
                resultSet.getInt("safe_people"),
                resultSet.getString("town"),
                resultSet.getDouble("award"),
                resultSet.getTimestamp("date"),
                resultSet.getTimestamp("accomplished"),
                resultSet.getDouble("experience"),
                heroes,
                resultSet.getBoolean("is_launched"),
                resultSet.getBoolean("is_done"),
                resultSet.getBoolean("is_accomplished"),
                resultSet.getBoolean("is_try"));
    }


    public Mission createFrom(ResultSet resultSet) throws SQLException {
        return new Mission(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getDouble("difficulty"),
                resultSet.getInt("safe_people"),
                resultSet.getString("town"),
                resultSet.getDouble("award"),
                resultSet.getTimestamp("date"),
                resultSet.getTimestamp("accomplished"),
                resultSet.getDouble("experience"));
    }

}
