package com.example.project;

import android.graphics.Shader;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ImageView backgroundImageView = findViewById(R.id.island_view);
        Glide.with(this).load(R.drawable.gmainmenu).into(backgroundImageView);


        // Load the cloud tile from your drawable resources
        Bitmap cloudBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ground);
        BitmapDrawable cloudDrawable = new BitmapDrawable(getResources(), cloudBitmap);

        // Set the drawable to tile
        cloudDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        cloudDrawable.setDither(true);

        // Find the parent view and set the tiled sky drawable as its background
        ConstraintLayout parentView = findViewById(R.id.parent_view);
        parentView.setBackground(cloudDrawable);

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