package com.example.project;

import android.view.WindowManager;
import android.widget.FrameLayout;
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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Code for setting background
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

// Create a FrameLayout to hold your SeamlessBackgroundView and the activity's layout
        FrameLayout rootLayout = new FrameLayout(this);

        // Create a SeamlessBackgroundView and add it to the FrameLayout
        SeamlessBackgroundView backgroundView = new SeamlessBackgroundView(this, R.drawable.floating_tree, R.drawable.floating_tree_sky);
        rootLayout.addView(backgroundView);

        // Inflate the activity's layout and add it to the FrameLayout
        View contentView = getLayoutInflater().inflate(R.layout.activity_directions, rootLayout, false);
        rootLayout.addView(contentView);

        // Set the FrameLayout as the content view
        setContentView(rootLayout);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

    public void question_mark(View v) {
        String[] dialogueChunks = {getString(R.string.directions_dialouge1), getString(R.string.directions_dialouge2), getString(R.string.directions_dialouge3)}; // Add your own dialogue here
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TalkingCharacter talkingCharacter = new TalkingCharacter(DirectionsActivity.this, R.layout.dialog_layout, dialogueChunks);
        talkingCharacter.showDialog();
    }
}