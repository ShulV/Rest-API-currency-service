package com.practice.service.services;

import com.practice.service.dao.CurrencyDAO;
import com.practice.service.model.CurrencyMenuItem;
import com.practice.service.parser.XMLParser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrencyService {
    private final CurrencyDAO currencyDAO;
    private final XMLParser xmlParser;
    public CurrencyService(CurrencyDAO currencyDAO, XMLParser xmlParser) {
        this.currencyDAO = currencyDAO;
        this.xmlParser = xmlParser;
    }
    public List<CurrencyMenuItem> getAllCurrencyDesignations() {
        return currencyDAO.getAllCurrencyDesignations();
    }

//    TODO сравнивать каждые 10 минут все записи в таблице Currency, добавлять недостающие
}
