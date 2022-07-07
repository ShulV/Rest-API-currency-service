package com.practice.service.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XMLParser {
    public static void main(String[] args) throws IOException {
        //xmlConnectDaily();
        xmlConnectPeriod(new Date(2022-1900, 0, 1),new Date(2022-1900, 0, 30),"1235");
    }

    public static void xmlConnectDaily() throws IOException {

        Document doc = Jsoup
                .connect("http://www.cbr.ru/scripts/XML_daily.asp")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
        doc = Jsoup.parse(String.valueOf(doc));

        for (Element e : doc.select("NumCode")) {
            System.out.println(e.text());

        }
        for (Element e : doc.select("CharCode")) {
            System.out.println(e.text());

        }

        for (Element e : doc.select("Nominal")) {
            System.out.println(e.text());

        }

        for (Element e : doc.select("Name")) {
            System.out.println(e.text());

        }

        for (Element e : doc.select("Value")) {
            System.out.println(e.text());

        }
    }
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
}
