package com.epam.web.service.impl;

import com.epam.web.dao.UserDtoDao;
import com.epam.web.dao.helper.DaoHelper;
import com.epam.web.dao.helper.DaoHelperFactory;
import com.epam.web.entity.dto.UserDto;
import com.epam.web.exception.ConnectionPoolException;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.TariffPlanService;
import com.epam.web.service.UserDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
