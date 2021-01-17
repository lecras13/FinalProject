package com.epam.web.service;

import com.epam.web.entity.User;
import com.epam.web.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String login, String password) throws ServiceException;

    List<User> getAllUsers() throws ServiceException;

    void saveUser(User user) throws ServiceException;

    void changePassword(Long id, String password) throws ServiceException;

    void updateUserByAdmin(Object[] params) throws ServiceException;

    void updateUserByUser(Object[] params) throws ServiceException;

    Optional<User> getUserById(Long id) throws ServiceException;
}
