package com.example.project;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class PlayDaysActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] questions = {
            "How many days are there in a week?",
            "Which is the first day of the week?",
            "Which is the last day of the week?",
            "Which days belong to weekdays?",
            "Which days belong to the weekend?",
            "Which days are considered weekend holidays?",
            "Which days require going to school?",
            "Which days have more time to relax at home?",
            "Which days are in the middle of the week?",
            "Which days are working days?"
    };

    private String[][] options = {
            {"5", "6", "7"},
            {"Sunday", "Monday", "Saturday"},
            {"Sunday", "Saturday", "Monday"},
            {"Monday, Tuesday, Wednesday, Thursday, Friday", "Tuesday, Wednesday, Thursday, Friday, Saturday", "Wednesday, Thursday, Friday, Saturday, Sunday"},
            {"Saturday, Sunday", "Sunday, Monday", "Monday, Tuesday"},
            {"Saturday, Sunday", "Monday, Tuesday", "Tuesday, Wednesday"},
            {"Monday, Tuesday, Wednesday, Thursday, Friday", "Tuesday, Wednesday, Thursday, Friday, Saturday", "Wednesday, Thursday, Friday, Saturday, Sunday"},
            {"Tuesday, Wednesday", "Saturday, Sunday", "Wednesday, Thursday"},
            {"Thursday", "Wednesday", "Friday"},
            {"Monday, Tuesday, Wednesday, Thursday, Friday", "Saturday, Sunday", "Sunday, Monday"}
    };

    private int[] correctAnswers = {2, 1, 0, 0, 0, 0, 0, 1, 1, 0};

    private ProgressBar progressBar;
    private TextView textViewQuestion;
    private Button[] answerButtons;
    private TextView textViewScore;

    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_days);

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
        SeamlessBackgroundView backgroundView = new SeamlessBackgroundView(this, R.drawable.bonebackground, R.drawable.dayssky);
        rootLayout.addView(backgroundView);

        // Inflate the activity's layout and add it to the FrameLayout
        View contentView = getLayoutInflater().inflate(R.layout.activity_play_days, rootLayout, false);
        rootLayout.addView(contentView);

        // Set the FrameLayout as the content view
        setContentView(rootLayout);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        progressBar = findViewById(R.id.progressBar);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        answerButtons = new Button[3];
        answerButtons[0] = findViewById(R.id.buttonAnswer1);
        answerButtons[1] = findViewById(R.id.buttonAnswer2);
        answerButtons[2] = findViewById(R.id.buttonAnswer3);
        textViewScore = findViewById(R.id.textViewScore);

        for (Button button : answerButtons) {
            button.setOnClickListener(this);
        }

        progressBar.setMax(questions.length);
        updateQuestion();
    }

    @Override
    public void onClick(View v) {
        int answerIndex = -1;

        switch (v.getId()) {
            case R.id.buttonAnswer1:
                answerIndex = 0;
                break;
            case R.id.buttonAnswer2:
                answerIndex = 1;
                break;
            case R.id.buttonAnswer3:
                answerIndex = 2;
                break;
        }

        if (answerIndex == correctAnswers[currentQuestionIndex]) {
            score++;
        } else {
            score--;
            if (score < 0) {
                score = 0;
            }
        }

        textViewScore.setText("Score: " + score);

        if (currentQuestionIndex < questions.length - 1) {
            currentQuestionIndex++;
            updateQuestion();
        } else {
            // Game over
            Toast.makeText(this, "Game over. Your score: " + score, Toast.LENGTH_SHORT).show();
            resetGame();
        }
    }

    private void updateQuestion() {
        textViewQuestion.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(options[currentQuestionIndex][i]);
        }
        progressBar.setProgress(currentQuestionIndex + 1);
    }

    private void resetGame() {
        currentQuestionIndex = 0;
        score = 0;
        textViewScore.setText("Score: 0");
        progressBar.setProgress(0);
        updateQuestion();
    }
}
