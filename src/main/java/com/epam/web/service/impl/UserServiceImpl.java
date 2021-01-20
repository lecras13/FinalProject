package com.epam.web.service.impl;

import com.epam.web.dao.UserDao;
import com.epam.web.dao.helper.DaoHelper;
import com.epam.web.dao.helper.DaoHelperFactory;
import com.epam.web.entity.User;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserDtoService;
import com.epam.web.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The {@code UserServiceImpl} presents implementation {@link UserService}
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private final DaoHelperFactory daoHelperFactory;

    public UserServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            return dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            LOGGER.error("Exception userService getting login!");
            throw new ServiceException(e);
        }
    }

    public void changePassword(Long id, String password) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            dao.changePasswordByUserId(id, password);
        } catch (DaoException e) {
            LOGGER.error("Exception userService changing password!");
            throw new ServiceException(e);
        }
    }

    public List<User> getAllUsers() throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            return dao.getAll();
        } catch (DaoException e) {
            LOGGER.error("Exception userService getting all users!");
            throw new ServiceException(e);
        }
    }

    public Optional<User> getUserById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            return dao.getById(id);
        } catch (DaoException e) {
            LOGGER.error("Exception userService getting user by id!");
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveUser(User user) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            dao.save(user);
        } catch (DaoException e) {
            LOGGER.error("Exception userService saving user!");
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateUserByAdmin(Object[] params) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            dao.updateUserByAdmin(params);
        } catch (DaoException e) {
            LOGGER.error("Exception userService updating user by admin!");
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateUserByUser(Object[] params) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            dao.updateUserByUser(params);
        } catch (DaoException e) {
            LOGGER.error("Exception userService updating user by user!");
            throw new ServiceException(e);
        }
    }
}

