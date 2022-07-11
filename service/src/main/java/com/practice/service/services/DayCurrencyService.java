package com.practice.service.services;

import com.practice.service.dao.CurrencyDAO;
import com.practice.service.dao.DayCurrencyDAO;
import com.practice.service.model.DayCurrency;
import com.practice.service.parser.XMLParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DayCurrencyService {

    private final DayCurrencyDAO dayCurrencyDAO;
    private final CurrencyDAO currencyDAO;
    private final XMLParser xmlParser;

    public DayCurrencyService(DayCurrencyDAO dayCurrencyDAO, CurrencyDAO currencyDAO, XMLParser xmlParser) {
        this.dayCurrencyDAO = dayCurrencyDAO;
        this.currencyDAO = currencyDAO;
        this.xmlParser = xmlParser;
    }

    public List<DayCurrency> getPeriodCurrencies(Date fromDate, Date toDate, String charcode) throws IOException, ParseException {


        String curId = currencyDAO.getIdByCharcode(charcode);
        List<DayCurrency> dayCurrencyList = xmlParser.xmlConnectPeriod(fromDate, toDate, curId);
        fillInEmptyLines(dayCurrencyList);
        dayCurrencyDAO.batchDayCurrencyUpdate(dayCurrencyList, charcode);
        List<DayCurrency> periodCurrencies = dayCurrencyDAO.getPeriodCurrencies(fromDate, toDate, charcode);


//        TODO проверка на количество объектов, если в периоде не хватает данных по определенным датам,
//        TODO находим все недостающие периоды (или дни)
//        TODO парсим по этим периодам (не в одном запросе) [можно юзануть асинхронность]
//        TODO дозаполняем БД пришедшими данными
//        TODO если данные за определенные дни не пришли, добавляем в ручную по прошлому заполненному дню

        return periodCurrencies;
    }

    public void insert(DayCurrency dayCurrency, String currencyName) {
        dayCurrencyDAO.insert(dayCurrency, currencyName);
    }

    private void fillInEmptyLines(List<DayCurrency> dayCurrencyList) {
        List<DayCurrency> newDayCurrencyList = new ArrayList<>();
        Date currencyDate;

        currencyDate = dayCurrencyList.get(0).getDate();
        System.out.println("Date: " + currencyDate);
//        TODO вынести в конфиг MS_IN_DAY
        int MS_IN_DAY = 1000*60*60*24;
        currencyDate.setTime(currencyDate.getTime() + MS_IN_DAY);
        System.out.println("Date after a 1 day adding: " + currencyDate);
//        TODO дописать метод (в цикле сравнивать дату с инкрементированной, пока не дошел до toDate,
//        TODO конкатенировать 2 списка и заинсертить это в базу
//        for (DayCurrency dayCurrency: dayCurrencyList
//             ) {
//        }
    }

}