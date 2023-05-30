package com.example.project;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DirectionsGameActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private Button buttonNorth, buttonWest, buttonEast, buttonSouth;

    private final int[] imageIds = {
            R.drawable.northdino,
            R.drawable.eastdino,
            R.drawable.westdino,
            R.drawable.southdino
    };

    private final int[] correctAnswers = {
            R.string.north,
            R.string.east,
            R.string.west,
            R.string.south
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions_game);

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
        View contentView = getLayoutInflater().inflate(R.layout.activity_directions_game, rootLayout, false);
        rootLayout.addView(contentView);

        // Set the FrameLayout as the content view
        setContentView(rootLayout);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        imageView = findViewById(R.id.imageView);
        buttonNorth = findViewById(R.id.buttonNorth);
        buttonWest = findViewById(R.id.buttonWest);
        buttonEast = findViewById(R.id.buttonEast);
        buttonSouth = findViewById(R.id.buttonSouth);

        buttonNorth.setOnClickListener(this);
        buttonWest.setOnClickListener(this);
        buttonEast.setOnClickListener(this);
        buttonSouth.setOnClickListener(this);

        showRandomImage();
    }

    private int lastImageIndex = -1;

    private void showRandomImage() {
        Random random = new Random();
        int randomIndex;

        // Generate a new random index that is different from the last one
        do {
            randomIndex = random.nextInt(imageIds.length);
        } while (randomIndex == lastImageIndex);

        imageView.setImageResource(imageIds[randomIndex]);
        imageView.setTag(imageIds[randomIndex]); // Set the tag with the image resource id

        lastImageIndex = randomIndex; // Remember the index of the last image
    }

    private void checkAnswer(String chosenAnswer) {
        int currentImageIndex = getCurrentImageIndex();
        String message;
        String correctAnswer = getString(correctAnswers[currentImageIndex]); // Convert resource ID to string

        if (chosenAnswer.equals(correctAnswer)) {
            message = getString(R.string.correct);
        } else {
            message = getString(R.string.too_bad) + correctAnswer;
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
                showRandomImage();
            }
        });
    }

    private int getCurrentImageIndex() {
        Integer currentImageResource = (Integer) imageView.getTag();

        if (currentImageResource == null) {
            return -1; // Image resource id not found
        }

        for (int i = 0; i < imageIds.length; i++) {
            if (imageIds[i] == currentImageResource) {
                return i;
            }
        }

        return -1; // Image index not found
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonNorth:
                checkAnswer(getString(R.string.north));
                break;
            case R.id.buttonWest:
                checkAnswer(getString(R.string.west));
                break;
            case R.id.buttonEast:
                checkAnswer(getString(R.string.south));
                break;
            case R.id.buttonSouth:
                checkAnswer(getString(R.string.east));
                break;
        }
    }

    public void question_mark(View v) {
        String[] dialogueChunks = {getString(R.string.direcitons_playing_dialouge1), getString(R.string.directions_playing_dialouge2)}; // Add your own dialogue here
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TalkingCharacter talkingCharacter = new TalkingCharacter(DirectionsGameActivity.this, R.layout.dialog_layout, dialogueChunks);
        talkingCharacter.showDialog();
    }
}