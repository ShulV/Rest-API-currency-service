package com.practice.service.services;

import com.practice.service.config.TestConfig;
import com.practice.service.model.DayCurrency;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Sql({"classpath:Generated.SQL", "classpath:DML.SQL"})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        TestConfig.class})
@ActiveProfiles("test")
@TestExecutionListeners(listeners = {SqlScriptsTestExecutionListener.class})
public class DayCurrencyServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private DayCurrencyService dayCurrencyService;

    @Test
    public void fillEmptyListTest() throws IOException, ParseException {

        List<DayCurrency> dayCurrencyList = dayCurrencyService
                .getPeriodCurrencies(Date.valueOf("2007-01-01"), Date.valueOf("2008-01-01"), "USD");
        assertEquals(dayCurrencyList.size(),365);
        
    }

    @Test
    @Sql({"classpath:MarchCurrency.sql"})
    public void marchHoursWithDataTest() throws IOException, ParseException {
        List<DayCurrency> dayCurrencyList = dayCurrencyService
                .getPeriodCurrencies(Date.valueOf("2005-03-01"), Date.valueOf("2005-04-01"), "USD");
        assertEquals(dayCurrencyList.size(), 32);
    }

    @Test
    public void marchHoursWithoutDataTest() throws IOException, ParseException {
        List<DayCurrency> dayCurrencyList = dayCurrencyService
                .getPeriodCurrencies(Date.valueOf("2005-03-01"), Date.valueOf("2005-03-03"), "USD");
        assertEquals(dayCurrencyList.size(), 3);

    }

    @Test
    @Sql({"classpath:OctoberCurrency.sql"})
    public void octoberHoursWithDataTest() throws IOException, ParseException {
        List<DayCurrency> dayCurrencyList = dayCurrencyService
                .getPeriodCurrencies(Date.valueOf("2005-10-01"), Date.valueOf("2005-11-01"), "USD");
        assertEquals(dayCurrencyList.size(), 32);
    }

    @Test
    public void octoberHoursWithoutDataTest() throws IOException, ParseException {
        List<DayCurrency> dayCurrencyList = dayCurrencyService
                .getPeriodCurrencies(Date.valueOf("2005-10-01"), Date.valueOf("2005-11-01"), "USD");
        assertEquals(dayCurrencyList.size(), 32);
    }
}
