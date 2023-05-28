package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlayDaysActivity extends AppCompatActivity {

    private LinearLayout slotContainer1, slotContainer2, slotContainer3, slotContainer4, slotContainer5, slotContainer6, slotContainer7;
    private Button btnMonday, btnTuesday, btnWednesday, btnThursday, btnFriday, btnSaturday, btnSunday, btnApply, btnReturn;
    private List<Button> dayButtons;
    private List<LinearLayout> slotContainers;

    private final String[] correctOrder = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_days);

        // Initialize the buttons and containers
        slotContainer1 = findViewById(R.id.slotContainer1);
        slotContainer2 = findViewById(R.id.slotContainer2);
        slotContainer3 = findViewById(R.id.slotContainer3);
        slotContainer4 = findViewById(R.id.slotContainer4);
        slotContainer5 = findViewById(R.id.slotContainer5);
        slotContainer6 = findViewById(R.id.slotContainer6);
        slotContainer7 = findViewById(R.id.slotContainer7);

        btnMonday = findViewById(R.id.btnMonday);
        btnTuesday = findViewById(R.id.btnTuesday);
        btnWednesday = findViewById(R.id.btnWednesday);
        btnThursday = findViewById(R.id.btnThursday);
        btnFriday = findViewById(R.id.btnFriday);
        btnSaturday = findViewById(R.id.btnSaturday);
        btnSunday = findViewById(R.id.btnSunday);

        dayButtons = Arrays.asList(btnMonday, btnTuesday, btnWednesday, btnThursday, btnFriday, btnSaturday, btnSunday);
        slotContainers = Arrays.asList(slotContainer1, slotContainer2, slotContainer3, slotContainer4, slotContainer5, slotContainer6, slotContainer7);

        shuffleDays();

        // Set the long click listeners for the day buttons
        for (Button btn : dayButtons) {
            btn.setOnLongClickListener(new MyLongClickListener());
        }

        // Set the drag listeners for the slot containers
        for (LinearLayout container : slotContainers) {
            container.setOnDragListener(new MyDragListener());
        }

        // Initialize and set the click listener for the Apply button
        btnApply = findViewById(R.id.btnApply);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkOrder()) {
                    Toast.makeText(PlayDaysActivity.this, "Congrats! You got it right!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PlayDaysActivity.this, "Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Initialize and set the click listener for the Return button
        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // or start a new activity depending on your navigation logic
            }
        });
    }

    private void shuffleDays() {
        // Remove day buttons from their current parent
        ViewGroup previousParent;
        for (Button btn : dayButtons) {
            previousParent = (ViewGroup) btn.getParent();
            if (previousParent != null) {
                previousParent.removeView(btn);
            }
        }

        // Shuffle the list of day buttons
        Collections.shuffle(dayButtons);

        // Add the shuffled day buttons to the slot containers
        for (int i = 0; i < dayButtons.size(); i++) {
            slotContainers.get(i).addView(dayButtons.get(i));
        }
    }

    private boolean checkOrder() {
        // Check if the order of days in the slot containers matches the correct order
        for (int i = 0; i < slotContainers.size(); i++) {
            Button btn = (Button) slotContainers.get(i).getChildAt(0);
            if (!btn.getText().toString().equals(correctOrder[i])) {
                return false;
            }
        }
        return true;
    }

    private final class MyLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View view) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDragAndDrop(null, shadowBuilder, view, 0);
            return true;
        }
    }

    class MyDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            Button draggedButton = (Button) event.getLocalState();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(Color.LTGRAY);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(Color.TRANSPARENT);
                    break;
                case DragEvent.ACTION_DROP:
                    LinearLayout droppedContainer = (LinearLayout) v;

                    if (droppedContainer.getChildCount() > 0) {
                        droppedContainer.removeAllViews();
                    }

                    ViewGroup owner = (ViewGroup) draggedButton.getParent();
                    owner.removeView(draggedButton);
                    droppedContainer.addView(draggedButton);

                    v.setBackgroundColor(Color.TRANSPARENT);

                    Toast.makeText(PlayDaysActivity.this, "Button successfully moved", Toast.LENGTH_SHORT).show();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundColor(Color.TRANSPARENT);
                    break;
                default:
                    break;
            }

            return true;
        }
    }
}
