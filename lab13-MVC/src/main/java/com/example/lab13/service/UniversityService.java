package com.example.lab13.service;

import com.example.lab13.exception.*;
import com.example.lab13.model.University;
import com.example.lab13.repository.RepositoryCreator;
import com.example.lab13.repository.UniversityRepository;

import java.util.List;

public class UniversityService {
    public List<University> findAll() throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UniversityRepository personRepository = repositoryCreator.getPersonRepository();
            return personRepository.findAll();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(University person) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UniversityRepository personRepository = repositoryCreator.getPersonRepository();
            personRepository.save(person);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
}
