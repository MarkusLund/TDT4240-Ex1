package com.example.ex1.androidhelicopterapplication.main.task4;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import sheep.game.State;
import sheep.game.World;

/**
 * Created by markuslund92 on 16.01.15.
 */
public class Task4Pong extends State implements View.OnTouchListener{

    private World gameWorld;
    private GameLayerPong gameLayer;
    private static int p1Score, p2Score;

    public Task4Pong() {
        this.gameWorld = new World();
        this.gameLayer = new GameLayerPong();
        gameWorld.addLayer(gameLayer);
        p1Score = 0;
        p2Score = 0;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if(p1Score >= 21 || p2Score >= 21){
            gameWon();
        }
        gameLayer.update(dt);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        gameWorld.draw(canvas);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.gameLayer.onTouch(v, event);
        return false;
    }

    protected static void addP1Score(){
        Log.i("Score","P1+1");
        p1Score++;
    }
    protected static void addP2Score(){
        Log.i("Score","P2+1");
        p2Score++;
    }

    protected static int getP1Score(){
        return p1Score;
    }

    protected static int getP2Score(){
        return p2Score;
    }

    private void gameWon(){
        Log.i("Score","GAMEWON");
        gameWorld.removeLayer(gameLayer);
        gameWorld.addLayer(gameLayer); //TODO: Change layer to win screen.
        p1Score=0;
        p2Score=0;
    }
}
