package com.epam.web.service;

import com.epam.web.entity.dto.UserDto;
import com.epam.web.exception.ServiceException;

import java.util.List;

public interface UserDtoService {
    List<UserDto> getUserDtoForPage(int firstRow, int rowCount) throws ServiceException;
}
