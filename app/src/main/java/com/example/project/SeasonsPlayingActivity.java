package com.example.project;

import android.app.Dialog;
import android.content.DialogInterface;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons_playing);

        // Initialize buttons and ImageView
        seasonImageView = findViewById(R.id.seasonImageView);
        springButton = findViewById(R.id.springButton);
        summerButton = findViewById(R.id.summerButton);
        autumnButton = findViewById(R.id.autumnButton);
        winterButton = findViewById(R.id.winterButton);

        // Initialize season images
        seasonImages = new HashMap<>();
        seasonImages.put("spring", new Integer[] {R.drawable.bonebackground});
        seasonImages.put("summer", new Integer[] {R.drawable.button_bone});
        seasonImages.put("autumn", new Integer[] {R.drawable.buttonstone1});
        seasonImages.put("winter", new Integer[] {R.drawable.buttonstone2});

        // Start game
        startGame();
    }

    private void startGame() {
        String[] seasons = new String[] {"spring", "summer", "autumn", "winter"};
        Random random = new Random();
        String randomSeason = seasons[random.nextInt(seasons.length)];
        Integer[] images = seasonImages.get(randomSeason);
        int randomImage = images[random.nextInt(images.length)];

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

