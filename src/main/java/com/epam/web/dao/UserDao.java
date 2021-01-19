package com.epam.web.dao;

import com.epam.web.entity.TariffPlan;
import com.epam.web.entity.User;
import com.epam.web.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The {@code TariffPlansDao} presents implementation {@link TariffPlan} data access object
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface UserDao extends DaoPersistent<User> {
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    void changePasswordByUserId(Long id, String password) throws DaoException;

    void updateUserByAdmin(Object[] params) throws DaoException;

    void updateUserByUser(Object[] params) throws DaoException;

    void updateAmount(Long id, Double amount) throws DaoException;
}
