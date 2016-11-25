package com.currency.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.currency.android.Models.CurrenciesNamesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Currency Created by Mohammed Fareed on 11/3/2016.
 */

public class AutoCompleteAdapter extends BaseAdapter implements Filterable {

    private static final int MAX_RESULTS = 10;
    private Context mContext;
    private List<CurrenciesNamesModel.CurrenciesBean> mResultList = new ArrayList<>();

    public AutoCompleteAdapter(Context context, List<CurrenciesNamesModel.CurrenciesBean> resultList) {
        mResultList = resultList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mResultList.size();
    }

    @Override
    public Object getItem(int position) {
        return mResultList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            LayoutInflater inflater = (LayoutInflater) mContext
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(android.R.layout.two_line_list_item, parent, false);
//        }
//        ((TextView) convertView.findViewById(R.id.text1)).setText(mResultList.get(position).getCurrencyCode());
//        ((TextView) convertView.findViewById(R.id.text2)).setText(mResultList.get(position).getCurrencyName());
//        return convertView;
        return null;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
