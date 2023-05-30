package com.example.project;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DigitBackwardActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_digit_backward);

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
        SeamlessBackgroundView backgroundView = new SeamlessBackgroundView(this, R.drawable.digits_ground, R.drawable.digits_sky);
        rootLayout.addView(backgroundView);

        // Inflate the activity's layout and add it to the FrameLayout
        View contentView = getLayoutInflater().inflate(R.layout.activity_digit_backward, rootLayout, false);
        rootLayout.addView(contentView);

        // Set the FrameLayout as the content view
        setContentView(rootLayout);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
        Collections.reverse(correctSequence); // Reverse the correct sequence
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

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            numberTextView.setText(""); // Add a blank "pause" after each number
                        }
                    }, 1000); // Display the blank "pause" for 1 second

                    if (currentIndex < numberSequence.size()) {
                        handler.postDelayed(this, 2000); // Display the next number after 2 seconds, taking into account the "pause"
                    } else {
                        userInputEditText.setEnabled(true);
                        submitButton.setEnabled(true);
                        restartButton.setEnabled(true);
                    }
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
            correctnessTextView.setText(getString(R.string.check_answer_correct));
        } else {
            correctnessTextView.setText(getString(R.string.check_answer_incorrect));
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
    public void question_mark(View v) {
        String[] dialogueChunks = {getString(R.string.digits_backward_dialouge1), getString(R.string.digits_backward_dialouge2)}; // Add your own dialogue here
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TalkingCharacter talkingCharacter = new TalkingCharacter(DigitBackwardActivity.this, R.layout.dialog_layout, dialogueChunks);
        talkingCharacter.showDialog();
    }
}
