package com.example.admin.myapplication;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    static final String tagName = MainActivity.class.getName();

    private int seconds = 0;
    private boolean isRunning = false;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("runState");
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                runTimer();
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("runState", isRunning);
    }

    public void onClickStart(View v) {
        isRunning = true;
    }

    public void onClickStop(View v) {
        isRunning = false;
    }

    public void onClickReset(View v) {
        isRunning = false;
        seconds = 0;
    }

    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        String time = String.format("%d:%02d:%02d", hours, minutes, secs);
        timeView.setText(time);

        if(isRunning) {
            Log.v(tagName, String.format("%d", seconds));
            seconds = seconds + 1;
        }
    }
}
