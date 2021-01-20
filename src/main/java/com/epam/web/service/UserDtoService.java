package com.epam.web.service;

import com.epam.web.entity.dto.UserDto;
import com.epam.web.exception.ServiceException;

import java.util.List;

/**
 * The {@code UserDtoService} represents operations with {@link UserDto}.
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface UserDtoService {

    /**
     * Get the user dto list for page
     *
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of user dto for page
     * @throws ServiceException in case of errors and also in case for checked exceptions of lower application levels
     */
    List<UserDto> getUserDtoForPage(int firstRow, int rowCount) throws ServiceException;
}
