package com.example.project;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Shader;
import android.text.TextUtils;
import android.widget.Button;
import android.view.WindowManager;
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
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String PREF_QUESTION_MARK_CALLED = "question_mark_called";

    private String currentLanguage = "en"; // Başlangıç dilini burada belirleyin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentLanguage = preferences.getString("language", "en");

        // Dil ayarlarını kontrol et


        setContentView(R.layout.activity_main);

        ImageView btnChangeLanguage = findViewById(R.id.btnChangeLanguage);
        btnChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dil değiştirme işlemini burada gerçekleştirin
                if (currentLanguage.equals("tr")) {
                    changeLanguage("en"); // İngilizce'ye geçiş yap
                } else {
                    changeLanguage("tr"); // Türkçe'ye geçiş yap
                }
            }
        });

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

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean questionMarkCalled = prefs.getBoolean(PREF_QUESTION_MARK_CALLED, false);

        if (!questionMarkCalled) {
            findViewById(R.id.question_mark_button).performClick(); // Trigger the question_mark function
            prefs.edit().putBoolean(PREF_QUESTION_MARK_CALLED, true).apply();
        }
    }

    private void changeLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.setLocale(locale);

        Resources resources = getResources();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        // Dil ayarlarını SharedPreferences kullanarak sakla
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putString("language", languageCode).apply();

        // Uygulamanın baştan başlaması için aktif aktiviteyi yeniden başlatın
        Intent refreshIntent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(refreshIntent);
        finish(); // Mevcut etkinliği sonlandır
    }



    public void question_mark(View v) {
        String[] dialogueChunks = {getString(R.string.main_dialouge1), getString(R.string.main_dialouge2), getString(R.string.main_dialouge3),getString(R.string.main_dialouge_4)}; // Add your own dialogue here
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TalkingCharacter talkingCharacter = new TalkingCharacter(MainActivity.this, R.layout.dialog_layout, dialogueChunks);
        talkingCharacter.showDialog();
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
