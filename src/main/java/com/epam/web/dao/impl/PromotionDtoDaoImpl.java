package com.epam.web.dao.impl;

import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.PromotionDtoDao;
import com.epam.web.entity.dto.PromotionDto;
import com.epam.web.exception.DaoException;
import com.epam.web.mapper.impl.PromotionDtoRowMapper;
import com.epam.web.mapper.impl.TariffRowMapper;

import java.sql.Connection;
import java.util.List;

public class PromotionDtoDaoImpl extends AbstractDao<PromotionDto> implements PromotionDtoDao {
    private static final String GET_ALL_PROMOTION_DTO = "select promotions.*, tariff_plans.* from promotions " +
            "left join tariff_plans on promotions.tariff_id=tariff_plans.id limit ?, ?";

    public PromotionDtoDaoImpl(Connection connection) {
        super(connection, new PromotionDtoRowMapper(new TariffRowMapper()));
    }

    @Override
    public List<PromotionDto> getAllPromotionDto(int firstRow, int rowCount) throws DaoException {
        return executeQuery(GET_ALL_PROMOTION_DTO,firstRow, rowCount);
    }
}

