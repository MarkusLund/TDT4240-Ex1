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
public class GameLayerPong extends Layer{

    private PongPaddle player1, player2;
    private Boolean init;
    private Image pong_paddle;
    private int canvasWidth;
    private int canvasHight;


    public GameLayerPong() {
        init = true;
        pong_paddle = new Image(R.drawable.pong_paddle);

        player1 = new PongPaddle(pong_paddle, 1);
        player2 = new PongPaddle(pong_paddle, 2);
        player1.setSpeed(0,0);
        player2.setSpeed(0,0);

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

        if (init){
            Log.i("init", "Init is run");
            canvasWidth = canvas.getWidth();
            canvasHight = canvas.getHeight();
            player1.setPosition(50, canvasHight/2);
            player2.setPosition(canvasWidth-50, canvasHight/2);
            init = false;
        }

        player1.setPosition(player1.getPosition().getX() + player1.getSpeed().getX(),player1.getPosition().getY() + player1.getSpeed().getY());
        player2.setPosition(player2.getPosition().getX() + player2.getSpeed().getX(),player2.getPosition().getY() + player2.getSpeed().getY());

    }

    public void onTouch(View v, MotionEvent event) {

        Boolean p1 = false, p2 = false;

        //Log.i("Touch in GameLayer", event.toString());
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                Log.i("Action Down", "Action Down");
                if(event.getY()<canvasHight/2 && event.getX()<canvasWidth/2){
                    player1.setSpeed(0, -4);
                    p1 = true;
                }if(event.getY()>canvasHight/2 && event.getX()<canvasWidth/2){
                    player1.setSpeed(0, 4);
                    p1 = true;
                }if(event.getY()<canvasHight/2 && event.getX()>canvasWidth/2){
                    player2.setSpeed(0, -4);
                    p2 = true;
                }if(event.getY()>canvasHight/2 && event.getX()>canvasWidth/2){
                    player2.setSpeed(0, 4);
                    p2 = true;
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.i("Action Pointer Down", "Action Pointer Down");
                int index = event.getActionIndex();
                if(event.getY(index)<canvasHight/2 && event.getX(index)<canvasWidth/2){
                    player1.setSpeed(0, -4);
                    p1 = true;
                }if(event.getY(index)>canvasHight/2 && event.getX(index)<canvasWidth/2){
                    player1.setSpeed(0, 4);
                    p1 = true;
                }if(event.getY(index)<canvasHight/2 && event.getX(index)>canvasWidth/2){
                    player2.setSpeed(0, -4);
                    p2 = true;
                }if(event.getY(index)>canvasHight/2 && event.getX(index)>canvasWidth/2){
                    player2.setSpeed(0, 4);
                    p2 = true;
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.i("Action Pointer UP", "Action Pointer UP");
                if (p1){
                    player1.setSpeed(0,0);
                    p1 = false;

                }else {
                    player2.setSpeed(0, 0);
                    p2 = false;
                }
                break;

            case MotionEvent.ACTION_UP:
                Log.i("Action UP", "Action UP");
                if (p1){
                    player1.setSpeed(0,0);
                    p1 = false;

                }else {
                    player2.setSpeed(0, 0);
                    p2 = false;
                }
                break;
            default:
                Log.i("Nothing!", event.toString());
                break;
        }


    }
}
