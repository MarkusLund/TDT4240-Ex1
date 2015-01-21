package com.example.ex1.androidhelicopterapplication.main.task4;

import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.ex1.androidhelicopterapplication.R;
import com.example.ex1.androidhelicopterapplication.main.Util;

import sheep.game.Layer;
import sheep.graphics.Color;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

/**
 * Created by markuslund92 on 16.01.15.
 */
public class GameLayerPong extends Layer {

    private PongPaddle player1, player2;
    private PongBall ball;
    private Boolean init;
    private Image pong_paddle;
    private int canvasWidth, canvasHeight;
    private float dt;
    private Font scoreFont;
    private static int p1Score, p2Score, winningScore;
    private boolean gameWon;

    public GameLayerPong() {
        init = true;
        pong_paddle = new Image(R.drawable.pong_paddle);

        p1Score = 0;
        p2Score = 0;
        winningScore = 3;
        gameWon = false;

        scoreFont = new Font(255, 255, 255, 50, Typeface.MONOSPACE, Typeface.NORMAL);
        player1 = new PongPaddle(pong_paddle, 1);
        player2 = new PongPaddle(pong_paddle, 2);
        ball = new PongBall();
        ball.setSpeed(Util.getRandSpeed(-4,4));

    }


    @Override
    public void update(float v) {

        if (gameWon){

        }else{
            Util.moveSprite(player1);
            Util.moveSprite(player2);
            Util.moveSprite(ball);

            if ( dt>0.05 && (ball.collides(player1) || ball.collides(player2)) ){
                ball.setSpeed(-ball.getSpeed().getX(),ball.getSpeed().getY());
                dt=0;
            }

            if( dt>0.05 && (ball.getPosition().getY()<(10+ball.getHeight()/2) || ball.getPosition().getY()>canvasHeight-10-ball.getHeight()/2) ){
                ball.setSpeed(ball.getSpeed().getX(),-ball.getSpeed().getY());
                dt=0;
            }

            if( ball.getPosition().getX()<0){
                addP2Score();
                init=true;
            }else if(ball.getPosition().getX()>canvasWidth){
                addP1Score();
                init=true;
            }

            if(p1Score >= winningScore || p2Score >= winningScore){
                gameWon = true;
            }

            dt+=v;
            player1.update(v);
            player2.update(v);
            ball.update(v);

        }


    }

    private void addP1Score() {
        p1Score++;
    }

    private void addP2Score() {
        p2Score++;
    }

    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        canvas.drawText(String.valueOf(getP1Score()), -80 + canvasWidth / 2, 100, scoreFont);
        canvas.drawText(String.valueOf(getP2Score()), 30 + canvasWidth / 2, 100, scoreFont);

        if (gameWon){
            if (getP1Score() > getP2Score()){

                canvas.drawText("Player 1 won!", canvasWidth / 2, 100, scoreFont);
            }else{
                canvas.drawText("Player 2 won!", canvasWidth / 2, 100, scoreFont);

            }
        }else{
            player1.draw(canvas);
            player2.draw(canvas);
            ball.draw(canvas);
        }



        //DRAWS GAME AREA

        //Upper line
        canvas.drawRect(0, 0, canvasWidth, 10, Color.WHITE);

        //Right Line
        canvas.drawRect(canvasWidth - 10, 0, canvasWidth, canvasHeight, Color.WHITE);

        //Lower Line
        canvas.drawRect(0, canvasHeight - 10, canvasWidth, canvasHeight, Color.WHITE);

        //Left Line
        canvas.drawRect(0, 0, 10, canvasHeight, Color.WHITE);

        //Midfield
        for (int i = 15; i < canvasHeight; i = i + 30) {
            canvas.drawRect(canvasWidth / 2 - 3, i, canvasWidth / 2 + 3, i + 10, Color.WHITE);
        }

        if (init) {
            Log.i("init", "Init is run");
            canvasWidth = canvas.getWidth();
            canvasHeight = canvas.getHeight();
            player1.setPosition(50, canvasHeight / 2);
            player2.setPosition(canvasWidth - 50, canvasHeight / 2);
            ball.setPosition(canvasWidth / 2, canvasHeight / 2);
            while (ball.getSpeed().getX() == 0) { //do not allow ball to go straight up and down
                ball.setSpeed(-ball.getSpeed().getX(), Util.getRandSpeed(1, 5).getY());
            }

            init = false;
        }

        if (gameWon){

        }

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

    public int getP2Score() {
        return p2Score;
    }

    public int getP1Score() {
        return p1Score;
    }
}
