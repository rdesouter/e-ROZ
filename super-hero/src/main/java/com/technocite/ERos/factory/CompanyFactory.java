package com.technocite.ERos.factory;

import com.technocite.ERos.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyFactory {

    public Company createFrom(ResultSet resultSet) throws SQLException {
        return new Company(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getDouble("assets"),
                resultSet.getString("town"));
    }

}
