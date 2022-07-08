package com.practice.service.parser;

import com.practice.service.model.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLParser {
    public static void main(String[] args) throws IOException {
        xmlConnectPeriod(new Date(2022-1900, 0, 1),new Date(2022-1900, 0, 30),"1235");
        xmlInitializeCurrency();
    }

    //нэйминг поправить (startDate, endDate, currencyId)
    //return List<CurrencyRate>
    public static void xmlConnectPeriod(Date date1, Date date2, String NameID) throws IOException {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dateFormat.format(date1) + " - " + dateFormat.format(date2));

        String html = "http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=" + dateFormat.format(date1)
                + "&date_req2=" + dateFormat.format(date2)+"&VAL_NM_RQ=R0" + NameID;
        Document doc = Jsoup
                .connect(html)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
        doc = Jsoup.parse(String.valueOf(doc));

        for (Element e : doc.select("Value")) {
            System.out.println(e.text());
        }

    }

    //static в общем случае лучше не юзать
    public static void xmlInitializeCurrency() throws IOException {

        List<Currency> currenciesList = new ArrayList<>();

        List<String> IDList = new ArrayList<>();
        List<String> NumCodeList = new ArrayList<>();
        List<String> CharCodeList = new ArrayList<>();
        List<String> NameList = new ArrayList<>();

        String html = "http://www.cbr.ru/scripts/XML_daily.asp";
        Document doc = Jsoup
                .connect(html)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        for (Element e : doc.select("Valute")) {
            IDList.add(e.attr("ID"));
        }
        for (Element e : doc.select("NumCode")) {
            NumCodeList.add(e.text());
        }

        for (Element e : doc.select("CharCode")) {
            CharCodeList.add(e.text());
        }

        for (Element e : doc.select("Name")) {
            NameList.add(e.text());
        }

        for(int i = 0; i < NameList.size(); i++){
            currenciesList.add(new Currency(
                            IDList.get(i),
                            NumCodeList.get(i),
                            CharCodeList.get(i),
                            NameList.get(i)));
        }

        for (Currency currency: currenciesList) {
            System.out.println(currency.toString());
        }
    }
}
