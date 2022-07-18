package com.practice.service.services;

import com.practice.service.dao.CurrencyDAO;
import com.practice.service.model.Currency;
import com.practice.service.model.CurrencyMenuItem;
import com.practice.service.parser.XMLParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<CurrencyMenuItem> currencyListFromDB = new ArrayList<>();
        List<Currency> currencyListFromParser;
        try {
            currencyListFromDB = currencyDAO.getAllCurrencyDesignations();
            currencyListFromParser = xmlParser.xmlInitializeCurrency();

            if (currencyListFromDB.size() != currencyListFromParser.size()) {
                currencyDAO.batchCurrencyUpdate(currencyListFromParser);
                currencyListFromDB = currencyDAO.getAllCurrencyDesignations();
            }
        }
        catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return currencyListFromDB;
    }

    public void initDB() throws IOException {
        List<Currency> currencyListDB = currencyDAO.getAll();
        List<Currency> currencyListFromParser = xmlParser.xmlInitializeCurrency();
        if (currencyListDB.size() == currencyListFromParser.size()) {
            System.out.println("equal");
            System.out.println("Parser" + (currencyListFromParser.size()));
            System.out.println("DB" + (currencyListDB.size()));
        } else {
            System.out.println("Parser" + (currencyListFromParser.size()));
            System.out.println("DB" + (currencyListDB.size()));
            currencyDAO.batchCurrencyUpdate(currencyListFromParser);


        }
    }
//    TODO сделать периодическое обращение к API
}
