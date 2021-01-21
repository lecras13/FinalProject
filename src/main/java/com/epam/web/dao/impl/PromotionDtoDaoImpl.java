package com.epam.web.dao.impl;

import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.PromotionDtoDao;
import com.epam.web.entity.TariffPlan;
import com.epam.web.entity.dto.PromotionDto;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.impl.PromotionDtoRowMapper;
import com.epam.web.mapper.impl.TariffRowMapper;

import java.sql.Connection;
import java.util.List;

/**
 * The {@code PromotionDtoDaoImpl} presents promotion dto dao implementation
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PromotionDtoDaoImpl extends AbstractDao<PromotionDto> implements PromotionDtoDao {
    private static final String GET_ALL_PROMOTION_DTO = "select promotions.*, tariff_plans.* from promotions " +
            "left join tariff_plans on promotions.tariff_id=tariff_plans.id limit ?, ?";
    private static final String GET_ALL_ACTIVE_PROMOTION_DTO = "select promotions.*, tariff_plans.* from promotions " +
            "left join tariff_plans on promotions.tariff_id=tariff_plans.id where promotions.status=0 limit ?, ?";

    public PromotionDtoDaoImpl(Connection connection) {
        super(connection, new PromotionDtoRowMapper(new TariffRowMapper()));
    }

    @Override
    public List<PromotionDto> getAllPromotionDtoForPage(int firstRow, int rowCount) throws DaoException {
        return executeQuery(GET_ALL_PROMOTION_DTO,firstRow, rowCount);
    }

    @Override
    public List<PromotionDto> getPromotionsDtoOnlyActiveForPage(int firstRow, int rowCount) throws DaoException {
        return executeQuery(GET_ALL_ACTIVE_PROMOTION_DTO, firstRow, rowCount);
    }
}

