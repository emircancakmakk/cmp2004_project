package com.example.project;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class PlayDaysActivity extends AppCompatActivity implements View.OnClickListener {

    private int[] questions = {
            R.string.days_question1,
            R.string.days_question2,
            R.string.days_question3,
            R.string.days_question4,
            R.string.days_question5,
            R.string.days_question6,
            R.string.days_question7,
            R.string.days_question8,
            R.string.days_question9,
            R.string.days_question10
    };

    private int[][] options = {
            {R.string.days_option_1_1, R.string.days_option_1_2, R.string.days_option_1_3},
            {R.string.days_option_2_1, R.string.days_option_2_2, R.string.days_option_2_3},
            {R.string.days_option_3_1, R.string.days_option_3_2, R.string.days_option_3_3},
            {R.string.days_option_4_1, R.string.days_option_4_2, R.string.days_option_4_3},
            {R.string.days_option_5_1, R.string.days_option_5_2, R.string.days_option_5_3},
            {R.string.days_option_6_1, R.string.days_option_6_2, R.string.days_option_6_3},
            {R.string.days_option_7_1, R.string.days_option_7_2, R.string.days_option_7_3},
            {R.string.days_option_8_1, R.string.days_option_8_2, R.string.days_option_8_3},
            {R.string.days_option_9_1, R.string.days_option_9_2, R.string.days_option_9_3},
            {R.string.days_option_10_1, R.string.days_option_10_2, R.string.days_option_10_3}
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
    public void question_mark(View v) {
        String[] dialogueChunks = {getString(R.string.play_days_dialouge_1), getString(R.string.play_days_dialouge_2),getString(R.string.play_days_dialouge_3)}; // Add your own dialogue here
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TalkingCharacter talkingCharacter = new TalkingCharacter(PlayDaysActivity.this, R.layout.dialog_layout, dialogueChunks);
        talkingCharacter.showDialog();
    }
}
