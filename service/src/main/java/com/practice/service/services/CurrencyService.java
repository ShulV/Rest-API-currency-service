package com.practice.service.services;

import com.practice.service.dao.CurrencyDAO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrencyService {
    private final CurrencyDAO currencyDAO;
    public CurrencyService(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }
    public List<String> getAllCurrencyNames() {
        return currencyDAO.getAllCurrencyNames();
    }
}
