package com.epam.internetprovider.dao.impl;

import com.epam.internetprovider.dao.AbstractDaoPersistent;
import com.epam.internetprovider.dao.PromotionDao;
import com.epam.internetprovider.entity.Promotion;
import com.epam.internetprovider.extractor.impl.PromotionExtractor;
import com.epam.internetprovider.mapper.impl.PromotionRowMapper;

import java.sql.Connection;

/**
 * The {@code PromotionDaoImpl} presents promotion dao implementation
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PromotionDaoImpl extends AbstractDaoPersistent<Promotion> implements PromotionDao {
    private static final String TABLE_NAME = "promotions";
    private static final String UPDATE = "UPDATE promotions SET promotion_name=?, start_date=?, end_date=?," +
            " description=?, tariff_id=?, new_price=?, status=? WHERE id=?";
    private static final String SAVE = "INSERT INTO promotions(promotion_name, start_date, end_date, description," +
            " tariff_id, new_price, status, id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    public PromotionDaoImpl(Connection connection) {
        super(connection, new PromotionRowMapper(), new PromotionExtractor(), TABLE_NAME, SAVE, UPDATE);
    }
}
