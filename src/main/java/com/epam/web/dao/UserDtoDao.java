package com.epam.web.dao;

import com.epam.web.entity.dto.UserDto;
import com.epam.web.exception.DaoException;

import java.util.List;

public interface UserDtoDao extends Dao<UserDto> {
    List<UserDto> getAllUserDto(int firstRow, int rowCount) throws DaoException;
}
