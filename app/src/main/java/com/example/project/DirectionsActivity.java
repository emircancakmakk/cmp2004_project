package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class DirectionsActivity extends AppCompatActivity {
    TextView northObj, eastObj, westObj, southObj;

    private MediaPlayer mediaPlayer1, mediaPlayer2, mediaPlayer3, mediaPlayer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);

        northObj = findViewById(R.id.north);
        eastObj =findViewById(R.id.east);
        westObj=findViewById(R.id.west);
        southObj=findViewById(R.id.south);
    }

    public void clickNorth(View view) {
        mediaPlayer1 = MediaPlayer.create(this, R.raw.north);
        mediaPlayer1.start();
    }

    public void clickSouth(View view) {
        mediaPlayer2 = MediaPlayer.create(this, R.raw.south);
        mediaPlayer2.start();
    }

    public void clickWest(View view) {
        mediaPlayer3 = MediaPlayer.create(this, R.raw.west);
        mediaPlayer3.start();
    }

    public void clickEast(View view) {
        mediaPlayer4 = MediaPlayer.create(this, R.raw.east);
        mediaPlayer4.start();
    }
}