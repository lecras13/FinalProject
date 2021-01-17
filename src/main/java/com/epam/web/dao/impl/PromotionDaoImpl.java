package com.epam.web.dao.impl;

import com.epam.web.dao.AbstractDaoPersistent;
import com.epam.web.dao.PromotionDao;
import com.epam.web.entity.Promotion;
import com.epam.web.extractor.impl.PromotionExtractor;
import com.epam.web.mapper.impl.PromotionRowMapper;

import java.sql.Connection;

public class PromotionDaoImpl extends AbstractDaoPersistent<Promotion> implements PromotionDao {
    private static final String TABLE_NAME = "promotions";
    private static final String UPDATE = "UPDATE promotions SET promotion_name=?, start_date=?, end_date=?," +
            " description=?, tariff_id=?, new_price=? WHERE id=?";
    private static final String SAVE = "INSERT INTO promotions(promotion_name, start_date, end_date, description," +
            " tariff_id, new_price, id) VALUES(?, ?, ?, ?, ?, ?, ?)";

    public PromotionDaoImpl(Connection connection) {
        super(connection, new PromotionRowMapper(), new PromotionExtractor(), TABLE_NAME, SAVE, UPDATE);
    }
}
