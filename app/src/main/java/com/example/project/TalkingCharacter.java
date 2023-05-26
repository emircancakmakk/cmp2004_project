package com.example.project;

import android.app.Activity;
import android.app.Dialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;

class TalkingCharacter {
    private final Dialog dialog;
    private final ImageView characterImageView;
    private final TextView speechBubbleTextView;
    private final String[] dialogueChunks;
    private int currentChunkIndex = 0;
    private int characterIndex = 0;
    private final Handler handler = new Handler();
    private boolean isSpeaking = false;
    private final int speed = 100; // Change this value to control the speed of the text

    // Constructor
    TalkingCharacter(Activity activity, int dialogLayoutId, String[] dialogueChunks){
        this.dialogueChunks = dialogueChunks;

        dialog = new Dialog(activity);
        dialog.setContentView(dialogLayoutId);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);

        characterImageView = dialog.findViewById(R.id.characterImageView);
        speechBubbleTextView = dialog.findViewById(R.id.speechBubbleTextView);
        ConstraintLayout layout = dialog.findViewById(R.id.dialog_layout);
        layout.setClickable(true);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPointInsideView(v.getX(), v.getY(), characterImageView)
                        && !isPointInsideView(v.getX(), v.getY(), speechBubbleTextView)) {
                    dialog.dismiss();
                }
            }
        });

        characterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSpeaking) {
                    skipText();
                } else {
                    nextChunk();
                }
            }
        });
    }

    void showDialog() {
        dialog.show();
        nextChunk();
    }

    private void nextChunk() {
        if (currentChunkIndex < dialogueChunks.length) {
            speak(dialogueChunks[currentChunkIndex]);
            currentChunkIndex++;
        } else {
            dialog.dismiss();
        }
    }

    private void speak(String text) {
        speechBubbleTextView.setText("");
        characterIndex = 0;
        isSpeaking = true;
        handler.post(new CharacterRunnable(text));
    }

    private void skipText() {
        handler.removeCallbacksAndMessages(null);
        if (currentChunkIndex > 0 && currentChunkIndex <= dialogueChunks.length) {
            speechBubbleTextView.setText(dialogueChunks[currentChunkIndex-1]);
        }
        isSpeaking = false;
    }


    private class CharacterRunnable implements Runnable {
        private final String text;

        CharacterRunnable(String text) {
            this.text = text;
        }

        @Override
        public void run() {
            if (characterIndex <= text.length()) {
                speechBubbleTextView.setText(text.substring(0, characterIndex));
                // Switch the image every time a new character is spoken
                if (characterIndex % 2 == 0) {
                    characterImageView.setImageResource(R.drawable.dino_avatar2); // mouth closed
                } else {
                    characterImageView.setImageResource(R.drawable.dino_avatar1); // mouth open
                }
                characterIndex++;
                handler.postDelayed(this, speed);
            } else {
                isSpeaking = false;
                // Ensure the mouth is closed when the character finishes speaking
                characterImageView.setImageResource(R.drawable.dino_avatar2); // mouth closed
            }
        }
    }

    private boolean isPointInsideView(float x, float y, @NotNull View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];

        // point is inside view bounds
        return ((x > viewX && x < (viewX + view.getWidth())) &&
                (y > viewY && y < (viewY + view.getHeight())));
    }
}
