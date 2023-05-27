package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SpellActivity extends AppCompatActivity {

    private TextView wordTextView;
    private Button checkButton;

    private String[] words = {"apple", "banana", "cat", "dog", "elephant"};
    private String currentWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell);

        wordTextView = findViewById(R.id.wordTextView);
        checkButton = findViewById(R.id.checkButton);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkWord();
            }
        });

        generateRandomWord();
    }

    private void generateRandomWord() {
        Random random = new Random();
        int index = random.nextInt(words.length);
        currentWord = words[index];
        wordTextView.setText(currentWord);
    }

    private void checkWord() {
        String enteredWord = wordTextView.getText().toString().trim();
        if (enteredWord.equalsIgnoreCase(currentWord)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            generateRandomWord();
        } else {
            Toast.makeText(this, "Incorrect. Try again!", Toast.LENGTH_SHORT).show();
        }
    }
}
