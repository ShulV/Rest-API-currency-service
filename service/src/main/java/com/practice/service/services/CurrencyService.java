package com.practice.service.services;

import com.practice.service.dao.CurrencyDAO;
import com.practice.service.model.CurrencyMenuItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrencyService {
    private final CurrencyDAO currencyDAO;
    public CurrencyService(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }
    public List<CurrencyMenuItem> getAllCurrencyDesignations() {
        return currencyDAO.getAllCurrencyDesignations();
    }
}
