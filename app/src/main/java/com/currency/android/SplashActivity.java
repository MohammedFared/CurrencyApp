package com.currency.android;

import android.content.Intent;
import android.os.Bundle;

/**
 * Currency Created by Mohammed Fareed on 11/25/2016.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
