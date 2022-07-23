package com.practice.service.dao.mappers;

import com.practice.service.model.FullCurrencyInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FullCurrencyInfoMapper implements RowMapper<FullCurrencyInfo> {
    @Override
    public FullCurrencyInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        FullCurrencyInfo fullCurrencyInfo = new FullCurrencyInfo();

        fullCurrencyInfo.setValue(rs.getDouble("value"));
        fullCurrencyInfo.setDate(rs.getDate("date"));
        fullCurrencyInfo.setNominal(rs.getInt("nominal"));
        fullCurrencyInfo.setCharcode(rs.getString("charcode"));
        fullCurrencyInfo.setName(rs.getString("name"));
        return fullCurrencyInfo;
    }
}
