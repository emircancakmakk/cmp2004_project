package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MultiplicationActivity extends AppCompatActivity {

    private TextView number1TextView;
    private TextView number2TextView;
    private EditText resultEditText;
    private Button startButton;
    private Button submitButton;
    private Button retryButton;

    private int correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);
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
        SeamlessBackgroundView backgroundView = new SeamlessBackgroundView(this, R.drawable.foresthome, R.drawable.forestsky);
        rootLayout.addView(backgroundView);

        // Inflate the activity's layout and add it to the FrameLayout
        View contentView = getLayoutInflater().inflate(R.layout.activity_multiplication, rootLayout, false);
        rootLayout.addView(contentView);

        // Set the FrameLayout as the content view
        setContentView(rootLayout);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        number1TextView = findViewById(R.id.number1TextView);
        number2TextView = findViewById(R.id.number2TextView);
        resultEditText = findViewById(R.id.resultEditText);
        startButton = findViewById(R.id.startButton);
        submitButton = findViewById(R.id.submitButton);
        retryButton = findViewById(R.id.retryButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomNumbers();
                startButton.setVisibility(View.GONE);
                submitButton.setVisibility(View.VISIBLE);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retry();
            }
        });

        // Initially show the submit button
        submitButton.setVisibility(View.VISIBLE);
    }

    private void generateRandomNumbers() {
        Random random = new Random();
        int number1 = random.nextInt(10) + 1;
        int number2 = random.nextInt(10) + 1;

        number1TextView.setText(String.valueOf(number1));
        number2TextView.setText(String.valueOf(number2));

        correctAnswer = number1 * number2;
    }

    private void checkAnswer() {
        int userAnswer = Integer.parseInt(resultEditText.getText().toString().trim());

        if (userAnswer == correctAnswer) {
            displayResult("Correct!");
        } else {
            displayResult("Incorrect!");
            submitButton.setVisibility(View.GONE);
            retryButton.setVisibility(View.VISIBLE);
        }
    }

    private void displayResult(String message) {
        resultEditText.setText(message);
        resultEditText.setEnabled(false);
    }

    private void retry() {
        resultEditText.setText("");
        resultEditText.setEnabled(true);
        submitButton.setVisibility(View.VISIBLE);
        generateRandomNumbers();
    }
}