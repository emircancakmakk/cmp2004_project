package com.example.project;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DigitForwardActivity extends AppCompatActivity {
    private TextView numberTextView;
    private EditText userInputEditText;
    private Button startButton;
    private Button submitButton;
    private Button restartButton;
    private TextView correctnessTextView;

    private List<Integer> numberSequence;
    private List<Integer> correctSequence;
    private int currentIndex;

    private Handler handler;
    private Runnable displayRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digit_forward);

        numberTextView = findViewById(R.id.numberTextView);
        userInputEditText = findViewById(R.id.userInputEditText);
        startButton = findViewById(R.id.startButton);
        submitButton = findViewById(R.id.submitButton);
        restartButton = findViewById(R.id.restartButton);
        correctnessTextView = findViewById(R.id.correctnessTextView);

        numberSequence = new ArrayList<>();
        correctSequence = new ArrayList<>();
        currentIndex = 0;

        handler = new Handler();

        generateNumberSequence();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUserInput();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });
    }

    private void generateNumberSequence() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int number = random.nextInt(10); // Generate a random number from 0 to 9
            numberSequence.add(number);
            correctSequence.add(number);
        }
    }

    private void startGame() {
        currentIndex = 0;
        startButton.setEnabled(false);
        userInputEditText.setEnabled(false);
        submitButton.setEnabled(false);
        restartButton.setEnabled(false);
        correctnessTextView.setText("");

        displayRunnable = new Runnable() {
            @Override
            public void run() {
                if (currentIndex < numberSequence.size()) {
                    int number = numberSequence.get(currentIndex);
                    numberTextView.setText(String.valueOf(number));
                    currentIndex++;
                    handler.postDelayed(this, 1000); // Display each number for 1 second
                } else {
                    numberTextView.setText("");
                    userInputEditText.setEnabled(true);
                    submitButton.setEnabled(true);
                    restartButton.setEnabled(true);
                }
            }
        };

        handler.post(displayRunnable);
    }

    private void validateUserInput() {
        userInputEditText.setEnabled(false);
        submitButton.setEnabled(false);

        String userInput = userInputEditText.getText().toString().trim();
        List<Integer> userInputSequence = new ArrayList<>();

        try {
            String[] numbers = userInput.split("");
            for (String number : numbers) {
                if (!number.isEmpty()) {
                    userInputSequence.add(Integer.parseInt(number));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (userInputSequence.equals(correctSequence)) {
            correctnessTextView.setText("Correct");
        } else {
            correctnessTextView.setText("Incorrect");
        }
    }

    private void restartGame() {
        numberSequence.clear();
        correctSequence.clear();
        currentIndex = 0;
        userInputEditText.setText("");
        userInputEditText.setEnabled(false);
        submitButton.setEnabled(false);
        restartButton.setEnabled(false);
        correctnessTextView.setText("");

        generateNumberSequence();
        startGame();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && displayRunnable != null) {
            handler.removeCallbacks(displayRunnable);
        }
    }
}
