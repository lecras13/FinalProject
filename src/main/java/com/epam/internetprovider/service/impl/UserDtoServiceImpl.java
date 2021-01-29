package com.epam.internetprovider.service.impl;

import com.epam.internetprovider.dao.UserDtoDao;
import com.epam.internetprovider.dao.helper.DaoHelper;
import com.epam.internetprovider.dao.helper.DaoHelperFactory;
import com.epam.internetprovider.entity.dto.UserDto;
import com.epam.internetprovider.exception.DaoException;
import com.epam.internetprovider.exception.ServiceException;
import com.epam.internetprovider.service.UserDtoService;

import java.util.List;

/**
 * The {@code UserDtoServiceImpl} presents implementation {@link UserDtoService}
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class UserDtoServiceImpl implements UserDtoService {
    private final DaoHelperFactory daoHelperFactory;

    public UserDtoServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public List<UserDto> getUserDtoForPage(int firstRow, int rowCount) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDtoDao dao = factory.createUserDtoDao();
            return dao.getAllUserDto(firstRow, rowCount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
