package com.example.lab13.repository;

import com.example.lab13.builder.UserBuilder;
import com.example.lab13.exception.*;
import com.example.lab13.model.User;
import com.example.lab13.repository.dbconstants.UserTableConstants;
import com.example.lab13.repository.paramspecification.Parameter;

import java.sql.Connection;
import java.util.*;

public class UserRepository extends AbstractRepository <User>{
    public UserRepository(Connection connection){
        super(connection);
    }
    @Override
    protected String getTableName() {
        return SQLHelper.USER_TABLE;
    }
    @Override
    public List<User> query(String sqlString, Parameter paramater) throws RepositoryException {
        List<User> users = executeQuery(sqlString,new UserBuilder(), paramater.getParameters());
        return users;
    }
    @Override
    public Optional<User> queryForSingleResult(String sqlString, Parameter parameter) throws RepositoryException {
        List<User> user = query(sqlString, parameter);
        return user.size() == 1 ?
                Optional.of(user.get(0)) :
                Optional.empty();
    }
    public Map<String,Object> getFields(User user) {
        Map<String,Object> fields = new HashMap<>();
        fields.put(UserTableConstants.LOGIN.getFieldName(), user.getLogin());
        fields.put(UserTableConstants.PASSWORD.getFieldName(), user.getPassword());
        return fields;
    }
}

