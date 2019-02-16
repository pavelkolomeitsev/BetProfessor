package com.example.paul.betprofessor.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.paul.betprofessor.R;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.WHITE)
                .withLogo(R.drawable.nba_big_logo)
                .withHeaderText("Developed by Paul Kolomeitsev")
                .withFooterText("BetProfessor");
        config.getHeaderTextView().setTextColor(Color.BLACK);
        config.getHeaderTextView().setTextSize(14);
        config.getFooterTextView().setTextColor(Color.BLACK);
        config.getFooterTextView().setTextSize(30);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}
