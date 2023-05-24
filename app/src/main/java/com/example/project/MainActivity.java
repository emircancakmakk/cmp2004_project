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

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ImageView backgroundImageView = findViewById(R.id.island_view);
        Glide.with(this).load(R.drawable.gmainmenu).into(backgroundImageView);


        // Load the tile from your drawable resources
        Bitmap tileBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ground);
        BitmapDrawable tileDrawable = new BitmapDrawable(getResources(), tileBitmap);

        // Set the drawable to tile
        tileDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        tileDrawable.setDither(true);

        // Find the parent view and set the tiled tile drawable as its background
        ConstraintLayout parentView = findViewById(R.id.parent_view);
        parentView.setBackground(tileDrawable);

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