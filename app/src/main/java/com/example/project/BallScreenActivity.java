package com.example.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BallScreenActivity extends Activity {
    private GameSurface gameSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_screen);

        gameSurface = findViewById(R.id.gameSurface);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameSurface.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameSurface.pause();
    }
}