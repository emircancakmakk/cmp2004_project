package com.example.project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    }


    public void run() {
        while (isRunning) {
            if (surfaceHolder.getSurface().isValid()) {
                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.WHITE);

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
                canvas.drawCircle(ballX, ballY, ballRadius, paint);

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
