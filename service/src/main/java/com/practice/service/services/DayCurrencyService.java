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
        return periodCurrencies;
    }

    public void insert(DayCurrency dayCurrency, String currencyName) {
        dayCurrencyDAO.insert(dayCurrency, currencyName);
    }

    private void fillInEmptyLines(List<DayCurrency> dayCurrencyList) {
        //TODO перенести в константы
        int MS_IN_DAY = 1000*60*60*24;

        Date startDate = dayCurrencyList.get(0).clone().getDate(); // Начальная дата запрашиваемого периода
        Date endDate = dayCurrencyList.get(dayCurrencyList.size() - 1).clone().getDate(); // Конечная дата запрашиваемого периода
        DayCurrency prevDayCurrency = dayCurrencyList.get(0).clone(); // Предыдущий объект для инициализации незаполненных дней
        List<DayCurrency> newDayCurrencyList = new ArrayList<>(); // Коллекция недостающих незаполненных дней

        int elemNum=0;
        // Проходим по валютам для каждого дня из периода
        while(startDate.compareTo(endDate) != 0) {
            // Если данных для текущей даты нет в коллекции, копируем предыдущий dayCurrencyList в текущий
            if(dayCurrencyList.get(elemNum).getDate().compareTo(startDate) != 0) {
                prevDayCurrency.setDate(startDate);
                newDayCurrencyList.add(prevDayCurrency.clone());
            }
            else {
                prevDayCurrency = dayCurrencyList.get(elemNum).clone();
                elemNum++;
            }
            startDate.setTime(startDate.getTime() + MS_IN_DAY);
        }
        dayCurrencyList.addAll(newDayCurrencyList);// Объединение коллекций
    }

////TODO для дебага
//    private void printList(List<DayCurrency> dayCurrencyList) {
//        for (DayCurrency dc: dayCurrencyList
//             ) {
//            System.out.println(dc);
//        }
//    }

}