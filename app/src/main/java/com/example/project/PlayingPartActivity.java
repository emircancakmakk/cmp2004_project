package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PlayingPartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_part);
    }

    public void PlayBallScreen(View view) {
        Intent intent = new Intent(this, BallScreenActivity.class);
        startActivity(intent);
    }

    public void PlaySeasons(View view) {
        Intent intent = new Intent(this, SeasonsPlayingActivity.class);
        startActivity(intent);
    }

    public void PlaySimilarPictures(View view) {
        Intent intent = new Intent(this, SimilarPictureActivity.class);
        startActivity(intent);
    }
}