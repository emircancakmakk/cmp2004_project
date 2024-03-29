package com.example.project;

import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MonthsActivity extends AppCompatActivity {

    Button[] buttons;
    MediaPlayer[] mediaPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_months);
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
        SeamlessBackgroundView backgroundView = new SeamlessBackgroundView(this, R.drawable.bonebackgroundinv, R.drawable.daysskyinv);
        rootLayout.addView(backgroundView);

        // Inflate the activity's layout and add it to the FrameLayout
        View contentView = getLayoutInflater().inflate(R.layout.activity_months, rootLayout, false);
        rootLayout.addView(contentView);

        // Set the FrameLayout as the content view
        setContentView(rootLayout);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        buttons = new Button[12];
        mediaPlayers = new MediaPlayer[12];

        buttons[0] = findViewById(R.id.mbutton1);
        buttons[1] = findViewById(R.id.mbutton2);
        buttons[2] = findViewById(R.id.mbutton3);
        buttons[3] = findViewById(R.id.mbutton4);
        buttons[4] = findViewById(R.id.mbutton5);
        buttons[5] = findViewById(R.id.mbutton6);
        buttons[6] = findViewById(R.id.mbutton7);
        buttons[7] = findViewById(R.id.mbutton8);
        buttons[8] = findViewById(R.id.mbutton9);
        buttons[9] = findViewById(R.id.mbutton10);
        buttons[10] = findViewById(R.id.mbutton11);
        buttons[11] = findViewById(R.id.mbutton12);

        mediaPlayers[0] = MediaPlayer.create(this, R.raw.january);
        mediaPlayers[1] = MediaPlayer.create(this, R.raw.february);
        mediaPlayers[2] = MediaPlayer.create(this, R.raw.march);
        mediaPlayers[3] = MediaPlayer.create(this, R.raw.april);
        mediaPlayers[4] = MediaPlayer.create(this, R.raw.may);
        mediaPlayers[5] = MediaPlayer.create(this, R.raw.june);
        mediaPlayers[6] = MediaPlayer.create(this, R.raw.july);
        mediaPlayers[7] = MediaPlayer.create(this, R.raw.august);
        mediaPlayers[8] = MediaPlayer.create(this, R.raw.september);
        mediaPlayers[9] = MediaPlayer.create(this, R.raw.october);
        mediaPlayers[10] = MediaPlayer.create(this, R.raw.november);
        mediaPlayers[11] = MediaPlayer.create(this, R.raw.december);

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayers[index].start();
                }
            });
        }
    }
    public void question_mark(View v) {
        String[] dialogueChunks = {getString(R.string.months_dialouge1), getString(R.string.months_dialouge2), getString(R.string.months_dialouge3)}; // Add your own dialogue here
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TalkingCharacter talkingCharacter = new TalkingCharacter(MonthsActivity.this, R.layout.dialog_layout, dialogueChunks);
        talkingCharacter.showDialog();
    }
}
