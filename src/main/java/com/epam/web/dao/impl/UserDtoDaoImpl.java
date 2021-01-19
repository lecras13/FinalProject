package com.epam.web.dao.impl;

import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.UserDtoDao;
import com.epam.web.entity.dto.UserDto;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.impl.PromotionRowMapper;
import com.epam.web.mapper.impl.TariffRowMapper;
import com.epam.web.mapper.impl.UserDtoRowMapper;

import java.sql.Connection;
import java.util.List;

/**
 * The {@code UserDtoDaoImpl} presents user dto dao implementation
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class UserDtoDaoImpl extends AbstractDao<UserDto> implements UserDtoDao {
    private static final String GET_ALL_USER_DTO = "select users.id, users.login, users.password, users.first_name, " +
            "users.last_name, users.role, users.total_amount, users.status_block, users.traffic, users.discount," +
            " tariff_plans.*, promotions.* from users left join tariff_plans on users.tariff_id=tariff_plans.id" +
            " left join promotions on users.promotion_id=promotions.id where users.role='USER' limit ?, ?";

    public UserDtoDaoImpl(Connection connection) {
        super(connection, new UserDtoRowMapper(new TariffRowMapper(), new PromotionRowMapper()));
    }

    @Override
    public List<UserDto> getAllUserDto(int firstRow, int rowCount) throws DaoException {
        return executeQuery(GET_ALL_USER_DTO, firstRow, rowCount);
    }
}
