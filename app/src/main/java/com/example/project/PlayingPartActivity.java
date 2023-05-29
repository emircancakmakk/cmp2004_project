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

    public void PlaySimilarPictures(View view) {
        Intent intent = new Intent(this, SimilarPictureActivity.class);
        startActivity(intent);
    }

    public void PlayDays(View view) {
        Intent intent = new Intent(this, PlayDaysActivity.class);
        startActivity(intent);
    }

    public void PlayMonths(View view) {
        Intent intent = new Intent(this, PlayMonthsActivity.class);
        startActivity(intent);
    }

    public void PlayDirections(View view) {
        Intent intent = new Intent(this, DirectionsGameActivity.class);
        startActivity(intent);
    }
}