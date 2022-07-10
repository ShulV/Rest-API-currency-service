package com.practice.service.parser;

import com.practice.service.model.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

@Component
public class XMLParser {
    public static void main(String[] args) throws IOException {
        //xmlConnectPeriod(new Date(2022-1900, 0, 1),new Date(2022-1900, 0, 30),"R01010");
        //xmlInitializeCurrency();
    }


    public List<Currency> xmlConnectPeriod(Date startDate, Date endDate, String currencyID) throws IOException {
        List<Currency> currenciesList = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dateFormat.format(startDate) + " - " + dateFormat.format(endDate));

        String xml = "http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=" + dateFormat.format(startDate)
                + "&date_req2=" + dateFormat.format(endDate)+"&VAL_NM_RQ=" + currencyID;
        Document doc = Jsoup
                .connect(xml)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
        doc = Jsoup.parse(String.valueOf(doc));

        for (Element e : doc.select("Value")) {
            System.out.println(e.text());
        }
        return currenciesList;
    }

    //static в общем случае лучше не юзать
    public List<Currency> xmlInitializeCurrency() throws IOException {

        List<Currency> currencyList = new ArrayList<>();

        List<String> IDList = new ArrayList<>();
        List<Integer> NumCodeList = new ArrayList<>();
        List<String> CharCodeList = new ArrayList<>();
        List<String> NameList = new ArrayList<>();

        String xml = "http://www.cbr.ru/scripts/XML_daily.asp";
        Document doc = Jsoup
                .connect(xml)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        for (Element e : doc.select("Valute")) {
            IDList.add(e.attr("ID"));
        }

        for (Element e : doc.select("NumCode")) {
            NumCodeList.add(parseInt(e.text()));
        }

        for (Element e : doc.select("CharCode")) {
            CharCodeList.add(e.text());
        }

        for (Element e : doc.select("Name")) {
            NameList.add(e.text());
        }

        for(int i = 0; i < NameList.size(); i++){
            currencyList.add(new Currency(
                            IDList.get(i),
                            NumCodeList.get(i),
                            CharCodeList.get(i),
                            NameList.get(i)));
        }

//        for (Currency currency: currencyList) {
//            System.out.println(currency.toString());
//        }

        return currencyList;
    }
}
