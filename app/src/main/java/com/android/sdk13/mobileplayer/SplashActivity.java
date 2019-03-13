package com.android.sdk13.mobileplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

public class SplashActivity extends Activity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );
        handler = new Handler();
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        },2000 );
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startMainActivity();
        return super.onTouchEvent( event );
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages( null );
        super.onDestroy();
    }

    public void startMainActivity() {
        startActivity( new Intent( SplashActivity.this,MainActivity.class ) );
        finish();
    }

}
