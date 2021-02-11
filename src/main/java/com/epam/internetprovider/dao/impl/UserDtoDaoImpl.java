package com.epam.internetprovider.dao.impl;

import com.epam.internetprovider.dao.AbstractDao;
import com.epam.internetprovider.dao.UserDtoDao;
import com.epam.internetprovider.entity.dto.UserDto;
import com.epam.internetprovider.exception.DaoException;
import com.epam.internetprovider.mapper.impl.PromotionRowMapper;
import com.epam.internetprovider.mapper.impl.TariffRowMapper;
import com.epam.internetprovider.mapper.impl.UserDtoRowMapper;

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

    /**
     * Get the user dto list for page
     *
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of user dto for page
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public List<UserDto> getAllUserDto(int firstRow, int rowCount) throws DaoException {
        return executeQuery(GET_ALL_USER_DTO, firstRow, rowCount);
    }
}
