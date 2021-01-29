package com.epam.internetprovider.dao.impl;

import com.epam.internetprovider.dao.AbstractDao;
import com.epam.internetprovider.dao.PromotionDtoDao;
import com.epam.internetprovider.entity.dto.PromotionDto;
import com.epam.internetprovider.exception.DaoException;
import com.epam.internetprovider.mapper.impl.PromotionDtoRowMapper;

import java.sql.Connection;
import java.util.List;

/**
 * The {@code PromotionDtoDaoImpl} presents promotion dto dao implementation
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PromotionDtoDaoImpl extends AbstractDao<PromotionDto> implements PromotionDtoDao {
    private static final String GET_ALL_PROMOTION_DTO_FOR_PAGE = "select promotions.*, tariff_plans.* from promotions " +
            "left join tariff_plans on promotions.tariff_id=tariff_plans.id limit ?, ?";
    private static final String GET_ALL_ACTIVE_PROMOTION_DTO_FOR_PAGE = "select promotions.*, tariff_plans.* from promotions " +
            "left join tariff_plans on promotions.tariff_id=tariff_plans.id where promotions.status=0 limit ?, ?";
    private static final String GET_ALL_ACTIVE_PROMOTION_DTO = "select promotions.*, tariff_plans.* from promotions " +
            "left join tariff_plans on promotions.tariff_id=tariff_plans.id where promotions.status=0";
    private static final String GET_ALL_PROMOTION_DTO = "select promotions.*, tariff_plans.* from promotions " +
            "left join tariff_plans on promotions.tariff_id=tariff_plans.id";


    public PromotionDtoDaoImpl(Connection connection) {
        super(connection, new PromotionDtoRowMapper());
    }

    @Override
    public List<PromotionDto> getAllPromotionDtoForPage(int firstRow, int rowCount) throws DaoException {
        return executeQuery(GET_ALL_PROMOTION_DTO_FOR_PAGE,firstRow, rowCount);
    }

    @Override
    public List<PromotionDto> getPromotionsDtoOnlyActiveForPage(int firstRow, int rowCount) throws DaoException {
        return executeQuery(GET_ALL_ACTIVE_PROMOTION_DTO_FOR_PAGE, firstRow, rowCount);
    }

    @Override
    public List<PromotionDto> getPromotionsDtoOnlyActive() throws DaoException {
        return executeQuery(GET_ALL_ACTIVE_PROMOTION_DTO);
    }

    @Override
    public List<PromotionDto> getPromotionsDto() throws DaoException {
        return executeQuery(GET_ALL_PROMOTION_DTO);
    }
}

