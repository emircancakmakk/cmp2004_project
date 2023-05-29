package com.example.project;

import android.os.Bundle;
import android.view.View;
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

    private final String[] correctAnswers = {
            "North",
            "East",
            "West",
            "South"
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

    private void showRandomImage() {
        Random random = new Random();
        int randomIndex = random.nextInt(imageIds.length);
        imageView.setImageResource(imageIds[randomIndex]);
        imageView.setTag(imageIds[randomIndex]); // Set the tag with the image resource id
    }

    private void checkAnswer(String chosenAnswer) {
        int currentImageIndex = getCurrentImageIndex();

        if (currentImageIndex >= imageIds.length) {
            // Quiz is already completed, no need to check the answer
            Toast.makeText(this, "Quiz completed!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (chosenAnswer.equals(correctAnswers[currentImageIndex])) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect! Try again.", Toast.LENGTH_SHORT).show();
            return; // Exit the method without incrementing the index if the answer is incorrect.
        }

        showRandomImage();
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
                checkAnswer("North");
                break;
            case R.id.buttonWest:
                checkAnswer("West");
                break;
            case R.id.buttonEast:
                checkAnswer("East");
                break;
            case R.id.buttonSouth:
                checkAnswer("South");
                break;
        }
    }
}