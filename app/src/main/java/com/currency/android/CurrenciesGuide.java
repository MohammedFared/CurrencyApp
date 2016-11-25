package com.currency.android;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.currency.android.Models.RestCountriesModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CurrenciesGuide extends BaseActivity {
    private String TAG = "CurrenciesGuideLOG";

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies_guide);

        AsyncHttpClient mAsyncHttpClient = new AsyncHttpClient();
        mAsyncHttpClient.get(this, restCountriesURL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                //The API returns a JSON Array not Object so i need to make a new listtyoe
                Type listType = new TypeToken<List<RestCountriesModel>>(){}.getType();
                ArrayList<RestCountriesModel> restCountriesModel =
                        (ArrayList<RestCountriesModel>) gson.fromJson(new String(responseBody), listType);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }
}
