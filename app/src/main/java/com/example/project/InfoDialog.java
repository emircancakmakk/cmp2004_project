package com.example.project;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoDialog extends Dialog {
    private String infoString;
    private ImageView characterView;
    private TextView bubbleView;
    private int currentIndex = 0;
    private static final int CHUNK_SIZE = 100; // change this value to alter how much text is displayed at a time

    public InfoDialog(Context context, Drawable characterDrawable, String info) {
        super(context);
        setContentView(R.layout.dialog_info);

        characterView = findViewById(R.id.characterView);
        bubbleView = findViewById(R.id.bubbleView);
        characterView.setImageDrawable(characterDrawable);
        infoString = info;

        updateBubbleText();

        characterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBubbleText();
            }
        });
    }

    private void updateBubbleText() {
        if (currentIndex < infoString.length()) {
            int endIndex = Math.min(currentIndex + CHUNK_SIZE, infoString.length());
            bubbleView.setText(infoString.substring(currentIndex, endIndex));
            currentIndex = endIndex;
        } else {
            dismiss(); // close the dialog when all text has been shown
        }
    }
}

