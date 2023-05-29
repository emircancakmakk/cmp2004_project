package com.example.project;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Random;

public class SeasonsPlayingActivity extends AppCompatActivity {
    private ImageView seasonImageView;
    private Button springButton, summerButton, autumnButton, winterButton;
    private HashMap<String, Integer[]> seasonImages;

    private int lastImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons_playing);

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
        SeamlessBackgroundView backgroundView = new SeamlessBackgroundView(this, R.drawable.forestpixel, R.drawable.forestsky);
        rootLayout.addView(backgroundView);

        // Inflate the activity's layout and add it to the FrameLayout
        View contentView = getLayoutInflater().inflate(R.layout.activity_seasons_playing, rootLayout, false);
        rootLayout.addView(contentView);

        // Set the FrameLayout as the content view
        setContentView(rootLayout);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Initialize buttons and ImageView
        seasonImageView = findViewById(R.id.seasonImageView);
        springButton = findViewById(R.id.springButton);
        summerButton = findViewById(R.id.summerButton);
        autumnButton = findViewById(R.id.autumnButton);
        winterButton = findViewById(R.id.winterButton);

        // Initialize season images
        seasonImages = new HashMap<>();
        seasonImages.put("spring", new Integer[] {R.drawable.bird,R.drawable.tree,R.drawable.flower,R.drawable.bee});
        seasonImages.put("summer", new Integer[] {R.drawable.sunglasses,R.drawable.sun,R.drawable.icecream,R.drawable.umbrella});
        seasonImages.put("autumn", new Integer[] {R.drawable.pumpkin,R.drawable.pine,R.drawable.mushroom,R.drawable.dryleaf});
        seasonImages.put("winter", new Integer[] {R.drawable.snow,R.drawable.snowman,R.drawable.snowglobe,R.drawable.winterhat});

        lastImage = -1;

        // Start game
        startGame();
    }

    private void startGame() {
        String[] seasons = new String[] {"spring", "summer", "autumn", "winter"};
        Random random = new Random();
        String randomSeason = seasons[random.nextInt(seasons.length)];
        Integer[] images = seasonImages.get(randomSeason);
        int randomImage;
        do {
            randomImage = images[random.nextInt(images.length)];
        } while (randomImage == lastImage);
        lastImage = randomImage;

        seasonImageView.setImageResource(randomImage);

        springButton.setOnClickListener(v -> checkAnswer("spring", randomSeason));
        summerButton.setOnClickListener(v -> checkAnswer("summer", randomSeason));
        autumnButton.setOnClickListener(v -> checkAnswer("autumn", randomSeason));
        winterButton.setOnClickListener(v -> checkAnswer("winter", randomSeason));
    }

    private void checkAnswer(String userAnswer, String correctAnswer) {
        String message;
        if (userAnswer.equals(correctAnswer)) {
            message = "CORRECT!";
        } else {
            message = "TOO BAD, the correct answer was " + correctAnswer;
        }

        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.seasons_dialog_layout);

        TextView dialogText = (TextView) dialog.findViewById(R.id.dialog_text);
        dialogText.setText(message);

        LinearLayout dialogLayout = (LinearLayout) dialog.findViewById(R.id.seasons_dialog_layout);
        dialogLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                startGame();
            }
        });
    }


}

