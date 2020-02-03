package com.technocite.ERos.dao;

import com.technocite.ERos.model.Company;

import java.sql.SQLException;

public interface CompanyDAO {

    boolean companyExist() throws SQLException;

    Company findByName(String name) throws SQLException;

    Company find() throws SQLException;

}
