package com.example.project;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurface extends SurfaceView implements Runnable {

    private Thread gameThread;
    private volatile boolean isRunning = false;

    private float ballX, ballY;
    private float ballRadius = 50;
    private float speedX = 5;
    private float speedY = 5;

    private SurfaceHolder surfaceHolder;
    private Paint paint;

    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder = getHolder();
        paint = new Paint();
        paint.setColor(Color.RED);

        // Get the screen dimensions
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        // Set the initial position of the ball to the center of the screen
        ballX = screenWidth / 2f;
        ballY = screenHeight / 2f;

        setZOrderOnTop(true); // Necessary
        SurfaceHolder holder = getHolder();
        holder.setFormat(PixelFormat.TRANSLUCENT);

    }


    public void run() {
        while (isRunning) {
            if (surfaceHolder.getSurface().isValid()) {
                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

                // Update ball position
                ballX += speedX;
                ballY += speedY;

                // Reverse ball direction when hitting screen edges
                if (ballX + ballRadius >= canvas.getWidth() || ballX - ballRadius <= 0) {
                    speedX = -speedX;
                }
                if (ballY + ballRadius >= canvas.getHeight() || ballY - ballRadius <= 0) {
                    speedY = -speedY;
                }

                // Draw ball on canvas
                Resources res = getResources();
                Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.egg);
                canvas.drawBitmap(bitmap, ballX-75, ballY-75, paint);

                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void resume() {
        isRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pause() {
        isRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
