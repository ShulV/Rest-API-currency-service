package com.practice.service.controllers;

import com.practice.service.dao.CurrencyDAO;
import com.practice.service.dao.DayCurrencyDAO;
import com.practice.service.model.DayCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/day-currencies")
public class DayCurrencyController {
    private final DayCurrencyDAO dayCurrencyDAO;

    @Autowired
    public DayCurrencyController(DayCurrencyDAO dayCurrencyDAO) {
        this.dayCurrencyDAO = dayCurrencyDAO;
    }

    @GetMapping(value = "/period-currencies", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DayCurrency> getCurrenciesForPeriod(@RequestParam(name="fromDate") String fromDate,
                                                    @RequestParam(name="toDate") String toDate,
                                                    @RequestParam(name="curName") String currencyName) {

        return dayCurrencyDAO.getCurrenciesForPeriod(toDate, fromDate, currencyName);
    }
}
