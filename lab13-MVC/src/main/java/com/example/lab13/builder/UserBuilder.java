package com.example.lab13.builder;

import com.example.lab13.exception.RepositoryException;
import com.example.lab13.model.User;
import com.example.lab13.repository.dbconstants.UserTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    @Override
    public User build(ResultSet resultSet) throws RepositoryException {
        try {
            String login = resultSet.getString(UserTableConstants.LOGIN.getFieldName());
            byte[] password = resultSet.getBytes(UserTableConstants.PASSWORD.getFieldName());
            return new User(login, password);
        }
        catch (SQLException exception) {
            throw new RepositoryException(exception.getMessage(), exception);
        }
    }
}

