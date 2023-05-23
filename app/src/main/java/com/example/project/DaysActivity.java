package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DaysActivity extends AppCompatActivity {

    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7;
    MediaPlayer mediaPlayer1, mediaPlayer2, mediaPlayer3, mediaPlayer4, mediaPlayer5, mediaPlayer6, mediaPlayer7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);

        btn_1 = findViewById(R.id.button1);
        btn_2 = findViewById(R.id.button2);
        btn_3 = findViewById(R.id.button3);
        btn_4 = findViewById(R.id.button4);
        btn_5 = findViewById(R.id.button5);
        btn_6 = findViewById(R.id.button6);
        btn_7 = findViewById(R.id.button7);

        mediaPlayer1 = MediaPlayer.create(this, R.raw.monday);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.tuesday);
        mediaPlayer3 = MediaPlayer.create(this, R.raw.wednesday);
        mediaPlayer4 = MediaPlayer.create(this, R.raw.thursday);
        mediaPlayer5 = MediaPlayer.create(this, R.raw.friday);
        mediaPlayer6 = MediaPlayer.create(this, R.raw.saturday);
        mediaPlayer7 = MediaPlayer.create(this, R.raw.sunday);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer1.start();
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer2.start();
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer3.start();
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer4.start();
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer5.start();
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer6.start();
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer7.start();
            }
        });
    }
}
