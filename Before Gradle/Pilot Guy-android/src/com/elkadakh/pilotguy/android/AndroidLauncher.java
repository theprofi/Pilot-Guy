package com.elkadakh.pilotguy.android;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.elkadakh.others.ActivityRequestHandler;
import com.elkadakh.pilotguy.GameMainClass;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AndroidLauncher extends AndroidApplication implements ActivityRequestHandler{

private final int SHOW_ADS = 1;
private final int HIDE_ADS = 0;
AdView adView;

	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration(); 
        // Create the layout 
        RelativeLayout layout = new RelativeLayout(this);
 
        // Do the stuff that initialize() would do for you 
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
 
        // Create the libgdx View 
        View gameView = initializeForView(new GameMainClass(this), config);
 
        // Create and setup the AdMob view 
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-2130843176870725/7646488490");
        AdRequest adRequest = new AdRequest.Builder()
            .build();
        adView.loadAd(adRequest);
        
        // Add the libgdx view 
        layout.addView(gameView);
 
        // Add the AdMob view 
        RelativeLayout.LayoutParams adParams = 
            new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        adParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        
        layout.addView(adView, adParams);
        adView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        // Hook it all up 
        setContentView(layout);
	}
	
	protected Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_ADS: {
				adView.setVisibility(View.VISIBLE);
				break;
			}
			case HIDE_ADS: {
				adView.setVisibility(View.GONE);
				break;
			}
			}
		}
	};
	
	// This is the callback that posts a message for the handler
    @Override
    public void showAds(boolean show) {
       handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
    }
}