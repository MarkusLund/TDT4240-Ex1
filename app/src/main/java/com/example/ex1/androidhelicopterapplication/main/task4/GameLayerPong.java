package com.example.ex1.androidhelicopterapplication.main.task4;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.ex1.androidhelicopterapplication.R;

import sheep.game.Layer;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

/**
 * Created by markuslund92 on 16.01.15.
 */
public class GameLayerPong extends Layer {

    PongPaddle player1, player2;
    Boolean init = true;
    Image pong_paddle = new Image(R.drawable.pong_paddle);
    private int canvasWidth;
    private int canvasHight;
    Boolean p1 = false, p2 = false;
    private PongPaddle actionDownPlayer;
    private PongPaddle actionPointerDownPlayer;

    public GameLayerPong() {
        player1 = new PongPaddle(pong_paddle, 1);
        player2 = new PongPaddle(pong_paddle, 2);
        player1.setSpeed(0, 0);
        player2.setSpeed(0, 0);
    }

    @Override
    public void update(float v) {
        player1.update(v);
        player2.update(v);
    }

    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        player1.draw(canvas);
        player2.draw(canvas);

        if (init) {
            Log.i("init", "Init is run");
            canvasWidth = canvas.getWidth();
            canvasHight = canvas.getHeight();
            player1.setPosition(50, canvasHight / 2);
            player2.setPosition(canvasWidth - 50, canvasHight / 2);
            init = false;
        }

        player1.setPosition(player1.getPosition().getX() + player1.getSpeed().getX(), player1.getPosition().getY() + player1.getSpeed().getY());
        player2.setPosition(player2.getPosition().getX() + player2.getSpeed().getX(), player2.getPosition().getY() + player2.getSpeed().getY());

    }

    public void onTouch(View v, MotionEvent event) {
        int eventAction = event.getActionMasked();

        int num = event.getPointerCount();

        // For every touch
        for (int a = 0; a < num; a++) {

            int X = (int) event.getX(event.getPointerId(a));
            int Y = (int) event.getY(event.getPointerId(a));

            switch (eventAction) {
                case MotionEvent.ACTION_MOVE:
                    //Move player1
                    if (X < canvasWidth / 2) {
                        player1.setPosition(player1.getPosition().getX(),Y);
                    }

                    //Move player2
                    if (X > canvasWidth / 2) {
                        player2.setPosition(player2.getPosition().getX(),Y);
                    }
                    break;

                default:
                    break;
            }
        }
    }
}
