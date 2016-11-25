package com.currency.android;

import com.currency.android.Models.CurrencyModelList.QueryBean.ResultsBean.RatesBean;

import java.util.ArrayList;

/**
 * Currency Created by Mohammed Fareed on 11/25/2016.
 */


public interface ICallback{
    public void callback(ArrayList<RatesBean> rates);
}
