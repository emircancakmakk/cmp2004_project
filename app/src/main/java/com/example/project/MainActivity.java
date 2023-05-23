package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OpenLearningPart(View view) {
        Intent intent = new Intent(this, LearningPartActivity.class);
        startActivity(intent);
    }

    public void OpenPlayingPart(View view) {
        Intent intent = new Intent(this, PlayingPartActivity.class);
        startActivity(intent);
    }
}