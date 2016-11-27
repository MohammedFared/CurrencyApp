package com.currency.android;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.currency.android.Models.CurrencyModel;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class MainActivity extends BaseActivity implements TextWatcher {
    String TAG = "MainActivityLOG";
    private EditText et_number_base;
    private AutoCompleteTextView actv_base, actv_target;
    TextView tv_number_target;

    FeatureCoverFlow mCoverFlow;
    private FlagsAdapter mAdapter;
    private ValueEventListener mVEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_number_base = (EditText) findViewById(R.id.et_base_num);
        tv_number_target = (TextView) findViewById(R.id.tv_target_num);
        actv_base = (AutoCompleteTextView) findViewById(R.id.actv_base);
        actv_target = (AutoCompleteTextView) findViewById(R.id.actv_target);
        mCoverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
    }

    @Override
    protected void onStart() {
        super.onStart();
        actv_base.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(3)});
        actv_target.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(3)});

        Log.d(TAG, "onStart: "+ flagsSnapshot);

        actv_base.addTextChangedListener(this);
        actv_target.addTextChangedListener(this);

        if(actv_base.requestFocus()) { // set focus on the base ET
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

        ArrayList<String> rates = new ArrayList<>();
        for (int i =0; i< 6; i++)
            rates.add("");

        mAdapter = new FlagsAdapter(MainActivity.this, mDataBaseRef, mStorageRef, flagsSnapshot,
                flagsExchangeRates);
        mCoverFlow.setAdapter(mAdapter);

//        autoCompleteUsingFirebase();
    }

//    private void autoCompleteUsingFirebase() {
//        //Nothing special, create database reference.
//        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//        //Create a new ArrayAdapter with your context and the simple layout for the dropdown menu provided by Android
//        final ArrayAdapter<String> autoComplete = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
//        //Child the root before all the push() keys are found and add a ValueEventListener()
//        database.child("currencies").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //Basically, this says "For each DataSnapshot *Data* in dataSnapshot, do what's inside the method.
//                for (DataSnapshot suggestionSnapshot : dataSnapshot.getChildren()){
//                    //Get the suggestion by childing the key of the string you want to get.
//                    String suggestion = suggestionSnapshot.child("CurrencyCode").getValue(String.class);
//                    //Add the retrieved string to the list
//                    autoComplete.add(suggestion);
//                    autoComplete.notifyDataSetChanged();
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        actv_base.setAdapter(autoComplete);
//        actv_target.setAdapter(autoComplete);
//    }

    private void onCurrencyChange() {
        if (validate()) {
            showProgressDialog();
            final AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            asyncHttpClient.get(this, getcurrencyExchangeUrl(actv_base.getText().toString(), actv_target.getText().toString())
                    , new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Gson gson = new Gson();
                    CurrencyModel currencyModel = gson.fromJson(new String(responseBody), CurrencyModel.class);

                    String id = currencyModel.getQuery().getResults().getRates().getId();
                    final String rate = currencyModel.getQuery().getResults().getRates().getRate();

                    et_number_base.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            tv_number_target.removeTextChangedListener(this);
                            if (s.length() != 0) {
                                try { // carch if the entered currecy is not correct
                                    double exchange = Double.parseDouble(et_number_base.getText().toString()) * Double.parseDouble(rate);
                                    tv_number_target.setText(Double.toString(exchange) + " " +actv_target.getText().toString());
                                } catch (NumberFormatException exception) {
                                    Log.d(TAG, "onTextChanged: Entered currency is not correct!");
                                    Toast.makeText(MainActivity.this, "Entered currency is not correct!", Toast.LENGTH_LONG).show();
                                }
                            } else
                                tv_number_target.setText("");
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                        }
                    });
                    et_number_base.setText("1");
                    hideProgressDialog();

                    if(et_number_base.requestFocus()) { // set focus on the numbers after entring the base
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    et_number_base.setText("");
                    tv_number_target.setText("");
                    Toast.makeText(MainActivity.this, "Failed to retrive data", Toast.LENGTH_LONG).show();
                    hideProgressDialog();
                }
            });
        }
    }

    private Boolean validate() {
        if (TextUtils.isEmpty(actv_base.getText().toString())) {
            actv_base.setError("please, write a base");
            return false;
        }

        if (actv_base.getText().toString().length() < 3) {
//            actv_base.setError("A currency is 3 characters");
            return false;
        }
        else {
            if(actv_target.requestFocus()) { // set focus on the target after entring the base
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }

        if (actv_target.getText().toString().length() < 3) {
//            actv_target.setError("A currency is 3 characters");
            return false;
        }
        else {
            if(actv_base.requestFocus()) { // set focus on the base after entring the target
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }

        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (before != 3) // to stop checking if the number of the charecters are more than 3 characters
            onCurrencyChange();
    }
    @Override
    public void afterTextChanged(Editable s) {
    }

    public void swap(View view) { // swap button
        String meddler = actv_base.getText().toString();
        actv_base.setText(actv_target.getText().toString());
        actv_target.setText(meddler);
        onCurrencyChange();
    }

    @Override
    protected void onStop() {
        actv_base.removeTextChangedListener(this);
        actv_target.removeTextChangedListener(this);
        if (mVEventListener != null)
            mDataBaseRef.removeEventListener(mVEventListener);

        mAdapter.removeListeners();
        super.onStop();
    }


}
