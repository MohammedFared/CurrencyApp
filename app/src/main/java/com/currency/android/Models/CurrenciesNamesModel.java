package com.currency.android.Models;

import java.util.List;

/**
 * Currency Created by Mohammed Fareed on 11/3/2016.
 */
public class CurrenciesNamesModel {

    /**
     * CurrencyCode : AED
     * CurrencyName : United Arab Emirates Dirham
     * CurrencySymbol :
     */

    private List<CurrenciesBean> currencies;

    public List<CurrenciesBean> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrenciesBean> currencies) {
        this.currencies = currencies;
    }

    public static class CurrenciesBean {
        private String CurrencyCode;
        private String CurrencyName;
        private String CurrencySymbol;

        public String getCurrencyCode() {
            return CurrencyCode;
        }

        public void setCurrencyCode(String CurrencyCode) {
            this.CurrencyCode = CurrencyCode;
        }

        public String getCurrencyName() {
            return CurrencyName;
        }

        public void setCurrencyName(String CurrencyName) {
            this.CurrencyName = CurrencyName;
        }

        public String getCurrencySymbol() {
            return CurrencySymbol;
        }

        public void setCurrencySymbol(String CurrencySymbol) {
            this.CurrencySymbol = CurrencySymbol;
        }
    }
}
