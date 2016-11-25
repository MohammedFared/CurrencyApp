package com.currency.android.Models;

import java.util.List;

/**
 * Currency Created by Mohammed Fareed on 11/25/2016.
 */

public class RestCountriesModel {

    /**
     * name : Afghanistan
     * topLevelDomain : [".af"]
     * alpha2Code : AF
     * alpha3Code : AFG
     * callingCodes : ["93"]
     * capital : Kabul
     * altSpellings : ["AF","Afġānistān"]
     * relevance : 0
     * region : Asia
     * subregion : Southern Asia
     * translations : {"de":"Afghanistan","es":"Afganistán","fr":"Afghanistan","ja":"アフガニスタン","it":"Afghanistan"}
     * population : 26023100
     * latlng : [33,65]
     * demonym : Afghan
     * area : 652230.0
     * gini : 27.8
     * timezones : ["UTC+04:30"]
     * borders : ["IRN","PAK","TKM","UZB","TJK","CHN"]
     * nativeName : افغانستان
     * numericCode : 004
     * currencies : ["AFN"]
     * languages : ["ps","uz","tk"]
     */

    private String name;
    private String alpha2Code;
    private java.util.List<String> currencies;

    public String getName() {
        return name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public List<String> getCurrencies() {
        return currencies;
    }
}
