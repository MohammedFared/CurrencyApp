package com.currency.android;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Currency Created by Mohammed Fareed on 10/29/2016.
 */

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    private static String TAG = "BaseActivityLOG";

    public static DataSnapshot flagsSnapshot;
    public static ArrayList<String> flagsExchangeRates;

    protected StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
    protected DatabaseReference mDataBaseRef = FirebaseDatabase.getInstance().getReference();

    protected String restCountriesURL = "https://restcountries.eu/rest/v1/all";

    //build the uri based on the selected base
    public static String getcurrencyExchangeUrl(String base, String target){
        return "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20" +
                "yahoo.finance.xchange%20where%20pair%20in%20" +
                "(%22"+base+ target +"%22)" +
                "&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
    }

    protected String getCurrenciesUrl(){
        return "https://openexchangerates.org/api/currencies.json";
    }

    protected String getListUrl(String base, ArrayList<String> currencyCode) {
        Log.d(TAG, "getListUrl: "+ currencyCode);
        return "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20" +
                "yahoo.finance.xchange%20where%20pair%20in%20" +
                "(%22" + base + currencyCode.get(0) + "%22%2C%20" +
                "%22" + base + currencyCode.get(1) + "%22%2C%20" +
                "%22" + base + currencyCode.get(2) + "%22%2C%20" +
                "%22" + base + currencyCode.get(3) + "%22%2C%20" +
                "%22" + base + currencyCode.get(4) + "%22%2C%20" +
                "%22" + base + currencyCode.get(5) + "%22)" +
                "&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
    }

    protected void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Converting...");
        }
        mProgressDialog.show();
    }

    protected void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
