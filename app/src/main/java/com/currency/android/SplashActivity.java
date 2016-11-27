package com.currency.android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.currency.android.Models.CurrencyModelList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Currency Created by Mohammed Fareed on 11/25/2016.
 */

public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivityLOG";
    ValueEventListener flagsEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /* Load the data from Firebase so the list can be updated without needing
            to update the application */
        flagsEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                flagsSnapshot = dataSnapshot;

                ArrayList<String> currencyCode = new ArrayList<>();
                for (DataSnapshot snap: flagsSnapshot.getChildren()) {
                    currencyCode.add(snap.child("CurrencyCode").getValue().toString());
                }

                Log.d(TAG, "onDataChange: " + currencyCode);
                AsyncHttpClient mAsyncHttpClient = new AsyncHttpClient();
                mAsyncHttpClient.get(SplashActivity.this, getListUrl("EGP", currencyCode), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Gson gson = new Gson();
                        CurrencyModelList currencyModel = gson.fromJson(new String(responseBody), CurrencyModelList.class);
//                            final String rate = currencyModel.getQuery().getResults().getRates().get(0).getRate();
                        for (CurrencyModelList.QueryBean.ResultsBean.RatesBean rate : currencyModel.getQuery().getResults().getRates()){
                            if (flagsExchangeRates == null)
                                flagsExchangeRates = new ArrayList<String>();
                            flagsExchangeRates.add(currencyModel.getQuery().getResults().getRates().get(0).getRate());

                        }
                        launchMainActivity();
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.d(TAG, "onFailure: ");
                        Toast.makeText(SplashActivity.this, "Failed to connect", Toast.LENGTH_LONG).show();
                        launchMainActivity();
                    }
                });
                /**************/

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SplashActivity.this, "Failed to connect", Toast.LENGTH_LONG).show();
                launchMainActivity();
            }
        };
        mDataBaseRef.child("countries").addListenerForSingleValueEvent(flagsEventListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (flagsEventListener != null)
            mDataBaseRef.removeEventListener(flagsEventListener);
    }

    private void launchMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
