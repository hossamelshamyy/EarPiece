package com.soloapplications.earpiece;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    Intent i;
    ToggleButton toggleButton;
    private InterstitialAd mInterstitialAd;
    ImageView img;
    RatingBar rt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-7556661338858134~7815312741");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-7556661338858134/3029889924");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7556661338858134/1083696519");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        toggleButton = findViewById(R.id.toggleButton);
        img = findViewById(R.id.img);
        rt = findViewById(R.id.ratingBar);
        i = new Intent(MainActivity.this, Sound_Service.class);


        if (isMyServiceRunning(Sound_Service.class)) {
            toggleButton.setChecked(true);
            img.setImageResource(R.drawable.mobile2);
        }
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                if (toggleButton.isChecked()) {
                    startService(i);
                    img.setImageResource(R.drawable.mobile2);

                } else {
                    stopService(i);
                    img.setImageResource(R.drawable.mobile);

                }
            }
        });
        rt.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.soloapplications.earpiece")));
            }
        });
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
