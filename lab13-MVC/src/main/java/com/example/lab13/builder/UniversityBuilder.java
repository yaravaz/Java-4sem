package com.example.lab13.builder;

import com.example.lab13.exception.RepositoryException;
import com.example.lab13.model.University;
import com.example.lab13.repository.dbconstants.UniversityTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UniversityBuilder implements Builder <University>{
    @Override
    public University build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(UniversityTableConstants.ID.getFieldName());
            String name = resultSet.getString(UniversityTableConstants.NAME.getFieldName());
            int rating = resultSet.getInt(UniversityTableConstants.RATING.getFieldName());
            String address = resultSet.getString(UniversityTableConstants.ADDRESS.getFieldName());
            return new University(name, Integer.toString(rating), address);
        } catch (SQLException exception) {
            throw new RepositoryException(exception.getMessage(), exception);
        }
    }
}
