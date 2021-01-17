package com.epam.web.service;

import com.epam.web.entity.dto.PromotionDto;
import com.epam.web.exception.ServiceException;

import java.util.List;

public interface PromotionDtoService {
    List<PromotionDto> getPromotionDtoForPage(int firstRow, int rowCount) throws ServiceException;
}
