package com.yagneshlp.orientation18;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.appolica.flubber.Flubber;
import com.appolica.flubber.interpolator.providers.bezier.BzrSpring;
import com.wang.avi.AVLoadingIndicatorView;

public class SplashActivity extends Activity {

    View logo,nitt;
    AVLoadingIndicatorView loadingIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        logo = findViewById(R.id.logo);
        nitt = findViewById(R.id.nittLogo);
        nitt.setVisibility(View.GONE);
        loadingIndicatorView =(AVLoadingIndicatorView) findViewById(R.id.loading);

        Flubber.with()
                .animation(Flubber.AnimationPreset.FADE_IN) // Slide up animation
                .interpolator(Flubber.Curve.BZR_EASE_IN_CUBIC)
                .repeatCount(0)                              // Repeat once
                .duration(1000)                              // Last for 1000 milliseconds(1 second)
                .createFor(logo)                             // Apply it to the view
                .start();                                    // Start it now

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nitt.setVisibility(View.VISIBLE);
                Flubber.with()
                        .animation(Flubber.AnimationPreset.FADE_IN)
                       // .interpolator(Flubber.Curve.BZR_EASE_IN)
                        .repeatCount(0)
                        .duration(500)
                        .createFor(nitt)
                        .start();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingIndicatorView.setVisibility(View.VISIBLE);
                        Flubber.with()
                                .animation(Flubber.AnimationPreset.FADE_IN)
                                .duration(100)
                                .createFor(loadingIndicatorView)
                                .start();
                        loadingIndicatorView.show();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                                startActivity(i);
                                overridePendingTransition(R.anim.fade_out,R.anim.no_change);
                                finishAffinity();
                                finish();
                            }
                        }, 2000);

                    }
                }, 1000);
            }
        }, 1000);

    }
}
