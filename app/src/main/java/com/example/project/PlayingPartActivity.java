package com.example.project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PlayingPartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_part);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Load the tile from your drawable resources
        Bitmap tileBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ground);
        BitmapDrawable tileDrawable = new BitmapDrawable(getResources(), tileBitmap);

        // Set the drawable to tile
        tileDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        tileDrawable.setDither(true);

        // Find the parent view and set the tiled tile drawable as its background
        ConstraintLayout parentView = findViewById(R.id.playing_view);
        parentView.setBackground(tileDrawable);
    }

    public void PlayBallScreen(View view) {
        Intent intent = new Intent(this, BallScreenActivity.class);
        startActivity(intent);
    }

    public void PlaySeasons(View view) {
        Intent intent = new Intent(this, SeasonsPlayingActivity.class);
        startActivity(intent);
    }

    public void LearnMultiplication(View view) {
        Intent intent = new Intent(this, MultiplicationActivity.class);
        startActivity(intent);
    }

    public void PlayDays(View view) {
        Intent intent = new Intent(this, PlayDaysActivity.class);
        startActivity(intent);
    }
}