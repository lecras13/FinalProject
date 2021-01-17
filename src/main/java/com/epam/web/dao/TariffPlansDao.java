package com.epam.web.dao;

import com.epam.web.entity.TariffPlan;
import com.epam.web.exception.DaoException;

import java.util.List;

public interface TariffPlansDao extends DaoPersistent<TariffPlan> {
    List<TariffPlan> getTariffPlansForPage(int firstRow, int rowCount) throws DaoException;
}
