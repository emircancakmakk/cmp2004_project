package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DirectionsGameActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private Button buttonNorth, buttonWest, buttonEast, buttonSouth;
    private TextView answerTextView;

    private final int[] imageIds = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4
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

        imageView = findViewById(R.id.imageView);
        buttonNorth = findViewById(R.id.buttonNorth);
        buttonWest = findViewById(R.id.buttonWest);
        buttonEast = findViewById(R.id.buttonEast);
        buttonSouth = findViewById(R.id.buttonSouth);
        answerTextView = findViewById(R.id.answerTextView);

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
        answerTextView.setText(""); // Clear the answer text
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

        answerTextView.setText(correctAnswers[currentImageIndex]); // Display the correct answer
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