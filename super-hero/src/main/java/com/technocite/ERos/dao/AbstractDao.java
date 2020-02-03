package com.technocite.ERos.dao;

import java.sql.Connection;
import java.sql.SQLException;

import static com.technocite.ERos.dao.ConnectionProvider.provide;

public abstract class AbstractDao<T> {
    protected Connection connection;

    public AbstractDao() throws SQLException {
        this.connection = provide();
    }

    public abstract void create(T obj) throws SQLException;

    public abstract boolean delete(int id) throws SQLException;

    public abstract void update(T obj) throws SQLException;

    public abstract T find(int id) throws SQLException;

}
