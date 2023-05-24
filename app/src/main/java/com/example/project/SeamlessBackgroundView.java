package com.example.project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class SeamlessBackgroundView extends View {

    private Bitmap groundBitmap;
    private Bitmap skyBitmap;

    public SeamlessBackgroundView(Context context, int mountainResId, int skyResId) {
        super(context);

        // Load the bitmaps
        groundBitmap = BitmapFactory.decodeResource(getResources(), mountainResId);
        skyBitmap = BitmapFactory.decodeResource(getResources(), skyResId);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // Draw the sky
        for (int i = 0; i < width; i += skyBitmap.getWidth()) {
            for (int j = 0; j < height; j += skyBitmap.getHeight()) {
                canvas.drawBitmap(skyBitmap, i, j, null);
            }
        }

        // Draw the mountains
        for (int i = 0; i < width; i += groundBitmap.getWidth()) {
            canvas.drawBitmap(groundBitmap, i, height - groundBitmap.getHeight(), null);
        }
    }
}
