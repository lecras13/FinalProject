package com.epam.web.dao;

import com.epam.web.entity.dto.PromotionDto;
import com.epam.web.exception.DaoException;

import java.util.List;

public interface PromotionDtoDao extends Dao<PromotionDto> {
    List<PromotionDto> getAllPromotionDto(int firstRow, int rowCount) throws DaoException;
}
