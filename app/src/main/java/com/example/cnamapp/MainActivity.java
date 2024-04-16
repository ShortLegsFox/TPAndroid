package com.example.cnamapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private SeekBar m_seekBar;
    private ProgressBar m_progressBar;
    private TextView m_textCounter;
    private Button m_buttonCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeSeekBarAndProgressBar();

        m_textCounter = findViewById(R.id.textCounter);
        m_buttonCounter = findViewById(R.id.buttonCounter);

        m_buttonCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementTextView();
            }
        });
    }

    private void initializeSeekBarAndProgressBar() {
        m_seekBar = findViewById(R.id.seekBar);
        m_progressBar = findViewById(R.id.progressBar);

        m_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateProgressBar(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Optional: You can add actions to perform when the user starts moving the SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Optional: You can add actions to perform when the user stops moving the SeekBar
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
}