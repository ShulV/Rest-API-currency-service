package com.practice.service.services;

import com.practice.service.dao.CurrencyDAO;
import com.practice.service.dao.DayCurrencyDAO;
import com.practice.service.model.DayCurrency;
import com.practice.service.parser.XMLParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        List<DayCurrency> periodCurrencies = dayCurrencyDAO.getPeriodCurrencies(fromDate, toDate, charcode);
        List<DayCurrency> dayCurrencyList;
        if (periodCurrencies.size() > 0) {
            List<Date> missingDateList = getMissingDates(fromDate, toDate, periodCurrencies);
            if (missingDateList.size() == 0) {
                return periodCurrencies;
            }
            missingDateList.sort(java.util.Date::compareTo);

            Date minDate = missingDateList.get(0);
            Date maxDate = missingDateList.get(missingDateList.size() - 1);

            dayCurrencyList = xmlParser.xmlConnectPeriod(minDate, maxDate, curId);

            fillInEmptyLines(minDate, maxDate, dayCurrencyList);

            dayCurrencyList.removeIf(dc -> !missingDateList.contains(dc.getDate()));
        }
        else {
            dayCurrencyList = xmlParser.xmlConnectPeriod(fromDate, toDate, curId);
            if (dayCurrencyList.isEmpty()) {
                dayCurrencyList = fillEmptyList(dayCurrencyList, fromDate, toDate, curId);
            }
            else {
                fillInEmptyLines(fromDate, toDate, dayCurrencyList);
            }
        }
        dayCurrencyDAO.batchDayCurrencyUpdate(dayCurrencyList, charcode);
        return dayCurrencyDAO.getPeriodCurrencies(fromDate, toDate, charcode);
    }

    public void insert(DayCurrency dayCurrency, String currencyName) {
        dayCurrencyDAO.insert(dayCurrency, currencyName);
    }

    private void fillInEmptyLines(Date fromDate, Date toDate, List<DayCurrency> dayCurrencyList) {
        //TODO перенести в константы
        int MS_IN_DAY = 1000 * 60 * 60 * 24;
        // Начальная дата запрашиваемого периода.
        Date startDate = (Date) fromDate.clone();
        // Конечная дата запрашиваемого периода.
        Date endDate = (Date) toDate.clone();
        // Предыдущий объект для инициализации незаполненных дней.
        DayCurrency prevDayCurrency = dayCurrencyList.get(0).clone();
        // Коллекция недостающих незаполненных дней.
        List<DayCurrency> newDayCurrencyList = new ArrayList<>();

        int elemNum = 0;
        // Проходим по валютам для каждого дня из периода
        while (startDate.compareTo(endDate) <= 0) {
            // Если данных для текущей даты нет в коллекции, копируем предыдущий dayCurrencyList в текущий
            if (elemNum > dayCurrencyList.size() - 1 ||
                    dayCurrencyList.get(elemNum).getDate().compareTo(startDate) != 0) {
                prevDayCurrency.setDate(startDate);
                newDayCurrencyList.add(prevDayCurrency.clone());
            } else {
                prevDayCurrency = dayCurrencyList.get(elemNum).clone();
                elemNum++;
            }
            startDate.setTime(startDate.getTime() + MS_IN_DAY);
        }
        dayCurrencyList.addAll(newDayCurrencyList);// Объединение коллекций
    }

    private List<DayCurrency> fillEmptyList(List<DayCurrency> dayCurrencyList,
                                            Date fromDate, Date toDate,
                                            String currencyId ) throws IOException, ParseException {
        int MS_IN_DAY = 1000 * 60 * 60 * 24;
        // Начальная дата запрашиваемого периода.
        Date startDate = (Date) fromDate.clone();
        // Конечная дата запрашиваемого периода
        Date endDate = (Date) toDate.clone();
        while (dayCurrencyList.isEmpty()) {
            // Расширение периода влево на 10 дней (в прошлое).
            startDate.setTime(startDate.getTime() - 10 * MS_IN_DAY);
            dayCurrencyList = xmlParser.xmlConnectPeriod(startDate, endDate, currencyId);
        }

        DayCurrency prevDayCurrency = dayCurrencyList.get(dayCurrencyList.size() - 1).clone();
        startDate = (Date) fromDate.clone();
        List<DayCurrency> newCurrencyList = new ArrayList<>();
        // Проходим по валютам для каждого дня из периода
        while (startDate.compareTo(endDate) <= 0) {
            prevDayCurrency.setDate(startDate);
            newCurrencyList.add(prevDayCurrency.clone());
            startDate.setTime(startDate.getTime() + MS_IN_DAY);
        }
        return newCurrencyList;
    }

    private List<Date> getMissingDates(Date fromDate, Date toDate, List<DayCurrency> dayCurrencyList) {
        //TODO перенести в константы
        int MS_IN_DAY = 1000 * 60 * 60 * 24;

        Date startDate = (Date) fromDate.clone(); // Начальная дата запрашиваемого периода
        Date endDate = (Date) toDate.clone(); // Конечная дата запрашиваемого периода
        List<Date> missingDateList = new ArrayList<>(); // Коллекция недостающих незаполненных дней

        int elemNum = 0;
        // Проходим по валютам для каждого дня из периода
        while (startDate.compareTo(endDate) <= 0) {
            if (elemNum > dayCurrencyList.size() - 1 || dayCurrencyList.get(elemNum).getDate().compareTo(startDate) != 0) {
                missingDateList.add((Date) startDate.clone());
            } else {
                elemNum++;
            }
            startDate.setTime(startDate.getTime() + MS_IN_DAY);
        }
        return missingDateList;
    }


////TODO для дебага
//    private void printList(List<DayCurrency> dayCurrencyList) {
//        for (DayCurrency dc: dayCurrencyList
//             ) {
//            System.out.println(dc);
//        }
//    }

}