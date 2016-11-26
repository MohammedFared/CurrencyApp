package com.currency.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.currency.android.Models.CurrencyModel;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.currency.android.BaseActivity.getcurrencyExchangeUrl;

/**
 * Currency Created by Mohammed Fareed on 11/4/2016.
 */
class FlagsAdapter extends BaseAdapter {
    private ValueEventListener flagsEventListener;
    private ArrayList<String> mRates = new ArrayList<>();
//    private String TAG = "FlagsAdapterLOG";

    private static class FlagsViewHolder {
        ImageView imgv_flag;
        TextView countryName;
        TextView exchangeRate;
    }
    private Context mContext;
    private DatabaseReference mDataBaseRef;
    private StorageReference mStorageRef;

    FlagsAdapter(Context context, DatabaseReference dBRef, StorageReference StorageRef, ArrayList<String> rates) {
        mContext = context;
        mDataBaseRef = dBRef;
        mStorageRef = StorageRef;
        mRates = rates;
    }

    @Override
    public int getCount() {
        return 6;
    }

    // referes to the position of the item in the Firebase Database
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final FlagsViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new FlagsViewHolder();
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.currency_single_row, parent, false);
            viewHolder.imgv_flag = (ImageView) convertView.findViewById(R.id.img_flag);
            viewHolder.countryName = (TextView) convertView.findViewById(R.id.tv_countryName);
            viewHolder.exchangeRate = (TextView) convertView.findViewById(R.id.tv_currency);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (FlagsViewHolder) convertView.getTag();
        }
        /* Load the data from Firebase so the list can be updated without needing
            to update the application */
        flagsEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String flagKey = dataSnapshot.child("FlagKey").getValue() + ".png";
                String countryName = dataSnapshot.child("CountryName").getValue() + "";
                final String currencyCode = dataSnapshot.child("CurrencyCode").getValue().toString();

                // Start loading the flags from Firebase Storage
                StorageReference flagRef = mStorageRef.child("flags/" + flagKey);
                Glide.with(mContext)
                        .using(new FirebaseImageLoader())
                        .load(flagRef)
                        .into(viewHolder.imgv_flag);
                // END loading Flags from firebase Storage
                viewHolder.countryName.setText(countryName);

                AsyncHttpClient mAsyncHttpClient = new AsyncHttpClient();
                mAsyncHttpClient.get(mContext, getcurrencyExchangeUrl("EGP", currencyCode), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Gson gson = new Gson();
                        CurrencyModel currencyModel = gson.fromJson(new String(responseBody), CurrencyModel.class);
                        final String rate = currencyModel.getQuery().getResults().getRates().getRate();
                        viewHolder.exchangeRate.setText(rate + " " +currencyCode);
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    }
                });
                /**************/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDataBaseRef.child("countries").child(position + "").addListenerForSingleValueEvent(flagsEventListener);
        /*END of loading from firebase*/
        return convertView;
    }

    // called onStop of the activity
    void removeListeners() {
        if (flagsEventListener != null) {
            mDataBaseRef.removeEventListener(flagsEventListener);
        }
    }
}
