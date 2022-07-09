package com.practice.service.services;

import com.practice.service.dao.CurrencyDAO;
import com.practice.service.dao.DayCurrencyDAO;
import com.practice.service.model.DayCurrency;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class DayCurrencyService {

    private final DayCurrencyDAO dayCurrencyDAO;

    public DayCurrencyService(DayCurrencyDAO dayCurrencyDAO) {
        this.dayCurrencyDAO = dayCurrencyDAO;
    }

    public List<DayCurrency> getCurrenciesForPeriod(Date fromDate, Date toDate, String currencyName) {
        return dayCurrencyDAO.getCurrenciesForPeriod(fromDate, toDate, currencyName);
    }

}