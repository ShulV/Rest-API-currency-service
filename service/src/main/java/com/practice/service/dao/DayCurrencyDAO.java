package com.practice.service.dao;

import com.practice.service.model.Currency;
import com.practice.service.model.DayCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.sql.Date;
import java.util.List;

@Component
public class DayCurrencyDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DayCurrencyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DayCurrency> getAll() {
        return jdbcTemplate.query("SELECT * FROM DayCurrency",
                new BeanPropertyRowMapper<>(DayCurrency.class));
    }

    public List<DayCurrency> getPeriodCurrencies(Date fromDate, Date toDate, String charcode) {

        return jdbcTemplate.query("SELECT \"PK_daycur\", \"value\", \"date\", \"nominal\", dc.\"PK_id\"\n" +
                        "FROM \"DayCurrency\" as dc\n" +
                        "join \"Currency\" as c on c.\"PK_id\" = dc.\"PK_id\"\n" +
                        "where \"charcode\" = ? and\n" +
                        "\"date\" between ? and ?\n" +
                        "ORDER BY \"date\";",
                new Object[]{charcode, fromDate, toDate},
                new BeanPropertyRowMapper<>(DayCurrency.class)).stream().toList();

    }

    public void insert(DayCurrency dayCurrency, String currencyName) {
        Double value = dayCurrency.getValue();
        Date date = dayCurrency.getDate();
        int nominal = dayCurrency.getNominal();
        String pkId = dayCurrency.getPK_id();

        jdbcTemplate.update("INSERT INTO \"DayCurrency\"(\"value\", \"date\", \"nominal\", \"PK_id\")" +
                "VALUES (?, ?, ?, (SELECT \"PK_id\" FROM public.\"Currency\"" +
                        "where \"name\"=?));",
                value, date, nominal, currencyName);
    }

    public void batchDayCurrencyUpdate(List<DayCurrency> dayCurrencyList, String charcode) {
        jdbcTemplate.batchUpdate("INSERT INTO \"DayCurrency\"(\"value\", \"date\", \"nominal\", \"PK_id\")" +
                        "VALUES (?, ?, ?," +
                        "(SELECT \"PK_id\"" +
                        "FROM \"Currency\" where \"charcode\"=?));",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setDouble(1, dayCurrencyList.get(i).getValue());
                        ps.setDate(2, dayCurrencyList.get(i).getDate());
                        ps.setInt(3, dayCurrencyList.get(i).getNominal());
                        ps.setString(4, charcode);
                    }
                    @Override
                    public int getBatchSize() {
                        return dayCurrencyList.size();
                    }
                });
    }

}
