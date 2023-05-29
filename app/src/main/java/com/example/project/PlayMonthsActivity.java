package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlayMonthsActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] questions = {
            "Which month comes after January?",
            "Which month has 28 or 29 days in a leap year?",
            "Which month is the tenth month of the year?",
            "Which month has 31 days?",
            "Which month is the first month of the year?",
            "Which month is known for being the hottest in the Northern Hemisphere?",
            "Which month is associated with Halloween?",
            "Which month is associated with Thanksgiving in the United States?",
            "Which month is known for having the longest day in the Northern Hemisphere?",
            "Which month marks the beginning of spring in the Northern Hemisphere?"
    };

    private String[][] options = {
            {"February", "March", "April"},
            {"February", "March", "December"},
            {"October", "November", "September"},
            {"January", "March", "May"},
            {"January", "February", "December"},
            {"July", "August", "September"},
            {"October", "November", "December"},
            {"November", "December", "October"},
            {"June", "July", "August"},
            {"March", "April", "May"}
    };

    private int[] correctAnswers = {0, 0, 2, 2, 0, 1, 2, 0, 2, 0};

    private ProgressBar progressBar;
    private TextView textViewQuestion;
    private Button[] answerButtons;
    private TextView textViewScore;

    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_months);

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
