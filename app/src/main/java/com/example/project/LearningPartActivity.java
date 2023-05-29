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

public class LearningPartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_part);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Load the tile from your drawable resources
        Bitmap tileBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fossil);
        BitmapDrawable tileDrawable = new BitmapDrawable(getResources(), tileBitmap);

        // Set the drawable to tile
        tileDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        tileDrawable.setDither(true);

        // Find the parent view and set the tiled tile drawable as its background
        ConstraintLayout parentView = findViewById(R.id.learning_view);
        parentView.setBackground(tileDrawable);

    }

    public void LearnSeasons(View view) {
        Intent intent = new Intent(this, SeasonsActivity.class);
        startActivity(intent);
    }

    public void LearnDigitForward(View view) {
        Intent intent = new Intent(this, DigitForwardActivity.class);
        startActivity(intent);
    }

    public void LearnClock(View view) {
        Intent intent = new Intent(this, ClockActivity.class);
        startActivity(intent);
    }

    public void LearnMonths(View view) {
        Intent intent = new Intent(this, MonthsActivity.class);
        startActivity(intent);
    }

    public void LearnDays(View view) {
        Intent intent = new Intent(this, DaysActivity.class);
        startActivity(intent);
    }

    public void LearnDigitBackward(View view) {
        Intent intent = new Intent(this, DigitBackwardActivity.class);
        startActivity(intent);
    }

    public void LearnDirections(View view) {
        Intent intent = new Intent(this, DirectionsActivity.class);
        startActivity(intent);
    }
}