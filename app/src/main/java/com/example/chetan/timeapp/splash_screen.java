package com.example.chetan.timeapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash_screen extends AppCompatActivity {
    private static long timeout = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler h=new Handler();
        Runnable r= new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splash_screen.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        };
            h.postDelayed(r,timeout);

    }
}
