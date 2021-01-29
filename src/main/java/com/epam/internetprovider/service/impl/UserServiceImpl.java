package com.epam.internetprovider.service.impl;

import com.epam.internetprovider.dao.UserDao;
import com.epam.internetprovider.dao.helper.DaoHelper;
import com.epam.internetprovider.dao.helper.DaoHelperFactory;
import com.epam.internetprovider.entity.User;
import com.epam.internetprovider.exception.DaoException;
import com.epam.internetprovider.exception.ServiceException;
import com.epam.internetprovider.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 * The {@code UserServiceImpl} presents implementation {@link UserService}
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class UserServiceImpl implements UserService {
    private final DaoHelperFactory daoHelperFactory;

    public UserServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            return dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void changePassword(Long id, String password) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            dao.changePasswordByUserId(id, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<User> getAllUsers() throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<User> getUserById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveUser(User user) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            dao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateUserByAdmin(Object[] params) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            dao.updateUserByAdmin(params);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateUserByUser(Object[] params) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            dao.updateUserByUser(params);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}

