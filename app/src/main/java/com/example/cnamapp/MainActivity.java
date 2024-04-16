package com.example.cnamapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private SeekBar m_seekBar;
    private ProgressBar m_progressBar;
    private TextView m_textCounter;
    private Button m_buttonCounter;
    private Handler handler = new Handler();
    private int m_counter;
    private Timer m_myTimer;
    private MyHandler m_handler;


    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            m_textCounter.setText(String.valueOf(m_counter));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_textCounter = findViewById(R.id.textCounter);
        m_buttonCounter = findViewById(R.id.buttonCounter);
        m_seekBar = findViewById(R.id.seekBar);
        m_progressBar = findViewById(R.id.progressBar);
        m_myTimer = new Timer();
        m_handler = new MyHandler();

        initializeSeekBarAndProgressBar();
        initializeCounter();
    }

    private void initializeSeekBarAndProgressBar() {
        m_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateProgressBar(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    private void initializeCounter() {
        m_buttonCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //incrementTextView();
                //autoIncrementTextViewHandler();
                //autoIncrementTextViewWithTimer();
                autoIncrementTextViewWithTimerBis();
            }
        });
    }

    private void updateProgressBar(int progress) {
        m_progressBar.setProgress(progress);
    }

    private void incrementTextView() {
        int currentValue = Integer.parseInt(m_textCounter.getText().toString());
        int newValue = currentValue + 1;
        m_textCounter.setText(String.valueOf(newValue));
    }

    private void autoIncrementTextViewHandler(){
        if (m_counter < 100) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    m_counter += 1;
                    m_handler.sendEmptyMessage(0);
                    //autoIncrementTextViewHandler();
                }
            }, 1000);
        }
    }

    private void autoIncrementTextViewWithTimer() {
        m_myTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        m_textCounter.setText(String.valueOf(m_counter));
                    }
                });
                m_counter++;
            }
        }, 0, 1000);
    }

    private void autoIncrementTextViewWithTimerBis() {
        m_myTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        m_counter++;
                        m_handler.sendEmptyMessage(0);
                        updateProgressBar(m_counter);
                    }
                });
            }
        }, 0, 1000);
    }
}