package com.example.lab13.repository;

import com.example.lab13.builder.UniversityBuilder;
import com.example.lab13.exception.*;
import com.example.lab13.model.University;
import com.example.lab13.repository.dbconstants.UniversityTableConstants;
import com.example.lab13.repository.paramspecification.Parameter;

import java.sql.Connection;
import java.util.*;

public class UniversityRepository extends AbstractRepository<University> {
    public UniversityRepository(Connection connection){
        super(connection);
    }
    @Override
    protected String getTableName() {
        return SQLHelper.PERSON_TABLE ;
    }
    @Override
    public List<University> query(String sqlString, Parameter paramater) throws RepositoryException {
        List<University> universities = executeQuery(sqlString,new UniversityBuilder(), paramater.getParameters());
        return universities;
    }
    @Override
    public Optional<University> queryForSingleResult(String sqlString, Parameter parameter) throws RepositoryException {
        List<University> university = query(sqlString, parameter);
        return university.size() == 1 ?
                Optional.of(university.get(0)) :
                Optional.empty();
    }
    public Map<String,Object> getFields(University unic) {
        Map<String,Object> fields = new HashMap<>();
        fields.put(UniversityTableConstants.NAME.getFieldName(), unic.getName());
        fields.put(UniversityTableConstants.RATING.getFieldName(), unic.getRating());
        fields.put(UniversityTableConstants.ADDRESS.getFieldName(), unic.getAddress());
        return fields;
    }
}
