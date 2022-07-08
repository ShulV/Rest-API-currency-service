package com.practice.service.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@Import(Config.class)
class CurrencyDAOTest {
    //ПОчитать про уровни логирования
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyDAOTest.class);

    @Autowired
    private  CurrencyDAO currencyDAO;

//    @Test
//    void getAllCurrencyNames() {
//        Assert.notNull(currencyDAO );
//    }
}