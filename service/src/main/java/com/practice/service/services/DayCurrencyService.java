package com.practice.service.services;

import com.practice.service.dao.CurrencyDAO;
import com.practice.service.dao.DayCurrencyDAO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DayCurrencyService {

    private final DayCurrencyDAO dayCurrencyDAO;

    public DayCurrencyService(DayCurrencyDAO dayCurrencyDAO) {
        this.dayCurrencyDAO = dayCurrencyDAO;
    }


}