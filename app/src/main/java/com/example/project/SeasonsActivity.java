package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class SeasonsActivity extends AppCompatActivity {

    private ImageView seasonImageView;
    private TextView seasonTextView;
    private TextView seasonCreditsTextView;
    private int currentSeasonIndex = 0;
    private int[] seasonGifIds = {R.drawable.spring, R.drawable.summer, R.drawable.autumn, R.drawable.winter};
    private String[] seasonNames;
    private String[] seasonCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        seasonImageView = findViewById(R.id.seasonImageView);
        seasonTextView = findViewById(R.id.seasonTextView);
        seasonCreditsTextView = findViewById(R.id.seasonCreditsTextView);

        seasonNames = getResources().getStringArray(R.array.season_names);
        seasonCredits = getResources().getStringArray(R.array.season_credits);

        if (savedInstanceState != null) {
            currentSeasonIndex = savedInstanceState.getInt("currentSeasonIndex");
        }

        updateSeason();

        findViewById(R.id.prevButton).setOnClickListener(v -> {
            currentSeasonIndex = (currentSeasonIndex + 3) % 4;
            updateSeason();
        });

        findViewById(R.id.nextButton).setOnClickListener(v -> {
            currentSeasonIndex = (currentSeasonIndex + 1) % 4;
            updateSeason();
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentSeasonIndex", currentSeasonIndex);
    }

    private void updateSeason() {
        Glide.with(this)
                .load(seasonGifIds[currentSeasonIndex])
                .into(seasonImageView);
        seasonTextView.setText(seasonNames[currentSeasonIndex]);
        seasonCreditsTextView.setText(seasonCredits[currentSeasonIndex]);
    }
}
