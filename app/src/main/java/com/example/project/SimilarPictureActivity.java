package com.example.project;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SimilarPictureActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView firstImageView;
    private ImageView secondImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_picture);

        firstImageView = findViewById(R.id.first_image_view);
        secondImageView = findViewById(R.id.second_image_view);

        firstImageView.setOnClickListener(this);
        secondImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.first_image_view) {
            Drawable firstImageDrawable = firstImageView.getDrawable();
            Drawable secondImageDrawable = secondImageView.getDrawable();

            if (firstImageDrawable.getConstantState().equals(secondImageDrawable.getConstantState())) {
                // The pictures are similar
                Toast.makeText(this, "Congratulations! You found similar pictures!", Toast.LENGTH_SHORT).show();
            } else {
                // The pictures are not similar
                Toast.makeText(this, "Oops! These pictures are not similar.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
