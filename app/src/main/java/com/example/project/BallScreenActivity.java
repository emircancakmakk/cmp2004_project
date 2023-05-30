package com.example.project;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;

public class BallScreenActivity extends Activity {
    private GameSurface gameSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_screen);

        gameSurface = findViewById(R.id.gameSurface);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);


        Bitmap tileBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dayssky);
        BitmapDrawable tileDrawable = new BitmapDrawable(getResources(), tileBitmap);

        // Set the drawable to tile
        tileDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        tileDrawable.setDither(true);

        // Find the parent view and set the tiled tile drawable as its background
        RelativeLayout parentView = findViewById(R.id.ball_screen);
        parentView.setBackground(tileDrawable);
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
    public void question_mark(View v) {
        String[] dialogueChunks = {getString(R.string.ball_dialouge1), getString(R.string.ball_dialouge2), getString(R.string.ball_dialouge3),getString(R.string.ball_dialouge4)}; // Add your own dialogue here
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TalkingCharacter talkingCharacter = new TalkingCharacter(BallScreenActivity.this, R.layout.dialog_layout, dialogueChunks);
        talkingCharacter.showDialog();
    }
}