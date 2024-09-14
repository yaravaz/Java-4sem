package com.example.lab13.service;

import com.example.lab13.exception.*;
import com.example.lab13.model.User;
import com.example.lab13.repository.RepositoryCreator;
import com.example.lab13.repository.SQLHelper;
import com.example.lab13.repository.UserRepository;
import com.example.lab13.repository.paramspecification.UserByLogin;
import com.example.lab13.repository.paramspecification.UserByLoginPassword;

import java.util.Optional;
public class UserService {
    public Optional<User> login(String login, byte[] password) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            UserByLoginPassword params = new UserByLoginPassword(login, password);
            return userRepository.queryForSingleResult(SQLHelper.SQL_GET_USER, params);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
    public Integer save(User user) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            UserByLogin param = new UserByLogin(user.getLogin());
            if (!userRepository.queryForSingleResult(SQLHelper.SQL_CHECK_LOGIN, param).isPresent()) {
                return userRepository.save(user);
            } else {
                return 0;
            }
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
}