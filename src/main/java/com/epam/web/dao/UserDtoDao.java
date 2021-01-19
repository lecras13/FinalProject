package com.epam.web.dao;

import com.epam.web.entity.dto.UserDto;
import com.epam.web.exception.DaoException;

import java.util.List;

/**
 * The {@code UserDtoDao} presents implementation {@link UserDto} data access object
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface UserDtoDao extends Dao<UserDto> {
    /**
     * Get the user dto list for page
     *
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of user dto for page
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<UserDto> getAllUserDto(int firstRow, int rowCount) throws DaoException;
}
