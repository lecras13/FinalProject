package com.epam.web.service;

import com.epam.web.entity.TariffPlan;
import com.epam.web.entity.User;
import com.epam.web.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface TariffPlanService {
    List<TariffPlan> getTariffPlans() throws ServiceException;

    Optional<TariffPlan> getTariffPlanById(Long id) throws ServiceException;

    void saveTariffPlan(Long id, String tariffName, Integer price, String description) throws ServiceException;

    void deleteTariffPlan(Long id) throws ServiceException;

    List<TariffPlan> getTariffPlansForPage(int firstRow, int rowCount) throws ServiceException;

}
