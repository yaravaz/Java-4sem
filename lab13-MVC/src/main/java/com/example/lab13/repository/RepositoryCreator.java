package com.example.lab13.repository;

import com.example.lab13.connection.ConnectionPool;

import java.sql.Connection;

public class RepositoryCreator implements AutoCloseable {
    private ConnectionPool connectionPool;
    private Connection connection;
    public RepositoryCreator() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }
    public UserRepository getUserRepository() {
        return new UserRepository(connection);
    }
    public UniversityRepository getPersonRepository() {
        return new UniversityRepository(connection);
    }
    @Override
    public void close() {
        connectionPool.releaseConnection(connection);
    }
}
