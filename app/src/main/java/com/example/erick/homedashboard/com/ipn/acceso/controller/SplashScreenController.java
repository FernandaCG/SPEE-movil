package com.example.erick.homedashboard.com.ipn.acceso.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erick.homedashboard.R;

public class SplashScreenController extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textView = (TextView) findViewById(R.id.splash_text_view_id);

        Animation splashAnimation = AnimationUtils.loadAnimation(this, R.anim.my_transition);
        textView.startAnimation(splashAnimation);

        final Intent intent = new Intent(this, LoginController.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
