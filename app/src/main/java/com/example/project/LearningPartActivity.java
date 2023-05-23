package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LearningPartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_part);
    }

    public void LearnSeasons(View view) {
        Intent intent = new Intent(this, SeasonsActivity.class);
        startActivity(intent);
    }

    public void LearnDigitForward(View view) {
        Intent intent = new Intent(this, DigitForwardActivity.class);
        startActivity(intent);
    }

    public void LearnClock(View view) {
        Intent intent = new Intent(this, ClockActivity.class);
        startActivity(intent);
    }

    public void LearnSpell(View view) {
        Intent intent = new Intent(this, SpellActivity.class);
        startActivity(intent);
    }

    public void LearnMonths(View view) {
        Intent intent = new Intent(this, MonthsActivity.class);
        startActivity(intent);
    }

    public void LearnDays(View view) {
        Intent intent = new Intent(this, DaysActivity.class);
        startActivity(intent);
    }

    public void LearnDigitBackward(View view) {
        Intent intent = new Intent(this, DigitBackwardActivity.class);
        startActivity(intent);
    }

    public void LearnDirections(View view) {
        Intent intent = new Intent(this, DirectionsActivity.class);
        startActivity(intent);
    }

    public void LearnMultiplication(View view) {
        Intent intent = new Intent(this, MultiplicationActivity.class);
        startActivity(intent);
    }
}