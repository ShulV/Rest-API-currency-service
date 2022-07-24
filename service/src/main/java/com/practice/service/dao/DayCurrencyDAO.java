package com.practice.service.dao;

import com.practice.service.dao.mappers.FullCurrencyInfoMapper;
import com.practice.service.model.DayCurrency;
import com.practice.service.model.FullCurrencyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        return jdbcTemplate.query("SELECT * FROM \"DayCurrency\"",
                new BeanPropertyRowMapper<>(DayCurrency.class));
    }

    public List<DayCurrency> getPeriodCurrencies(Date fromDate, Date toDate, String charcode) {

        return jdbcTemplate.query("SELECT \"PK_daycur\", \"value\", \"date\", \"nominal\", dc.\"PK_id\" " +
                        "FROM \"DayCurrency\" as dc " +
                        "join \"Currency\" as c on c.\"PK_id\" = dc.\"PK_id\" " +
                        "where \"charcode\" = ? and " +
                        "\"date\" between ? and ? " +
                        "ORDER BY \"date\";",
                new BeanPropertyRowMapper<>(DayCurrency.class),
                new Object[]{charcode, fromDate, toDate}).stream().toList();

    }

    public void insert(DayCurrency dayCurrency, String currencyName) {
        Double value = dayCurrency.getValue();
        Date date = dayCurrency.getDate();
        int nominal = dayCurrency.getNominal();
        String pkId = dayCurrency.getPK_id();

        jdbcTemplate.update("INSERT INTO \"DayCurrency\"(\"value\", \"date\", \"nominal\", \"PK_id\") " +
                "VALUES (?, ?, ?, (SELECT \"PK_id\" FROM public.\"Currency\" " +
                        "where \"name\"=?));",
                value, date, nominal, currencyName);
    }


    public void batchDayCurrencyInsert(List<DayCurrency> dayCurrencyList, String charcode) {
        jdbcTemplate.batchUpdate("INSERT INTO \"DayCurrency\"(\"value\", \"date\", \"nominal\", \"PK_id\") " +
                        "VALUES (?, ?, ?, " +
                        "(SELECT \"PK_id\" " +
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

    public void deleteForDate(Date date) {
        jdbcTemplate.update("DELETE FROM public.\"DayCurrency\"" +
                "WHERE \"date\"= ?;", date);
    }


    public List<FullCurrencyInfo> getAllCurrenciesForDay(java.util.Date date) {
        return jdbcTemplate.query("SELECT \"value\", \"date\", \"nominal\", c.\"charcode\", c.\"name\"" +
                        "FROM \"DayCurrency\" as dc " +
                        "join \"Currency\" as c on c.\"PK_id\" = dc.\"PK_id\" " +
                        "where \"date\" = ? " +
                        "ORDER BY c.\"name\"",
                new FullCurrencyInfoMapper(),
                new Object[]{date}).stream().toList();
    }

    public void insertForDate(List<DayCurrency> dayCurrencyList) {
        jdbcTemplate.batchUpdate("INSERT INTO \"DayCurrency\"(\"value\", \"date\", \"nominal\", \"PK_id\") " +
                        "VALUES (?, ?, ?, ?);",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setDouble(1, dayCurrencyList.get(i).getValue());
                        ps.setDate(2, dayCurrencyList.get(i).getDate());
                        ps.setInt(3, dayCurrencyList.get(i).getNominal());
                        ps.setString(4, dayCurrencyList.get(i).getPK_id());
                    }
                    @Override
                    public int getBatchSize() {
                        return dayCurrencyList.size();
                    }
                });
    }
}
