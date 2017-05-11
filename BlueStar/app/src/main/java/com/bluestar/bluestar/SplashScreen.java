package com.bluestar.bluestar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    ProgressBar progressBar = null;
    Timer progressTimer = null;
    ImageView iv_progress = null;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);
        progressTimer = new Timer();
        progressBar = (ProgressBar) findViewById(R.id.prog);
        progressTimer.scheduleAtFixedRate(r, 0, 500);
        iv_progress = (ImageView) findViewById(R.id.progress);
        Animation anim = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.rotation);
        iv_progress.startAnimation(anim);
    }

    TimerTask r = new TimerTask()
    {
        @Override
        public void run()
        {
            i = (i + 10) % 101;
            if (i == 100)
            {
                startLoginActivity();
                progressBar.setProgress(i);
                progressTimer.cancel();
                finish();
            }
            progressBar.setProgress(i);
        }
    };

    private void startLoginActivity()
    {
        // TODO Auto-generated method stub
        Intent in = new Intent();
        in.setClass(SplashScreen.this, BaseActivity.class); //ConfigureBluetoothActivity
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);

    }
}
