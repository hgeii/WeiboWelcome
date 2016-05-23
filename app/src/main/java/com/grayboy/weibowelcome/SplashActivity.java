package com.grayboy.weibowelcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private boolean isFirstIn = false;
    private final String SHAREDPREFERENCES_NAME = "first_pref";
    private final int GOWELCOME = 1000;
    private final int GOHOME = 1001;
    private final long DELAYED_MILL = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        init();
    }

    private void init() {
        SharedPreferences preferences = getSharedPreferences(SHAREDPREFERENCES_NAME, MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("isFirstIn", true);
        if (isFirstIn) {
            mHandler.sendEmptyMessageDelayed(GOWELCOME, DELAYED_MILL);
        } else {
            mHandler.sendEmptyMessageDelayed(GOHOME, DELAYED_MILL);
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GOHOME:
                    goHome();
                    break;
                case GOWELCOME:
                    goWelcome();
                    break;
            }
        }
    };

    private void goWelcome() {
        Intent i  = new Intent(SplashActivity.this, WelcomeActivity.class);
        startActivity(i);
        finish();
    }


    private void goHome() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
