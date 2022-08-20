package com.practice.service.parser;

import com.practice.service.model.Currency;
import com.practice.service.model.DayCurrency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;

@Component
public class XMLParser {

    DateFormat fromFormat = new SimpleDateFormat("dd.MM.yyyy");
    DateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private final Environment environment;

    @Autowired
    public XMLParser(Environment environment) {
        this.environment = environment;
    }

    public List<DayCurrency> xmlDailyValutes(Date date) {
        List<DayCurrency> dayCurrencyList = new ArrayList<>();

        List<String> IDList = new ArrayList<>();
        List<Double> valueList = new ArrayList<>();
        List<Integer> nominalList = new ArrayList<>();

        Date todayDate;

        String xml = environment.getProperty("parser.targetDay") + dateFormat.format(date);
        Document doc;
        try {
            doc = Jsoup
                    .connect(xml)
                    .userAgent(Objects.requireNonNull(environment.getProperty("parser.userAgent")))
                    .referrer(Objects.requireNonNull(environment.getProperty("parser.referrer")))
                    .get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            todayDate = Date.valueOf(
                    myFormat.format(
                            fromFormat.parse(doc.select("ValCurs")
                                    .attr("Date"))));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        for (Element e : doc.select("Valute")) {
            IDList.add(e.attr("ID"));
        }

        for (Element e : doc.select("Value")) {
            valueList.add(Double.parseDouble((e.text().replace(",", "."))));
        }

        for (Element e : doc.select("Nominal")) {
            nominalList.add(parseInt(e.text()));
        }

        for(int i = 0; i < IDList.size(); i++){
            dayCurrencyList.add(new DayCurrency(
                    valueList.get(i),
                    todayDate,
                    nominalList.get(i),
                    IDList.get(i)));
        }
        return dayCurrencyList;
    }

    public List<DayCurrency> xmlConnectPeriod(Date startDate,
                                              Date endDate, String currencyID) throws IOException, ParseException {
        List<DayCurrency> dayCurrencyList = new ArrayList<>();

        List<Double> valueList = new ArrayList<>();
        List<Integer> nominalList = new ArrayList<>();
        List<Date> dateList = new ArrayList<>();

        String xml = environment.getProperty("parser.period") + dateFormat.format(startDate)
                + "&date_req2=" + dateFormat.format(endDate) + "&VAL_NM_RQ=" + currencyID;
        Document doc = Jsoup
                .connect(xml)
                .userAgent(Objects.requireNonNull(environment.getProperty("parser.userAgent")))
                .referrer(Objects.requireNonNull(environment.getProperty("parser.referrer")))
                .get();
        doc = Jsoup.parse(String.valueOf(doc));

        for (Element e : doc.select("Value")) {
            valueList.add(Double.parseDouble((e.text().replace(",","."))));
        }

        for (Element e : doc.select("Nominal")) {
            nominalList.add(parseInt(e.text()));
        }

        for (Element e : doc.select("Record")) {
            dateList.add(Date.valueOf(myFormat.format(fromFormat.parse(e.attr("Date")))));
        }

        for(int i = 0; i < nominalList.size(); i++){
            dayCurrencyList.add(new DayCurrency(
                    valueList.get(i),
                    dateList.get(i),
                    nominalList.get(i)
                    ));
        }
        return dayCurrencyList;
    }

    public List<Currency> xmlInitializeCurrency() throws IOException {
        List<Currency> currencyList = new ArrayList<>();

        List<String> IDList = new ArrayList<>();
        List<Integer> numCodeList = new ArrayList<>();
        List<String> charCodeList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();

        String xml = environment.getProperty("parser.daily");
        Document doc = Jsoup
                .connect(xml)
                .userAgent(Objects.requireNonNull(environment.getProperty("parser.userAgent")))
                .referrer(Objects.requireNonNull(environment.getProperty("parser.referrer")))
                .get();

        for (Element e : doc.select("Valute")) {
            IDList.add(e.attr("ID"));
        }

        for (Element e : doc.select("NumCode")) {
            numCodeList.add(parseInt(e.text()));
        }

        for (Element e : doc.select("CharCode")) {
            charCodeList.add(e.text());
        }

        for (Element e : doc.select("Name")) {
            nameList.add(e.text());
        }

        for(int i = 0; i < nameList.size(); i++){
            currencyList.add(new Currency(
                            IDList.get(i),
                            numCodeList.get(i),
                            charCodeList.get(i),
                            nameList.get(i)));
        }
        return currencyList;
    }
}
