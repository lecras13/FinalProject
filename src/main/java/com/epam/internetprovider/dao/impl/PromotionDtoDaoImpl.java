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

    /**
     * Get the promotions list for page
     *
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of promotions for page
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public List<PromotionDto> getAllPromotionDtoForPage(int firstRow, int rowCount) throws DaoException {
        return executeQuery(GET_ALL_PROMOTION_DTO_FOR_PAGE,firstRow, rowCount);
    }

    /**
     * Get the promotions only active list for page
     *
     * @param firstRow row to start to read
     * @param rowCount count rows for page
     * @return found {@link List} of promotions for page
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public List<PromotionDto> getPromotionsDtoOnlyActiveForPage(int firstRow, int rowCount) throws DaoException {
        return executeQuery(GET_ALL_ACTIVE_PROMOTION_DTO_FOR_PAGE, firstRow, rowCount);
    }

    /**
     * Get promotions only active list
     *
     * @return found {@link List} of promotions active only
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public List<PromotionDto> getPromotionsDtoOnlyActive() throws DaoException {
        return executeQuery(GET_ALL_ACTIVE_PROMOTION_DTO);
    }
    /**
     * Get promotions list
     *
     * @return found {@link List} of promotions
     * @throws DaoException in case of errors and also in case for checked exceptions of lower application levels
     */
    @Override
    public List<PromotionDto> getPromotionsDto() throws DaoException {
        return executeQuery(GET_ALL_PROMOTION_DTO);
    }
}

